package com.mukhtaryusuf.treesandgraphs;

/**
 * Created by mukhtaryusuf on 3/14/18.
 */

public class BinarySearchTree {
    public TreeNode root;

    public void insert(int value){
        if(root == null)
            root = new TreeNode(value);
        else{
            TreeNode cur = root;
            TreeNode prev = root;
            boolean isLeft = false;
            while(cur != null){
                prev = cur;
                if(value <= cur.val) {//Go left
                    cur = cur.left;
                    isLeft = true;
                }else{ //Go right
                    cur = cur.right;
                    isLeft = false;
                }
            }
            if(isLeft)
                prev.left = new TreeNode(value);
            else
                prev.right = new TreeNode(value);
        }
    }
}
