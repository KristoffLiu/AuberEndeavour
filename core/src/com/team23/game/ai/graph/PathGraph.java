package com.team23.game.ai.graph;

import com.badlogic.gdx.math.Vector2;
import com.team23.game.utils.Utility;
import com.team23.game.ai.graph.queue.PriorityItem;
import com.team23.game.ai.graph.queue.PriorityQueue;

import java.util.ArrayList;
import java.util.LinkedList;

public class PathGraph {
    private static final int VERY_LARGE = 2147483647;

    private ArrayList<PathNode> nodes;

    public PathGraph(){
        nodes = new ArrayList<>();
    }

    public PathGraph(PathNode[] initNodes){
        nodes = new ArrayList<>();
        for(PathNode node:initNodes){
            addNode(node);
        }
    }

    /**
     * Adds a node if it was node already in the graph
    *@param node the node to be added to the graph
     */
    public void addNode(PathNode node){
        if(nodes.contains(node)){
            return;
        }
        nodes.add(node);
    }

    /**
     * *Adds an directionless edge between two nodes in the graph
     * @param node1 One of the nodes to be connected by the edge
     * @param node2 One of the nodes to be connected by the edge
     */
    public void addEdge(PathNode node1,PathNode node2){
        if(nodes.contains(node1)&&nodes.contains(node2)&&!node1.equals(node2)){
            node1.addAdjacentNode(node2);
            node2.addAdjacentNode(node1);
        }
    }

    /**
     * Returns the node in the graph nearest to a given position
     * @param position The position from which the nearest node is found
     * @return Nearest node to the position
     */
    public PathNode getNearestNode(Vector2 position){
        PathNode nearest = new PathNode(new Vector2(VERY_LARGE,VERY_LARGE),false);
        for (PathNode node:nodes){
            if(position.dst2(node.position)<position.dst2(nearest.position)){
                nearest = node;
            }
        }
        return nearest;
    }

    /**
     * Returns a randomly selected node that represents an operational system
     * @return A node representing an operational system
     */
    public PathNode getRandomWorkingSystem(){
        LinkedList<PathNode> workingSystems = new LinkedList<>();
        for(PathNode node:nodes){
            if(node.isWorkingSystem()){
                workingSystems.add(node);
            }
        }
        if(workingSystems.isEmpty()){return null;}
        return workingSystems.get(Utility.randomIntBelow(workingSystems.size()));
    }

    /**
     * Returns a randomly selected node that does not represent an operational system
     * @return A node that does not represent a system
     */
    public PathNode getRandomNonSystem(){
        LinkedList<PathNode> nonSystems = new LinkedList<>();
        for(PathNode node:nodes){
            if(node.isNonSystem()){
                nonSystems.add(node);
            }
        }
        if(nonSystems.isEmpty()){return null;}
        return nonSystems.get(Utility.randomIntBelow(nonSystems.size()));
    }

    /**
     * Finds a path between the start and goal nodes and returns the first step in that path
     * @param start The node the path starts at
     * @param goal The node the path ends at
     * @return The first node in a bath between the start and goal
     */
    public  PathNode findPath(PathNode start,PathNode goal){
        //Maximum amount of iterations before the search ends as a failure
        final int MAX_ITERATIONS = 100;

        //A* search, returns the next node the AI should go to

        //Custom priority queue used to store the fringe of nodes to be visited
        PriorityQueue fringe = new PriorityQueue();
        LinkedList<PathNode> visited = new LinkedList<>();
        fringe.push(new PriorityItem(start,null,0,0));
        int count = 0;
        while(count<MAX_ITERATIONS && !fringe.isEmpty()){
            count++;
            PriorityItem current = fringe.pop();
            if(current.node.equals(goal)){
                return current.firstStep;
            }
            visited.add(current.node);

            //Inserting children into queue
            PathNode[] next = current.node.getAdjacentNodes();
            PathNode firstStep = current.firstStep;
            for(PathNode node:next) {
                if (!visited.contains(node)) {
                    //If this is the first step taken then first step will be the current node
                    if (current.firstStep == null) {
                        firstStep = node;
                    }
                    fringe.push(new PriorityItem(node, firstStep,
                            heuristicAndPathCost(current.node, goal, node, current.pathCost),
                            pathCost(current.pathCost, current.node, node)));
                }
            }
        }
        return null;
    }

    /**
     * Returns the path cost of a node, from the path cost of the node before
     * @param currentPathCost The path cost of current
     * @param current The current node in the graph the search is on
     * @param next The node in the graph who's path cost is being calculated
     * @return The path cost of next
     */
    private float pathCost(float currentPathCost,PathNode current,PathNode next){
        return currentPathCost + current.position.dst2(next.position);
    }

    /**
     * Calculates the heuristic + path cost of a given node
     * @param current The node in the graph the search is currently on
     * @param goal The goal node of the search
     * @param next The node the h+p is being calculated for
     * @param currentPathCost The path cost of current
     * @return The heuristic + path cost of next
     */
    private float heuristicAndPathCost(PathNode current, PathNode goal, PathNode next, float currentPathCost){
        //heuristic
        float heuristic = goal.position.dst(next.position);
        //path cost
        heuristic +=pathCost(currentPathCost,current,next);
        return heuristic;
    }

    /**
     * Returns the node with the most edges adjacent to a given node.
     * @param node The node the returned node is adjacent to
     * @return The adjacent node to the given node with the most edges
     */
    public PathNode getMostEdgesAdjacentNode(PathNode node){
        PathNode[] adjacent = node.getAdjacentNodes();
        PathNode mostEdges = new PathNode(new Vector2(-1,-1),false);
        for(PathNode adjNode:adjacent){
            if(adjNode.getAdjacentNodes().length>mostEdges.getAdjacentNodes().length){
                mostEdges = adjNode;
            }
        }
        return mostEdges;
    }

    public ArrayList<PathNode> getNodes(){
        return nodes;
    }
}
