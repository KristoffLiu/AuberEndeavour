package com.team23.game;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.team23.game.screens.playscreen.PlayScreen;

import java.util.ArrayList;
import java.util.Hashtable;

/***
 * TileWorld
 * the class for reading tiled-map file.
 *
 * @author Zhikang Liu
 */
public class TileWorld {
    private Hashtable<String,Rectangle> teleporters;
    private ArrayList<ShipSystem> shipSystems;
    private ArrayList<Rectangle> collisionBoxes;


    private Rectangle infirmary;
    private Rectangle brig;
    private Rectangle crew;
    private Rectangle command;
    private Rectangle laboratory;
    private Rectangle engine;
    private ArrayList<RectangleMapObject> teleporterRectangleMapObjects;
    private int scale;


    /**
     * Creates all objects that the sprites can interactive with
     * @param screen the main game screen
     */
    public TileWorld(PlayScreen screen){

        TiledMap map= screen.getMap();
        this.scale= GameEntry.ZOOM;
        createRooms(map);


        // create systems
        shipSystems = new ArrayList<>();
        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = magnifyRectange(((RectangleMapObject) object).getRectangle());
            shipSystems.add(new ShipSystem(rect.x,rect.y,getRoom(rect.x,rect.y),screen.graph));
        }

        //create objects
        collisionBoxes=new ArrayList<>();

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = magnifyRectange(((RectangleMapObject) object).getRectangle());
            collisionBoxes.add(rect);
        }

        //create teleporters
        teleporters = new Hashtable<String,Rectangle>();
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            rect.x=rect.x*scale+rect.width*scale/2;
            rect.y=rect.y*scale+rect.height*scale/2;
            rect.width=25;
            rect.height=25;
            teleporters.put(getRoom(rect.x,rect.y),rect);
        }

        //walls
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = magnifyRectange(((RectangleMapObject) object).getRectangle());
            collisionBoxes.add(rect);
        }

        teleporterRectangleMapObjects = new ArrayList<>();
        MapLayer teleporterLayer = map.getLayers().get("teleporters");
        for(MapObject mapObject : teleporterLayer.getObjects()){
            teleporterRectangleMapObjects.add((RectangleMapObject) mapObject);
        }

        //create
    }

    public void setShipSystems(ArrayList<ShipSystem> arrayList){
        this.shipSystems = arrayList;
    }

    /**
     * Sets rectangle bounds for each room
     * @param map The game's tile map
     */
    private void createRooms(TiledMap map){
        MapObject roomObj=new MapObject();
        roomObj=map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class).get(0);
        infirmary = magnifyRectange(((RectangleMapObject) roomObj).getRectangle());
        roomObj=map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class).get(0);
        brig = magnifyRectange(((RectangleMapObject) roomObj).getRectangle());
        roomObj=map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class).get(0);
        crew = magnifyRectange(((RectangleMapObject) roomObj).getRectangle());
        roomObj=map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class).get(0);
        laboratory = magnifyRectange(((RectangleMapObject) roomObj).getRectangle());
        roomObj=map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class).get(0);
        command = magnifyRectange(((RectangleMapObject) roomObj).getRectangle());
        roomObj=map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class).get(0);
        engine = magnifyRectange(((RectangleMapObject) roomObj).getRectangle());
    }

    /**
     * Magnifies the bounds of the rectangle to fit with the zoom of screen
     * @param rect The bounds being enlarged
     * @return The magnified bounds
     */
    private Rectangle magnifyRectange(Rectangle rect){
        rect.x=rect.x*scale;
        rect.y=rect.y*scale;
        rect.width=rect.width*scale;
        rect.height=rect.height*scale;
        return rect;
    }

    /**
     * get the teleporters.
     * @return the
     */
    public Hashtable<String, Rectangle> getTeleporters(){
        return teleporters;
    }

    /**
     * get the teleporters.
     * @return the
     */
    public Rectangle getTeleporterRectangle(String name){
        Rectangle rectangle = new Rectangle();
        for(RectangleMapObject mapObject : this.teleporterRectangleMapObjects){
            String mapObjectName = mapObject.getName();
            if(mapObject.getName().equals(name)){
                rectangle = mapObject.getRectangle();
            }
        }
        return rectangle;
    }

    /**
     * get the infirmary.
     */
    public Rectangle getInfirmary(){return infirmary;}

    /**
     * get the systems from the tiled map.
     */
    public ArrayList<ShipSystem> getSystems(){
        return shipSystems;
    }

    /**
     * get the boxes which will cause collision.
     */
    public ArrayList<Rectangle> getCollisionBoxes(){
            return collisionBoxes;
    }

    /**
     * Finds the room that a pair of coordinates are in
     * @param x The x coordinate
     * @param y The y coordinate
     * @return A string the representing the room the coordinates are in or "none" if they are not in a room
     */
    public String getRoom(float x, float y){
        if (infirmary.contains(x,y)){
            return "infirmary";
        }else if (command.contains(x,y)){
            return "command";
        }else if (laboratory.contains(x,y)){
            return "laboratory";
        }else if (brig.contains(x,y)){
            return "brig";
        }else if (crew.contains(x,y)){
            return  "crew";
        }else if (engine.contains(x,y)){
            return "engine";
        } else{
            return "none";
        }
    }
}
