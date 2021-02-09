package com.team23.game.ai.graph.queue;

import java.util.ArrayList;

public class PriorityQueue {
    private ArrayList<PriorityItem> queue;

    public PriorityQueue(){
        queue = new ArrayList<>();
    }

    public PriorityQueue(PriorityItem[] arr){
        this();
        for(PriorityItem item:arr){
            push(item);
        }
    }

    public void push(PriorityItem insert){
        for(int i=0;i<queue.size();i++){
            if(insert.priority<queue.get(i).priority){
                queue.add(i,insert);
                return;
            }
        }
        queue.add(insert);
    }

    public PriorityItem pop(){
        return queue.remove(0);
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
