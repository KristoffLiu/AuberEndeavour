package com.team23.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team23.game.GameEntry;
import com.team23.game.ui.layouts.UIGroup;

/***
 * the stage showing the user interfaces only.
 */
public class UIPage extends Stage implements IUIStage{
    private UIFrame parentFrame = null;
    Array<Actor> uiElements = new Array<Actor>();

    public UIPage() {
        super(new StretchViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT));
    }

    public UIPage(Viewport viewport) {
        super(viewport);
    }

    /***
     * add UI element
     * @param uiElement the actor which will be the child
     */
    @Override
    public void addUIElement(Actor uiElement) {
        super.addActor(uiElement);
        this.uiElements.add(uiElement);
    }

    /***
     * add UI group
     * @param uiGroup the ui group which will be the child
     */
    public void addUIGroup(UIGroup uiGroup) {
        super.addActor(uiGroup);
    }

    /***
     * remove the ui element
     * @param uiElement the ui element which will be the child
     */
    @Override
    public void removeUIElement(UIElement uiElement) {

    }

    /***
     * remove the ui element
     * @return the array of actor representing the child elements of this ui stage.
     */
    @Override
    public Array<Actor> getUIElementsAll() {
        return uiElements;
    }

    /***
     * hide
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
            ((UIElement)actor).appear();
        }
    }

    public UIFrame getParentFrame(){
        return this.parentFrame;
    }

    public void setParentFrame(UIFrame uiFrame){
        this.parentFrame = uiFrame;
    }
}
