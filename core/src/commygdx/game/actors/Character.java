package commygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import commygdx.game.systems.MovementSystem;

import java.util.List;

public abstract class Character extends Actor {

    //Constants
    private final float MOV_SPEED = 8;

    public Sprite sprite;
    public MovementSystem movementSystem;
    protected Batch batch;

    public Character(Vector2 position,SpriteBatch batch){
        this.batch = batch;
        sprite = new Sprite(getTexture());
        sprite.setSize(150, 170);
        movementSystem = new MovementSystem(position, MOV_SPEED);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    protected abstract Texture getTexture();

    public Vector2 getPosition(){
        return new Vector2(getX(),getY());
    }

    @Override
    public void act(float delta) {
        handleMovement();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }


    protected abstract void handleMovement();

    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(getX(),getY());
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
            if(sprite.getBoundingRectangle().overlaps(collisionBox)){
                movementSystem.getDirection();
                movementSystem.setCollided(true);
                return true;
            }
        }
        return false;
    }

}
