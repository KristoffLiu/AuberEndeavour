package com.team23.game.ui.controls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.team23.game.ui.pages.UIPage;

/***
 * Class for all selectable ui elemts
 */
public class SelectableUIElement extends Container {
    public Container container;

    public TextureRegion unselectedTexture;
    public TextureRegion selectedTexture;
    public TextureRegion unselectedHoveredTexture;
    public TextureRegion unselectedPressedTexture;
    public TextureRegion selectedHoveredTexture;
    public TextureRegion selectedPressedTexture;
    public TextureRegion notActivatedTexture;

    SelectableUIElementClickListener selectableUIElementClickListener;
    SelectableUIState uiState = SelectableUIState.UNSELECTED;
    SelectableState selectableState = SelectableState.UNSELECTED;

    public SelectableUIElement() {
        super();
        container = new Container();
        container.setUIParent(this);
        uiState = SelectableUIState.UNSELECTED;
        selectableUIElementClickListener = new SelectableUIElementClickListener();
        this.addListener(selectableUIElementClickListener);
    }

    @Override
    public void setRootPage(UIPage rootPage) {
        super.setRootPage(rootPage);
        container.setRootPage(rootPage);
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
        this.unselectedTexture = !unselectedTexturePath.isEmpty() ? new TextureRegion(new Texture(unselectedTexturePath)) : null;
        this.selectedTexture = !selectedTexturePath.isEmpty() ? new TextureRegion(new Texture(selectedTexturePath)) : null;
        this.unselectedHoveredTexture = !unselectedHoveredTexturePath.isEmpty() ? new TextureRegion(new Texture(unselectedHoveredTexturePath)) : null;
        this.unselectedPressedTexture = !unselectedPressedTexturePath.isEmpty() ? new TextureRegion(new Texture(unselectedPressedTexturePath)) : null;
        this.selectedHoveredTexture = !selectedHoveredTexturePath.isEmpty() ? new TextureRegion(new Texture(selectedHoveredTexturePath)) : null;
        this.selectedPressedTexture = !selectedPressedTexturePath.isEmpty() ? new TextureRegion(new Texture(selectedPressedTexturePath)) : null;
        this.notActivatedTexture = !notActivatedTexturePath.isEmpty() ? new TextureRegion(new Texture(notActivatedTexturePath)) : null;
        switch (this.uiState){
            case UNSELECTED:
                this.setTextureRegion(unselectedTexture);
                break;
            case SELECTED:
                this.setTextureRegion(selectedTexture);
                break;
            case UNSELECTEDHOVERED:
                this.setTextureRegion(unselectedHoveredTexture);
                break;
            case UNSELECTEDPRESSED:
                this.setTextureRegion(unselectedPressedTexture);
                break;
            case SELECTEDHOVERED:
                this.setTextureRegion(selectedHoveredTexture);
                break;
            case SELECTEDPRESSED:
                this.setTextureRegion(selectedPressedTexture);
                break;
            case NOTACTIVATED:
                this.setTextureRegion(notActivatedTexture);
                break;
        }
    }

    public void setClickListener(SelectableUIElementClickListener clickListener){
        this.removeListener(selectableUIElementClickListener);
        selectableUIElementClickListener = clickListener;
        this.addListener(selectableUIElementClickListener);
    }

    //Switches the texture of the button to be highlighted or not highlighted
    public void setUIState(SelectableUIState selectableUIState){
        if(selectableUIState != this.uiState){
            float previousWidth  = this.getWidth();
            float previousHeight = this.getHeight();
            switch (selectableUIState){
                case UNSELECTED:
                    this.setTextureRegion(unselectedTexture);
                    break;
                case SELECTED:
                    this.setTextureRegion(selectedTexture);
                    break;
                case UNSELECTEDHOVERED:
                    this.setTextureRegion(unselectedHoveredTexture);
                    break;
                case UNSELECTEDPRESSED:
                    this.setTextureRegion(unselectedPressedTexture);
                    break;
                case SELECTEDHOVERED:
                    this.setTextureRegion(selectedHoveredTexture);
                    break;
                case SELECTEDPRESSED:
                    this.setTextureRegion(selectedPressedTexture);
                    break;
                case NOTACTIVATED:
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
                case UNSELECTED:
                    this.setTextureRegion(unselectedTexture);
                    break;
                case SELECTED:
                    this.setTextureRegion(selectedTexture);
                    break;
                case NOTACTIVATED:
                    this.setTextureRegion(notActivatedTexture);
                    break;
            }
            this.selectableState = selectableState;
        }
    }

    /***
     * actions when it has been selected
     */
    public void select(){
        //need to be implemented
    }

    /***
     * enum for selectable ui state
     */
    public enum SelectableUIState {
        UNSELECTED,
        SELECTED,
        UNSELECTEDHOVERED,
        UNSELECTEDPRESSED,
        SELECTEDHOVERED,
        SELECTEDPRESSED,
        NOTACTIVATED
    }

    /***
     * enum for selectable state
     */
    public enum SelectableState {
        UNSELECTED, SELECTED, NOTACTIVATED
    }
}
