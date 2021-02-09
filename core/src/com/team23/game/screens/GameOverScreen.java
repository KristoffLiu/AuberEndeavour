package com.team23.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.team23.game.GameEntry;

public class GameOverScreen implements Screen {
    private OrthographicCamera gamecam;
    private GameEntry game;
    private BitmapFont font;

    public GameOverScreen(GameEntry game){
        this.game=game;

        gamecam=new OrthographicCamera();

        gamecam.setToOrtho(false, GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT);
        font = new BitmapFont();
        font.getData().setScale(5f);


    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
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
        switch (game.getState()){
            case win:
                font.draw(game.batch, "Game Over: You Won!", GameEntry.VIEW_WIDTH / 2f - 300f, GameEntry.VIEW_HEIGHT / 2f);
                break;
            case lost:
                font.draw(game.batch, "Game Over: You Lost", GameEntry.VIEW_WIDTH / 2f - 300f, GameEntry.VIEW_HEIGHT / 2f);
                break;
            default:
                break;
        }
        game.batch.end();
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
