package com.team23.game.save;

import com.team23.game.actors.characters.Auber;
import com.team23.game.actors.characters.Infiltrator;
import com.team23.game.actors.characters.NPC;
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
    public ArrayList<SystemInfo> systemInfoList;

    public Save(String name){
        this.name = name;
        Date date = new Date();
        System.out.println(date.toString());
        this.dateTime = date.toString();
        auberInfo = new AuberInfo();
        npcsInfoList = new ArrayList<>();
        systemInfoList = new ArrayList<>();
    }

    public Save(){

    }

    public Auber getAuber(){
        return new Auber(this.auberInfo);
    }

    public ArrayList<NPC> getNPCsList(PathGraph pathGraph){
        ArrayList<NPC> results = new ArrayList<>();
        for(NPCInfo npcInfo : npcsInfoList){
            results.add(new NPC(npcInfo, pathGraph));
        }
        return results;
    }

    public ArrayList<Infiltrator> getEnemyList(PathGraph pathGraph){
        ArrayList<Infiltrator> results = new ArrayList<>();
        for(EnemyInfo enemyInfo : enemiesInfoList){
            results.add(new Infiltrator(enemyInfo, pathGraph));
        }
        return results;
    }

    public void setAuberInfo(Auber auber){
        this.auberInfo = new AuberInfo(auber);
    }

    public void setNpcsInfoList(ArrayList<NPC> arrayList){
        npcsInfoList = new ArrayList<>();
        for(NPC npc : arrayList){
            npcsInfoList.add(new NPCInfo(npc));
        }
    }

    public void setEnemiesInfoList(ArrayList<Infiltrator> arrayList){
        enemiesInfoList = new ArrayList<>();
        for(Infiltrator infiltrator : arrayList){
            enemiesInfoList.add(new EnemyInfo(infiltrator));
        }
    }
}


