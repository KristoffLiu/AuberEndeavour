package com.team23.game.ui.controls;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import com.team23.game.ui.pages.UIPage;

public interface IUIElement {
    Object uiParent = null;
    UIElement.HorizontalAlignment horizontalAlignment = UIElement.HorizontalAlignment.LEFT_ALIGNMENT;
    UIElement.VerticalAlignment verticalAlignment = UIElement.VerticalAlignment.BOTTOM_ALIGNMENT;
    float RELATIVEX = 0;
    float RELATIVEY = 0;

    void setUIParent(UIElement _uiElement);
    Object getParent();

    void setRootPage(UIPage rootPage);
    UIPage getRootPage();

    float getXRelative();
    float getYRelative();
    void setXRelative(float XRelative);
    void setYRelative(float YRelative);
    UIElement.HorizontalAlignment getHorizontalAlignment();
    UIElement.VerticalAlignment getVerticalAlignment();
    void setHorizontalAlignment(UIElement.HorizontalAlignment alignment);
    void setVerticalAlignment(UIElement.VerticalAlignment alignment);
    void setRelativePosition(float relativeX, float relativeY, UIElement.HorizontalAlignment horizontalAlignment, UIElement.VerticalAlignment verticalAlignment);

    void hide();
    void hide(float duration);
    void appear();
    void appear(float duration);
    void fadeOut(float x, float y, float duration, @Null Interpolation interpolation);
    void fadeIn(float x, float y, float duration, @Null Interpolation interpolation);


    void setTag(Object tag);
    Object getTag();
}
