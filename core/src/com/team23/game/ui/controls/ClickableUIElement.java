package com.team23.game.ui.controls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.team23.game.ui.UIElement;

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
    ButtonUIState buttonUIState = com.team23.game.ui.controls.Button.ButtonUIState.normal;

    public ClickableUIElement(Object uiParent) {
        super(uiParent);
        isEnabled = true;

        clickableUIElementClickListener = new ClickableUIElementClickListener();
        this.addListener(clickableUIElementClickListener);
    }

    public ClickableUIElement(Object uiParent,
                              TextureRegion normalTexture, TextureRegion hoveredTexture,
                              TextureRegion pressedTexture, TextureRegion notActivatedTexture) {
        super(uiParent, normalTexture);

        this.normalTexture = normalTexture;
        this.hoveredTexture = hoveredTexture;
        this.pressedTexture = pressedTexture;
        this.notActivatedTexture = notActivatedTexture;
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
            case normal:
                this.setTextureRegion(normalTexture);
                break;
            case hovered:
                this.setTextureRegion(hoveredTexture);
                break;
            case pressed:
                this.setTextureRegion(pressedTexture);
                break;
            case notActivated:
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
            setButtonUIState(ButtonUIState.normal);
        }
        else {
            setButtonUIState(ButtonUIState.notActivated);
        }
    }

    //Switches the texture of the button to be highlighted or not highlighted
    public void setButtonUIState(com.team23.game.ui.controls.Button.ButtonUIState buttonUIStateInput){
        if(buttonUIStateInput != this.buttonUIState){
            float previousWidth  = this.getWidth();
            float previousHeight = this.getHeight();
            switch (buttonUIStateInput){
                case normal:
                    this.setTextureRegion(normalTexture);
                    break;
                case hovered:
                    this.setTextureRegion(hoveredTexture);
                    break;
                case pressed:
                    this.setTextureRegion(pressedTexture);
                    break;
                case notActivated:
                    this.setTextureRegion(notActivatedTexture);
                    break;
            }
            this.buttonUIState = buttonUIStateInput;
            this.setWidth(previousWidth);
            this.setHeight(previousHeight);
        }
    }

    public enum ButtonUIState{
        normal, hovered, pressed, notActivated
    }
}

