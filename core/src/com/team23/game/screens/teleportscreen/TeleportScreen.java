package com.team23.game.screens.teleportscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.team23.game.GameEntry;

/***
 * Teleport Menu
 */
public class TeleportScreen implements Screen {

    private GameEntry game;
    public TeleportPage teleportPage;

    /***
     * Constructor
     */
    public TeleportScreen(GameEntry game){
        this.game = game;

        teleportPage = new TeleportPage();
        Gdx.input.setInputProcessor(teleportPage);
    }

    /***
     * show
     */
    @Override
    public void show() {

    }

    /***
     * render
     */
    @Override
    public void render(float delta) {
        //draw menu
        Gdx.gl.glClearColor(21/255f,25/255f,38/255f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        teleportPage.act(delta);
        teleportPage.draw();
        //if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){ onClick(Gdx.input.getX(),Gdx.input.getY()); };
    }

    /***
     * resize the width and the height
     */
    @Override
    public void resize(int width, int height) {

    }

    /***
     * pause the game
     */
    @Override
    public void pause() {

    }

    /***
     * resume the game
     */
    @Override
    public void resume() {

    }

    /***
     * hide the game
     */
    @Override
    public void hide() {

    }

    /***
     * dispose this object
     */
    @Override
    public void dispose() {

    }
}
