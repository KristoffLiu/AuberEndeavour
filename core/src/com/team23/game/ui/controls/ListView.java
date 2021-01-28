package com.team23.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.team23.game.ui.Padding;
import com.team23.game.ui.UIElement;

import java.util.ArrayList;

public class ListView extends Container {
    public float itemWidth = 400f;
    public float itemHeight = 100f;
    public float itemSpacing = 20f;
    public Padding padding = new Padding(0);

    /***
     * add UI element
     * @param parent the actor which will be the child
     */
    public ListView(Object parent) {
        super(parent);
        children = new ArrayList<>();
    }

    public void updateLayout(){
        int count = 1;
        for(UIElement child : children){
            child.setWidth(itemWidth);
            child.setHeight(itemHeight);
            child.setPosition(
                    this.getX() + padding.left,
                    this.getY() + this.getHeight() - count * itemHeight - (count-1) * itemSpacing - padding.top);
            count++;
        }
    }

    /***
     * add UI element
     * @param child the actor which will be the child
     */
    public void add(ListViewItem child){
        super.add(child);
        child.parentListView = this;
        itemWidth = child.getWidth();
        itemHeight = child.getHeight();
    }

    /***
     * select item
     * @param handle the event when a child is selected
     */
    public void selectItem(ListViewItem item){
        for(UIElement ui_child : children){
            ListViewItem child = (ListViewItem)ui_child;
            if(child == item){
                child.setState(SelectableUIElement.SelectableState.selected);
            }
            else {
                if(child.selectableState != SelectableUIElement.SelectableState.notActivated){
                    child.setState(SelectableUIElement.SelectableState.unselected);
                }
            }
        }
    }




    /**
     * logic handler of the actor
     *
     * @param delta
     *		the change of time from the last rendered frame to the current rendering frame,
     *	    or we call it the rendering time step / time difference.
     *	    the unite is second.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        updateLayout();
    }

    /**
     * RenderActor
     *
     * @param batch
     *
     * @param parentAlpha
     * 		The Sprite Batch, is used to render the texture of actor encapsulation.
     *
     * @param parentAlpha
     * 		the transparent of parent
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
