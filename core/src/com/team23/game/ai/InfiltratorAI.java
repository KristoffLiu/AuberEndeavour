package com.team23.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.team23.game.utils.Utility;
import com.team23.game.ai.graph.PathGraph;
import com.team23.game.ai.graph.PathNode;

/***
 * Infiltrator AI class
 */
public class InfiltratorAI {
    //The Infiltrator will go here if it has nowhere else to go
    protected final PathNode restingPosition = new PathNode(new Vector2(4712,4956),false);

    protected MovementAI movAI;
    protected PathGraph graph;
    protected PathNode goal;

    public InfiltratorAI(PathGraph graph){
        this.graph = graph;
        movAI = new MovementAI();
    }

    public void update(Vector2 position) {
        //If the AI does not have a goal a new goal is generated, if the player is at the generated goal a new one will be generated
        //An appropriate destination is then set
        if (goal==null) {
            while (goal == null) {
                goal = generateNewGoal();
                if (Utility.closeEnough(position, goal.position)) {
                    goal = null;
                }
            }
            movAI.setDestination(generateNewDestination(position));
        }
        //If the Ai is at its destination the next one is set
        if (movAI.atDestination(position)) {
            if(movAI.destination == restingPosition.position){
                goal = generateNewGoal();
            }
            movAI.setDestination(generateNewDestination(position));
        }
    }

    /**
     * Generates a new goal node from the graph
     * @return A node that either represents a working system, or is a default node
     */
    protected PathNode generateNewGoal(){
        PathNode newGoal = graph.getRandomWorkingSystem();
        if(newGoal!=null){
            return newGoal;
        }
        return restingPosition;
    }

    /**
     * Generates a new destination for the agent based on its position and goal
     * @param position The position of the agent
     * @return Coordinates of the generated destination
     */
    private Vector2 generateNewDestination(Vector2 position){
        PathNode nearest = graph.getNearestNode(position);
        if(Utility.closeEnough(nearest.position,position)){
            PathNode destNode = graph.findPath(nearest,goal);
            if(destNode == null){
                destNode = graph.getMostEdgesAdjacentNode(nearest);
                goal = null;
            }
            return destNode.position;
        }
        return nearest.position;
    }

    //Directional movement methods


    /**
     * Decides if the agent should be move left or not, based on its position, destinationa and if it's in prison
     * @param position Position of the agent
     * @param arrested If the agent is in prison
     * @return True if the agent should move left, false otherwise
     */
    public boolean left(Vector2 position,boolean arrested){
        return !arrested && movAI.left(position);
    }

    /**
     * Decides if the agent should be move right or not, based on its position, destinationa and if it's in prison
     * @param position Position of the agent
     * @param arrested If the agent is in prison
     * @return True if the agent should move right, false otherwise
     */
    public boolean right(Vector2 position,boolean arrested){
        return !arrested && movAI.right(position);
    }

    /**
     * Decides if the agent should be move up or not, based on its position, destinationa and if it's in prison
     * @param position Position of the agent
     * @param arrested If the agent is in prison
     * @return True if the agent should move up, false otherwise
     */
    public boolean up(Vector2 position,boolean arrested){
        return !arrested && movAI.up(position);
    }

    /**
     * Decides if the agent should be move down or not, based on its position, destinationa and if it's in prison
     * @param position Position of the agent
     * @param arrested If the agent is in prison
     * @return True if the agent should move down, false otherwise
     */
    public boolean down(Vector2 position,boolean arrested){
            return !arrested && movAI.down(position);
    }
}
