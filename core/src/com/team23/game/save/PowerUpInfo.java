package com.team23.game.save;

import com.team23.game.actors.items.PowerUp;
import com.team23.game.utils.Position;

public class PowerUpInfo extends CharacterInfo {
    public String name;
    public boolean isActivated;

    public PowerUpInfo(){
    }

    public PowerUpInfo(PowerUp powerUp){
        this.position = Position.fromVector2(powerUp.getPosition());
        this.isActivated = powerUp.isActivated();
        this.name = powerUp.getName();
    }
}
