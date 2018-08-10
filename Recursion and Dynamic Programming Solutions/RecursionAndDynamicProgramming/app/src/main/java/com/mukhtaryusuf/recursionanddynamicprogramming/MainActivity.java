package com.mukhtaryusuf.recursionanddynamicprogramming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int result = tripleStep(3);
        System.out.println("Triple Step Result: " + result);

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
}
