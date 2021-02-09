package com.team23.game.save;

import java.util.ArrayList;

/***
 * the model of json for us to store the data
 */
public class JsonModel {
    public ArrayList<Save> saves;
    public JsonModel(){
        saves = new ArrayList<>();
    }
}
