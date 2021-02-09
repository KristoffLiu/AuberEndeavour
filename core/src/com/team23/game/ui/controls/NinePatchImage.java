package com.team23.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/***
 * class of a Nine Patch Image, which is inherited from a UI Element.
 */
public class NinePatchImage extends UIElement {

    NinePatch   ninePatch;
    float       ninePatchX;
    float       ninePatchY;
    float       ninePatchWidth;
    float       ninePatchHeight;

    /**
     * constructor
     ***/
    public NinePatchImage(TextureRegion textureRegion,
                          int left, int right, int top, int bottom) {
        super();
        setImage(textureRegion, left, right, top, bottom);
    }

    /**
     * set the image
     ***/
    public void setImage(TextureRegion textureRegion,
                               int left, int right, int top, int bottom){
        ninePatch = new NinePatch(textureRegion, left, right, top, bottom);
    }

    /**
     * set the coordination of X
     ***/
    @Override
    public float getX(){
        return ninePatchX;
    }

    /**
     * get the coordination of Y
     ***/
    @Override
    public float getY(){
        return ninePatchY;
    }

    /**
     * set the coordination of X
     ***/
    @Override
    public void setX(float x){
        ninePatchX = x;
    }

    /**
     * set the coordination of Y
     ***/
    @Override
    public void setY(float y){
        ninePatchY = y;
    }

    /**
     * set the NinePatch
     ***/
    public void setNinePatch(float leftWidth, float middleWidth, float rightWidth,
                             float topHeight, float middleHeight, float bottomHeight){
        ninePatch.setLeftWidth(leftWidth);
        ninePatch.setMiddleWidth(middleWidth);
        ninePatch.setRightWidth(rightWidth);
        ninePatch.setTopHeight(topHeight);
        ninePatch.setMiddleHeight(middleHeight);
        ninePatch.setBottomHeight(bottomHeight);
    }

    /**
     * draw
     ***/
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (batch == null || !isVisible()) {
            return;
        }
        ninePatch.draw(
                batch,
                ninePatchX,
                ninePatchY,
                ninePatchWidth,
                ninePatchHeight
        );
    }
}
