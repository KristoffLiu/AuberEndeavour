package com.team23.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.team23.game.GameEntry;

public class TeleportMenu implements Screen {

    private GameEntry game;
    private Texture mapTexture;

    private OrthographicCamera gamecam;

    private Rectangle brigRect;
    private Rectangle commandRect;
    private Rectangle crewRect;
    private Rectangle engineRect;
    private Rectangle infirmaryRect;
    private Rectangle laboratoryRect;
    private final int zoom=7;

    private int tWidth;
    private int tHeight;

    public TeleportMenu(GameEntry game){
        this.game=game;
        gamecam=new OrthographicCamera();

        gamecam.setToOrtho(false, GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT);
        //get button textures
        mapTexture=new Texture("mapv3_signs.png");
        //button rectange bounds
        tWidth=mapTexture.getWidth()*zoom;
        tHeight=mapTexture.getHeight()*zoom;

        //teleport buttons
        brigRect=new Rectangle(870,465,183,308);
        commandRect=new Rectangle(1143,1090,313,181);
        laboratoryRect =new Rectangle(1121,796,357,222);
        engineRect=new Rectangle(1143,213,315,232);
        infirmaryRect=new Rectangle(1121,508,359,223);
        crewRect=new Rectangle(1543,466,182,307);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //draw menu
        Gdx.gl.glClearColor(21/255f,25/255f,38/255f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int textureWidth=mapTexture.getWidth()*zoom;
        int textureHeight=mapTexture.getHeight()*zoom;

        //draw buttons
        game.batch.begin();
        game.batch.draw(mapTexture,850,200,textureWidth,textureHeight);
        game.batch.end();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){ onClick(Gdx.input.getX(),Gdx.input.getY()); };


    }

    private void onClick(int x,int y){
            //convert click screen cords to world
            Vector3 clickPos= new Vector3(x,y,0);
            gamecam.unproject(clickPos);
            //check which button pressed
            if (brigRect.contains(clickPos.x,clickPos.y)){game.onTeleport="brig";}
            if (commandRect.contains(clickPos.x,clickPos.y)){game.onTeleport="command";}
            if (crewRect.contains(clickPos.x,clickPos.y)){game.onTeleport="crew";}
            if (engineRect.contains(clickPos.x,clickPos.y)){game.onTeleport="engine";}
            if (infirmaryRect.contains(clickPos.x,clickPos.y)){game.onTeleport="infirmary";}
            if (laboratoryRect.contains(clickPos.x,clickPos.y)){game.onTeleport="laboratory";}
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
