package com.team23.game.actors.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.team23.game.ShipSystem;
import com.team23.game.ai.InfiltratorAI;
import com.team23.game.ai.graph.PathGraph;

import com.team23.game.screens.playscreen.PlayScreen;
//import org.graalvm.compiler.lir.aarch64.AArch64Move;

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


    public Infiltrator(Vector2 position, SpriteBatch batch, int power, PathGraph graph, float movSpeed) {
        super(position, batch, movSpeed);
        setPosition(position.x,position.y);
        this.power=power;
        powerOn=false;
        powerDuration=0;
        powerCoolDown=0;
        ai = new InfiltratorAI(graph);
        facingRight=true;
        highlighted = false;
    }

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

    public void usePower(PlayScreen screen,String room){
        resetPower();
        if (power==1){sprite.setTexture(new Texture(Gdx.files.internal("Characters/infiltratorInvisibleSprite.png")));}
        if (power==2&&room!="infirmary"){screen.setHallucinate(true);}
        if (power==3){sprite.setTexture(new Texture(Gdx.files.internal("Characters/infiltratorShapeshift.png")));}
        if (power==4){movementSystem.setSpeed(20f);}
    }

    public void setTexture(Texture texture){
        sprite.setTexture(texture);
    }

    private void resetPower(){
        powerCoolDown=0;
        powerDuration=0;
        powerOn=true;
    }

    public void stopPower(PlayScreen screen){
        if (power==1){resetTexture(); }
        if (power==3){resetTexture();}
        if (power==4){movementSystem.setSpeed(movSpeed);}
        powerOn=false;


    }

    public float getPowerDuration(){return powerDuration;}
    public float getPowerCooldown(){return powerCoolDown;}

    @Override
    protected Texture getTexture(){
        return new Texture(Gdx.files.internal("Characters/infiltratorSprite.png"));
    }

    @Override
    protected void handleMovement() {
        if(ai.left(new Vector2(getX(),getY()),isArrested)){
            Vector2 pos = movementSystem.left();
            setPosition(pos.x, pos.y);
            if (facingRight==true){
                sprite.flip(true,false);
                facingRight=false;
            }
        }
        if(ai.right(new Vector2(getX(),getY()),isArrested)){
            Vector2 pos = movementSystem.right();
            setPosition(pos.x, pos.y);
            if (facingRight==false){
                sprite.flip(true,false);
                facingRight=true;
            }
        }
        if(ai.up(new Vector2(getX(),getY()),isArrested)){
            Vector2 pos = movementSystem.up();
            setPosition(pos.x, pos.y);
        }
        if(ai.down(new Vector2(getX(),getY()),isArrested)){
            Vector2 pos = movementSystem.down();
            setPosition(pos.x, pos.y);
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

    public void resetTexture(){
        powerCoolDown=0;
        powerDuration=0;
        powerOn=true;
        sprite.setTexture(getTexture());
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
}
