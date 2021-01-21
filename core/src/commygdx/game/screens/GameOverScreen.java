package commygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import commygdx.game.AuberGame;

public class GameOverScreen implements Screen {
    private OrthographicCamera gamecam;
    private AuberGame game;
    private BitmapFont font;

    public GameOverScreen(AuberGame game){
        this.game=game;

        gamecam=new OrthographicCamera();

        gamecam.setToOrtho(false, AuberGame.V_WIDTH, AuberGame.V_HEIGHT);
        font = new BitmapFont();
        font.getData().setScale(5f);


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(21/255f,25/255f,38/255f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);

        game.batch.begin();
        //draw background, objects, etc.
        if (game.gameState==2){
            font.draw(game.batch, "Game Over: You Won!", AuberGame.V_WIDTH/2-300, AuberGame.V_HEIGHT/2);
        }else{
            font.draw(game.batch, "Game Over: You Lost", AuberGame.V_WIDTH/2-300, AuberGame.V_HEIGHT/2);
        }
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
