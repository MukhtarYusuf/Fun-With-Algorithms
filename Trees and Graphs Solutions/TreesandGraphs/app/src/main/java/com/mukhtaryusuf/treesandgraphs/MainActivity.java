package com.mukhtaryusuf.treesandgraphs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    1. Write a method to check if a route exists between two nodes
     */
    public boolean existsRoute(GraphNode a, GraphNode b){
        if( a == null || b == null)
            return false;

        LinkedList<GraphNode> q = new LinkedList<>();
        q.addLast(a);
        while(!q.isEmpty()){
            GraphNode cur = q.removeFirst();
            if(cur == b)
                return true;
            for(GraphNode graphNode : cur.children)
                q.addLast(graphNode);
        }
        return false;
    }

    /*
    2. Minimal Tree
     */
    public BinarySearchTree minTree(int[] arr){
        if(arr == null)
            return null;

        int length = arr.length;
        BinarySearchTree bst = new BinarySearchTree();
        recMinTree(bst, arr, 0, length-1);
        return bst;
    }

    public void recMinTree(BinarySearchTree binarySearchTree, int[] a, int lower, int upper){
        if(lower > upper)
            return;
        int mid = (lower + upper)/2;
        binarySearchTree.insert(a[mid]); //Process midpoint
        recMinTree(binarySearchTree, a, lower, mid-1); //Process left half
        recMinTree(binarySearchTree, a, mid+1, upper); //Process right half
    }

}
