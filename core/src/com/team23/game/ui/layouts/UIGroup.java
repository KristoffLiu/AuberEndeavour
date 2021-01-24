package com.team23.game.ui.layouts;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.team23.game.ui.IUIElement;
import com.team23.game.ui.IUIStage;
import com.team23.game.ui.UIElement;
import com.team23.game.ui.UIStage;

/***
 * class of a UI Group, which is inherited from Group class.
 */
public class UIGroup extends Group implements IUIElement, IUIStage {
    Object uiParent = null;
    Array<Actor> uiElements = new Array<>();
    UIElement.HorizontalAlignment horizontalAlignment = UIElement.HorizontalAlignment.leftAlignment;
    UIElement.VerticalAlignment verticalAlignment = UIElement.VerticalAlignment.bottomAlignment;
    float relativeX = 0;
    float relativeY = 0;

    /**
     * constructor
     ***/
    public UIGroup(Object parent) {
        if(parent instanceof UIStage){
            UIStage _parent = (UIStage) parent;
            _parent.addUIElement(this);
            setUIParent(parent);
        }
        else if(parent instanceof UIGroup){
            UIGroup _parent = (UIGroup) parent;
            _parent.addActor(this);
            setUIParent(parent);
        }
    }

    /**
     * set ui parent
     ***/
    public void setUIParent(Object _uiParent){
        this.uiParent = _uiParent;
    }

    /**
     * get ui parent
     * @return return the ui parent
     ***/
    public Object getUIParent() {
        return uiParent;
    }

    /**
     * get relative coordination of X
     * @return return the relative coordination of X
     ***/
    @Override
    public float getRelativeX() {
        return relativeX;
    }

    /**
     * get relative coordination of Y
     * @return return the relative coordination of Y
     ***/
    @Override
    public float getRelativeY() {
        return relativeY;
    }

    /**
     * set relative coordination of X
     ***/
    @Override
    public void setRelativeX(float relativeX){
        if(uiParent != null){
            float offset = 0f;
            if(uiParent instanceof UIStage){
                UIStage _parent = (UIStage) uiParent;
                switch (this.horizontalAlignment){
                    case leftAlignment:
                        this.setX(offset + relativeX);
                        break;
                    case centreAlignment:
                        offset = _parent.getWidth() / 2 - this.getWidth() / 2;
                        this.setX(offset + relativeX);
                        break;
                    case rightAlignment:
                        offset = _parent.getWidth() - this.getWidth();
                        this.setX(offset - relativeX);
                        break;
                }
            }
            else if(uiParent instanceof UIGroup){
                UIGroup _parent = (UIGroup) uiParent;
                switch (this.horizontalAlignment){
                    case leftAlignment:
                        this.setX(offset + relativeX);
                        break;
                    case centreAlignment:
                        offset = _parent.getWidth() / 2 - this.getWidth() / 2;
                        this.setX(offset + relativeX);
                        break;
                    case rightAlignment:
                        offset = _parent.getWidth() - this.getWidth();
                        this.setX(offset - relativeX);
                        break;
                }
            }
        }
        else{
            setX(relativeX);
        }
        this.relativeX = relativeX;
    }

    /**
     * set relative coordination of Y
     ***/
    @Override
    public void setRelativeY(float relativeY){
        if(uiParent != null){
            float offset = 0f;
            if(uiParent instanceof UIStage){
                UIStage _parent = (UIStage) uiParent;
                switch (this.verticalAlignment) {
                    case topAlignment:
                        offset = _parent.getHeight() - this.getHeight();
                        this.setY(offset - relativeY);
                        break;
                    case centreAlignment:
                        offset = _parent.getHeight() / 2 - this.getHeight() / 2;
                        this.setY(offset + relativeY);
                        break;
                    case bottomAlignment:
                        this.setY(offset + relativeY);
                        break;
                }
            }
            else if(uiParent instanceof UIGroup){
                UIGroup _parent = (UIGroup) uiParent;
                switch (this.verticalAlignment) {
                    case topAlignment:
                        offset = _parent.getHeight() - this.getHeight();
                        this.setY(offset - relativeY);
                        break;
                    case centreAlignment:
                        offset = _parent.getHeight() / 2 - this.getHeight() / 2;
                        this.setY(offset + relativeY);
                        break;
                    case bottomAlignment:
                        this.setY(offset + relativeY);
                        break;
                }
            }
        }
        else{
            setY(relativeY);
        }
        this.relativeY = relativeY;
    }

