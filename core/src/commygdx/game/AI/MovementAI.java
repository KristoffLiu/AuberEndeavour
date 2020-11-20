package commygdx.game.AI;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class MovementAI {
    private final float DESTINATION_BUFFER = 10;

    Vector2 destination;

    public void setDestination(Vector2 destination) {
        this.destination = destination;
    }

    public boolean left(Vector2 position){
        if(position.x>destination.x){
            return true;
        }return false;
    }

    public boolean right(Vector2 position){
        if(position.x<destination.x){
            return true;
        }return false;
    }

    public boolean up(Vector2 position){
        if(position.y<destination.y){
            return true;
        }return false;
    }

    public boolean down(Vector2 position){
        if(position.y>destination.y){
            return true;
        }return false;
    }

    public boolean hasDestination(){
        if(destination!=null){
            return true;
        }
        return false;
    }

    public boolean atDestination(Vector2 position){
        if(destination.dst(position)<DESTINATION_BUFFER){
            return true;
        }
        return false;
    }
}
