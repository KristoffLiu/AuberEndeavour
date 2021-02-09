package com.team23.game.ui.controls.labels;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelStyles {

    private static String font = "font/ImpactFont.fnt";

    public static Label.LabelStyle usingImpactFontStyle(boolean isClearer,
                                                        float scale,
                                                        float r, float g, float b, float a){
        return LabelStylesHelper.generateLabelStyle(  font,
                isClearer,
                scale,
                r,g,b,a);
    }



    public static Label.LabelStyle getGameTitleLabelStyle(){
        return LabelStylesHelper.generateLabelStyle(  font,
                true,
                2.0f,
                1,1,1,1);
    }

    public static Label.LabelStyle getHeadlineStyle(){
        return LabelStylesHelper.generateLabelStyle(  font,
                true,
                2.0f,
                1,1,1,1);
    }
}
