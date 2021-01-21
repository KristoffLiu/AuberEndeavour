package commygdx.game;

import com.badlogic.gdx.math.Vector2;
import commygdx.game.ai.graph.PathGraph;

public class ShipSystem {
    private float x;
    private float y;
    private int state;
    private String room;
    private PathGraph graph;

    public ShipSystem(float x,float y, String room, PathGraph graph){
        this.x=x;
        this.y=y;
        this.room=room;
        this.state=0;
        this.graph = graph;
    }
    public void setState(int state){
        //state 0= operational, state 1=under attack, state 2= not operational
        this.state=state;
    }
    public int getState(){
        return state;
    }
    public String getRoom(){
        return room;
    }
    public Vector2 getPosition(){
        return new Vector2(x,y);
    }

    public void destroy(){
        state = 2;
        graph.getNearestNode(getPosition()).setWorking(false);
    }
    public void startAttack(){
        state = 1;
    }
    public void stopAttack(){
        state = 0;
    }

    @Override
    public String toString() {
        return getPosition().toString() + room;
    }
}
