package com.team23.game.save;

import com.team23.game.utils.Position;

/***
 * Save Object for the Character
 */
public class CharacterInfo {
    public Position position;
    public float moveSpeed;

    public CharacterInfo(){
        this.position = new Position();
    }
}
