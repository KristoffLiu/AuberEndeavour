package com.team23.game.save;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * Save Class
 * Save data including: Player position, NPC position, Sabotaged systems, Abilities on NPC and on player
 */
public class Save {
    public int id;
    public String name;
    public String dateTime;
    public Player player;

    public Save(String name){
        this.name = name;
        Date date = new Date();
        System.out.println(date.toString());
        this.dateTime = date.toString();
    }

    public Save(){
    }

}


