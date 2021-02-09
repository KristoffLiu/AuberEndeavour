package com.team23.game.actors.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.team23.game.actors.CustomActor;
import com.team23.game.save.CharacterInfo;
import com.team23.game.systems.MovementSystem;

import java.util.List;

/***
 * Character class
 */
public abstract class Character extends CustomActor {
    private final float movSpeed;
    public final MovementSystem movementSystem;

    /***
     * Constructor - create the Character object when it is loaded from the save
     * @param info the Character instance which is loaded from the save
     */
    protected Character(CharacterInfo info){
        this(info.position.toVector2(), info.moveSpeed);
    }

    /***
     * Constructor - create the Character object from a new game
     * @param position the position
     * @param movSpeed the movement speed
     */
    protected Character(Vector2 position, float movSpeed){
        super();
        movementSystem = new MovementSystem(position, movSpeed);
        setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.movSpeed = movSpeed;
    }

    /***
     * get the texture.
     */
    protected abstract Texture getTexture();

    /***
     * get the position.
     */
    public Vector2 getPosition(){
        return new Vector2(getX(),getY());
    }

    /***
     * Constructor - create the Auber object when it is loaded from the save
     * @param delta the AuberInfo instance which is loaded from the save
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        if(this.getTextureRegion()!=null){
            handleMovement();
        }
    }

    /**
     * Checks if the character is colliding with any of the collision boxes
     */
    protected abstract void handleMovement();

    /**
     * Checks if the character is colliding with any of the collision boxes
     * @param collisionBoxes The collision boxes the character could be colliding with
     * @return True if the character is colliding with one or more of the boxes, false otherwise
     */
    public boolean checkCollision(List<Rectangle> collisionBoxes){
        for (Rectangle collisionBox: collisionBoxes){
            if(this.getBounds().overlaps(collisionBox)){
                movementSystem.getDirection();
                movementSystem.setCollided(true);
                return true;
            }
        }
        return false;
    }

    /***
     * get the movement speed.
     * @return the current movement speed as a float value.
     */
    public float getMovSpeed(){
        return this.movSpeed;
    }
}
