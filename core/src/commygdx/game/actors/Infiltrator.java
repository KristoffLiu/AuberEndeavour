package commygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import commygdx.game.AI.MovementAI;

import commygdx.game.syst.MovementSystem;

public class Infiltrator extends Character {

    private MovementAI movementAI;
    private Vector2 destination;

    public Infiltrator(Vector2 position, SpriteBatch batch) {
        super(position, batch);
    }


    @Override
    protected void handleMovement(){
        if(movementAI.left()){
            movementSystem.left();
        }
        if(movementAI.right()){
            movementSystem.right();
        }
        if(movementAI.up()){
            movementSystem.up();
        }
        if(movementAI.down()){
            movementSystem.down();
        }
    }


}