package com.mukhtaryusuf.linkedlists;

/**
 * Created by mukhtaryusuf on 1/2/18.
 */

public class LinkedList {
    LNode head;
    private int length = 0;

    public void insertLast(int v){
        LNode newNode = new LNode(v);
        LNode cur = head;
        if(head == null) {
            head = newNode;
            return;
        }
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = newNode;
        length++;
    }

    public void insertLast(LNode n){
        LNode cur = head;
        if(head == null) {
            head = n;
            return;
        }
        while(cur.next != null)
            cur = cur.next;
        cur.next = n;
        length++;
    }

    public LNode getRandomNode(){
        double random = Math.random();
        int randomInt = (int)(random * 100) % length;
        LNode cur = head;
        for(int i = 0; i < randomInt; i++){
            cur = cur.next;
        }

        return cur;
    }

    public void displayList(){
        LNode cur = head;
        while(cur != null){
            System.out.print(cur.value);
            if(cur.next != null)
                System.out.print("->");
            cur = cur.next;
        }
        System.out.println();
    }
    public static void displayList(LNode node){
        LNode cur = node;
        while(cur != null){
            System.out.print(cur.value);
            if(cur.next != null)
                System.out.print("->");
            cur = cur.next;
        }
    }
}
