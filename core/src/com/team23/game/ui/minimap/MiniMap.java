package com.team23.game.ui.minimap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.team23.game.GameEntry;
import com.team23.game.ShipSystem;
import com.team23.game.actors.CustomActor;
import com.team23.game.screens.playscreen.PlayScreen;
import com.team23.game.ui.pages.UIPage;

import java.util.ArrayList;
import java.util.Hashtable;

/***
 * Mini Map
 * the class for constructing the minimap
 *
 * @author Zhikang Liu
 */
public class MiniMap extends UIPage {
    private TiledMap map;
    private TmxMapLoader loader;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    public ArrayList<ZoneClickButton> zoneClickButtons;
    Array<TextureMapObject> rooms;

    public MiniMap(){
        loader      = new TmxMapLoader();
        map         = loader.load("MiniMap.tmx");
        this.orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(map,1.0f);

        setRooms();
    }

    public void setRooms(){
        rooms = new Array<>();
        MapLayer blocksLayer = this.map.getLayers().get("rooms");
        for(MapObject mapObject : blocksLayer.getObjects()){
            rooms.add((TextureMapObject) mapObject);
        }
    }

    public Array<TextureMapObject> getRooms(){
        return this.rooms;
    }

    public void render(){
        orthogonalTiledMapRenderer.render();
    }
}
