package com.team23.game.ui.controls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.team23.game.ui.UIElement;
import com.team23.game.ui.UIPage;

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
