package com.team23.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public interface IUIStage {
    void addUIElement(UIElement uiElement);
    void removeUIElement(UIElement uiElement);
    Array<Actor> getUIElementsAll();
    void hide();
    void appear();
}
