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

    public int getRank(int data){
        if(data == val)
            return leftSize;
        else if(data < val){
            if(left == null)
                return -1;
            else
                return left.getRank(data);
        }else{
            if(right == null)
                return -1;
            else{
                int rightResult = right.getRank(data);
                if(rightResult == -1)
                    return -1;
                else
                    return leftSize + 1 + rightResult;
            }
        }
    }
}
