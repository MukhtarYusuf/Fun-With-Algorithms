package com.mukhtaryusuf.stacksandqueues;

import java.util.Stack;

/**
 * Created by mukhtaryusuf on 1/24/18.
 */

public class StacksOfStacks<T> {
    private java.util.Stack<T> dStack;
    private java.util.Stack<java.util.Stack> setOfStacks;
    int stackCap = 4;

    public StacksOfStacks(){
        setOfStacks = new Stack<>();
        dStack = new Stack<>();
        setOfStacks.push(dStack);
    }

    public void push(T val){
        java.util.Stack<T> topStack = setOfStacks.peek();
        if(topStack.size() == stackCap){
            Stack newStack = new Stack();
            setOfStacks.push(newStack);
            newStack.push(val);
        }else
            topStack.push(val);
    }
    public T pop(){
        java.util.Stack<T> topStack = getTopStack();
        return topStack.pop();
    }
    public T peek(){
        java.util.Stack<T> topStack = getTopStack();
        return topStack.peek();
    }

    public boolean isEmpty(){
        return (setOfStacks.size() == 1 && setOfStacks.peek().isEmpty());
    }

    private java.util.Stack getTopStack(){
        java.util.Stack<T> tStack = setOfStacks.peek();
        if(tStack.isEmpty() && tStack != dStack){
            setOfStacks.pop();
            tStack = setOfStacks.peek();
        }
        return tStack;
    }
}
