package com.team23.game.ui;

public class Padding {
    public float left;
    public float right;
    public float top;
    public float bottom;

    public Padding(float uniformLength){
        this.left   = uniformLength;
        this.right  = uniformLength;
        this.top    = uniformLength;
        this.bottom = uniformLength;
    }

    public Padding(float left, float right, float top, float bottom){
        this.left   = left;
        this.right  = right;
        this.top    = top;
        this.bottom = bottom;
    }
}
