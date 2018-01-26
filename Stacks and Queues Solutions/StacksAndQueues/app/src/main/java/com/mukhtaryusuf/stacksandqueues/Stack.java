package com.mukhtaryusuf.stacksandqueues;

import java.util.EmptyStackException;

/**
 * Created by mukhtaryusuf on 1/23/18.
 */

public class Stack {
    private LinkedList<NodeWithMin> data;

    public Stack(){
        data = new LinkedList<>();
    }

    public void push(int v){
        int min = Math.min(min(),v);
        NodeWithMin nodeWithMin = new NodeWithMin(v, min);
        data.insertFirst(nodeWithMin);
    }

    public int pop() throws EmptyStackException{
        int poppedValue = 0;
        if(!data.isEmpty()){
            NodeWithMin popped = data.removeFirst();
            poppedValue = popped.value;
        }else
            throw new EmptyStackException();

        return poppedValue;
    }

    public int min(){
        int min = 0;
        if(!data.isEmpty())
            min = data.head.value.min;
        else
            return Integer.MAX_VALUE;

        return min;
    }
}
