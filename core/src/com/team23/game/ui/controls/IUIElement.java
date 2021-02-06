package com.team23.game.ui.controls;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import com.team23.game.ui.pages.UIPage;

public interface IUIElement {
    Object uiParent = null;
    UIElement.HorizontalAlignment horizontalAlignment = UIElement.HorizontalAlignment.leftAlignment;
    UIElement.VerticalAlignment verticalAlignment = UIElement.VerticalAlignment.bottomAlignment;
    float relativeX = 0;
    float relativeY = 0;

    void setUIParent(UIElement _uiElement);
    Object getParent();

    void setRootPage(UIPage rootPage);
    UIPage getRootPage();

    float getRelativeX();
    float getRelativeY();
    void setRelativeX(float relativeX);
    void setRelativeY(float relativeY);
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

}
