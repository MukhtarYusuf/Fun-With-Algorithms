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

    //Solution 2
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

    /*
    Problem: Given a String, find the length of the longest substring without repeating characters

    Solution 1: Two pointer solution using HashMap. Time: O(n), Space: O(n)
    Solution 2: Two pointer solution using Array of Constant Size. Time: O(n), Space: O(1)
     */

    //Solution 2
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int length = s.length();
        if (length == 1)
            return 1;
        int longestLength = 0;
        int tempLength = 0;
        int tailPointer = 0;
        boolean[] tracker = new boolean[256];
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (tracker[c]) {//Duplicate found
                while (tailPointer < i) {
                    char ch = s.charAt(tailPointer);
                    tracker[ch] = false;
                    tailPointer++;
                    tempLength--;
                    if (ch == c)
                        break;
                }
            }
            tracker[c] = true;
            tempLength++;
            longestLength = Math.max(longestLength, tempLength);
        }
        return longestLength;
    }

    /*
    Problem: Given a String s, find the longest palindromic substring in s

    Solution: Two pointer expanding solution
     */

    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0)
            return null;
        int length = s.length();
        boolean odd = (length%2 != 0);
        int longestLength = 0;
        String result = "";
        for(int i = 0; i < length; i++){//Check
            int lwr = i; int upr = i+1;
            String tempString = calcPalindromeLength(s,lwr,upr,longestLength);
            if(tempString != null){
                longestLength = tempString.length();
                result = tempString;
            }
            lwr = i; upr = i;
            tempString = calcPalindromeLength(s,lwr,upr,longestLength);
            if(tempString != null){
                longestLength = tempString.length();
                result = tempString;
            }
        }
        return result;
    }
}
