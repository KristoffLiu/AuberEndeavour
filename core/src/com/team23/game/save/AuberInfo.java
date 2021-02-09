package com.team23.game.save;

import com.team23.game.actors.characters.Auber;
import com.team23.game.utils.Position;

/***
 * Save Object for the auber
 */
public class AuberInfo extends CharacterInfo {
    public AuberInfo(){
    }

    public AuberInfo(Auber auber){
        this.position = Position.fromVector2(auber.getPosition());
        this.moveSpeed = auber.getMovSpeed();
    }
}
