package com.team23.game.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;

public class SaveManager {
    public static SaveManager current;
    public Model model;
    private String filePath;

    public SaveManager(String filePath){
        current = this;
        this.filePath = filePath;
        loadFromFile();
    }

    public class Model {
        public ArrayList<Save> saves;
    }

    public void add(Save newSave){
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
        FileHandle file = Gdx.files.internal(filePath);
        String jsonStr = file.readString();
        model = json.fromJson(Model.class, jsonStr);
    }

    public void saveToFile(){
        Json json = new Json();
        String jsonStr = json.toJson(model);
        FileHandle file = Gdx.files.internal(filePath);
        file.writeString(jsonStr,false);
    }
}
