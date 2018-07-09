package com.mukhtaryusuf.leetcodesolutions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    Problem: Given an array of integers, return indices of the two numbers such that they add up to a specific target

    Solution 1: Two for Loops. Time: O(n^2), Space: O(1)
    Solution 2: HashMap Solution. Time: O(n), Space: O(n)
    Solution 3: Sorting and Searching. Time: O(nlogn), Space: O(n)
    Solution 4: Sorting with Two Pointer Approach. Time: O(nlogn), Space: O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        //Populate HashMap with (values,indices)
        for(int i = 0; i < nums.length; i++){
            hm.put(nums[i], i);
        }

        for(int i = 0; i < nums.length; i++){
            int secondTarget = target - nums[i];
            if(hm.containsKey(secondTarget) && (hm.get(secondTarget) != i)){
                indices[0] = i;
                indices[1] = hm.get(secondTarget);
                break;
            }
        }
        //printAll(2);
        return indices;
    }
}
