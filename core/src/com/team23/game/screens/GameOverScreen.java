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
            font.draw(game.batch, "Game Over: You Won!", GameEntry.VIEW_WIDTH /2-300, GameEntry.VIEW_HEIGHT /2);
        }else{
            font.draw(game.batch, "Game Over: You Lost", GameEntry.VIEW_WIDTH /2-300, GameEntry.VIEW_HEIGHT /2);
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
