package com.team23.game.screens.startscreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.team23.game.GameEntry;
import com.team23.game.save.Save;
import com.team23.game.save.SaveManager;
import com.team23.game.screens.playscreen.PlayConfig;
import com.team23.game.ui.Padding;
import com.team23.game.ui.controls.UIElement;
import com.team23.game.ui.UIPage;
import com.team23.game.ui.controls.*;
import com.team23.game.ui.controls.labels.LabelStyles;

public class LoadAndSavePage extends UIPage {
    Label saveTitle;
    ListView listView;

    public LoadAndSavePage() {
        super();

        listView = new ListView();
        listView.setBackground("ui/LoadAndSavePage/SaveListBackground.png");
        listView.setRelativePosition(20,50, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.topAlignment);
        listView.padding = new Padding(20f,20f,150f,10f);
        updateSaveList();

        Image loadAndSavePage = new Image(new TextureRegion(new Texture("ui/LoadAndSavePage/LoadAndSave.png")));
        loadAndSavePage.setRelativePosition(0, 100);

        Button loadButton = new Button();
        loadButton.setTextures(
                "ui/LoadAndSavePage/loadNormal.png",
                "ui/LoadAndSavePage/loadHovered.png",
                "ui/LoadAndSavePage/loadPressed.png",
                "");
        loadButton.setRelativePosition(500,175, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        loadButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                loadSave();
            }
        });

        Button deleteButton = new Button();
        deleteButton.setTextures(
                "ui/LoadAndSavePage/deleteNormal.png",
                "ui/LoadAndSavePage/deleteHovered.png",
                "ui/LoadAndSavePage/deletePressed.png",
                "");
        deleteButton.setRelativePosition(200,175, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        deleteButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getParentFrame().goBack();
            }
        });

        Button backButton = new Button();
        backButton.setTextures(
                "ui/CreateNewGamePage/backNormal.png",
                "ui/CreateNewGamePage/backHovered.png",
                "ui/CreateNewGamePage/backPressed.png",
                "");
        backButton.setRelativePosition(0,50, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.bottomAlignment);
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

        saveTitle = new Label("Auber Game", LabelStyles.getGameTitleLabelStyle());
        saveTitle.setPosition(this.getWidth()/2-this.getWidth()/2,800);
        this.addUIElement(listView);
        this.addUIElement(loadAndSavePage);
        this.addUIElement(loadButton);
        this.addUIElement(deleteButton);
        this.addUIElement(backButton);
        this.hide();
    }

    public void loadSave(){
        Save selectedSave = ((SaveListViewItem)listView.selectedItem).save;
        SaveManager.current.setLoadedSave(selectedSave);
        GameEntry.current.createPlayScreen(PlayConfig.demoGame());
    }

    public void updateSaveList(){
        for(Save save : GameEntry.current.saveManager.getSaves()){
            listView.add(new SaveListViewItem(save));
        }
    }

    /***
     *
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
