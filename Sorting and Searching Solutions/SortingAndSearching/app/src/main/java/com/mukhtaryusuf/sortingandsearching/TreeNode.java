package com.mukhtaryusuf.sortingandsearching;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    int leftSize;

    public TreeNode(int v){
        val = v;
    }

    public void insert(int data){
        if(data <= val){
            if(left == null)
                left = new TreeNode(data);
            else
                left.insert(data);
            leftSize++;
        }else{
            if(right == null)
                right = new TreeNode(data);
            else
                right.insert(data);
        }
    }

}
