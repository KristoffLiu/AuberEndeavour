package com.team23.game.ai;

import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;
import com.team23.game.ai.graph.PathGraph;
import com.team23.game.ai.graph.PathNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import javax.xml.validation.Validator;
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
    public void nearestNode() {
        // Get nearest node (21,9)
        Assert.assertEquals(graph.getNearestNode(new Vector2(20, 10)), nodes.get(9));
    }

    @Test
    public void nearestNodeEqual() {
        // Get nearest node when in the middle of two
        Assert.assertTrue((graph.getNearestNode(new Vector2(28, 3)) == nodes.get(0) ||
                graph.getNearestNode(new Vector2(28, 3)) == nodes.get(4)));
    }
    @Test
    public void nearestNodeOnNode() {
        // Get nearest node when on the node
        Assert.assertEquals(graph.getNearestNode(new Vector2(26, 3)), nodes.get(0));

    }

    @Test
    public void nearestNodeNoNode() {
        // Check when there are no nodes
        PathGraph tempGraph = new PathGraph();
        Assert.assertEquals(tempGraph.getNearestNode(new Vector2(2,2)).position, new Vector2(2147483647, 2147483647));
    }

    @Test
    public void generatePath(){

        // Basic path
        Assert.assertEquals(graph.findPath(nodes.get(4), nodes.get(10)), nodes.get(0));

        // No path
        PathGraph tempGraph = new PathGraph();
        PathNode node1 = new PathNode(new Vector2(2,2), false);
        PathNode node2 = new PathNode(new Vector2(4,2), false);
        PathNode node3 = new PathNode(new Vector2(2,4), false);
        PathNode node4 = new PathNode(new Vector2(4,4), false);

        tempGraph.addNode(node1);
        tempGraph.addNode(node2);
        tempGraph.addNode(node3);
        tempGraph.addNode(node4);

        Assert.assertNull(tempGraph.findPath(node1, node4));

        // 2 Paths

        tempGraph.addEdge(node1,node2);
        tempGraph.addEdge(node1,node3);
        tempGraph.addEdge(node3,node4);
        tempGraph.addEdge(node2,node4);

        PathNode result = tempGraph.findPath(node1,node4);

        Assert.assertTrue(result == node2 || result == node3);

    }

    @Test
    public void getNodeWithMostEdgesSimple() {
        // node with only 1 edge
        Assert.assertEquals(graph.getMostEdgesAdjacentNode(nodes.get(5)), nodes.get(2));
    }

    @Test
    public void getNodeWithMostEdges() {
        // node where you have to work it out
        Assert.assertEquals(graph.getMostEdgesAdjacentNode(nodes.get(9)), nodes.get(2));

    }

    @Test
    public void getNodeWithMostEdgesEqual() {
        // nodes have both the same number of edges
        PathNode node1 = new PathNode(new Vector2(2, 2), false);
        PathNode node2 = new PathNode(new Vector2(3, 3), false);
        PathNode node3 = new PathNode(new Vector2(4, 4), false);
    }
    @Test
    public void addingNodesWithArray() {
        // Tests adding nodes as an array
        PathNode node1 = new PathNode(new Vector2(2, 2), false);
        PathNode[] tempNodes = {node1};
        PathGraph tempGraph = new PathGraph(tempNodes);
    }

    @Test
    public void getNodeWithMostEdges1Node() {
        PathNode node1 = new PathNode(new Vector2(2, 2), false);
        PathNode[] tempNodes = {node1};
        PathGraph tempGraph = new PathGraph(tempNodes);

        // Testing when there is only 1 node
        PathNode result = tempGraph.getMostEdgesAdjacentNode(node1);
        Assert.assertEquals(result.position, new Vector2(-1, -1));
    }

    @Test
    public void getNodeWithMostEdgesSameEdges() {
        // Test when the nodes have the same number of edges
        PathNode node1 = new PathNode(new Vector2(2, 2), false);
        PathNode node2 = new PathNode(new Vector2(3, 3), false);
        PathNode node3 = new PathNode(new Vector2(4, 4), false);
        PathNode[] tempNodes = {node1,node2,node3};
        PathGraph tempGraph = new PathGraph(tempNodes);
        tempGraph.addEdge(node1, node2);
        tempGraph.addEdge(node2, node3);

        PathNode result = tempGraph.getMostEdgesAdjacentNode(node2);
        Assert.assertTrue((result == node1 || result == node3));

    }

    @Test
    public void getRandomNodes(){
        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(graph.getRandomWorkingSystem().isWorkingSystem());
            Assert.assertTrue(graph.getRandomNonSystem().isNonSystem());
        }
    }
}
