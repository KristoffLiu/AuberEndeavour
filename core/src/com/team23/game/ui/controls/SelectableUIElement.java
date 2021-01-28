package com.team23.game.ui.controls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.team23.game.ui.UIElement;

public class SelectableUIElement extends UIElement {
    public Container container;

    public TextureRegion unselectedTexture          ;
    public TextureRegion selectedTexture            ;
    public TextureRegion unselectedHoveredTexture   ;
    public TextureRegion unselectedPressedTexture   ;
    public TextureRegion selectedHoveredTexture     ;
    public TextureRegion selectedPressedTexture     ;
    public TextureRegion notActivatedTexture        ;

//    unselected,
//    selected,
//    unselectedHovered,
//    unselectedPressed,
//    selectedHovered,
//    selectedPressed,
//    notActivated

    SelectableUIElementClickListener selectableUIElementClickListener;
    SelectableUIState uiState = SelectableUIState.unselected;
    SelectableState selectableState = SelectableState.unselected;

    public SelectableUIElement() {
        super();
        uiState = SelectableUIState.unselected;
        selectableUIElementClickListener = new SelectableUIElementClickListener();
        this.addListener(selectableUIElementClickListener);
    }

    public SelectableUIElement(Object uiParent) {
        super(uiParent);
        selectableUIElementClickListener = new SelectableUIElementClickListener();
        this.addListener(selectableUIElementClickListener);
    }

    /**
     * set the texture of the button
     *
     * @param unselectedTexturePath
     * @param selectedTexturePath
     * @param unselectedHoveredTexturePath
     * @param unselectedHoveredTexturePath
     * @param selectedHoveredTexturePath
     * @param selectedPressedTexturePath
     * @param notActivatedTexturePath
     */
    public void setTextures(String unselectedTexturePath, String selectedTexturePath,
                            String unselectedHoveredTexturePath, String unselectedPressedTexturePath,
                            String selectedHoveredTexturePath, String selectedPressedTexturePath,
                            @Null String notActivatedTexturePath){
        this.unselectedTexture          = !unselectedTexturePath.isEmpty() ? new TextureRegion(new Texture(unselectedTexturePath)) : null;
        this.selectedTexture            = !selectedTexturePath.isEmpty() ? new TextureRegion(new Texture(selectedTexturePath)) : null;
        this.unselectedHoveredTexture   = !unselectedHoveredTexturePath.isEmpty() ? new TextureRegion(new Texture(unselectedHoveredTexturePath)) : null;
        this.unselectedPressedTexture   = !unselectedPressedTexturePath.isEmpty() ? new TextureRegion(new Texture(unselectedPressedTexturePath)) : null;
        this.selectedHoveredTexture     = !selectedHoveredTexturePath.isEmpty() ? new TextureRegion(new Texture(selectedHoveredTexturePath)) : null;
        this.selectedPressedTexture     = !selectedPressedTexturePath.isEmpty() ? new TextureRegion(new Texture(selectedPressedTexturePath)) : null;
        this.notActivatedTexture        = !notActivatedTexturePath.isEmpty() ? new TextureRegion(new Texture(notActivatedTexturePath)) : null;
        switch (this.uiState){
            case unselected:
                this.setTextureRegion(unselectedTexture);
                break;
            case selected:
                this.setTextureRegion(selectedTexture);
                break;
            case unselectedHovered:
                this.setTextureRegion(unselectedHoveredTexture);
                break;
            case unselectedPressed:
                this.setTextureRegion(unselectedPressedTexture);
                break;
            case selectedHovered:
                this.setTextureRegion(selectedHoveredTexture);
                break;
            case selectedPressed:
                this.setTextureRegion(selectedPressedTexture);
                break;
            case notActivated:
                this.setTextureRegion(notActivatedTexture);
                break;
        }
    }

    public void setClickListener(SelectableUIElementClickListener clickListener){
        this.removeListener(selectableUIElementClickListener);
        selectableUIElementClickListener = clickListener;
        this.addListener(selectableUIElementClickListener);
    }

//    public void isEnabled(boolean isEnabled){
//        this.isEnabled = isEnabled;
//        if(isEnabled){
//            setButtonUIState(uiState.normal);
//        }
//        else {
//            setButtonUIState(ClickableUIElement.ButtonUIState.notActivated);
//        }
//    }

    //Switches the texture of the button to be highlighted or not highlighted
    public void setUIState(SelectableUIState selectableUIState){
        if(selectableUIState != this.uiState){
            float previousWidth  = this.getWidth();
            float previousHeight = this.getHeight();
            switch (selectableUIState){
                case unselected:
                    this.setTextureRegion(unselectedTexture);
                    break;
                case selected:
                    this.setTextureRegion(selectedTexture);
                    break;
                case unselectedHovered:
                    this.setTextureRegion(unselectedHoveredTexture);
                    break;
                case unselectedPressed:
                    this.setTextureRegion(unselectedPressedTexture);
                    break;
                case selectedHovered:
                    this.setTextureRegion(selectedHoveredTexture);
                    break;
                case selectedPressed:
                    this.setTextureRegion(selectedPressedTexture);
                    break;
                case notActivated:
                    this.setTextureRegion(notActivatedTexture);
                    break;
            }
            this.uiState = selectableUIState;
            this.setWidth(previousWidth);
            this.setHeight(previousHeight);
        }
    }

    public void setState(SelectableState selectableState){
        if(selectableState != this.selectableState){
            switch (selectableState){
                case unselected:
                    this.setTextureRegion(unselectedTexture);
                    break;
                case selected:
                    this.setTextureRegion(selectedTexture);
                    break;
                case notActivated:
                    this.setTextureRegion(notActivatedTexture);
                    break;
            }
            this.selectableState = selectableState;
        }
    }

    public void select(){

    }

    public enum SelectableUIState {
        unselected,
        selected,
        unselectedHovered,
        unselectedPressed,
        selectedHovered,
        selectedPressed,
        notActivated
    }

    public enum SelectableState {
        unselected, selected, notActivated
    }
}
