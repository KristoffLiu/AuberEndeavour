package com.team23.game.ui.controls.labels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class LabelStylesHelper {

    public static LabelStyle generateLabelStyle(String path,
                                                boolean isClearer,
                                                float scale,
                                                float r, float g, float b, float a){
        BitmapFont bitmapFont = getBitmapFontFromAssets();
        if (isClearer) {
            bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        bitmapFont.getData().setScale(scale);

        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = new Color(r,g,b,a);
        return labelStyle;
    }

    public static LabelStyle generateLabelStyle(String path,
                                                boolean isClearer,
                                                float scale,
                                                Color color){
        BitmapFont bitmapFont = getBitmapFontFromAssets();
        if (isClearer) {
            bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        bitmapFont.getData().setScale(scale);

        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = color;
        return labelStyle;
    }

    public static BitmapFont getBitmapFontFromAssets(){
        return new BitmapFont(Gdx.files.internal("font/ImpactFont.fnt"));
    }

    public static void setBitmapFontScale(BitmapFont font, float scaleFactor){
        font.getData().setScale(scaleFactor);
    }

    public static LabelStyle setScale(LabelStyle style, float scaleFactor){
        style.font.getData().setScale(scaleFactor);
        return style;
    }

    public static void setBitmapFontColor(BitmapFont font, float r, float g, float b, float a){
        font.setColor(r,g,b,a);
    }
}
