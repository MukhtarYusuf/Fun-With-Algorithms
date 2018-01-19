package com.mukhtaryusuf.linkedlists;

/**
 * Created by mukhtaryusuf on 1/2/18.
 */

public class LNode {
    LNode next;
    int value;

    public LNode(int v){
        value = v;
    }

    public void displayNode(){
        System.out.print(value);
    }
}
