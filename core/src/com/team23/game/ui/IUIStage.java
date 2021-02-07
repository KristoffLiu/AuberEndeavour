package com.team23.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.team23.game.ui.controls.UIElement;

public interface IUIStage {
    void addUIElement(UIElement uiElement);
    void removeUIElement(UIElement uiElement);
    Array<Actor> getUIElementsAll();
    void hide();
    void appear();
}
