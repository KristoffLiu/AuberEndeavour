package com.team23.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.team23.game.ui.UIElement;
import com.team23.game.ui.UIStage;

public class NinePatchImage extends UIElement {

    NinePatch ninePatch         ;
    float ninePatch_X           ;
    float ninePatch_Y           ;
    float ninePatch_width       ;
    float ninePatch_height      ;

    public NinePatchImage(TextureRegion textureRegion,
                          int left, int right, int top, int bottom) {
        super(textureRegion);
        setImage(textureRegion, left, right, top, bottom);
    }

    public NinePatchImage(UIStage stage, TextureRegion textureRegion,
                          int left, int right, int top, int bottom) {
        super(stage, textureRegion);
        setImage(textureRegion, left, right, top, bottom);
    }

    public void setImage(TextureRegion textureRegion,
                               int left, int right, int top, int bottom){
        ninePatch = new NinePatch(textureRegion, left, right, top, bottom);
    }

    @Override
    public float getX(){
        return ninePatch_X;
    }

    @Override
    public float getY(){
        return ninePatch_Y;
    }

    @Override
    public void setX(float x){
        ninePatch_X = x;
    }

    @Override
    public void setY(float y){
        ninePatch_Y = y;
    }

    public void setNinePatch(float leftWidth, float middleWidth, float rightWidth,
                             float topHeight, float middleHeight, float bottomHeight){
        ninePatch.setLeftWidth(leftWidth);
        ninePatch.setMiddleWidth(middleWidth);
        ninePatch.setRightWidth(rightWidth);
        ninePatch.setTopHeight(topHeight);
        ninePatch.setMiddleHeight(middleHeight);
        ninePatch.setBottomHeight(bottomHeight);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (batch == null || !isVisible()) {
            return;
        }
        ninePatch.draw(
                batch,
                ninePatch_X,
                ninePatch_Y,
                ninePatch_width,
                ninePatch_height
        );
    }
}
