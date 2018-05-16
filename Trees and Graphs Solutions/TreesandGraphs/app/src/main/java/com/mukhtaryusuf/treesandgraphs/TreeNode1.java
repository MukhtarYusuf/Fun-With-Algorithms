package com.mukhtaryusuf.treesandgraphs;

import java.util.Random;

/**
 * Created by mukhtaryusuf on 5/10/18.
 */

public class TreeNode1 {
    public int val;
    public TreeNode1 left;
    public TreeNode1 right;
    private int size;

    public TreeNode1(int val){
        this.val = val;
        this.size = 1;
    }

    public TreeNode1 getRandomNode(){
        Random rand = new Random();
        int index = rand.nextInt(size) + 1;

        if(index == size)
            return this;
        else if(left != null && index <= left.size)
            return left.getRandomNode();
        else
            return right.getRandomNode();
    }

    public void insert(int v){
        if(v <= val){
            if(left == null)
                left = new TreeNode1(v);
            else
                left.insert(v);
        }else{
            if(right == null)
                right = new TreeNode1(v);
            else
                right.insert(v);
        }
        size++;
    }

}
