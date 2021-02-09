package com.team23.game.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

/***
 * Save Manager Class
 * Save data including: Player position, NPC position, Sabotaged systems, Abilities on NPC and on player
 */
public class SaveManager {
    public static SaveManager current;
    public JsonModel model;

    private Save loadedSave;
    private String filePath;

    /***
     * Constructor
     */
    public SaveManager(String filePath){
        current = this;
        this.filePath = filePath;
        loadFromFile();
    }

    /***
     * add new save
     * @param newSave new save to be added
     */
    public void add(Save newSave){
        int maxId = 0;
        for(Save save : model.saves){
            if(save.id > maxId){
                maxId = save.id;
            }
        }
        newSave.id = maxId + 1;
        newSave.name = "Save " + newSave.id;
        this.model.saves.add(newSave);
        saveToFile();
    }

    /***
     * add new save
     * @param save new save to be added
     */
    public void saveCurrentGame(Save save){
        this.add(save);
        this.saveToFile();
    }

    /***
     * delete save
     * @param index delete the item at position with specific index .
     */
    public void delete(int index){
        this.model.saves.remove(index);
        saveToFile();
    }

    /***
     * delete save
     * @param deletingSave delete the item input.
     */
    public void delete(Save deletingSave){
        this.model.saves.remove(deletingSave);
        saveToFile();
    }

    /***
     * load from the file.
     */
    public void loadFromFile(){
        Json json = new Json();
        FileHandle file = Gdx.files.local(filePath);
        String jsonStr = file.readString();
        model = json.fromJson(JsonModel.class, jsonStr);
        if(model == null){
            model = new JsonModel();
        }
    }

    /***
     * save to the file
     */
    public void saveToFile(){
        Json json = new Json();
        String jsonStr = json.prettyPrint(model);
        FileHandle file = Gdx.files.local(filePath);
        file.writeString(jsonStr,false);
    }

    /***
     * get all the saves
     */
    public ArrayList<Save> getSaves(){
        return model.saves;
    }

    /***
     * get the save which is loaded to the running game.
     */
    public Save getLoadedSave() {
        return loadedSave;
    }

    /***
     * set the loaded save.
     */
    public void setLoadedSave(Save loadedSave) {
        this.loadedSave = loadedSave;
    }

}
