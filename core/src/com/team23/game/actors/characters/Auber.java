package com.team23.game.actors.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.team23.game.TileWorld;
import com.team23.game.actors.items.PowerUp;
import com.team23.game.inputs.PlayerInput;
import com.team23.game.save.AuberInfo;
import com.team23.game.screens.playscreen.Hud;

import java.util.ArrayList;


/***
 * Auber class
 */
public class Auber extends Character {

    private float movSpeed;
    protected boolean facingRight;
    private int powerDuration;
    private String currentPower;
    private boolean teleportPowerUp;

    /***
     * Constructor - create the Auber object when it is loaded from the save
     * @param info the AuberInfo instance which is loaded from the save
     */
    public Auber(AuberInfo info){
        this(info.position.toVector2(), info.moveSpeed);
    }

    /***
     * Constructor - create the Auber object from a new game
     * @param position the position
     * @param movSpeed the movement speed
     */
    public Auber(Vector2 position, float movSpeed) {
        super(position, movSpeed);
        this.setSize(150, 170);
        this.setTextureRegion(new TextureRegion(getTexture()));
        this.setPosition(position.x, position.y);
        shuffle();
        movementSystem.setSpeed(movSpeed);
        facingRight=true;
        teleportPowerUp = false;
    }

    /***
     * get the texture.
     */
    @Override
    protected Texture getTexture() {
        return new Texture(Gdx.files.internal("Characters/auberSprite.png"));
    }

    /***
     * handle the movement of the auber
     */
    @Override
    protected void handleMovement() {
        switch (PlayerInput.getDirection()){
            case 1:
                handleMovement(movementSystem.left());
                if (facingRight == true){
                    this.getTextureRegion().flip(true,false);
                    facingRight = false;
                }
                break;
            case 2:
                handleMovement(movementSystem.right());
                if (facingRight == false){
                    this.getTextureRegion().flip(true,false);
                    facingRight = true;
                }
                break;
            case 3:
                handleMovement(movementSystem.up());
                break;
            case 4:
                handleMovement(movementSystem.down());
                break;
            case 5:
                handleMovement(movementSystem.upRight());
                break;
            case 6:
                handleMovement(movementSystem.downRight());
                break;
            case 7:
                handleMovement(movementSystem.downLeft());
                break;
            case 8:
                handleMovement(movementSystem.upLeft());
                break;
        }
    }

    /***
     * handle the movement of the auber
     */
    private void handleMovement(Vector2 position) {
        setPosition(position.x,position.y);
    }

    /**
     * Checks if the auber is on a teleporter
     * @param tiles Contains the teleporters
     * @return True if the auber is on a teleporter, false otherwise
     */
    public boolean teleportCheck(TileWorld tiles){
        //check if standing on teleporter
        for (Rectangle rect : tiles.getTeleporters().values()) {
            if(this.getBounds().contains(rect)){
                return true;
            }
        }
        return false;
    }

    /**
     * Arrests any infiltrators in range of the player
     * @param infiltrators A list of all infiltrators in the game
     * @param hud The games HUD overlay
     */
    public void arrest(ArrayList<Infiltrator> infiltrators,Hud hud){
        if(PlayerInput.arrest()) {
            for (Infiltrator infiltrator : infiltrators) {
                if (Math.abs(infiltrator.getX() - this.getX()) < 100 && Math.abs(infiltrator.getY() - this.getY()) < 100) {
                    infiltrator.arrest(new Vector2((float)Math.random()*1000+1200,(float)Math.random()*400+5400));
                    hud.infiltratorCaught(infiltrators);
                }
            }
        }
    }

    /**Runs when the player picks up a powerup
     *
     * @param powerups A list of all powerups in the game
     * @param infiltrators A list of all infiltrators in the game
     * @param npcs A list of all npcs in the game
     */
    public void usePowerUp(ArrayList<PowerUp> powerups, ArrayList<Infiltrator> infiltrators, ArrayList<NPC> npcs){
        if(PlayerInput.arrest() && powerDuration == 0) {
            for (PowerUp powerup : powerups) {
                if(!powerup.isActivated()) {
                    if (Math.abs(powerup.getX() - this.getX()) < 100 && Math.abs(powerup.getY() - this.getY()) < 100) {
                        if (powerup.getName() == "Speed") {
                            currentPower = powerup.getName();
                            setMovSpeed(movementSystem.getSpeed() * 2);
                            powerup.activate();
                            powerDuration = 300;
                        }
                        else if(powerup.getName() == "Immunity"){
                            currentPower = powerup.getName();
                            powerup.activate();
                            powerDuration = 300;
                        }
                        else if(powerup.getName() == "Highlight"){
                            currentPower = powerup.getName();
                            for(Infiltrator infiltrator : infiltrators){
                                infiltrator.setTexture(new Texture(Gdx.files.internal("Characters/infiltratorSpriteHighlighted.png")));
                                infiltrator.setHighlighted(true);
                            }
                            powerup.activate();
                            powerDuration = 300;
                        }
                        else if(powerup.getName() == "Freeze"){
                            currentPower = powerup.getName();
                            for(Infiltrator infiltrator : infiltrators){
                                infiltrator.setFrozen(true);
                            }
                            for(NPC npc : npcs){
                                npc.setFrozen(true);
                            }
                            powerup.activate();
                            powerDuration = 300;
                        }
                        else if(powerup.getName() == "Teleport"){
                            currentPower = powerup.getName();
                            teleportPowerUp = true;
                            powerup.activate();
                            powerDuration = 300;
                        }
                    }
                }
            }
        }
    }


    /***
     * set the movement speed.
     * @param  newMovSpeed the float value to be set as the movement speed.
     */
    private void setMovSpeed(float newMovSpeed) {
        movementSystem.setSpeed(newMovSpeed);
    }

    /***
     * moves the camera to the auber when game starts
     */
    public void shuffle(){
        Vector2 position = movementSystem.left();
        setPosition(position.x,position.y);
    }

    /***
     * the overridden act method.
     */
    public void act(float delta){
        if(powerDuration != 0) {
            powerDuration -= delta;
        }
        if(powerDuration <= 0){
            if(currentPower == "Speed"){
                setMovSpeed(movementSystem.getSpeed() / 2);
            }
            powerDuration = 0;
            currentPower = "";
        }
        handleMovement();
    }

    /***
     * set the movement speed.
     * @return return the current power of the auber as a String.
     */
    public String getCurrentPower() {
        return currentPower;
    }

    /***
     * whether teh auber has the power up of teleporting.
     * @return return whether teh auber has the power up of teleporting.
     */
    public boolean isTeleportPowerUp() {
        return teleportPowerUp;
    }

    /***
     * set the Power Up of teleporting ability to the auber.
     * @param teleportPowerUp the boolean value which indicates whether the auber should have the teleport power up ability.
     */
    public void setTeleportPowerUp(boolean teleportPowerUp) {
        this.teleportPowerUp = teleportPowerUp;
    }
}

