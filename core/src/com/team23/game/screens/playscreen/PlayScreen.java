package com.team23.game.screens.playscreen;

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
import com.team23.game.GameEntry;
import com.team23.game.ShipSystem;
import com.team23.game.TileWorld;
import com.team23.game.actors.characters.*;
import com.team23.game.actors.items.PowerUp;
import com.team23.game.save.*;
import com.team23.game.screens.TeleportMenu;
import com.team23.game.utils.Utility;
import com.team23.game.ai.graph.PathGraph;
import com.team23.game.ai.graph.PathNode;
import com.team23.game.stages.Hud;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class PlayScreen implements Screen {
    protected GameEntry gameEntry;
    private PlayConfig config;

    private Hud hud;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public Auber player;
    public ArrayList<Infiltrator> enemies;
    public ArrayList<NPC> npcs;
    public ArrayList<PowerUp> powerups;
    //Graph used for AI pathfinding
    public PathGraph graph;

    //Scene2D

    private Stage shipStage;

    //Used for the infiltrator's hallucinate power
    private boolean hallucinate;
    private Texture hallucinateTexture;


    private TileWorld tiles;

    protected int scale;


    public PlayScreen(GameEntry gameEntry, PlayConfig playConfig) {
        this.gameEntry = gameEntry;
        this.config = playConfig;
        this.scale = GameEntry.ZOOM;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT, gamecam);
        /*Possible fullscreen
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());*/

        //load map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("mapV2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, scale);

        //create ai pathing graph
        graph = createPathGraph("csv/nodes.csv", "csv/edges.csv");

        //sets up stage and actors
        tiles = new TileWorld(this);
        shipStage = new Stage(new StretchViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT, gamecam));
        switch (this.config.mode){
            case newGame:
                switch (this.config.difficulty){
                    case simple:
                        createSimpleGame();
                        break;
                    case normal:
                        createNormalGame();
                    case difficult:
                        createDifficultGame();
                }
                break;
            case loadedGame:
                createLoadedGame();
                break;
            case demoGame:
                createDemoGame();
                break;
        }

        for (PowerUp powerup: powerups){
            shipStage.addActor(powerup);
        }

        shipStage.addActor(player);
        //Adding infiltrators to stage
        for (Infiltrator enemy : enemies) {
            shipStage.addActor(enemy);
        }

        for (NPC npc: npcs){
            shipStage.addActor(npc);
        }



        //Used for the infiltrator's hallucinate power
        hallucinateTexture = new Texture("hallucinateV2.png");
        hallucinate = false;

        hud = new Hud(gameEntry.batch, enemies, tiles.getSystems());
    }

    /**Creates auber, enemies, npcs and powerups for a simple game**/
    public void createSimpleGame(){
        player = new Auber(new Vector2(450 * scale, 778 * scale), 9f);

        //Creating and placing infiltrators
        enemies = new ArrayList<Infiltrator>(Arrays.asList(
                new Infiltrator(new Vector2(4700, 2000), 1, graph, 7f),
                new Infiltrator(new Vector2(4800, 2300), 2, graph, 7f),
                new Infiltrator(new Vector2(5000, 7356), 3, graph, 7f),
                new Infiltrator(new Vector2(4732, 7000), 4, graph, 7f),
                new Infiltrator(new Vector2(4732, 7500), 1, graph, 7f),
                new Infiltrator(new Vector2(4732, 7800), 1, graph, 7f)
        ));

        npcs = new ArrayList<NPC>(Arrays.asList(
                new NPC(new Vector2(4700, 2000), graph, 7f),
                new NPC(new Vector2(4800, 2300), graph, 7f),
                new NPC(new Vector2(5000, 7356), graph, 7f),
                new NPC(new Vector2(4732, 7000), graph, 7f),
                new NPC(new Vector2(4732, 7500), graph, 7f),
                new NPC(new Vector2(4732, 7800), graph, 7f)
        ));

        powerups = new ArrayList<PowerUp>(Arrays.asList(
            new PowerUp(new Vector2(4700, 7800), "Speed"),
                new PowerUp(new Vector2(4700, 6000), "Speed"),
                new PowerUp(new Vector2(4700, 5400), "Immunity"),
            new PowerUp(new Vector2(6900, 4750), "Highlight"),
                new PowerUp(new Vector2(5500, 4750), "Highlight"),
            new PowerUp(new Vector2(4750, 3000), "Freeze"),
            new PowerUp(new Vector2(2500, 4700), "Teleport"),
        new PowerUp(new Vector2(4750, 4700), "Teleport")
        ));
    }

    /**Creates auber, enemies, npcs and powerups for a normal game**/
    public void createNormalGame(){
        player = new Auber(new Vector2(450 * scale, 778 * scale), 9f);
        player.setPosition(450 * scale, 778 * scale);

        //Creating and placing infiltrators
        enemies = new ArrayList<Infiltrator>(Arrays.asList(
                new Infiltrator(new Vector2(4700, 2000), 1, graph, 9f),
                new Infiltrator(new Vector2(4800, 2300), 2, graph, 9f),
                new Infiltrator(new Vector2(5000, 7356), 3, graph, 9f),
                new Infiltrator(new Vector2(4732, 7000), 4, graph, 9f),
                new Infiltrator(new Vector2(4732, 7500), 1, graph, 9f),
                new Infiltrator(new Vector2(4732, 7800), 1, graph, 9f),
                new Infiltrator(new Vector2(4200, 7800), 2, graph, 9f),
                new Infiltrator(new Vector2(5400, 7800), 2, graph, 9f)
        ));

        npcs    = new ArrayList<NPC>(Arrays.asList(
                new NPC(new Vector2(4700, 2000), graph, 9f),
                new NPC(new Vector2(4800, 2300), graph, 9f),
                new NPC(new Vector2(5000, 7356), graph, 9f),
                new NPC(new Vector2(4732, 7000), graph, 9f),
                new NPC(new Vector2(4732, 7500), graph, 9f),
                new NPC(new Vector2(4732, 7800), graph, 9f),
                new NPC(new Vector2(4200, 7800), graph, 9f),
                new NPC(new Vector2(5400, 7800), graph, 9f)
        ));

        powerups = new ArrayList<PowerUp>(Arrays.asList(
                new PowerUp(new Vector2(4700, 7800), "Speed"),
                new PowerUp(new Vector2(4700, 5400), "Immunity"),
                new PowerUp(new Vector2(6900, 4750), "Highlight"),
                new PowerUp(new Vector2(4750, 3000), "Freeze"),
                new PowerUp(new Vector2(2500, 4700), "Teleport")
        ));
    }

    /**Creates auber, enemies, npcs and powerups for a difficult game**/
    public void createDifficultGame(){
        player = new Auber(new Vector2(450 * scale, 778 * scale), 9f);
        player.setPosition(450 * scale, 778 * scale);

        //Creating and placing infiltrators
        enemies = new ArrayList<Infiltrator>(Arrays.asList(
                new Infiltrator(new Vector2(4700, 2000), 1, graph, 11f),
                new Infiltrator(new Vector2(4800, 2300), 2, graph, 11f),
                new Infiltrator(new Vector2(5000, 7356), 3, graph, 11f),
                new Infiltrator(new Vector2(4732, 7000), 4, graph, 11f),
                new Infiltrator(new Vector2(4732, 7500), 1, graph, 11f),
                new Infiltrator(new Vector2(4732, 7800), 1, graph, 11f),
                new Infiltrator(new Vector2(4200, 7800), 2, graph, 11f),
                new Infiltrator(new Vector2(5400, 7800), 2, graph, 11f),
                new Infiltrator(new Vector2(5000, 5300),  2, graph, 11f),
                new Infiltrator(new Vector2(4900, 7800),  2, graph, 11f)));


        npcs = new ArrayList<NPC>(Arrays.asList(
                new NPC(new Vector2(4700, 2000), graph, 11f),
                new NPC(new Vector2(4800, 2300), graph, 11f),
                new NPC(new Vector2(5000, 7356), graph, 11f),
                new NPC(new Vector2(4732, 7000), graph, 11f),
                new NPC(new Vector2(4732, 7500), graph, 11f),
                new NPC(new Vector2(4732, 7800), graph, 11f),
                new NPC(new Vector2(4200, 7800), graph, 11f),
                new NPC(new Vector2(5400, 7800), graph, 11f),
                new NPC(new Vector2(5400, 7500), graph, 11f),
                new NPC(new Vector2(4100, 7800), graph, 11f),
                new NPC(new Vector2(4732, 7356), graph, 11f)));


        powerups = new ArrayList<PowerUp>(Arrays.asList(
                new PowerUp(new Vector2(4700, 7800), "Speed")
        ));
    }

    public void createLoadedGame(){
        Save loadedSave = SaveManager.current.getLoadedSave();
        this.player = loadedSave.getAuber();
        this.enemies = loadedSave.getEnemyList(this.graph);
        this.npcs = loadedSave.getNPCsList(this.graph);
        this.powerups = loadedSave.getPowerUpList();
        this.tiles.setShipSystems(loadedSave.getSystemList(graph));
    }

    public void createDemoGame(){
        player = new DemoAuber(new Vector2(450 * scale, 778 * scale), graph, 9f);

        //Creating and placing infiltrators
        enemies = new ArrayList<Infiltrator>(Arrays.asList(
                new Infiltrator(new Vector2(4700, 2000), 1, graph, 9f),
                new Infiltrator(new Vector2(4800, 2300), 2, graph, 9f),
                new Infiltrator(new Vector2(5000, 7356), 3, graph, 9f),
                new Infiltrator(new Vector2(4732, 7000), 4, graph, 9f),
                new Infiltrator(new Vector2(4732, 7500), 1, graph, 9f),
                new Infiltrator(new Vector2(4732, 7800), 1, graph, 9f),
                new Infiltrator(new Vector2(4200, 7800), 2, graph, 9f),
                new Infiltrator(new Vector2(5400, 7800), 2, graph, 9f)
        ));

        npcs    = new ArrayList<NPC>(Arrays.asList(
                new NPC(new Vector2(4700, 2000), graph, 9f),
                new NPC(new Vector2(4800, 2300), graph, 9f),
                new NPC(new Vector2(5000, 7356), graph, 9f),
                new NPC(new Vector2(4732, 7000), graph, 9f),
                new NPC(new Vector2(4732, 7500), graph, 9f),
                new NPC(new Vector2(4732, 7800), graph, 9f),
                new NPC(new Vector2(4200, 7800), graph, 9f),
                new NPC(new Vector2(5400, 7800), graph, 9f)
        ));

        powerups = new ArrayList<PowerUp>(Arrays.asList(
                new PowerUp(new Vector2(4732, 7800), "Speed"),
                new PowerUp(new Vector2(4200, 7800), "Immunity"),
                new PowerUp(new Vector2(5400, 7800), "Highlight"),
                new PowerUp(new Vector2(5400, 7500), "Freeze"),
                new PowerUp(new Vector2(4732, 7500), "Teleport")
        ));
    }

    public void save() {
        Save currentSave = new Save();
        currentSave.setAuberInfo(this.player);
        currentSave.setEnemiesInfoList(this.enemies);
        currentSave.setNpcsInfoList(this.npcs);
    }

    public void update(float dt) {
        shipStage.act(dt);
        player.arrest(enemies, hud);
        player.usePowerUp(powerups,enemies, npcs);
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
        //Resets infiltrator sprite after highlight has ended
        resetAfterPowerup();
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
        gameEntry.batch.setProjectionMatrix(hud.stage.getCamera().combined);

    }

    private void teleportCheck(){
        switch (this.config.mode){
            case newGame:
            case loadedGame:
                //switch to teleport menu
                if (player.teleportCheck(tiles) && gameEntry.teleporting == "false") {
                    gameEntry.setScreen(new TeleportMenu(gameEntry));
                }

                else if(player.isTeleportPowerUp()){
                    gameEntry.setScreen(new TeleportMenu(gameEntry));
                    player.setTeleportPowerUp(false);
                }

                //teleport auber
                if (gameEntry.teleporting != "true" && gameEntry.teleporting != "false") {
                    teleportAuber();
                    gameEntry.teleporting = "false";
                }
                break;
            //teleport is disabled in demo mode, because the ai can't handle it
            case demoGame:
                return;
        }

    }

    /**
     * Draws hallucination texture overlay on screen and removes if the auber is in infirmary
     */
    private void drawHallucinate() {
        gameEntry.batch.begin();
        gameEntry.batch.draw(hallucinateTexture, 0, 0);
        gameEntry.batch.end();
        if (player.getBounds().overlaps(tiles.getInfirmary()) || player.getCurrentPower() == "Immunity") {
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
        float x = tiles.getTeleporters().get(gameEntry.teleporting).x + 100;
        float y = tiles.getTeleporters().get(gameEntry.teleporting).y;
        player.setPosition(x, y);
        player.movementSystem.updatePos(new Vector2(x, y));
        switch (this.config.mode){
            case newGame:
            case loadedGame:
                break;
            case demoGame:
                player.act(0);
                break;
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
            gameEntry.setGameState(PlayState.win);
        }
        if (hud.getSystemsUp() == 0) {
            gameEntry.setGameState(PlayState.lost);
        }
    }

    public void updateInfiltrators(float dt) {

        for (Infiltrator enemy : enemies) {
            enemy.updateTimers(dt * 100);

            //the infiltrator will use their power if the auber is close enough and it's off cooldown
            if (enemy.getPowerCooldown() > 500 && inRange(enemy) && !enemy.getIsArrested()) {
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
        if(player.getCurrentPower() != "Immunity") {
            this.hallucinate = hallucinate;
            hud.showHallucinateLabel(hallucinate);
        }
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

    //Resets the infiltrators and NPCs after powerups have ended
    public void resetAfterPowerup(){
        if(!player.getCurrentPower().equals("Highlight")){
            for(Infiltrator infiltrator: enemies) {
                if(infiltrator.isHighlighted()){
                    infiltrator.setTexture(new Texture(Gdx.files.internal("Characters/infiltratorSprite.png")));
                    infiltrator.setHighlighted(false);
                }
            }
        }
        if(!player.getCurrentPower().equals("Freeze")){
            for(Infiltrator infiltrator: enemies) {
                if(infiltrator.isFrozen()){
                    infiltrator.setFrozen(false);
                }
            }

            for(NPC npc: npcs){
                if(npc.isFrozen()){
                    npc.setFrozen(false);
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
        gameEntry.batch.dispose();

    }

}

