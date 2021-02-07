package com.team23.game.ui.controls;

/***
 * ListView Item
 */
public class ListViewItem extends SelectableUIElement {
    public ListView parentListView;

    /***
     * Constructor of the Listview Item
     */
    public ListViewItem() {
        super();
        selectableUIElementClickListener = new SelectableUIElementClickListener();
        this.addListener(selectableUIElementClickListener);
    }

    @Override
    public void select(){
        super.select();
        this.parentListView.selectItem(this);
    }
}
