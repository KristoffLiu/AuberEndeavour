package com.team23.game.utils;

import com.badlogic.gdx.math.Vector2;

/***
 * Class for position (basically a wrapper for vector2)
 */
public class Position {
    public float x;
    public float y;

    public Position(){
    }

    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2 toVector2(){
        return new Vector2(x,y);
    }

    public static Position fromVector2(Vector2 vector2){
        return new Position(vector2.x, vector2.y);
    }
}
