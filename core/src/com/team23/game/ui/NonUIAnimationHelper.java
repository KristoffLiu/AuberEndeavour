package com.team23.game.ui;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.Null;

public class NonUIAnimationHelper {

    Actor actor;
    float animationOrigin_X = 0f;
    float animationOrigin_Y = 0f;

    public NonUIAnimationHelper(Actor actor){
        this.actor = actor;
    }


    public void setAnimationOrigin(float x, float y){
        animationOrigin_X = x;
        animationOrigin_Y = y;
    }

    public void hide(){
        AlphaAction uiElementAlphaAction = Actions.alpha(0f,0f);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        actor.addAction(uiElementAlphaAction);
        actor.addAction(uiElementVisibleAction);
    }

    public void hide(float duration){
        AlphaAction uiElementAlphaAction = Actions.alpha(0,duration);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        DelayAction uiElementDelayAction = Actions.delay(duration,uiElementVisibleAction);
        actor.addAction(uiElementAlphaAction);
        actor.addAction(uiElementDelayAction);
    }

    public void appear(){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,0f);
        actor.addAction(uiElementVisibleAction);
        actor.addAction(uiElementAlphaAction);
    }

    public void appear(float duration){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,duration);
        actor.addAction(uiElementVisibleAction);
        actor.addAction(uiElementAlphaAction);
    }

    public void fadeOut(float duration){
        fadeOut(0f, 0f, duration, null);
    }

    public void fadeOut(float offset, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeOut(offset, 0f, duration, null); else fadeOut(0f, offset, duration, null);
    }

    public void fadeOut(float offset_x, float offset_y, float duration){
        fadeOut(offset_x, offset_y, duration, null);
    }

    public void fadeOut(float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        fadeOut(animationOrigin_X, animationOrigin_Y, offset_x, offset_y, duration, interpolation);
    }

    public void fadeOut(float x, float y, float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        if(actor.isVisible()){
            MoveToAction uiElementMoveToAction = Actions.moveTo(x,y,0f);
            AlphaAction uiElementAlphaAction = Actions.alpha(0f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offset_x, offset_y, duration, interpolation);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(uiElementMoveToAction,parallelAction);
            actor.addAction(sequenceAction);
        }
    }

    public void fadeIn(float duration){
        fadeIn(0f, 0f, duration, null);
    }

    public void fadeIn(float value, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeIn(value, 0f, duration, null); else fadeIn(0f, value, duration, null);

    }

    public void fadeIn(float offset_x, float offset_y, float duration){
        fadeIn(offset_x, offset_y, duration, null);
    }

    public void fadeIn(float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        fadeIn(animationOrigin_X, animationOrigin_Y, offset_x, offset_y, duration, interpolation);
    }

    public void fadeIn(float x, float y, float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        if(actor.isVisible()){
            MoveToAction uiElementMoveToAction = Actions.moveTo(x - offset_x,y - offset_y,0f);
            AlphaAction uiElementAlphaAction = Actions.alpha(1f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offset_x, offset_y, duration, interpolation);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(uiElementMoveToAction,parallelAction);
            actor.addAction(sequenceAction);
        }
    }
}
