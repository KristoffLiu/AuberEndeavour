package com.team23.game.ui.controls;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.Null;
import com.team23.game.actors.CustomActor;
import com.team23.game.ui.pages.UIPage;

/***
 * the actor showing the user-interface elements only.
 */
public class UIElement extends CustomActor implements IUIElement{
    UIElement uiParent = null;
    UIPage rootPage = null;
    HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT_ALIGNMENT;
    VerticalAlignment verticalAlignment = VerticalAlignment.TOP_ALIGNMENT;
    float XRelative = 0;
    float YRelative = 0;

    private Object tag;

    /***
     * add UI element
     */
    public UIElement() {
        super();
    }

    /***
     * set the UI Parent
     * @param uiParent parent of the ui
     */
    public void setUIParent(UIElement uiParent){
        this.uiParent = uiParent;
    }

    /***
     * set the UI Parent
     * @return return the parent of this ui
     */
    public UIElement getUIParent() {
        return uiParent;
    }

    @Override
    public void setRootPage(UIPage rootPage) {
        if(this.rootPage != rootPage){
            this.rootPage = rootPage;
            rootPage.addUIElement(this);
        }
    }

    @Override
    public UIPage getRootPage() {
        return this.rootPage;
    }

    /***
     * set the UI Parent
     * @return get the relative coordination of X
     */
    @Override
    public float getXRelative() {
        return XRelative;
    }

    /***
     * set the UI Parent
     * @return get the relative coordination of X
     */
    @Override
    public float getYRelative() {
        return YRelative;
    }

    /***
     * set the relative coordination of X
     * @param XRelative the relative coordination of X
     */
    public void setXRelative(float XRelative){
        if(this.rootPage != null){
            float xUIParent = 0;
            float offset = 0f;
            float parentWidth = this.getRootPage().getWidth();

            if(uiParent == null){

            }
            else{
                xUIParent = uiParent.getX();
                offset += xUIParent;
                parentWidth = this.getParent().getWidth();
            }

            switch (this.horizontalAlignment){
                case LEFT_ALIGNMENT:
                    this.setX(offset + XRelative);
                    break;
                case CENTRE_ALIGNMENT:
                    offset += parentWidth / 2 - this.getWidth() * this.getScaleX() / 2;
                    this.setX(offset + XRelative);
                    break;
                case RIGHT_ALIGNMENT:
                    offset += parentWidth - this.getWidth() * this.getScaleX();
                    this.setX(offset - XRelative);
                    break;
            }
        }
        else{
            setX(XRelative);
        }
        this.XRelative = XRelative;
    }

    /***
     * set the relative coordination of Y
     * @param YRelative the relative coordination of Y
     */
    public void setYRelative(float YRelative){
        if(this.rootPage != null){
            float yUIParent = 0;
            float offset = 0f;
            float parentHeight;
            parentHeight = this.getRootPage().getHeight();

            if(uiParent == null){

            }
            else{
                yUIParent = uiParent.getY() + uiParent.getHeight();
                offset += yUIParent;
                parentHeight = this.getParent().getHeight();
            }

            switch (this.verticalAlignment) {
                case TOP_ALIGNMENT:
                    offset += (parentHeight - this.getHeight() * this.getScaleY());
                    this.setY(offset - YRelative);
                    break;
                case CENTRE_ALIGNMENT:
                    offset += parentHeight / 2 - (this.getHeight() * this.getScaleY()) / 2;
                    this.setY(offset + YRelative);
                    break;
                case BOTTOM_ALIGNMENT:
                    this.setY(offset + YRelative);
                    break;
            }
        }
        else{
            setX(YRelative);
        }
        this.YRelative = YRelative;
    }

    /***
     * get the HorizontalAlignment
     * @return get the HorizontalAlignment
     */
    @Override
    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /***
     * get the HorizontalAlignment
     * @return get the HorizontalAlignment
     */
    @Override
    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    /***
     * set the horizontalAlignment
     * @param alignment set the horizontal alignment
     */
    public void setHorizontalAlignment(HorizontalAlignment alignment){
        horizontalAlignment = alignment;
        setXRelative(XRelative);
    }

    /***
     * set the horizontalAlignment
     * @param alignment set the vertiacal alignment
     */
    public void setVerticalAlignment(VerticalAlignment alignment){
        verticalAlignment = alignment;
        setYRelative(YRelative);
    }

    /***
     * set the set relative position
     * @param relativeX set the relative coordination of X
     * @param relativeY set the relative coordination of Y
     */
    public void setRelativePosition(float relativeX, float relativeY) {
        this.XRelative = relativeX;
        this.YRelative = relativeY;
        setHorizontalAlignment(this.horizontalAlignment);
        setVerticalAlignment(this.verticalAlignment);
    }

    /***
     * set the set relative position
     * @param relativeX set the relative coordination of X
     * @param relativeY set the relative coordination of Y
     * @param horizontalAlignment set the horizontal alignment
     * @param verticalAlignment set the vertical alignment
     */
    @Override
    public void setRelativePosition(float relativeX, float relativeY, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.XRelative = relativeX;
        this.YRelative = relativeY;
        setHorizontalAlignment(horizontalAlignment);
        setVerticalAlignment(verticalAlignment);
    }

