package com.team23.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.team23.game.ai.graph.PathNode;
import com.team23.game.ai.graph.queue.PriorityItem;
import com.team23.game.ai.graph.queue.PriorityQueue;
import org.junit.Assert;
import org.junit.Test;

public class Queue {

    @Test
    public void testPop() {
        PriorityQueue testQueue = new PriorityQueue();

        PathNode node1 = new PathNode(new Vector2(2,2), false);
        PathNode node2 = new PathNode(new Vector2(2,2), false);

        PriorityItem item = new PriorityItem(node1,node2,9,1);

        testQueue.push(item);

        PriorityItem result = testQueue.pop();
        Assert.assertEquals(result, item);
    }

    @Test
    public void testPriorityPop(){
        PriorityQueue testQueue = new PriorityQueue();

        PathNode node1 = new PathNode(new Vector2(2,2), false);
        PathNode node2 = new PathNode(new Vector2(2,2), false);

        PriorityItem priority3 = new PriorityItem(node1,node2,3,1);
        PriorityItem priority2 = new PriorityItem(node1,node2,2,1);
        PriorityItem priority1 = new PriorityItem(node1,node2,1,1);

        testQueue.push(priority1);
        testQueue.push(priority3);
        testQueue.push(priority2);

        PriorityItem result = testQueue.pop();
        Assert.assertEquals(result, priority1);
        result = testQueue.pop();
        Assert.assertEquals(result, priority2);
        result = testQueue.pop();
        Assert.assertEquals(result, priority3);
    }


    @Test
    public void samePriority(){
        PathNode node1 = new PathNode(new Vector2(2,2), false);
        PathNode node2 = new PathNode(new Vector2(2,2), false);

        PriorityQueue testQueue = new PriorityQueue();
        PriorityItem priority2 = new PriorityItem(node1,node2,2,1);
        PriorityItem priority1 = new PriorityItem(node1,node2,2,1);
        testQueue.push(priority1);
        testQueue.push(priority2);

        PriorityItem result = testQueue.pop();
        Assert.assertTrue((result.equals(priority1) || result.equals(priority2)));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmptyPop(){
        PriorityQueue testQueue = new PriorityQueue();
        testQueue.pop();

    }
}
