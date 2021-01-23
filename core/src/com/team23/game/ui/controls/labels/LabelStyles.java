package com.team23.game.ui.controls.labels;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelStyles {

    public static Label.LabelStyle usingImpactFontStyle(boolean isClearer,
                                                        float scale,
                                                        float r, float g, float b, float a){
        return LabelStylesHelper.generateLabelStyle(  "font/ImpactFont.fnt",
                isClearer,
                scale,
                r,g,b,a);
    }



    public static Label.LabelStyle getGameTitleLabelStyle(){
        return LabelStylesHelper.generateLabelStyle(  "font/ImpactFont.fnt",
                true,
                2.0f,
                1,1,1,1);
    }

    public static Label.LabelStyle getHeadlineStyle(){
        return LabelStylesHelper.generateLabelStyle(  "font/ImpactFont.fnt",
                true,
                2.0f,
                1,1,1,1);
    }
}
