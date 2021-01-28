package com.team23.game.ui.controls;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/***
 * handles events of a Clickable UI Element.
 */
public class SelectableUIElementClickListener extends ClickListener {

    boolean isStillOverAfterClicked = false;
    boolean isStillPressed = false;


    /** Called any time the mouse cursor or a finger touch is moved over an actor. On the desktop, this event occurs even when no
     * mouse buttons are pressed (pointer will be -1).
     * @param fromActor May be null.
     * @see ClickListener */
    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);
        SelectableUIElement selectableUIElement = (SelectableUIElement) event.getListenerActor();
        switch (selectableUIElement.selectableState){
            case unselected:
                selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.unselectedHovered);
                break;
            case selected:
                selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.selectedHovered);
                break;
            case notActivated:
                selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.notActivated);
        }
        isStillOverAfterClicked = false;
    }

    /** Called any time the mouse cursor or a finger touch is moved out of an actor. On the desktop, this event occurs even when no
     * mouse buttons are pressed (pointer will be -1).
     * @param toActor May be null.
     * @see ClickListener */
    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);
        if(isStillOverAfterClicked){
            enter(event, x, y, pointer, toActor);
        }else {
            SelectableUIElement selectableUIElement = (SelectableUIElement) event.getListenerActor();
            switch (selectableUIElement.selectableState){
                case unselected:
                    selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.unselected);
                    break;
                case selected:
                    selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.selected);
                    break;
                case notActivated:
                    selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.notActivated);
            }
        }
    }

    /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
     * button or touch. The touchUp event is always {@link Event#handle() handled}.
     * @see InputEvent */
    @Override
    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        SelectableUIElement selectableUIElement = (SelectableUIElement) event.getListenerActor();
        selectableUIElement.select();
    }

    /** Called when a mouse button or a finger touch goes down on the actor. If true is returned, this listener will have
     * {@link Stage#addTouchFocus(EventListener, Actor, Actor, int, int) touch focus}, so it will receive all touchDragged and
     * touchUp events, even those not over this actor, until touchUp is received. Also when true is returned, the event is
     * {@link Event#handle() handled}.
     * @see ClickListener */
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        SelectableUIElement selectableUIElement = (SelectableUIElement) event.getListenerActor();
        switch (selectableUIElement.selectableState){
            case unselected:
                selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.unselectedPressed);
                break;
            case selected:
                selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.selectedPressed);
                break;
            case notActivated:
                selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.notActivated);
        }
        return super.touchDown(event, x, y, pointer, button);
    }

    /** Called when a mouse button or a finger touch is moved anywhere, but only if touchDown previously returned true for the
     * mouse button or touch. The touchDragged event is always {@link Event#handle() handled}.
     * @see ClickListener */
    @Override
    public void touchDragged (InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
        SelectableUIElement selectableUIElement = (SelectableUIElement) event.getListenerActor();
        switch (selectableUIElement.selectableState){
            case unselected:
                selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.unselectedPressed);
                break;
            case selected:
                selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.selectedPressed);
                break;
            case notActivated:
                selectableUIElement.setUIState(SelectableUIElement.SelectableUIState.notActivated);
        }
    }

//    /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
//     * button or touch. The touchUp event is always {@link Event#handle() handled}.
//     * @see ClickListener */
//    @Override
//    public void clicked (InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        boolean a = isOver();
//        if(isOver()){
//            isStillOverAfterClicked = true;
//        }
//        ClickableUIElement clickableUIElement = (ClickableUIElement) event.getListenerActor();
//        if (clickableUIElement.isEnabled) {
//            if(isStillOverAfterClicked){
//                clickableUIElement.setButtonUIState(ClickableUIElement.ButtonUIState.hovered);
//            }
//            else{
//                clickableUIElement.setButtonUIState(ClickableUIElement.ButtonUIState.normal);
//            }
//        } else {
//            clickableUIElement.setButtonUIState(ClickableUIElement.ButtonUIState.notActivated);
//        }
//    }
}
