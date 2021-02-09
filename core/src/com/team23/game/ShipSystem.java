package com.team23.game;

import com.badlogic.gdx.math.Vector2;
import com.team23.game.ai.graph.PathGraph;
import com.team23.game.save.ShipSystemInfo;

/***
 * Ship System
 * the class for providing the systems of the whole ship.
 */
public class ShipSystem {
    public float x;
    public float y;
    public int state;
    public String room;
    public PathGraph graph;

    /**
     * constructor
     * @param x the main game screen
     * @param y the main game screen
     * @param room the main game screen
     * @param graph the main game screen
     ***/
    public ShipSystem(float x,float y, String room, PathGraph graph){
        this.x=x;
        this.y=y;
        this.room=room;
        this.state=0;
        this.graph = graph;
    }

    public ShipSystem(ShipSystemInfo shipSystemInfo, PathGraph graph) {
        this(shipSystemInfo.x, shipSystemInfo.y, shipSystemInfo.room, graph);
        this.state = shipSystemInfo.state;
    }

    /**
     * Creates all objects that the sprites can interactive with
     * @param state the main game screen
     */
    public void setState(int state){
        //state 0= operational, state 1=under attack, state 2= not operational
        this.state=state;
    }
    public int getState(){
        return state;
    }
    public String getRoom(){
        return room;
    }
    public Vector2 getPosition(){
        return new Vector2(x,y);
    }

    /***
     * Creates all objects that the sprites can interactive with
     * @param the main game screen
     */
    public void destroy(){
        state = 2;
        graph.getNearestNode(getPosition()).setWorking(false);
    }
    public void startAttack(){
        state = 1;
    }
    public void stopAttack(){
        state = 0;
    }

    @Override
    public String toString() {
        return getPosition().toString() + room;
    }
}
