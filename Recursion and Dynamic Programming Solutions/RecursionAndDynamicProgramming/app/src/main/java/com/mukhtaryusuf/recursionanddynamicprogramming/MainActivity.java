package com.mukhtaryusuf.recursionanddynamicprogramming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int result = tripleStep(3);
        System.out.println("Triple Step Result: " + result);

        result = tripleStep1(2);
        System.out.println("Triple Step 1 Result: " + result);

        boolean[][] grid = new boolean[][]{{true, true, true, true},
                                            {false, false, true, true},
                                            {true, false, true, true},
                                            {true, true, true, true}
                                        };

        ArrayList<Point> robotPathResult = robotPath(grid);
        System.out.println("----------Robot Path Result----------");
        printList(robotPathResult);

        robotPathResult = robotPath1(grid);
        System.out.println("----------Robot Path 1 Result----------");
        printList(robotPathResult);

        Stack<Integer> sourceStack = new Stack<>();
        Stack<Integer> tempStack = new Stack<>();
        Stack<Integer> destStack = new Stack<>();

        sourceStack.push(5);
        sourceStack.push(4);
        sourceStack.push(3);
        sourceStack.push(2);
        sourceStack.push(1);

        System.out.println("----------Towers of Hanoi Result----------");
        System.out.println("Before Hanoi...");
        System.out.println(sourceStack);
        solveHanoi(sourceStack, tempStack, destStack);

        System.out.println("After Hanoi...");
        System.out.println(destStack);

    }

    public void printList(ArrayList<Point> pList){
        for(Point p : pList)
            System.out.println(p);
    }

    //Solution 1: Brute Force. Time:O(3^n), Space: O(n)
    public int tripleStep(int n){
        if(n < 0)
            return 0;
        if(n == 0)
            return 1;

        int nWays = 0;
        nWays += tripleStep(n-1);
        nWays += tripleStep(n-2);
        nWays += tripleStep(n-3);

        return nWays;
    }

    //Solution 2: Dynamic Programming. Time:O(n), Space: O(n)
    public int tripleStep1(int n){
        int[] cache = new int[n+1];
        Arrays.fill(cache, -1);
        return tripleStep1(n, cache);
    }

    public int tripleStep1(int n, int[] cache){
        if(n < 0)
            return 0;
        if(n == 0)
            return 1;
        if(cache[n] > -1)//Already Computed
            return cache[n];
        else{
            cache[n] = tripleStep1(n-1, cache) + tripleStep1(n-2, cache) + tripleStep1(n-3, cache);
            return cache[n];
        }
    }

    public ArrayList<Point> robotPath(boolean[][] grid){
        if(grid == null || grid.length == 0)
            return null;

        ArrayList<Point> result = new ArrayList<>();
        robotPath(grid, 0, 0, result);

        return result;
    }

    public boolean robotPath(boolean[][] grid, int row, int col, ArrayList<Point> path){
        if(row >= grid.length || col >= grid[0].length || !grid[row][col])
            return false;

        boolean isAtEnd = (row == grid.length-1 && col == grid[0].length-1);

        if(isAtEnd || robotPath(grid, row+1, col, path) || robotPath(grid, row, col+1, path)){
            Point p = new Point(row, col);
            path.add(p);
            return true;
        }

        return false;
    }

    public ArrayList<Point> robotPath1(boolean[][] grid){
        if(grid == null || grid.length == 0)
            return null;

        ArrayList<Point> result = new ArrayList<>();
        HashSet<Point> cache = new HashSet<>();
        robotPath1(grid, 0, 0, result, cache);
        return result;
    }

    public boolean robotPath1(boolean[][] grid, int row, int col, ArrayList<Point> points, HashSet<Point> cache){
        if(row >= grid.length || col >= grid[0].length || !grid[row][col])
            return false;

        Point p = new Point(row, col);
        if(cache.contains(p))
            return false;

        boolean isAtEnd = (row == grid.length-1 && col == grid[0].length-1);
        if(isAtEnd || robotPath1(grid, row+1, col, points, cache) || robotPath1(grid, row, col+1, points, cache)){
            points.add(p);
            return true;
        }

        cache.add(p);
        return false;
    }

    public int magicIndex(int[] array){
        if(array == null || array.length == 0)
            return -1;

        return recMagicIndex(array, 0, array.length-1);
    }

    public int recMagicIndex(int[] array, int lower, int upper){
        if(lower > upper)
            return -1;

        int mid = (lower + upper)/2;

        if(mid > array[mid])//Search Right Half
            return recMagicIndex(array, mid+1, upper);
        else if(mid < array[mid])//Search Left Half
            return recMagicIndex(array, lower, mid-1);
        else
            return mid;
    }

    public int magicIndex1(int[] array){
        if(array == null || array.length == 0)
            return -1;
        return recMagicIndex1(array, 0, array.length-1);
    }

    public int recMagicIndex1(int[] array, int lower, int upper){
        if(lower > upper)
            return -1;

        int midIndex = (lower + upper)/2;
        int midValue = array[midIndex];

        if(midIndex == midValue)
            return midIndex;

        int leftIndex = Math.min(midIndex-1, midValue);
        int leftResult = recMagicIndex1(array, lower, leftIndex);
        if(leftResult != -1)
            return leftResult;

        int rightIndex = Math.max(midIndex+1, midValue);
        int rightResult = recMagicIndex1(array, rightIndex, upper);

        return rightResult;
    }

    public ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index){
        ArrayList<ArrayList<Integer>> allSubsets = null;
        if(index == set.size()){
            allSubsets = new ArrayList<>();
            allSubsets.add(new ArrayList<Integer>());
        }else{
            allSubsets = getSubsets(set, index+1);

            ArrayList<ArrayList<Integer>> extraSubsets = new ArrayList<>();
            int current = set.get(index);

            for(ArrayList<Integer> subset : allSubsets){
                ArrayList<Integer> newSubset = new ArrayList<>();
                newSubset.addAll(subset);
                newSubset.add(current);
                extraSubsets.add(newSubset);
            }
            allSubsets.addAll(extraSubsets);
        }

        return allSubsets;
    }

    //Solution 1. Time: O(logs), Space: O(s)
    public int multiply(int a, int b){
        int smaller = a < b ? a : b;
        int bigger = a < b ? b : a;
        int[] cache = new int[smaller+1];

        return recMultiply(bigger, smaller, cache);
    }

    public int recMultiply(int bigger, int smaller, int[] cache){
        if(smaller == 0)
            return 0;
        if(smaller == 1)
            return bigger;
        if(cache[smaller] > 0)
            return cache[smaller];

        int halfSmaller = smaller >> 1;
        int leftResult = recMultiply(bigger, halfSmaller, cache);
        int rightResult = leftResult;

        if(smaller % 2 == 1)
            rightResult = recMultiply(bigger, smaller - halfSmaller, cache);

        cache[smaller] = leftResult + rightResult;
        return cache[smaller];
    }

    //Solution 2. Time: O(logs), Space: O(1)
    public int multiply1(int a, int b){
        int smaller = a < b ? a : b;
        int bigger = a < b ? b : a;

        return recMultiply1(bigger, smaller);
    }

    public int recMultiply1(int bigger, int smaller){
        if(smaller == 0)
            return 0;
        if(smaller == 1)
            return bigger;

        int halfSmaller = smaller >> 1;
        int halfResult = recMultiply1(bigger, halfSmaller);

        if(smaller % 2 == 0)
            return halfResult + halfResult;
        else
            return halfResult + halfResult + bigger;
    }

    public void solveHanoi(Stack<Integer> source, Stack<Integer> temp, Stack<Integer> dest){
        int n = source.size();
        if(n == 0)
            return;

        recSolveHanoi(source, temp, dest, n);
    }

    public void recSolveHanoi(Stack<Integer> source, Stack<Integer> temp, Stack<Integer> dest, int n){
        if(n == 0)
            return;

        recSolveHanoi(source, dest, temp, n-1);
        dest.push(source.pop());
        recSolveHanoi(temp, source, dest, n-1);
    }
}
