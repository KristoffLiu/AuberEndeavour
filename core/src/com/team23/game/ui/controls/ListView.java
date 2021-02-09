package com.team23.game.ui.controls;

import com.team23.game.ui.basics.Padding;

import java.util.ArrayList;

/**
 * Class for making lists in the ui
 */
public class ListView extends Container {
    public float itemWidth = 400f;
    public float itemHeight = 100f;
    public float itemSpacing = 20f;
    public Padding padding = new Padding(0);
    public ListViewItem selectedItem;

    /***
     * add UI element
     */
    public ListView() {
        super();
        children = new ArrayList<>();
    }

    public void updateLayout(){
        int count = 0;
        for(UIElement child : children){
            child.setWidth(itemWidth);
            child.setHeight(itemHeight);
            child.setRelativePosition(
                    padding.left,
                    count * (itemHeight + itemSpacing) + padding.top);
            count++;
        }
    }

    /***
     * add UI element
     * @param child the actor which will be the child
     */
    public void addItem(ListViewItem child){
        super.add(child);
        if(this.getRootPage() != null){
            child.setRootPage(this.getRootPage());
        }
        child.parentListView = this;
        itemWidth = child.getWidth();
        itemHeight = child.getHeight();
    }

    /***
     * add UI element
     * @param child the actor which will be the child
     */
    public void deleteItem(ListViewItem child){
        children.remove(child);
        child.getRootPage().removeUIElement(child);
        child.removed();
        child.container.removed();
    }

    /***
     * select item
     * @param item the list view item
     */
    public void selectItem(ListViewItem item){
        for(UIElement ui_child : children){
            ListViewItem child = (ListViewItem)ui_child;
            if(child == item){
                child.setState(SelectableUIElement.SelectableState.SELECTED);
                selectedItem = child;
            }
            else {
                if(child.selectableState != SelectableUIElement.SelectableState.NOTACTIVATED){
                    child.setState(SelectableUIElement.SelectableState.UNSELECTED);
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
}
