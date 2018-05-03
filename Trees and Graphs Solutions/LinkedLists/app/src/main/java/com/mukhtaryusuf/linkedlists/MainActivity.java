package com.mukhtaryusuf.linkedlists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinkedList list = new LinkedList();
        list.insertLast(1);
        list.insertLast(3);
        list.insertLast(5);
        list.insertLast(7);
        list.insertLast(6);
        list.insertLast(15);
        list.insertLast(10);
        list.insertLast(5);
        list.insertLast(1);
        list.insertLast(3);
        list.insertLast(5);
        list.insertLast(3);
        list.insertLast(5);
        list.insertLast(1);

        LinkedList list1 = new LinkedList();
        list1.insertLast(6);
        list1.insertLast(1);
        list1.insertLast(7);
        list1.insertLast(1);

        LinkedList list2 = new LinkedList();
        list2.insertLast(2);
        list2.insertLast(9);
        LNode randomNode = list1.getRandomNode();
        list2.insertLast(randomNode);

        LinkedList list3 = new LinkedList();
        list3.insertLast(1);
        list3.insertLast(3);
        list3.insertLast(6);
        list3.insertLast(3);
        list3.insertLast(1);

        LinkedList list4 = new LinkedList();
        list4.insertLast(6);
        list4.insertLast(1);
        list4.insertLast(7);
        list4.insertLast(8);
        LNode testLoopStart = list4.getRandomNode();
        //list4.insertLast(testLoopStart);


//        System.out.println("Before Removal:");
//        list.displayList();
//        removeDups1(list.head);
//        System.out.println("After Removal:");
//        list.displayList();

//        LNode kNode = kToLast(list.head, 0);
//        LNode kNode = kToLast1(list.head, 10);
        LNode kNode = kToLast2(list.head, 10);
        if(kNode != null)
            System.out.println("K to Last Node: " + kNode.value);
        else
            System.out.println("K to Last Node: null");

        System.out.println("Addition of reverse order lists: ");
        System.out.print("List 1: ");
        list1.displayList();
        System.out.println();
        System.out.print("List 2: ");
        list2.displayList();

        LinkedList result = new LinkedList();
//        result.head = sumLists(list1.head, list2.head);
//        result.head = recSumLists(list1.head, list2.head, 0);
        result.head = sumList1Caller(list1.head, list2.head);
        System.out.println();
        System.out.print("Result list: ");
        result.displayList();

        System.out.println("Checking if List Below is Palindrome:");
        list3.displayList();
        System.out.println(isPalindrome1(list3.head)? "True" : "False");

        System.out.println("Checking if Lists contain intersection:");
        System.out.print("List 1: ");
        list1.displayList();
        System.out.println();
        System.out.print("List 2: ");
        list2.displayList();
        System.out.println(containsIntersection1(list1.head, list2.head)? "True" : "False");

        System.out.println("Detecting loop for list with start loop node: " + testLoopStart.value);
