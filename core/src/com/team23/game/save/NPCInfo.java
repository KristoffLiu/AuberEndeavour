package com.team23.game.save;

import com.team23.game.actors.characters.Infiltrator;
import com.team23.game.actors.characters.NPC;
import com.team23.game.utils.Position;

public class NPCInfo extends CharacterInfo {
    public NPCInfo(){
    }

    public NPCInfo(NPC npc){
        this.position = Position.fromVector2(npc.getPosition());
        this.moveSpeed = npc.getMovSpeed();
    }
}
