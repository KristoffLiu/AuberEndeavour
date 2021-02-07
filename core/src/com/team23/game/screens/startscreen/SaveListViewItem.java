package com.team23.game.screens.startscreen;

import com.team23.game.save.Save;
import com.team23.game.ui.controls.ListViewItem;
import com.team23.game.ui.controls.TextBlock;

public class SaveListViewItem extends ListViewItem {
    Save save;

    String title = "";
    String datetime = "";
    TextBlock titleTextBlock;
    TextBlock timeTextBlock;

    public SaveListViewItem(Save existingSave) {
        super();
        this.setTextures(
                "ui/LoadAndSavePage/SaveListItemUnselected.png",
                "ui/LoadAndSavePage/SaveListItemSelected.png",
                "ui/LoadAndSavePage/SaveListItemUnselectedHovered.png",
                "ui/LoadAndSavePage/SaveListItemUnselectedPressed.png",
                "ui/LoadAndSavePage/SaveListItemSelectedHovered.png",
                "ui/LoadAndSavePage/SaveListItemSelectedPressed.png",
                "");
        this.save = existingSave;
        titleTextBlock = new TextBlock();
        titleTextBlock.setText(save.name);
        titleTextBlock.setVerticalAlignment(VerticalAlignment.centreAlignment);
        titleTextBlock.setHorizontalAlignment(HorizontalAlignment.leftAlignment);
        titleTextBlock.setRelativePosition(40,-45);
        titleTextBlock.setFontSize(0.8f);
        titleTextBlock.setFontColor(1,1,1,1);

        timeTextBlock = new TextBlock();
        timeTextBlock.setText(save.dateTime);
        timeTextBlock.setVerticalAlignment(VerticalAlignment.centreAlignment);
        timeTextBlock.setHorizontalAlignment(HorizontalAlignment.leftAlignment);
        timeTextBlock.setRelativePosition(620,-60);
        timeTextBlock.setFontSize(0.4f);
        timeTextBlock.setFontColor(1,1,1,1);

        this.container.add(titleTextBlock);
        this.container.add(timeTextBlock);
    }
}
