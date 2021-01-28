package com.team23.game.ui;

public class Thickness {
    public float left;
    public float right;
    public float top;
    public float bottom;

    public Thickness(float uniformLength){
        this.left   = uniformLength;
        this.right  = uniformLength;
        this.top    = uniformLength;
        this.bottom = uniformLength;
    }

    public Thickness(float left, float right, float top, float bottom){
        this.left   = left;
        this.right  = right;
        this.top    = top;
        this.bottom = bottom;
    }
}
