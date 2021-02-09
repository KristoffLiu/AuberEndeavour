package com.team23.game.save;

import com.team23.game.ShipSystem;
import com.team23.game.actors.characters.Auber;
import com.team23.game.actors.characters.Infiltrator;
import com.team23.game.actors.characters.NPC;
import com.team23.game.actors.items.PowerUp;
import com.team23.game.ai.graph.PathGraph;

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
    public AuberInfo auberInfo;
    public ArrayList<NPCInfo> npcsInfoList;
    public ArrayList<EnemyInfo> enemiesInfoList;
    public ArrayList<PowerUpInfo> powerUpInfoList;
    public ArrayList<ShipSystemInfo> shipSystemInfoList;

    /***
     * Constructor
     * @param name
     */
    public Save(String name){
        this.name = name;
        Date date = new Date();
        this.dateTime = date.toString();
        auberInfo = new AuberInfo();
        npcsInfoList = new ArrayList<>();
        shipSystemInfoList = new ArrayList<>();
    }

    /***
     * Save
     */
    public Save(){

    }

    /***
     * get the Auber
     */
    public Auber getAuber(){
        return new Auber(this.auberInfo);
    }

    /***
     * get the NPCs List
     */
    public ArrayList<NPC> getNPCsList(PathGraph pathGraph){
        ArrayList<NPC> results = new ArrayList<>();
        for(NPCInfo npcInfo : npcsInfoList){
            results.add(new NPC(npcInfo, pathGraph));
        }
        return results;
    }

    /***
     * get the Enemy List
     */
    public ArrayList<Infiltrator> getEnemyList(PathGraph pathGraph){
        ArrayList<Infiltrator> results = new ArrayList<>();
        for(EnemyInfo enemyInfo : enemiesInfoList){
            results.add(new Infiltrator(enemyInfo, pathGraph));
        }
        return results;
    }

    /***
     * get the Power Up list
     */
    public ArrayList<PowerUp> getPowerUpList(){
        ArrayList<PowerUp> results = new ArrayList<>();
        for(PowerUpInfo powerUpInfo : this.powerUpInfoList){
            results.add(new PowerUp(powerUpInfo));
        }
        return results;
    }

    /***
     * get the Power Up list
     */
    public ArrayList<ShipSystem> getSystemList(PathGraph pathGraph){
        ArrayList<ShipSystem> results = new ArrayList<>();
        for(ShipSystemInfo shipSystemInfo : this.shipSystemInfoList){
            results.add(new ShipSystem(shipSystemInfo, pathGraph));
        }
        return results;
    }

    /***
     * set the auber info
     */
    public void setAuberInfo(Auber auber){
        this.auberInfo = new AuberInfo(auber);
    }

    /***
     * set the npcs info list
     */
    public void setNpcsInfoList(ArrayList<NPC> arrayList){
        npcsInfoList = new ArrayList<>();
        for(NPC npc : arrayList){
            npcsInfoList.add(new NPCInfo(npc));
        }
    }

    /***
     * set the enemies info list
     */
    public void setEnemiesInfoList(ArrayList<Infiltrator> arrayList){
        enemiesInfoList = new ArrayList<>();
        for(Infiltrator infiltrator : arrayList){
            enemiesInfoList.add(new EnemyInfo(infiltrator));
        }
    }

    /***
     * set the power up info list
     */
    public void setPowerUpInfoList(ArrayList<PowerUp> arrayList){
        powerUpInfoList = new ArrayList<>();
        for(PowerUp powerUp : arrayList){
            powerUpInfoList.add(new PowerUpInfo(powerUp));
        }
    }

    /***
     * set the ship system info list
     */
    public void setShipSystemInfoList(ArrayList<ShipSystem> arrayList){
        shipSystemInfoList = new ArrayList<>();
        for(ShipSystem shipSystem : arrayList){
            shipSystemInfoList.add(new ShipSystemInfo(shipSystem));
        }
    }
}


