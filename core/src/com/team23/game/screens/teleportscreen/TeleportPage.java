package com.team23.game.screens.teleportscreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Array;
import com.team23.game.GameEntry;
import com.team23.game.screens.playscreen.PlayState;
import com.team23.game.ui.controls.*;
import com.team23.game.ui.pages.UIPage;

import java.util.ArrayList;

/***
 * Teleport Page
 *
 * @author Zhikang Liu
 */
public class TeleportPage extends UIPage {
    public Image image;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TiledMap map;
    private TmxMapLoader loader;
    private float scale;
    private TextBlock textBlock;

    Array<RectangleMapObject> rooms;

    /***
     * Constructor
     */
    public TeleportPage(){
        loader      = new TmxMapLoader();
        map         = loader.load("MiniMap.tmx");

        scale = 1.3f;
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
        textBlock.setText("Teleport Map");
        textBlock.setRelativePosition(30,30, UIElement.HorizontalAlignment.LEFT_ALIGNMENT, UIElement.VerticalAlignment.TOP_ALIGNMENT);
        textBlock.setFontColor(1,1,1,1);

        Button backButton = new Button();
        backButton.setTextures(
                "ui/CreateNewGamePage/backNormal.png",
                "ui/CreateNewGamePage/backHovered.png",
                "ui/CreateNewGamePage/backPressed.png",
                "");
        backButton.setRelativePosition(0,50, UIElement.HorizontalAlignment.LEFT_ALIGNMENT, UIElement.VerticalAlignment.BOTTOM_ALIGNMENT);
        backButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameEntry.current.setGameState(PlayState.PLAYING);
            }
        });

        ArrayList<Button> buttons = new ArrayList<>();
        ArrayList<TextBlock> roomTextBlocks = new ArrayList<>();
        for(RectangleMapObject rectangleMapObject : rooms){
            Rectangle buttonRect = rectangleMapObject.getRectangle();
            final Button button = new Button();
            button.setRelativePosition(
                    buttonRect.getX() * this.scale + 1920 / 2 - 400 * this.scale,
                    buttonRect.getY() * this.scale + 1080 / 2 - 504 * this.scale,
                    UIElement.HorizontalAlignment.LEFT_ALIGNMENT,
                    UIElement.VerticalAlignment.BOTTOM_ALIGNMENT);
            button.setSize(buttonRect.getWidth() * this.scale,buttonRect.getHeight() * this.scale);
            button.setTextures(
                    "ui/TeleportPage/TeleportRoomButtonNormal.png",
                    "ui/TeleportPage/TeleportRoomButtonHovered.png",
                    "ui/TeleportPage/TeleportRoomButtonPressed.png",
                    "Background/transparent.png");
            button.setTag(rectangleMapObject);
            button.addListener(new ButtonClickListener(){
                /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
                 * button or touch. The touchUp event is always.
                 * @see ButtonClickListener */
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    GameEntry.current.playScreen.teleportAuber(((RectangleMapObject)button.getTag()).getName());
                }
            });
            buttons.add(button);

            TextBlock textBlock = new TextBlock();
            textBlock.setText(rectangleMapObject.getName() + " Room");
            textBlock.setRelativePosition(
                    buttonRect.getX() * this.scale + 1920 / 2 - 400 * this.scale + 30f,
                    buttonRect.getY() * this.scale + 1080 / 2 - 504 * this.scale + buttonRect.getHeight() / 2 + 25f,
                    UIElement.HorizontalAlignment.LEFT_ALIGNMENT,
                    UIElement.VerticalAlignment.BOTTOM_ALIGNMENT);
            textBlock.setFontSize(0.5f);
            textBlock.setFontColor(1,1,1,1);
            roomTextBlocks.add(textBlock);
        }

        //this.addUIElement(image);
        this.addUIElement(textBlock);
        //this.addUIElement(backButton);
        for(Button button : buttons){
            this.addUIElement(button);
            this.addUIElement(roomTextBlocks.get(buttons.indexOf(button)));
        }
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

    /***
     *
     */
    @Override
    public void hide(){
        for(Actor actor : this.getActors()){
            ((UIElement)actor).hide();
        }
    }

    /***
     * appear
     */
    @Override
    public void appear(){
        for(Actor actor : this.getActors()){
            ((UIElement)actor).fadeIn(100f,0f,0.6f, Interpolation.fade);
        }
    }

    @Override
    public void draw(){
        Batch batch = getBatch();
        batch.begin();
        image.draw(batch, 1.0f);
        batch.end();
        orthogonalTiledMapRenderer.render();
        super.draw();
    }
}
