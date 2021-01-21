package commygdx.game.ai;

import com.badlogic.gdx.math.Vector2;
import commygdx.game.Utility;

public class MovementAI {


    public Vector2 destination;

    public void setDestination(Vector2 destination) {
        this.destination = destination;
    }

    /**
     * Decides if the agent should move left or not, depending on it's position and destination
     * @param position The position of the agent
     * @return True if the agent should move left, false otherwise
     */
    public boolean left(Vector2 position){
        if(position.x>destination.x){
            return true;
        }return false;
    }

    /**
     * Decides if the agent should move right or not, depending on it's position and destination
     * @param position The position of the agent
     * @return True if the agent should move right, false otherwise
     */
    public boolean right(Vector2 position){
        if(position.x<destination.x){
            return true;
        }return false;
    }

    /**
     * Decides if the agent should move up or not, depending on it's position and destination
     * @param position The position of the agent
     * @return True if the agent should move up, false otherwise
     */
    public boolean up(Vector2 position ){
        if(position.y<destination.y){
            return true;
        }return false;
    }

    /**
     * Decides if the agent should move down or not, depending on it's position and destination
     * @param position The position of the agent
     * @return True if the agent should move down, false otherwise
     */
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

    /**
     * Determines if the agent is within a small distance of its destination
     * @param position The position of the agent
     * @return True if the distance between the agent and it's destination is small enough, false otherwise
     */
    public boolean atDestination(Vector2 position){return Utility.closeEnough(destination,position);
    }
}
