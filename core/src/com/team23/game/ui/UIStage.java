package com.team23.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team23.game.ui.layouts.UIGroup;

public class UIStage extends Stage implements IUIStage{

    Array<Actor> uiElements = new Array<Actor>();
    public UIStage(Viewport viewport) {
        super(viewport);
    }

    /***
     * Gives a random positive integer less than n
     * @param uiElement the actor
     * @return Integer less than n
     */
    @Override
    public void addUIElement(Actor uiElement) {
        super.addActor(uiElement);
        this.uiElements.add(uiElement);
    }

    public void addUIGroup(UIGroup uiGroup) {
        super.addActor(uiGroup);
    }

    @Override
    public void removeUIElement(UIElement uiElement) {

    }

    @Override
    public Array<Actor> getUIElementsAll() {
        return uiElements;
    }

    @Override
    public void hide(){

    }

    @Override
    public void appear(){

    }
}
