package com.mukhtaryusuf.stacksandqueues;

/**
 * Created by mukhtaryusuf on 1/23/18.
 */

public class LinkedList<T> {
    LNode<T> head;

    public void insertFirst(T val){
        LNode<T> newNode = new LNode<>();
        newNode.value = val;
        newNode.next = head;
        head = newNode;
    }
    public T removeFirst(){
        LNode<T> removedNode = head;
        if(head != null)
            head = head.next;

        return removedNode.value;
    }

//    public void insertFirst(LNode node){
//        node.next = head;
//        head = node;
//    }
//    public LNode removeFirst(){
//        LNode node = head;
//        if(head != null)
//            head = head.next;
//        return node;
//    }

    public boolean isEmpty(){
        return (head == null);
    }
}
