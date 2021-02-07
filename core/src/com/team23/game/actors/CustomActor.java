package com.team23.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Custom Actor class extends from interface Actor.
 * This custom actor offers actor the feature of collision detection.
 *
 * - Zhikang Liu 2020/11/03
 */
public class CustomActor extends Actor {
    // TextureRegion of this Customer Actor
    private TextureRegion textureRegion;
    private final Rectangle Bounds = new Rectangle();

    public CustomActor() {
        super();
    }

    public CustomActor(TextureRegion textureRegion) {
        super();
        setTextureRegion(textureRegion);
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public Rectangle getBounds(){
        Bounds.set(
                this.getX(),
                this.getY(),
                this.getWidth(),
                this.getHeight()
        );
        return Bounds;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        // reset the width and height after resetting the texture region.

        if(this.getWidth() == 0 && this.getHeight() == 0){
            setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
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
        // Usually there's no more actions to deal with.
    }



    /**
     * RenderActor
     *
     * @param batch
     *
     * @param parentAlpha
     * 		The Sprite Batch, is used to render the texture of actor encapsulation.
     *
     * @param parentAlpha
     * 		the transparent of parent
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (textureRegion == null || !isVisible()) {
            return;
        }
        else if (textureRegion.getTexture() == null){
            return;
        }

        Color tempBatchColor = batch.getColor();
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(
                textureRegion,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );
    }
}
