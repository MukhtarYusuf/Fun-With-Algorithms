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

}
