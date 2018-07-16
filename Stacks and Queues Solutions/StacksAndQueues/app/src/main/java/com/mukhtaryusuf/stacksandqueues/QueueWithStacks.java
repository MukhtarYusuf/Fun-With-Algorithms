package com.mukhtaryusuf.stacksandqueues;

import java.util.Stack;

public class QueueWithStacks<T>{
    private Stack<T> stack1;
    private Stack<T> stack2;

    public QueueWithStacks(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void insert(T value){
        stack1.push(value);
    }
    public boolean isEmpty(){
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
