package com.team23.game.ai;

import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;
import com.team23.game.ai.graph.PathGraph;
import com.team23.game.ai.graph.PathNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class Graph {
    public ArrayList<PathNode> nodes = new ArrayList<>();
    public PathGraph graph = new PathGraph();

    @Before
    public void insertPoints(){
        /*
        Inserts points into the graph
         */

        int[] sysX = {26, 12, 15, 25, 30};
        int[] sysY = {3, 14, 15, 10, 3};
        int[] nonSysX = {13, 15, 21, 9, 21, 16};
        int[] nonSysY = {12, 5, 26, 17, 9, 28};

        for (int i = 0; i < sysX.length ; i++) {
            nodes.add(new PathNode(new Vector2(sysX[i], sysY[i]), true));
        }
        for (int i = 0; i < nonSysX.length ; i++) {
            nodes.add(new PathNode(new Vector2(nonSysX[i], nonSysY[i]), false));
        }

        for (PathNode node: nodes){
            graph.addNode(node);
        }

        graph.addEdge(nodes.get(0),nodes.get(3));
        graph.addEdge(nodes.get(0),nodes.get(4));
        graph.addEdge(nodes.get(1),nodes.get(8));
        graph.addEdge(nodes.get(1),nodes.get(2));
        graph.addEdge(nodes.get(2),nodes.get(5));
        graph.addEdge(nodes.get(2),nodes.get(9));
        graph.addEdge(nodes.get(2),nodes.get(10));
        graph.addEdge(nodes.get(10),nodes.get(7));
        graph.addEdge(nodes.get(6),nodes.get(9));
        graph.addEdge(nodes.get(9),nodes.get(3));

    }

    @Test
    public void nearestNode(){
        // Get nearest node (21,9)
        Assert.assertEquals(graph.getNearestNode(new Vector2(20,10)), nodes.get(9));

        // Get nearest node when in the middle of two
        Assert.assertTrue((graph.getNearestNode(new Vector2(28,3)) == nodes.get(0) ||
                graph.getNearestNode(new Vector2(28,3)) == nodes.get(4) ));

        // Get nearest node when on the node
        Assert.assertEquals(graph.getNearestNode(new Vector2(26,3)), nodes.get(0));
    }
}
