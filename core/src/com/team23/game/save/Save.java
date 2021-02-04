package com.team23.game.save;

import java.util.ArrayList;
import java.util.Date;

/***
 * Save Class
 * Save data including: Player position, NPC position, Sabotaged systems, Abilities on NPC and on player
 */
public class Save {
    public int id;
    public String name;
    public String dateTime;
    public CharacterInfo playerInfo;
    public ArrayList<CharacterInfo> npcsInfoList;
    public ArrayList<CharacterInfo> enemiesInfoList;
    public ArrayList<SystemInfo> systemInfoList;

    public Save(String name){
        this.name = name;
        Date date = new Date();
        System.out.println(date.toString());
        this.dateTime = date.toString();
        playerInfo = new CharacterInfo();
        npcsInfoList = new ArrayList<>();
        systemInfoList = new ArrayList<>();
    }

    public Save(){

    }
}


