package com.team23.game.save;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Save {
    public int id;
    public String name;
    public String dateTime;

    public Save(int id, String name){
        this.id = id;
        this.name = name;
        Date date = new Date();
        System.out.println(date.toString());
        this.dateTime = date.toString();
    }

    public Save(int id, String name, String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id = id;
        this.name = name;
        this.dateTime = date;
    }
}


