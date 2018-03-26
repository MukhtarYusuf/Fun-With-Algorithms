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

    /*
    3. List of Depths
     */
    public ArrayList<ArrayList<TreeNode>> listOfDepths(TreeNode root){
        ArrayList<ArrayList<TreeNode>> result = new ArrayList<>();

        if(root == null)
            return result;

        ArrayList<TreeNode> parents = new ArrayList<>();
        parents.add(root);

        while(!parents.isEmpty()){
            ArrayList<TreeNode> children = new ArrayList<>();
            result.add(new ArrayList<>(parents));
            for(TreeNode node : parents){
                if(node.left != null)
                    children.add(node.left);
                if(node.right != null)
                    children.add(node.right);
            }
            parents = children; //Move down one level
        }

        return result;
    }

    /*
    4. Check Balanced
     */
    //Solution 1 to problem 4: Nested recursive calls
    public int calcDepth(TreeNode node){
        if(node == null)
            return -1;
        int maxLeft = 1 + calcDepth(node.left);
        int maxRight = 1 + calcDepth(node.right);
        return Math.max(maxLeft, maxRight);
    }
    public boolean recIsBalanced(TreeNode treeNode){
        if(treeNode == null)
            return true;

        int leftDepth = calcDepth(treeNode.left);
        int rightDepth = calcDepth(treeNode.right);
        int diff = Math.abs(leftDepth-rightDepth);
        if(diff > 1) //Not balanced at this node;
            return false;
        return recIsBalanced(treeNode.left) && recIsBalanced(treeNode.right);
    }

    //Solution 2 to problem 4: Make each node return it's height or an error code for imbalance
    public int recBalanced(TreeNode node){
        if(node == null)
            return -1;

        int leftHeight = recBalanced(node.left); //Height of left subtree
        int rightHeight = recBalanced(node.right); //Height of right subtree
        if(leftHeight == Integer.MIN_VALUE || rightHeight == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        int diff = Math.abs(leftHeight - rightHeight);
        if(diff > 1)
            return  Integer.MIN_VALUE;

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public boolean balanced(TreeNode root){
        return (recBalanced(root) != Integer.MIN_VALUE);
    }

    /*
    5. Validate BST
    */
    //Solution 1 to problem 5
    public boolean validateBST(TreeNode node){
        if(node == null)
            return true;
        Integer min = null;
        Integer max = null;
        return recValidateBST(node, min, max);
    }
    public boolean recValidateBST(TreeNode treeNode, Integer minimum, Integer maximum){
        if(treeNode == null)
            return true;
        if(minimum != null && minimum > treeNode.val)
            return false;
        if(maximum != null && treeNode.val > maximum)
            return false;
        return recValidateBST(treeNode.left, minimum, treeNode.val) &&
                recValidateBST(treeNode.right, treeNode.val+1, maximum);
    }

    /*Solution 2 to problem 5: Construct an array list doing an in-order traversal on the binary tree,
                               then check if the array list is sorted
     */

    /*
    6. In order Successor
     */
    public TreeNode findSuccessor(TreeNode node){
        if(node == null)
            return null;

        TreeNode cur = node; TreeNode prev = node;
        if(cur.right != null){
            cur = cur.right;
            while(cur != null){
                prev = cur;
                cur = cur.left;
            }
        }else{
            cur = cur.parent;
            while(cur != null && cur.left != prev){//Check
                prev = cur;
                cur = cur.parent;
            }
        }
        return cur;
    }
}