    /**
     * get the horizontal alignment
     ***/
    @Override
    public UIElement.HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * get the vertical alignment
     ***/
    @Override
    public UIElement.VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * set the vertical alignment
     ***/
    @Override
    public void setHorizontalAlignment(UIElement.HorizontalAlignment alignment){
        horizontalAlignment = alignment;
        setRelativeX(relativeX);
    }

    /**
     * set the vertical alignment
     ***/
    @Override
    public void setVerticalAlignment(UIElement.VerticalAlignment alignment){
        verticalAlignment = alignment;
        setRelativeY(relativeY);
    }

    /**
     * set the relative position
     ***/
    @Override
    public void setRelativePosition(float relativeX, float relativeY, UIElement.HorizontalAlignment horizontalAlignment, UIElement.VerticalAlignment verticalAlignment) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        setHorizontalAlignment(horizontalAlignment);
        setVerticalAlignment(verticalAlignment);
    }

    /**
     * add the ui element
     ***/
    @Override
    public void addUIElement(Actor uiElement) {
        super.addActor(uiElement);
        this.uiElements.add(uiElement);
    }

    /**
     * remove the ui element
     ***/
    @Override
    public void removeUIElement(UIElement uiElement) {

    }

    /**
     * get all the UI Elements
     ***/
    @Override
    public Array<Actor> getUIElementsAll() {
        return uiElements;
    }

    float animationOrigin_X = 0f;
    float animationOrigin_Y = 0f;

    /**
     * get all the UI Elements
     ***/
    public void setAnimationOrigin(float x, float y){
        animationOrigin_X = x;
        animationOrigin_Y = y;
    }

    /**
     * hide
     ***/
    public void hide(){
        AlphaAction uiElementAlphaAction = Actions.alpha(0f,0f);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        this.addAction(uiElementAlphaAction);
        this.addAction(uiElementVisibleAction);
    }

    /**
     * hide
     ***/
    public void hide(float duration){
        AlphaAction uiElementAlphaAction = Actions.alpha(0,duration);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        DelayAction uiElementDelayAction = Actions.delay(duration,uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
        this.addAction(uiElementDelayAction);
    }

    /**
     * appear
     ***/
    public void appear(){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,0f);
        this.addAction(uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
    }

    /**
     * appear
     ***/
    public void appear(float duration){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,duration);
        this.addAction(uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
    }

    /**
     * fade out
     ***/
    public void fadeOut(float duration){
        fadeOut(0f, 0f, duration, null);
    }

    /**
     * fade out
     ***/
    public void fadeOut(float offset, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeOut(offset, 0f, duration, null); else fadeOut(0f, offset, duration, null);
    }

    /**
     * fade out
     ***/
    public void fadeOut(float offset_x, float offset_y, float duration){
        fadeOut(offset_x, offset_y, duration, null);
    }

    /**
     * fade out
     ***/
    public void fadeOut(float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        fadeOut(animationOrigin_X, animationOrigin_Y, offset_x, offset_y, duration, interpolation);
    }

    /**
     * fade out
     ***/
    public void fadeOut(float x, float y, float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){
            MoveToAction uiElementMoveToAction = Actions.moveTo(x,y,0f);
            AlphaAction uiElementAlphaAction = Actions.alpha(0f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offset_x, offset_y, duration, interpolation);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(uiElementMoveToAction,parallelAction);
            this.addAction(sequenceAction);
        }
    }

    /**
     * fade out
     ***/
    public void fadeIn(float duration){
        fadeIn(0f, 0f, duration, null);
    }

    /**
     * fade out
     ***/
    public void fadeIn(float value, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeIn(value, 0f, duration, null); else fadeIn(0f, value, duration, null);

    }

    /**
     * fade out
     ***/
    public void fadeIn(float offset_x, float offset_y, float duration){
        fadeIn(offset_x, offset_y, duration, null);
    }

    /**
     * fade out
     ***/
    public void fadeIn(float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        fadeIn(animationOrigin_X, animationOrigin_Y, offset_x, offset_y, duration, interpolation);
    }

    /**
     * fade out
     ***/
    public void fadeIn(float x, float y, float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){
            MoveToAction uiElementMoveToAction = Actions.moveTo(x - offset_x,y - offset_y,0f);
            AlphaAction uiElementAlphaAction = Actions.alpha(1f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offset_x, offset_y, duration, interpolation);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(uiElementMoveToAction,parallelAction);
            this.addAction(sequenceAction);
        }
    }
}
