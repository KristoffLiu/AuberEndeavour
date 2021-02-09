package com.team23.game.screens.startscreen;

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

/***
 * UI page for creating new game
 */
public class CreateNewGamePage extends UIPage {
    /***
     * Constructor
     */
    public CreateNewGamePage() {
        super();

        Image headLine = new Image(new TextureRegion(new Texture("font/Headline.png")));
        headLine.setScale(0.7f);
        headLine.setRelativePosition(50, 50, UIElement.HorizontalAlignment.RIGHT_ALIGNMENT, UIElement.VerticalAlignment.TOP_ALIGNMENT);

        Image createNewGameTitle = new Image(new TextureRegion(new Texture("ui/CreateNewGamePage/CreateNewGame.png")));
        createNewGameTitle.setRelativePosition(0, 550, UIElement.HorizontalAlignment.LEFT_ALIGNMENT, UIElement.VerticalAlignment.BOTTOM_ALIGNMENT);

        Image selectYourDifficultyTitle = new Image(new TextureRegion(new Texture("ui/CreateNewGamePage/SelectYourDifficulty.png")));
        selectYourDifficultyTitle.setRelativePosition(0, 450,  UIElement.HorizontalAlignment.LEFT_ALIGNMENT, UIElement.VerticalAlignment.BOTTOM_ALIGNMENT);

        Button difficultButton = new Button();
        difficultButton.setTextures(
                "ui/CreateNewGamePage/difficultNormal.png",
                "ui/CreateNewGamePage/difficultHovered.png",
                "ui/CreateNewGamePage/difficultPressed.png",
                "");
        difficultButton.setRelativePosition(0,375, UIElement.HorizontalAlignment.LEFT_ALIGNMENT, UIElement.VerticalAlignment.BOTTOM_ALIGNMENT);
        difficultButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameEntry.current.createPlayScreen(PlayConfig.difficultNewGame());
            }
        });

        final Button normalButton = new Button();
        normalButton.setTextures(
                "ui/CreateNewGamePage/normalNormal.png",
                "ui/CreateNewGamePage/normalHovered.png",
                "ui/CreateNewGamePage/normalPressed.png",
                "");
        normalButton.setRelativePosition(0,300, UIElement.HorizontalAlignment.LEFT_ALIGNMENT, UIElement.VerticalAlignment.BOTTOM_ALIGNMENT);
        normalButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameEntry.current.createPlayScreen(PlayConfig.normalNewGame());
            }
        });

        Button simpleButton = new Button();
        simpleButton.setTextures(
                "ui/CreateNewGamePage/simpleNormal.png",
                "ui/CreateNewGamePage/simpleHovered.png",
                "ui/CreateNewGamePage/simplePressed.png",
                "");
        simpleButton.setRelativePosition(0,225, UIElement.HorizontalAlignment.LEFT_ALIGNMENT, UIElement.VerticalAlignment.BOTTOM_ALIGNMENT);
        simpleButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameEntry.current.createPlayScreen(PlayConfig.simpleNewGame());
            }
        });

        Button backButton = new Button();
        backButton.setTextures(
                "ui/CreateNewGamePage/backNormal.png",
                "ui/CreateNewGamePage/backHovered.png",
                "ui/CreateNewGamePage/backPressed.png",
                "");
        backButton.setRelativePosition(0,100, UIElement.HorizontalAlignment.LEFT_ALIGNMENT, UIElement.VerticalAlignment.BOTTOM_ALIGNMENT);
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
        this.addUIElement(headLine);
        this.addUIElement(createNewGameTitle);
        this.addUIElement(selectYourDifficultyTitle);
        this.addUIElement(difficultButton);
        this.addUIElement(normalButton);
        this.addUIElement(simpleButton);
        this.addUIElement(backButton);
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
