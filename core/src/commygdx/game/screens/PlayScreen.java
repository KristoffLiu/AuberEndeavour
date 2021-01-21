package commygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.*;
import commygdx.game.ai.graph.PathGraph;
import commygdx.game.ai.graph.PathNode;

import commygdx.game.AuberGame;
import commygdx.game.ShipSystem;
import commygdx.game.TileWorld;
import commygdx.game.Utility;
import commygdx.game.actors.DemoAuber;
import commygdx.game.actors.Infiltrator;
import commygdx.game.stages.Hud;
import commygdx.game.actors.Auber;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class PlayScreen implements Screen {
    protected AuberGame auberGame;
    private Hud hud;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    public ArrayList<Infiltrator> enemies;
    //Graph used for AI pathfinding
    public PathGraph graph;
    private boolean demo;


    //Scene2D
    protected Auber player;
    private Stage shipStage;

    //Used for the infiltrator's hallucinate power
    private boolean hallucinate;
    private Texture hallucinateTexture;


    private TileWorld tiles;

    protected int scale;


    public PlayScreen(AuberGame auberGame,boolean demo) {
        this.auberGame = auberGame;
        this.demo = demo;
        this.scale = AuberGame.ZOOM;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(AuberGame.V_WIDTH, AuberGame.V_HEIGHT, gamecam);
        /*Possible fullscreen
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());*/

        //load map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("mapV2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, scale);

        //create ai pathing graph
        graph = createPathGraph("csv/nodes.csv", "csv/edges.csv");
        //sets up stage and actors
        setupShipStage();


        tiles = new TileWorld(this);

        //Used for the infiltrator's hallucinate power
        hallucinateTexture = new Texture("hallucinateV2.png");
        hallucinate = false;


        hud = new Hud(auberGame.batch, enemies, tiles.getSystems());
    }

    /**
     * Creates stage and adds characters (auber and infiltrators) to it
     */
    private void setupShipStage() {
        shipStage = new Stage(new StretchViewport(AuberGame.V_WIDTH, AuberGame.V_HEIGHT, gamecam));
        createAuber();
        player.sprite.setPosition(450 * scale, 778 * scale);

        //Creating and placing infiltrators
        enemies = new ArrayList<Infiltrator>(Arrays.asList(
                new Infiltrator(new Vector2(4700, 2000), auberGame.batch, 1, graph),
                new Infiltrator(new Vector2(4800, 2300), auberGame.batch, 2, graph),
                new Infiltrator(new Vector2(5000, 7356), auberGame.batch, 3, graph),
                new Infiltrator(new Vector2(4732, 7000), auberGame.batch, 4, graph),
                new Infiltrator(new Vector2(4732, 7500), auberGame.batch, 1, graph),
                new Infiltrator(new Vector2(4732, 7800), auberGame.batch, 1, graph),
                new Infiltrator(new Vector2(4200, 7800), auberGame.batch, 2, graph),
                new Infiltrator(new Vector2(5400, 7800), auberGame.batch, 2, graph)
        ));


        shipStage.addActor(player);
        //Adding infiltrators to stage
        for (Infiltrator enemy : enemies) {
            shipStage.addActor(enemy);
        }


    }

    protected void createAuber() {
        //A different version of Auber is used for the player depending on if it's a demo or not
        if (!demo){
            player = new Auber(new Vector2(450 * scale, 778 * scale), auberGame.batch);
        }else {
            player = new DemoAuber(new Vector2(450 * scale, 778 * scale),auberGame.batch,graph);
        }
    }

    public void update(float dt) {
        shipStage.act(dt);
        player.arrest(enemies, hud);
    }

    @Override
    public void show() {

    }

    /**
     * Draws the game to screen and updates game
     * @param delta Time difference from last call
     */
    @Override
    public void render(float delta) {
        //updates game
        checkGameState();
        update(delta);
        updateInfiltrators(delta);
        teleportCheck();
        player.checkCollision(tiles.getCollisionBoxes());
        updateCamera();

        //draws game
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        shipStage.draw();

        if (hallucinate) { drawHallucinate();}
        hud.updateAttacks(tiles.getSystems());
        hud.stage.draw();
    }

    private void updateCamera(){
        //sets camera to players position
        Vector3 pos = new Vector3((player.getX()) + player.getWidth() / 2, (player.getY()) + player.getHeight() / 2, 0);
        shipStage.getViewport().getCamera().position.set(pos);
        gamecam.position.set(pos);
        gamecam.update();
        renderer.setView(gamecam);
        auberGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);

    }

    private void teleportCheck(){
        //teleport is disabled in demo mode, because the ai can't handle it
        if(demo){
            return;
        }
        //switch to teleport menu
        if (player.teleportCheck(tiles) && auberGame.onTeleport == "false") {
            auberGame.setScreen(new TeleportMenu(auberGame));
        }
        //teleport auber
        if (auberGame.onTeleport != "true" && auberGame.onTeleport != "false") {
            teleportAuber();
            auberGame.onTeleport = "false";
        }
    }

    /**
     * Draws hallucination texture overlay on screen and removes if the auber is in infirmary
     */
    private void drawHallucinate() {
        auberGame.batch.begin();
        auberGame.batch.draw(hallucinateTexture, 0, 0);
        auberGame.batch.end();
        if (player.sprite.getBoundingRectangle().overlaps(tiles.getInfirmary())) {
            hud.showHallucinateLabel(false);
            hallucinate = false;

        }

    }


    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    /**
     * Sets auber's position to selected teleporter's position
     */
    public void teleportAuber() {
        float x = tiles.getTeleporters().get(auberGame.onTeleport).x + 100;
        float y = tiles.getTeleporters().get(auberGame.onTeleport).y;
        player.setPosition(x, y);
        player.movementSystem.updatePos(new Vector2(x, y));
        if(demo){
            player.act(0);
        }
    }

    /**
     * Creates an ai pathing graph from .csv files
     * @param nodesFilepath The filepath for the .csv file containing the nodes of the graph
     * @param edgesFilepath The filepath for the .csv file containing the edges of the graph
     * @return The resultant pathing graph
     */
    private PathGraph createPathGraph(String nodesFilepath, String edgesFilepath) {
        PathGraph graph = new PathGraph();
        try {
            //Getting nodes from file
            LinkedList<PathNode> nodes = new LinkedList<PathNode>();

            FileHandle nodesFile = Gdx.files.internal(nodesFilepath);
            BufferedReader reader = nodesFile.reader(1000);
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String data[] = line.split(",");
                PathNode node = new PathNode(new Vector2(Float.parseFloat(data[2]), Float.parseFloat(data[3])), Boolean.parseBoolean(data[4]));
                nodes.add(node);
                graph.addNode(node);
            }

            //Getting edges from file
            FileHandle edgesFile = Gdx.files.internal(edgesFilepath);
            reader = edgesFile.reader(100);
            line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String data[] = line.split(",");
                graph.addEdge(nodes.get(Integer.parseInt(data[0])), nodes.get(Integer.parseInt(data[1])));
            }

            reader.close();

            for (PathNode node : nodes) {
                if (node.getAdjacentNodes().length == 0) {
                    System.out.println(node);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return graph;
    }

    /**
     * Checks if the game needs to switch to: a winning state, a losing state or to stay in a playing state
     */
    private void checkGameState() {
        if (hud.getInfiltratorsRemaining() == 0) {
            auberGame.setGameState(2);
        }
        if (hud.getSystemsUp() == 0) {
            auberGame.setGameState(3);
        }
    }

    public void updateInfiltrators(float dt) {

        for (Infiltrator enemy : enemies) {
            enemy.updateTimers(dt * 100);

            //the infiltrator will use their power if the auber is close enough and it's off cooldown
            if (enemy.getPowerCooldown() > 500 && inRange(enemy) && enemy.getIsArrested() == false) {
                enemy.usePower(this,tiles.getRoom(player.getX(), player.getY()));
            }
            //the infiltrator will stop their power when it's gone on past it's limit
            if (enemy.getPowerDuration() > 1000) {
                enemy.stopPower(this);
            }

        }
        checkInfiltratorsSystems();
    }

    /**
     * Determines if the given enemy is within a certain range of the auber
     * @param enemy The enemy whose range is being checked
     * @return True if the enemy is in range, false otherwise
     */
    public boolean inRange(Infiltrator enemy) {
        if (enemy.getX() < player.getX() + 1000 && enemy.getX() > player.getX() - 1000 &&
                enemy.getY() < player.getY() + 1000 && enemy.getY() > player.getY() - 1000) {
            return true;
        }
        return false;

    }

    public void setHallucinate(boolean hallucinate) {
        this.hallucinate = hallucinate;
        hud.showHallucinateLabel(hallucinate);
    }

    public TiledMap getMap() {
        return map;
    }

    /**
     * Checks if every infiltrator is both available and close enough to a system to attack it.
     * If an infiltrator fits those criteria then it will attack the system it is close enough to.
     */
    private void checkInfiltratorsSystems() {
        //Starts attack if infiltrator in range of a system
        for (Infiltrator infiltrator : enemies) {
            if (infiltrator.isAvailable()) {
                for (ShipSystem system : tiles.getSystems()) {
                    if (system.getState() == 0) {
                        if (Utility.closeEnough(new Vector2(infiltrator.getX(), infiltrator.getY()), system.getPosition())) {
                            infiltrator.startDestruction(system);
                            system.startAttack();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        shipStage.dispose();
        auberGame.batch.dispose();

    }
}

