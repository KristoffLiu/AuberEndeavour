package com.team23.game.save;

import com.team23.game.ShipSystem;

public class ShipSystemInfo {
    public float x;
    public float y;
    public int state;
    public String room;

    public ShipSystemInfo(){

    }

    public ShipSystemInfo(ShipSystem shipSystem){
        this.x = shipSystem.x;
        this.y = shipSystem.y;
        this.state = shipSystem.state;
        this.room = shipSystem.room;
    }
}
