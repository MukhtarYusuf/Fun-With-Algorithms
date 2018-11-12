package com.mukhtaryusuf.sortingandsearching;

/*
Problem 10: Rank From Stream
 */

public class BinarySearchTree {
    TreeNode root;

    public void track(int data){
        if(root == null)
            root = new TreeNode(data);
        else
            root.insert(data);
    }

