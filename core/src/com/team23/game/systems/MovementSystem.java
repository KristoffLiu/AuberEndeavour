package com.team23.game.systems;

import com.badlogic.gdx.math.Vector2;

/***
 * Movement System
 * handling the movement
 */
public class MovementSystem {

    Collider collider;
    float movementSpeed;
    private int direction;
    private  boolean collided;
    //direction 1=left 2=right 3=up 4=down 5=upRight, 6=downRight, 7=downLeft, 8= upLeft

    /***
     * constructor
     */
    public MovementSystem(Vector2 position,float speed){
        this.collider = new Collider(position);
        this.movementSpeed = speed;
        this.direction=0;
    }

    /***
     * updating the position
     */
    public void updatePos(Vector2 position){
        this.collider = new Collider(position);

    }

    /***
     * updating the speed
     */
    public void setSpeed(float speed){
        movementSpeed = speed;
    }

    /**
     * Calculates the position of a character after a left move
     * @return The new position of the character
     */
    public Vector2 left(){
        //don't move if collided
        if (direction==1 && collided){
            return collider.position;
        }
        Vector2 newPos = collider.position;
        newPos.x -= movementSpeed;
        this.direction=1;
        collided=false;
        return newPos;
    }

    /**
     * Calculates the position of a character after a right move
     * @return The new position of the character
     */
    public Vector2 right(){
        //don't move if collided
        if (direction==2 && collided){
            return collider.position;
        }
        Vector2 newPos = collider.position;
        newPos.x += movementSpeed;
        this.direction=2;
        collided=false;
        return newPos;
    }

    /**
     * Calculates the position of a character after a upwards move
     * @return The new position of the character
     */
    public Vector2 up(){
        //don't move if collided
        if (direction==3 && collided){
            return collider.position;
        }
        Vector2 newPos = collider.position;
        newPos.y += movementSpeed;
        this.direction=3;
        collided=false;
        return newPos;
    }

    /**
     * Calculates the position of a character after a downwards move
     * @return The new position of the character
     */
    public Vector2 down(){
        //don't move if collided
        if (direction==4 && collided){
            return collider.position;
        }

        Vector2 newPos = collider.position;
        newPos.y -= movementSpeed;
        this.direction=4;
        collided=false;
        return newPos;
    }

    public Vector2 upRight() {
        //don't move if collided
        if (direction==5 && collided){
            return collider.position;
        }

        Vector2 newPos = collider.position;
        newPos.y += movementSpeed;
        newPos.x += movementSpeed;
        this.direction=5;
        collided=false;
        return newPos;
    }

    public Vector2 downRight() {
        //don't move if collided
        if (direction==6 && collided){
            return collider.position;
        }

        Vector2 newPos = collider.position;
        newPos.y -= movementSpeed;
        newPos.x += movementSpeed;
        this.direction=6;
        collided=false;
        return newPos;
    }

    public Vector2 downLeft() {
        //don't move if collided
        if (direction==7 && collided){
            return collider.position;
        }

        Vector2 newPos = collider.position;
        newPos.y -= movementSpeed;
        newPos.x -= movementSpeed;
        this.direction=7;
        collided=false;
        return newPos;
    }

    public Vector2 upLeft() {
        //don't move if collided
        if (direction==8 && collided){
            return collider.position;
        }

        Vector2 newPos = collider.position;
        newPos.y += movementSpeed;
        newPos.x -= movementSpeed;
        this.direction = 8;
        collided=false;
        return newPos;
    }

  /***
     * get the position
     */
    public Vector2 getPos(){
        return collider.position;

    }

    /***
     * get the direction
     */
    public int getDirection(){
        return direction;
    }

    /***
     * set the collision
     */
    public void setCollided(boolean collided) {
        this.collided = collided;
    }
}
