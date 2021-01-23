package com.team23.game.ai.graph;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class PathNode {
    private boolean system;
    private boolean working;

    private ArrayList<PathNode> adjacentNodes;

    public Vector2 position;

    public PathNode(Vector2 position, boolean system){
        this.position = position;
        this.system = system;
        this.working = true;
        adjacentNodes = new ArrayList<PathNode>();
    }

    @Override
    public boolean equals(Object obj) {
        if(position==((PathNode)obj).position){
            return  true;
        }
        return false;
    }

    public void addAdjacentNode(PathNode node){
        adjacentNodes.add(node);
    }

    public PathNode[] getAdjacentNodes() {
        //manually doing toArray to avoid ClassCastException
        PathNode[] edgesArray = new PathNode[adjacentNodes.size()];
        for(int i = 0; i< adjacentNodes.size(); i++){
            edgesArray[i] = adjacentNodes.get(i);
        }
        return edgesArray;
    }

    /**
     * Returns true if the node represents a system that is working
     * @return True if the node represents a working system, false otherwise
     */
    public boolean isWorkingSystem(){
        if(system&&working){
            return true;
        }
        return false;
    }

    public void setWorking(boolean working){
        this.working = working;
    }

    public boolean isNonSystem(){
        return !system;
    }

    @Override
    public String toString() {
        String s = position.toString();
        if(system){s= s + " " + "system";}
        if(working){s= s + " " + "working";}
        return s;
    }
}
