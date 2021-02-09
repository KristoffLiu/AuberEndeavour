package com.team23.game.ui.controls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;

/***
 * ClickableUIElement
 * class of a Clickable UI Element.
 *
 */
public class ClickableUIElement extends UIElement {
    public TextureRegion normalTexture         ;
    public TextureRegion hoveredTexture        ;
    public TextureRegion pressedTexture        ;
    public TextureRegion notActivatedTexture   ;
    boolean isEnabled;

    ClickableUIElementClickListener clickableUIElementClickListener;
    ButtonUIState buttonUIState = com.team23.game.ui.controls.Button.ButtonUIState.NORMAL;

    public ClickableUIElement() {
        super();
        isEnabled = true;

        clickableUIElementClickListener = new ClickableUIElementClickListener();
        this.addListener(clickableUIElementClickListener);
    }

    /**
     * set the texture of the button
     *
     * @param normalTexturePath
     * @param hoveredTexturePath
     * @param pressedTexturePath
     * @param notActivatedTexturePath
     */
    public void setTextures(String normalTexturePath, String hoveredTexturePath,
                            String pressedTexturePath, @Null String notActivatedTexturePath){
        this.normalTexture       = !normalTexturePath.isEmpty() ? new TextureRegion(new Texture(normalTexturePath)) : null;
        this.hoveredTexture      = !hoveredTexturePath.isEmpty() ? new TextureRegion(new Texture(hoveredTexturePath)) : null;
        this.pressedTexture      = !pressedTexturePath.isEmpty() ? new TextureRegion(new Texture(pressedTexturePath)) : null;
        this.notActivatedTexture = !notActivatedTexturePath.isEmpty() ? new TextureRegion(new Texture(notActivatedTexturePath)) : null;
        switch (this.buttonUIState){
            case NORMAL:
                this.setTextureRegion(normalTexture);
                break;
            case HOVERED:
                this.setTextureRegion(hoveredTexture);
                break;
            case PRESSED:
                this.setTextureRegion(pressedTexture);
                break;
            case NOTACTIVATED:
                this.setTextureRegion(notActivatedTexture);
                break;
        }
    }

    public void setClickListener(ClickableUIElementClickListener clickListener){
        this.removeListener(clickableUIElementClickListener);
        clickableUIElementClickListener = clickListener;
        this.addListener(clickableUIElementClickListener);
    }

    public void isEnabled(boolean isEnabled){
        this.isEnabled = isEnabled;
        if(isEnabled){
            setButtonUIState(ButtonUIState.NORMAL);
        }
        else {
            setButtonUIState(ButtonUIState.NOTACTIVATED);
        }
    }

    //Switches the texture of the button to be highlighted or not highlighted
    public void setButtonUIState(com.team23.game.ui.controls.Button.ButtonUIState buttonUIStateInput){
        if(buttonUIStateInput != this.buttonUIState){
            float previousWidth  = this.getWidth();
            float previousHeight = this.getHeight();
            switch (buttonUIStateInput){
                case NORMAL:
                    this.setTextureRegion(normalTexture);
                    break;
                case HOVERED:
                    this.setTextureRegion(hoveredTexture);
                    break;
                case PRESSED:
                    this.setTextureRegion(pressedTexture);
                    break;
                case NOTACTIVATED:
                    this.setTextureRegion(notActivatedTexture);
                    break;
            }
            this.buttonUIState = buttonUIStateInput;
            this.setWidth(previousWidth);
            this.setHeight(previousHeight);
        }
    }

    public enum ButtonUIState{
        NORMAL, HOVERED, PRESSED, NOTACTIVATED
    }
}