//        list4.displayList();
        LNode startOfLoop = detectLoop(list4.head);
        if(startOfLoop != null)
            System.out.println("The Node at the beginning of the loop is: " + startOfLoop.value);
        else
            System.out.println("This list contains no loops");

        System.out.println("Partitioning List: ");
        list.displayList();
        System.out.println("List after partition: ");
        partition(list.head, 7);
        list.displayList();

        System.out.println("----------Testing Hashmap----------");
        HashMap<Integer,Integer> hm = new HashMap<>();
        hm.put(1,1);
        hm.put(2,2);
        hm.put(3,3);
        hm.put(4,4);
        Iterator i = hm.entrySet().iterator();
        while(i.hasNext()){
            Map.Entry en = (Map.Entry)i.next();
            int key = (int)en.getKey();
            int value = (int)en.getValue();
            System.out.println("Key: " + key + " value: " + value);
        }
    }

    public void removeDups(LNode head){
        if(head == null)
            return;

        HashSet<Integer> hs = new HashSet<>();
        LNode prev = head;
        LNode cur = head;

        while(cur != null){
            if(!hs.contains(cur.value)) {
                hs.add(cur.value);
                prev = cur;
            }
            else
                prev.next = cur.next;
            cur = cur.next;
        }
    }

    public void removeDups1(LNode head){
        if(head == null)
            return;
        LNode cur = head;
        while(cur.next != null){
            LNode prev = cur;
            LNode runner = cur.next;
            while(runner != null){
                if(cur.value == runner.value) {//Duplicate
                    prev.next = runner.next;
                }else{
                    prev = runner;
                }
                runner = runner.next;
            }
            cur = cur.next;
        }
    }

    //Iterative approach. Time: O(n), Space: O(1)
    public LNode kToLast(LNode h, int k) {
        if (h == null)
            return null;
        if (k <= 0)
            return null;

        int listSize = 0;
        LNode cur = h;
        while (cur != null) {
            listSize++;
            cur = cur.next;
        }
        int index = listSize - k;
        if (index < 0)
            return null;
        int i = 0;
        cur = h;
        while (i < index) {
            cur = cur.next;
            i++;
        }
        return cur;
    }

    //Recursive approach. Time: O(n), Space: O(n)
    public class Index{
        int index = 0;
    }

    public LNode kToLast1(LNode h, int k){
        if(h == null)
            return null;
        Index index = new Index();
        return recKToLast1(h,k,index);
    }

    public LNode recKToLast1(LNode head, int k, Index index){
        if(head == null)
            return null;

        LNode node = recKToLast1(head.next, k, index);
        index.index = index.index + 1;
        if(index.index == k)
            return head;

        return node;
    }

    //Iterative approach 2. Time: O(n), Space: O(1)
    //Uses the runner technique
    public LNode kToLast2(LNode h, int k){
        if(h == null)
            return null;
        LNode p1 = h;
        LNode p2 = h;

        //Put p2 k steps ahead of p1
        for(int i = 0; i < k; i++){
            if(p2 == null)
                return null;
            p2 = p2.next;
        }

        //Iterate simultaneously until p2 hits the end of the list
        while(p2 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    //Sum List Iterative Approach (Reverse Order)
    public LNode sumLists(LNode h1, LNode h2){
        if(h1 == null && h2 == null)
            return null;

        LNode cur1 = h1; LNode cur2 = h2;
        LNode rHead = null; LNode cur3 = null;
        int carry = 0;

        while(cur1 != null || cur2 != null || carry != 0){
            int val1 = 0; int val2 = 0; int val3 = 0;
            if(cur1 != null){
                val1 = cur1.value; cur1 = cur1.next;
            }
            if(cur2 != null){
                val2 = cur2.value; cur2 = cur2.next;
            }
            val3 = val1 + val2 + carry;
            if(val3 >= 10){
                carry = 1; val3 = val3 - 10;
            }else
                carry = 0;
            LNode result = new LNode(val3);
            if(rHead == null){
                rHead = result; cur3 = result;
            }else{
                cur3.next = result;
                cur3 = cur3.next;
            }
        }
        return rHead;
    }

    //Recursive Approach (Reverse Order)
    public LNode recSumLists(LNode h1, LNode h2, int carry){
        if(h1 == null && h2 == null && carry == 0)
            return null;

        int val1 = 0; int val2 = 0; int val3 = 0; int carryLocal = 0;
        LNode h1Next = null; LNode h2Next = null;
        if(h1 != null) {
            val1 = h1.value;
            h1Next = h1.next;
        }
        if(h2 != null) {
            val2 = h2.value;
            h2Next = h2.next;
        }
        val3 = val1 + val2 + carry;
        if(val3 >= 10){
            carryLocal = 1;
            val3 = val3 - 10;
        }
        LNode result = new LNode(val3);
        result.next = recSumLists(h1Next, h2Next, carryLocal);

        return result;
    }

    public class MyInteger{
        public int intValue;

        public MyInteger(int v){ intValue = v; }
    }

    public LNode sumList1Caller(LNode head1, LNode head2){
        LNode[] heads = makeSameLength(head1, head2);
        head1 = heads[0];
        head2 = heads[1];
        MyInteger myInt = new MyInteger(0);
        return recSumLists1(head1, head2, myInt);
    }
    //Recursive Approach (Forward Order)
    public LNode recSumLists1(LNode h1, LNode h2, MyInteger carry){
        if(h1 == null && h2 == null)
            return null;
        LNode result = new LNode(0);
        result.next = recSumLists1(h1.next, h2.next, carry);
        int val1 = 0; int val2 = 0; int val3 = 0;
        if(h1 != null)
            val1 = h1.value;
        if(h2 != null)
            val2 = h2.value;
        val3 = val1 + val2 + carry.intValue;
        if(val3 >= 10){
            carry.intValue = 1;
            val3 = val3 - 10;
        }else
            carry.intValue = 0;
        result.value = val3;
        return result;
    }

    public LNode[] makeSameLength(LNode h1, LNode h2){
        int length1 = calcListLen(h1);
        int length2 = calcListLen(h2);
        int diff = Math.abs(length1-length2);
        if(length1 < length2){
            for(int i = 0; i<diff; i++){
                LNode nHead = new LNode(0);
                nHead.next = h1;
                h1 = nHead;
            }
        }else if(length1 > length2){
            for(int i = 0; i<diff; i++){
                LNode nHead = new LNode(0);
                nHead.next = h2;
                h2 = nHead;
            }
        }
        return new LNode[]{h1, h2};
    }
    public int calcListLen(LNode h){
        int length = 0;
        LNode current = h;
        while(current != null){
            length++;
            current = current.next;
        }
        return length;
    }

    public boolean isPalindrome(LNode head){
        if(head == null)
            return false;
        Stack<LNode> s = new Stack<>();
        LNode cur = head;
        while(cur != null){
            s.push(cur);
            cur = cur.next;
        }
        cur = head;
        while(!s.empty()){
            LNode poppedNode = s.pop();
            if(cur.value != poppedNode.value)
                return false;
            cur = cur.next;
        }
        return true;
    }

    public class Result{
        LNode head;
        boolean isValid;
        public Result(LNode h){head = h; isValid = true;}
    }
    public boolean isPalindrome1(LNode head){
        if(head == null)
            return false;
        Result result = new Result(head);
        return recIsPalindrome(head, result);
    }
    public boolean recIsPalindrome(LNode tail, Result res){

        if(tail.next != null){
            recIsPalindrome(tail.next, res);
        }
        boolean isValid = res.head.value == tail.value;
        res.isValid = res.isValid && isValid;
        if(res.head.next != null)
            res.head = res.head.next;

        return res.isValid;
    }

    public boolean containsIntersection(LNode h1, LNode h2){
        if(h1 == null || h2 == null)
            return false;
        HashSet<LNode> hs = new HashSet<>();
        LNode cur1 = h1;
        LNode cur2 = h2;
        while(cur1 != null){
            hs.add(cur1);
            cur1 = cur1.next;
        }
        while(cur2 != null){
            if(hs.contains(cur2))
                return true;
            cur2 = cur2.next;
        }
        return false;
    }

    public class CustomNodes{
        LNode first;
        LNode second;
        public CustomNodes(LNode f, LNode s){first = f; second = s;}
    }

    public boolean containsIntersection1(LNode h1, LNode h2){
        if(h1 == null || h2 == null)
            return false;
        CustomNodes cn = new CustomNodes(h1, h2);
        makeSameLength1(cn);

        h1 = cn.first; h2 = cn.second;

        while(h1 != null && h2 != null){
            if(h1 == h2)
                return true;
            h1 = h1.next;
            h2 = h2.next;
        }
        return false;
    }

    public void makeSameLength1(CustomNodes customNodes){//Assumes non null lists
        int length1 = calcListLen(customNodes.first);
        int length2 = calcListLen(customNodes.second);
        int diff = Math.abs(length1-length2);
        if(length1 < length2){
            for(int i = 0; i < diff; i++){
                customNodes.first = customNodes.first.next;
            }
        }else if(length1 > length2){
            for(int i = 0; i < diff; i++){
                customNodes.second = customNodes.second.next;
            }
        }
    }

    public LNode detectLoop(LNode head){
        if(head == null)
            return null;

        HashSet<LNode> hs = new HashSet<>();
        LNode cur = head;
        while(cur != null){
            if(!hs.contains(cur))
                hs.add(cur);
            else
                return cur;

            cur = cur.next;
        }
        return cur;
    }

    public LNode detectLoop1(LNode head){
        if(head == null)
            return null;
        //Detect Cycle
        LNode slower = head; LNode runner = head; LNode result = head;
        do{
            if(runner == null || runner.next == null)
                return null;
            slower = slower.next;
            runner = runner.next.next;
        }while(slower != runner);
        //Detect Cycle Start
        while(result != slower){
            result = result.next;
            slower = slower.next;
        }
        return result;
    }

    public void partition(LNode head, int x){
        if(head == null)
            return;
        LNode cur = head; LNode tail = head;
        while(cur != null){
            if(cur.value < x){
                swap(tail, cur);
                tail = tail.next;
            }
            cur = cur.next;
        }
    }

    public void swap(LNode node1, LNode node2){
        int temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }

    /*--List Reversal Approaches--
    1. Stack approach. Time: O(n), Space: O(n)
    2. Recursive approach. Time: O(n), Space: O(n)
    3. Iterative approach. Time: O(n), Space: O(1)
     */

    public LNode reverseList(LNode head){
        if(head == null)
            return null;
        LNode prev = null; LNode cur = head;
        while(cur != null){
            LNode nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }
        return prev;
    }
}
