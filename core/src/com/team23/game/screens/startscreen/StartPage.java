package com.team23.game.screens.startscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.team23.game.GameEntry;
import com.team23.game.ui.UIElement;
import com.team23.game.ui.UIPage;
import com.team23.game.ui.controls.Button;
import com.team23.game.ui.controls.ButtonClickListener;
import com.team23.game.ui.controls.Image;

public class StartPage extends UIPage {

    public StartPage() {
        super();
        Image headLine = new Image(this, new TextureRegion(new Texture("font/Headline.png")));
        headLine.setScale(0.7f);
        headLine.setPosition(this.getWidth() - headLine.getWidth() + 550, this.getHeight() - headLine.getHeight() / 2 - 130);

        Button playButton = new Button(this);
        playButton.setTextures(
                "ui/StartPage/playNormal.png",
                "ui/StartPage/playHovered.png",
                "ui/StartPage/playPressed.png",
                "");
        playButton.setWidth(playButton.getWidth()/2f);
        playButton.setHeight(playButton.getHeight()/2f);
        playButton.setPosition(0,325);
        playButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
//                GameEntry.current.createPlayScreen(false);
//                GameEntry.current.gameState=0;
                getParentFrame().navigate(new CreateNewGamePage());
            }
        });

        Button loadButton = new Button(this);
        loadButton.setTextures(
                "ui/StartPage/loadNormal.png",
                "ui/StartPage/loadHovered.png",
                "ui/StartPage/loadPressed.png",
                "");
        loadButton.setPosition(0,250);
        loadButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getParentFrame().navigate(new LoadAndSavePage());
            }
        });

        Button demoButton = new Button(this);
        demoButton.setTextures(
                "ui/StartPage/demoNormal.png",
                "ui/StartPage/demoHovered.png",
                "ui/StartPage/demoPressed.png",
                "");
        demoButton.setPosition(0,175);
        demoButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameEntry.current.createPlayScreen(true);
                GameEntry.current.gameState=0;
            }
        });

        Button exitButton = new Button(this);
        exitButton.setTextures(
                "ui/StartPage/exitNormal.png",
                "ui/StartPage/exitHovered.png",
                "ui/StartPage/exitPressed.png",
                "");
        exitButton.setPosition(0,100);
        exitButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
    }

    /***
     * hide
     */
    @Override
    public void hide(){
        for(Actor actor : this.getActors()){
            ((UIElement)actor).hide();
        }
    }

    /***
     * appear
     */
    @Override
    public void appear(){
        for(Actor actor : this.getActors()){
            ((UIElement)actor).fadeIn(100f,0f,0.6f, Interpolation.fade);
        }
    }
}
