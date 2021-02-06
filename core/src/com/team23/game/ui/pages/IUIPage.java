package com.team23.game.ui.pages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.team23.game.ui.controls.UIElement;

public interface IUIPage {
    void addUIElement(UIElement uiElement);
    void removeUIElement(UIElement uiElement);
    Array<Actor> getUIElementsAll();
    void hide();
    void appear();
}
