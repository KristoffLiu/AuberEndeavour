package com.team23.game.screens.startscreen;

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

public class CreateNewGamePage extends UIPage {
    public CreateNewGamePage() {
        super();

        Image headLine = new Image(this, new TextureRegion(new Texture("font/Headline.png")));
        headLine.setScale(0.7f);
        headLine.setPosition(this.getWidth() - headLine.getWidth() + 550, this.getHeight() - headLine.getHeight() / 2 - 130);

        Image createNewGameTitle = new Image(this, new TextureRegion(new Texture("ui/CreateNewGamePage/CreateNewGame.png")));
        createNewGameTitle.setPosition(0, 550);

        Image selectYourDifficultyTitle = new Image(this, new TextureRegion(new Texture("ui/CreateNewGamePage/SelectYourDifficulty.png")));
        selectYourDifficultyTitle.setPosition(0, 450);

        Button difficultButton = new Button(this);
        difficultButton.setTextures(
                "ui/CreateNewGamePage/difficultNormal.png",
                "ui/CreateNewGamePage/difficultHovered.png",
                "ui/CreateNewGamePage/difficultPressed.png",
                "");
        difficultButton.setPosition(0,375);
        difficultButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameEntry.current.createPlayScreen(false);
                GameEntry.current.gameState=0;
            }
        });

        Button normalButton = new Button(this);
        normalButton.setTextures(
                "ui/CreateNewGamePage/normalNormal.png",
                "ui/CreateNewGamePage/normalHovered.png",
                "ui/CreateNewGamePage/normalPressed.png",
                "");
        normalButton.setPosition(0,300);
        normalButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameEntry.current.createPlayScreen(false);
                GameEntry.current.gameState=0;
            }
        });

        Button simpleButton = new Button(this);
        simpleButton.setTextures(
                "ui/CreateNewGamePage/simpleNormal.png",
                "ui/CreateNewGamePage/simpleHovered.png",
                "ui/CreateNewGamePage/simplePressed.png",
                "");
        simpleButton.setPosition(0,225);
        simpleButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameEntry.current.createPlayScreen(false);
                GameEntry.current.gameState=0;
            }
        });



        Button backButton = new Button(this);
        backButton.setTextures(
                "ui/CreateNewGamePage/backNormal.png",
                "ui/CreateNewGamePage/backHovered.png",
                "ui/CreateNewGamePage/backPressed.png",
                "");
        backButton.setPosition(0,100);
        backButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getParentFrame().goBack();
            }
        });
        this.hide();
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
