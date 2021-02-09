package com.team23.game.ui.minimap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.team23.game.GameEntry;
import com.team23.game.screens.playscreen.PlayScreen;
import com.team23.game.ui.controls.Image;
import com.team23.game.ui.controls.TextBlock;
import com.team23.game.ui.controls.UIElement;
import com.team23.game.ui.pages.UIPage;

import java.util.ArrayList;

/***
 * Teleport Page
 *
 * @author Zhikang Liu
 */
public class TeleportPage extends UIPage {
    public PlayScreen playScreen;
    public ArrayList<ZoneClickButton> zoneClickButtons;
    public Image image;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TiledMap map;
    private TmxMapLoader loader;
    private float scale = 2f;
    private TextBlock textBlock;

    Array<RectangleMapObject> rooms;

    public TeleportPage(PlayScreen playScreen){
        this.playScreen = playScreen;
        loader      = new TmxMapLoader();
        map         = loader.load("MiniMap.tmx");

        OrthographicCamera orthographicCamera = new OrthographicCamera();
        orthographicCamera.zoom = 1f;
        orthographicCamera.position.set(new Vector3(400 * this.scale,504 * this.scale,0));
        orthographicCamera.viewportWidth = 1920f;
        orthographicCamera.viewportHeight = 1080f;
        orthographicCamera.update();
        this.orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(this.map, this.scale);
        orthogonalTiledMapRenderer.setView(orthographicCamera);
        setRooms();

        image = new Image();
        image.setTextureRegion(new TextureRegion(new Texture("minimapbg.png")));
        image.setSize(2500,1600);

        textBlock = new TextBlock();

        textBlock.setText("Transport");
        textBlock.setRelativePosition(10,10);
        textBlock.setFontColor(1,1,1,1);

        this.addUIElement(image);
    }

    public void setRooms(){
        rooms = new Array<>();
        MapLayer blocksLayer = this.map.getLayers().get("rooms");
        for(MapObject mapObject : blocksLayer.getObjects()){
            rooms.add((RectangleMapObject) mapObject);
        }
    }

    public Array<RectangleMapObject> getRooms(){
        return this.rooms;
    }

    @Override
    public void draw(){
        super.draw();
        orthogonalTiledMapRenderer.render();
        this.textBlock.draw();
    }
}
