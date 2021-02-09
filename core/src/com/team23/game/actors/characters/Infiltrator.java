package com.team23.game.actors.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.team23.game.ShipSystem;
import com.team23.game.ai.InfiltratorAI;
import com.team23.game.ai.graph.PathGraph;

import com.team23.game.save.EnemyInfo;
import com.team23.game.screens.playscreen.PlayScreen;

/***
 * Infiltrator class
 */
public class Infiltrator extends Character {

    //Constants
    private float movSpeed;
    private final float TIME_TO_DESTROY = 500f;

    private InfiltratorAI ai;
    private Vector2 destination;
    private boolean isArrested;
    //0=none, 1=invisibility, 2=hallucination 3=shapeshift 4=speed booast
    private int power;
    private float powerCoolDown;
    private float powerDuration;
    private boolean powerOn;
    private ShipSystem destroyingSystem=null;
    private float destructionTimer = 0;
    private boolean facingRight;
    private boolean highlighted;
    private boolean frozen;

    /***
     * Constructor - create the Auber object when it is loaded from the save
     * @param enemyInfo info the AuberInfo instance which is loaded from the save
     * @param graph the path graph
     */
    public Infiltrator(EnemyInfo enemyInfo, PathGraph graph) {
        this(enemyInfo.position.toVector2(), enemyInfo.power, graph, enemyInfo.moveSpeed);
    }

    /***
     * Constructor - create the Auber object from the new game.
     * @param position info the AuberInfo instance which is loaded from the save
     * @param power the path graph
     * @param movSpeed the movement speed
     */
    public Infiltrator(Vector2 position, int power, PathGraph graph, float movSpeed) {
        super(position, movSpeed);
        this.setSize(150, 170);
        this.setTextureRegion(new TextureRegion(getTexture()));
        setPosition(position.x,position.y);
        this.power = power;
        powerOn = false;
        powerDuration = 0;
        powerCoolDown = 0;
        ai = new InfiltratorAI(graph);
        facingRight = true;
        highlighted = false;
        frozen = false;
    }

    /***
     * store the logic code
     * @param delta
     */
    @Override
    public void act(float delta) {
        if(isArrested){
            if (destroyingSystem!=null){destroyingSystem.setState(0);}
            return;}
        if(destroyingSystem!=null){
            destructionTimer += delta*100;
            if(destructionTimer>TIME_TO_DESTROY){
                destroyingSystem.destroy();
                destroyingSystem = null;
            }
        }else {
            ai.update(delta, new Vector2(getX(), getY()));
            super.act(delta);
            powerCoolDown = (int) (Math.random() * 1000);
        }
    }

    /***
     * use the power
     * @param screen
     * @param room
     */
    public void usePower(PlayScreen screen,String room){
        resetPower();
        if (power==1){this.getTextureRegion().setTexture(new Texture(Gdx.files.internal("Characters/infiltratorInvisibleSprite.png")));}
        if (power==2&&room!="infirmary"){screen.setHallucinate(true);}
        if (power==3){this.getTextureRegion().setTexture(new Texture(Gdx.files.internal("Characters/infiltratorShapeshift.png")));}
        if (power==4){movementSystem.setSpeed(20f);}
    }

    /***
     * set the Texture
     * @param texture
     */
    public void setTexture(Texture texture){
        this.getTextureRegion().setTexture(texture);
    }

    /***
     * reset the power
     */
    private void resetPower(){
        powerCoolDown=0;
        powerDuration=0;
        powerOn=true;
    }

    /***
     * stop the power
     * @param screen
     */
    public void stopPower(PlayScreen screen){
        if (power==1){resetTexture(); }
        if (power==3){resetTexture();}
        if (power==4){movementSystem.setSpeed(movSpeed);}
        powerOn=false;
    }

    /***
     * stop the duration of the power
     */
    public float getPowerDuration(){return powerDuration;}

    /***
     * stop the time of cooldown of the power
     */
    public float getPowerCooldown(){return powerCoolDown;}

    /***
     * stop the duration of the power
     */
    @Override
    protected Texture getTexture(){
        return new Texture(Gdx.files.internal("Characters/infiltratorSprite.png"));
    }

    @Override
    protected void handleMovement() {
        if(!isFrozen()) {
            if (ai.left(new Vector2(getX(), getY()), isArrested)) {
                Vector2 pos = movementSystem.left();
                setPosition(pos.x, pos.y);
                if (facingRight == true) {
                    this.getTextureRegion().flip(true, false);
                    facingRight = false;
                }
            }
            if (ai.right(new Vector2(getX(), getY()), isArrested)) {
                Vector2 pos = movementSystem.right();
                setPosition(pos.x, pos.y);
                if (facingRight == false) {
                    this.getTextureRegion().flip(true, false);
                    facingRight = true;
                }
            }
            if (ai.up(new Vector2(getX(), getY()), isArrested)) {
                Vector2 pos = movementSystem.up();
                setPosition(pos.x, pos.y);
            }
            if (ai.down(new Vector2(getX(), getY()), isArrested)) {
                Vector2 pos = movementSystem.down();
                setPosition(pos.x, pos.y);
            }
        }
    }

    /**
     * Arrests the infiltrator, sending them to the given coordinates and setting them to arrested mode
     * @param coords The coordinates the infiltrator will be sent to
     */
    public void arrest(Vector2 coords){
        isArrested = true;
        setPosition(coords.x, coords.y);
    }

    /***
     * reset the texture
     * @param screen
     */
    public void resetTexture(){
        powerCoolDown=0;
        powerDuration=0;
        powerOn=true;
        this.getTextureRegion().setTexture(getTexture());
    }



    public void updateTimers(float dt){
        if (powerOn==false){
            powerCoolDown+=dt;
        }
        if (powerOn==true){
            powerDuration+=dt;
        }
    }

    public boolean isAvailable(){
        return !(isArrested&&destroyingSystem!=null);
    }

    /**
     * Begins the destruction process of the given system
     * @param system The system being destroyed
     */
    public void startDestruction(ShipSystem system){
        destroyingSystem=system;
        destructionTimer = 0;
    }

    public boolean getIsArrested(){return isArrested;}

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public int getPower(){
        return this.power;
    }
    
    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
