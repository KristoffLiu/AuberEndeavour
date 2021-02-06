package com.team23.game.screens.startscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.team23.game.GameEntry;
import com.team23.game.screens.playscreen.PlayConfig;
import com.team23.game.ui.controls.UIElement;
import com.team23.game.ui.pages.UIPage;
import com.team23.game.ui.controls.Button;
import com.team23.game.ui.controls.ButtonClickListener;
import com.team23.game.ui.controls.Image;

public class StartPage extends UIPage {

    public StartPage() {
        super();
        Image headLine = new Image(new TextureRegion(new Texture("font/Headline.png")));
        headLine.setScale(0.7f);
        headLine.setRelativePosition(50, 50, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);

        Button playButton = new Button();
        playButton.setTextures(
                "ui/StartPage/playNormal.png",
                "ui/StartPage/playHovered.png",
                "ui/StartPage/playPressed.png",
                "");
        playButton.setWidth(playButton.getWidth()/2f);
        playButton.setHeight(playButton.getHeight()/2f);
        playButton.setRelativePosition(0,325, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.bottomAlignment);
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

        Button loadButton = new Button();
        loadButton.setTextures(
                "ui/StartPage/loadNormal.png",
                "ui/StartPage/loadHovered.png",
                "ui/StartPage/loadPressed.png",
                "");
        loadButton.setRelativePosition(0,250, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.bottomAlignment);
        loadButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getParentFrame().navigate(new LoadAndSavePage());
            }
        });

        Button demoButton = new Button();
        demoButton.setTextures(
                "ui/StartPage/demoNormal.png",
                "ui/StartPage/demoHovered.png",
                "ui/StartPage/demoPressed.png",
                "");
        demoButton.setRelativePosition(0,175, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.bottomAlignment);
        demoButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameEntry.current.createPlayScreen(PlayConfig.demoGame());
            }
        });

        Button exitButton = new Button();
        exitButton.setTextures(
                "ui/StartPage/exitNormal.png",
                "ui/StartPage/exitHovered.png",
                "ui/StartPage/exitPressed.png",
                "");
        exitButton.setRelativePosition(0,100, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.bottomAlignment);
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

        this.addUIElement(headLine);
        this.addUIElement(playButton);
        this.addUIElement(loadButton);
        this.addUIElement(demoButton);
        this.addUIElement(exitButton);
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
