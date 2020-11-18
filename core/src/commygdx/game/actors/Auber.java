package commygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import commygdx.game.input.PlayerInput;

public class Auber extends Character {

    public Auber(Vector2 position, SpriteBatch batch) {
        super(position,batch);
    }

    @Override
    protected void handleMovement() {
        //Left movement
        if(PlayerInput.left()){
            Vector2 position = movementSystem.left();
            setPosition(position.x,position.y);
        }
        //Right movement
        if(PlayerInput.right()){
            Vector2 position = movementSystem.right();
            setPosition(position.x,position.y);
        }
        //Up movement
        if(PlayerInput.up()){
            Vector2 position = movementSystem.up();
            setPosition(position.x,position.y);
        }
        //Down movement
        if(PlayerInput.down()){
            Vector2 position = movementSystem.down();
            setPosition(position.x,position.y);
        }
    }
}