    /***
     * set size of the ui element
     * @param width set the width
     * @param height set the height
     */
    @Override
    public void setSize(float width, float height){
        this.setWidth(width);
        this.setHeight(height);
    }

    public enum HorizontalAlignment{
        LEFT_ALIGNMENT, CENTRE_ALIGNMENT, RIGHT_ALIGNMENT
    }

    public enum VerticalAlignment{
        TOP_ALIGNMENT, CENTRE_ALIGNMENT, BOTTOM_ALIGNMENT
    }

    float animationOriginX = 0f;
    float animationOriginY = 0f;

    /***
     * set the animation
     * @param x set the x coordination for animation
     * @param y set the x coordination for animation
     */
    public void setAnimationOrigin(float x, float y){
        animationOriginX = x;
        animationOriginY = y;
    }

    /***
     * hide this ui element immediately
     */
    public void hide(){
        AlphaAction uiElementAlphaAction = Actions.alpha(0f,0f);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        this.addAction(uiElementAlphaAction);
        this.addAction(uiElementVisibleAction);
    }

    /***
     * hide this ui element in a duration of time
     * @param duration set the x coordination for animation
     */
    public void hide(float duration){
        AlphaAction uiElementAlphaAction = Actions.alpha(0,duration);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        DelayAction uiElementDelayAction = Actions.delay(duration,uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
        this.addAction(uiElementDelayAction);
    }

    /***
     * appear this ui element immediately
     */
    public void appear(){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,0f);
        this.addAction(uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
    }

    /***
     * appear this ui element in a duration of time
     * @param duration duration of appearing
     */
    public void appear(float duration){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,duration);
        this.addAction(uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
    }

    /***
     * fade out this ui element in a duration of time
     * @param duration duration of fading
     */
    public void fadeOut(float duration){
        fadeOut(0f, 0f, duration, null);
    }

    /***
     * fade out this ui element in a duration of time
     */
    public void fadeOut(float offset, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeOut(offset, 0f, duration, null); else fadeOut(0f, offset, duration, null);
    }

    /***
     * fade out this ui element in a duration of time
     */
    public void fadeOut(float offsetX, float offsetY, float duration){
        fadeOut(offsetX, offsetY, duration, null);
    }

    /***
     * fade out this ui element in a duration of time
     */
    public void fadeOut(float offsetX, float offsetY, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){
            AlphaAction uiElementAlphaAction = Actions.alpha(0f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offsetX, offsetY, duration, interpolation);
            MoveByAction endingMoveByAction = Actions.moveBy(-offsetX,-offsetY,0f);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(parallelAction,endingMoveByAction);
            this.addAction(sequenceAction);
        }
    }

    /***
     * fade out this ui element in a duration of time
     */
    public void fadeOut(float x, float y, float offsetX, float offsetY, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){
            MoveToAction uiElementMoveToAction = Actions.moveTo(x,y,0f);
            AlphaAction uiElementAlphaAction = Actions.alpha(0f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offsetX, offsetY, duration, interpolation);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(uiElementMoveToAction,parallelAction);
            this.addAction(sequenceAction);
        }
    }

    /***
     * fade in this ui element in a duration of time
     */
    public void fadeIn(float duration){
        fadeIn(0f, 0f, duration, null);
    }

    /***
     * fade in this ui element in a duration of time
     */
    public void fadeIn(float value, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeIn(value, 0f, duration, null); else fadeIn(0f, value, duration, null);

    }

    /***
     * fade in this ui element in a duration of time
     */
    public void fadeIn(float offsetX, float offsetY, float duration){
        fadeIn(offsetX, offsetY, duration, null);
    }

    /***
     * fade in this ui element in a duration of time
     */
    public void fadeIn(float offsetX, float offsetY, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){
            VisibleAction uiElementVisibleAction = Actions.visible(true);
            MoveByAction beginningMoveToAction = Actions.moveBy(-offsetX, -offsetY,0f);
            AlphaAction beginningAlphaAction = Actions.alpha(0f, 0);
            AlphaAction uiElementAlphaAction = Actions.alpha(1f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offsetX, offsetY, duration, interpolation);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(uiElementVisibleAction,beginningMoveToAction,beginningAlphaAction,parallelAction);
            this.addAction(sequenceAction);
        }
    }

    /***
     * fade in this ui element in a duration of time
     */
    public void fadeIn(float x, float y, float offsetX, float offsetY, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){
            VisibleAction uiElementVisibleAction = Actions.visible(true);
            MoveToAction uiElementMoveToAction = Actions.moveTo(x - offsetX,y - offsetY,0f);
            AlphaAction beginningAlphaAction = Actions.alpha(0f, 0);
            AlphaAction uiElementAlphaAction = Actions.alpha(1f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offsetX, offsetY, duration, interpolation);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(uiElementVisibleAction,uiElementMoveToAction,beginningAlphaAction,parallelAction);
            this.addAction(sequenceAction);
        }
    }

    @Override
    public void act(float delta){
        setHorizontalAlignment(horizontalAlignment);
        setVerticalAlignment(verticalAlignment);
    }

    @Override
    public void setTag(Object tag){
        this.tag = tag;
    }

    @Override
    public Object getTag(){
        return this.tag;
    }
}
