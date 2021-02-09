package com.team23.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.team23.game.ai.graph.PathGraph;
import com.team23.game.ai.graph.PathNode;

/***
 * Demo AI class
 */
public class DemoAI extends InfiltratorAI {
    public DemoAI(PathGraph graph){
        super(graph);
    }

    /**
     * Generates a new goal node from graph
     * @return A node that doesn't represent a system
     */
    @Override
    protected PathNode generateNewGoal() {
        PathNode goal = graph.getRandomNonSystem();
        if(goal!=null){
            return goal;
        }
        return restingPosition;
    }

    /**
     * Returns if the agent should move left or not, given it's position and depending on it's destination
     * @param position The position of the agent
     * @return True if the agent should move left, false otherwise
     */
    public boolean left(Vector2 position) {
        return movAI.left(position);
    }

    /**
     * Returns if the agent should move right or not, given it's position
     * @param position The position of the agent
     * @return True if the agent should move right, false otherwise
     */
    public boolean right(Vector2 position) {
        return movAI.right(position);
    }

    /**
     * Returns if the agent should move up or not, given it's position
     * @param position The position of the agent
     * @return True if the agent should move up, false otherwise
     */
    public boolean up(Vector2 position) {
        return movAI.up(position);
    }

    /**
     * Returns if the agent should move down or not, given it's position
     * @param position The position of the agent
     * @return True if the agent should move down, false otherwise
     */
    public boolean down(Vector2 position) {
        return movAI.down(position);
    }
}
