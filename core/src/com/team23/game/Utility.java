package com.team23.game;

import com.badlogic.gdx.math.Vector2;

public class Utility {
    private static final float DISTANCE_BUFFER = 10;

    public static final String[] rooms = {"brig","crew","infirmary","laboratory","command","engine"};

    /***
     * Gives a random positive integer less than n
     * @param n The integer that the generated integer will be less than
     * @return Integer less than n
     */
    public static int randomIntBelow(int n){
        return (int)(Math.floor(Math.random()*n));
    }

    /***
     * Determines if two positions are close enough to each other to be handled as the same position
     * @param pos1 Coordinates of one of the positions
     * @param pos2 Coordinates of one of the positions
     * @return True if the positions are close enough, false otherwise
     */
    public static boolean closeEnough(Vector2 pos1,Vector2 pos2){
        if(pos1.dst(pos2)< DISTANCE_BUFFER){
            return true;
        }
        return false;
    }
}
