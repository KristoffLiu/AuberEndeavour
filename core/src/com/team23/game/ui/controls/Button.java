package com.team23.game.ui.controls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.team23.game.ui.UIStage;

/**
 * A button class which extends from ClickableUIElement class
 */
public class Button extends ClickableUIElement implements IButton{

    /**
     * Constructor of a Button
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param parentUIStage
     */
    public Button(UIStage parentUIStage) {
        super(parentUIStage);
    }

    /**
     * Constructor of a Button
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param parentUIStage
     * @param texture
     */
    public Button(UIStage parentUIStage, TextureRegion texture) {
        super(parentUIStage, texture, texture, texture, texture);
    }

    /**
     * Constructor of a Button
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     * the pressed parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param uiParent
     * @param normalTexture
     * @param pressedTexture
     */
    public Button(Object uiParent,
                  TextureRegion normalTexture,
                  TextureRegion pressedTexture) {
        super(uiParent, normalTexture, pressedTexture,
                pressedTexture, null);
    }

    /**
     * Constructor of a Button
     * the texture input will be its mapping texture.
     *
     * @param uiParent
     * @param normalTexture
     * @param hoveringTexture
     * @param pressedTexture
     */
    public Button(Object uiParent,
                  TextureRegion normalTexture, TextureRegion hoveringTexture,
                  TextureRegion pressedTexture, TextureRegion notActivatedTexture) {
        super(uiParent, normalTexture, hoveringTexture, pressedTexture, notActivatedTexture);
    }

}
