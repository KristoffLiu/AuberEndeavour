package com.team23.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.team23.game.ui.UIElement;

public class Image extends UIElement {

    public Image(TextureRegion textureRegion) {
        super(textureRegion);
    }

    public Image(Object parent, TextureRegion textureRegion) {
        super(parent, textureRegion);
    }
}
