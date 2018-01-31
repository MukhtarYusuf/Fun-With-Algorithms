package com.mukhtaryusuf.stacksandqueues;

import java.util.*;
import java.util.Stack;

/**
 * Created by mukhtaryusuf on 1/25/18.
 */

public class StacksOfStacks1<T> {
    private int topIndex;
    private int stackCap;
    java.util.Stack<T> dStack;
    ArrayList<java.util.Stack> stacks;

    public StacksOfStacks1(){
        topIndex = 0;
        stackCap = 4;
        dStack = new Stack<>();
        stacks = new ArrayList<>();
        stacks.add(dStack);
    }

    public void push(T val){
        Stack<T> topStack = stacks.get(topIndex);
        if(topStack.size() == stackCap){
            Stack<T> nStack = new Stack<>();
            nStack.push(val);
            stacks.add(nStack);
            topIndex++;
        }else
            topStack.push(val);
    }

    public T pop(){
        Stack<T> topStack = stacks.get(topIndex);
        if(topStack.isEmpty() && topStack != dStack){
            stacks.remove(topIndex);
            topIndex--;
            topStack = stacks.get(topIndex);
        }
        if(!topStack.isEmpty())
            return topStack.pop();
        else
            return null;
    }

    public T popAtIndex(int index){
        if(index < 0 || index > topIndex)
            return null;
        Stack<T> stack = stacks.get(index);
        if(!stack.isEmpty())
            return stack.pop();
        else
            return null;
    }

    public boolean isEmpty(){
        return (stacks.get(0).isEmpty());
    }
}
