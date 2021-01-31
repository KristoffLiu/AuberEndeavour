package com.team23.game.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;

public class SaveManager {
    public static SaveManager current;
    public JsonModel model;
    private String filePath;

    public SaveManager(String filePath){
        current = this;
        this.filePath = filePath;
        loadFromFile();
    }

    public void add(Save newSave){
        int maxId = 0;
        for(Save save : model.saves){
            if(save.id > maxId){
                maxId = save.id;
            }
        }
        newSave.id = maxId + 1;
        this.model.saves.add(newSave);
    }

    public void delete(int index){
        this.model.saves.remove(index);
    }

    public void delete(Save deletingSave){
        this.model.saves.remove(deletingSave);
    }

    public void loadFromFile(){
        Json json = new Json();
        FileHandle file = Gdx.files.local(filePath);
        String jsonStr = file.readString();
        model = json.fromJson(JsonModel.class, jsonStr);
        if(model == null){
            model = new JsonModel();
        }
    }

    public void saveToFile(){
        Json json = new Json();
        String jsonStr = json.prettyPrint(model);
        FileHandle file = Gdx.files.local(filePath);
        file.writeString(jsonStr,false);
    }
}
