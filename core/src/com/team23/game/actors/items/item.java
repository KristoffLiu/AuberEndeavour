package com.team23.game.actors.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.team23.game.actors.CustomActor;

import java.util.List;

public abstract class item extends CustomActor {
    protected Batch batch;
    private String spriteName;

    public item(Vector2 position, String spriteName){
        this.batch = batch;
        this.setTextureRegion(new TextureRegion(getTexture(spriteName)));
        this.setWidth(150);
        this.setHeight(170);
    }

    protected abstract Texture getTexture(String spriteName);

    public Vector2 getPosition(){
        return new Vector2(getX(),getY());
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        this.setPosition(getX(),getY());
    }

    /**
     * Checks if the character is colliding with any of the collision boxes
     * @param collisionBoxes The collision boxes the character could be colliding with
     * @return True if the character is colliding with one or more of the boxes, false otherwise
     */
    public boolean checkCollision(List<Rectangle> collisionBoxes){
        for (Rectangle collisionBox: collisionBoxes){
            //System.out.println(sprite.getBoundingRectangle());
            //System.out.println(wall);
            if(this.getBounds().overlaps(collisionBox)){
                return true;
            }
        }
        return false;
    }

}
