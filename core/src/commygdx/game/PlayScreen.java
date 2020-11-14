package commygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import commygdx.game.Scenes.Hud;
import commygdx.game.entities.Auber;

public class PlayScreen implements Screen {
    private AuberGame auberGame;
    private Hud hud;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private Auber player;

    //Box2D
    private World world;
    private Box2DDebugRenderer b2dr;



    public PlayScreen(AuberGame auberGame){
        this.auberGame = auberGame;
        gamecam=new OrthographicCamera();
        gamePort=new FitViewport(AuberGame.V_WIDTH, AuberGame.V_HEIGHT,gamecam);
        hud=new Hud(auberGame.batch);
        //load map
        mapLoader=new TmxMapLoader();
        map=mapLoader.load("map1.tmx");
        renderer=new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(400,835,0);

        setupBox2D();
    }

    private void setupBox2D(){
        world = new World(new Vector2(0,0),true);
        b2dr = new Box2DDebugRenderer();
        //temp
        player = new Auber(world);
    }

    public void update(float dt){
        world.step(1/60f,6,2);
        player.update(dt);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        gamecam.update();
        renderer.setView(gamecam);
        //bg colour
        Gdx.gl.glClearColor(61/255f,74/255f,77/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        auberGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

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

    }
}
