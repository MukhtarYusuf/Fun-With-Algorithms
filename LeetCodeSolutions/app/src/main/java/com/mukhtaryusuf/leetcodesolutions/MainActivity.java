package com.mukhtaryusuf.leetcodesolutions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    public String calcPalindromeLength(String s, int lower, int upper, int longestLength){
        int length = 0;
        while(lower >= 0 && upper <= s.length()-1){
            char first = s.charAt(lower);
            char second = s.charAt(upper);
            if(first == second){
                length = (upper-lower) + 1;
            }else{
                break;
            }
            lower--; upper++;
        }
        if(length > longestLength)
            return s.substring(lower+1,upper);
        else
            return null;
    }


    /*
    Problem: Zig-Zag Conversion
     */

    public String convert(String s, int numRows) {

        if(s.length() == 0)
            return "";
        if(numRows == 1)
            return s;
        char[][] mat = new char[numRows][s.length()];
        int numOfDiagonal = numRows - 2; //Number of times not adding vertically
        int xIndexDiag = numRows - 2; //Starting x index when going diagonal
        int xIndexVert = 0;
        boolean isVertical = true;
        StringBuilder sb = new StringBuilder();

        for(int j = 0, count = 0; count < s.length(); j++){
            if(isVertical){
                for(int i = 0; i < numRows && count < s.length(); i++){
                    mat[i][j] = s.charAt(count);
                    count++;
                }
                isVertical = false;
            }else{
                if(xIndexDiag > 0){
                    mat[xIndexDiag][j] = s.charAt(count);
                    xIndexDiag--;
                    count++;
                }else{
                    xIndexDiag = numRows - 2;
                    isVertical = true;
                }
            }
        }
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                if(mat[i][j] != 0)
                    sb.append(mat[i][j]);
            }
        }
        return sb.toString();
    }

    /*
    Problem: Given a 32 bit signed integer, reverse the integer
    e.g. input = 123, Output = 321
     */

    public int reverse(int x) {
        String intString;
        StringBuilder sb = new StringBuilder();
        if(x < 0){
            sb.append("-");
            x = x * -1;
        }
        intString = Integer.toString(x);
        for(int i = intString.length() - 1; i >= 0; i--){
            sb.append(intString.charAt(i));
        }
        String resultString = sb.toString();
        int result;
        try{
            result = Integer.parseInt(resultString);
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    /*
    Problem: Implement atoi, which converts a String to an Integer.
    The function first discards as many whitespace characters as necessary until the first non-whitespace character is
    found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical
    digits as possible, and interprets them as a numerical value. The string can contain additional characters after
    those that form the integral number, which are ignored and have no effect on the behavior of this function. If the
    first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists
    because either str is empty or it contains only whitespace characters, no conversion is performed.
    If no valid conversion could be performed, a zero value is returned.
     */

    public int myAtoi(String str) {
        final int MAX_INT = 2147483647;
        final int MIN_INT = -2147483648;
        boolean isNegative = false;
        long result = 0;
        if(str == null || str.equals(""))
            return 0;
        else{
            str = str.trim();
            if(str.charAt(0) == '-')
                isNegative = true;
        }

        for(int i = str.length()-1, reverseIndex = 0; i >= 0; i--, reverseIndex++){
            int digit = 0;
            try{
                if(str.charAt(i) != '+' && str.charAt(i) != '-'){
                    digit = Integer.parseInt(Character.toString(str.charAt(i)));
                    System.out.println(digit);
                    result += digit * (int) Math.pow(10, reverseIndex);
                }
                if((str.charAt(i) == '+' && i != 0) || (str.charAt(i) == '-' && i != 0))
                    throw new Exception();
            }catch(Exception e){
                digit = 0;
                reverseIndex = -1;
                result = 0;
                System.out.println("Catch executed");
            }
        }
        if(isNegative){
            System.out.println("is negative executed");
            result = result * -1;
        }
        if(result > MAX_INT)
            result = MAX_INT;
        if(result < MIN_INT)
            result = MIN_INT;
        return (int) result;
    }

    /*
    Problem: Determine whether an integer is a palindrome.

    e.g. 1 2 2 1, Output: True
    */

    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        int length = Integer.toString(x).length();

        for(int i = 0, i2 = length-1; i < i2; i++,i2--){
            int digit1 = (x % (int) Math.pow(10, i+1)) / (int) Math.pow(10, i);
            int digit2 = (x % (int) Math.pow(10, i2+1)) / (int) Math.pow(10, i2);

            if(digit1 != digit2)
                return false;
        }
        return true;
    }

    /*
    Problem: Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
    n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
    which together with x-axis forms a container, such that the container contains the most water.

    Note: You may not slant the container and n is at least 2.

    Solution 1: 2 For Loops - Try out every combination of Heights. Time: O(n2), Space: O(1)
    Solution 2: Two Pointer Solution. Time: O(n), Space: O(1)
     */

    //Solution 1: 2 For Loops
    public int maxArea(int[] height){
        int maxArea = 0;
        for(int i = 0; i < height.length-1; i++){
            for(int j = i+1; j < height.length; j++){
                int tempMaxArea = 0;
                int width = 0;
                int height1 = 0;
                int height2 = 0;

                width = Math.abs(i-j);
                if(height[i] < height[j])
                    height1 = height[i];
                else
                    height1 = height[j];

                height2 = Math.abs(height[i] - height[j]);
                tempMaxArea = (width * height1);

                if(tempMaxArea > maxArea)
                    maxArea = tempMaxArea;
            }
        }
        return maxArea;
    }

    //Solution 2: Two Pointer Solution
    public int maxArea1(int[] height) {
        int maxArea = 0;

        for (int i = 0, j = height.length - 1; i < j; ) {
            int tempMaxArea = 0;
            int width = j - i;
            int length = Math.min(height[i], height[j]);

            tempMaxArea = width * length;
            maxArea = Math.max(tempMaxArea, maxArea);

            if (height[i] < height[j])
                i++;
            else
                j--;
        }

        return maxArea;
    }

    /*
    Problem: Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
    Find all unique triplets in the array which gives the sum of zero.

    Note: The solution set must not contain duplicate triplets.
     */

    //Solution: Sorting with 2 Pointer Solution
    public List<List<Integer>> threeSum(int[] nums){
        if(nums == null)
            return null;
        int length = nums.length;
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0; i < length-1; i++){
            if(i == 0 || i > 0 && nums[i] != nums[i-1]){
                int newSum = sum - nums[i];

                int lwr = i+1;
                int upr = length-1;
                while(lwr < upr){
                    int lastSum = nums[lwr]+nums[upr];
                    if(lastSum == newSum){
                        Integer[] resultArray = new Integer[]{nums[i],nums[lwr],nums[upr]};
                        List<Integer> result = new ArrayList<>(Arrays.asList(resultArray));
                        results.add(result);
                        while(lwr < upr && nums[lwr] == nums[lwr+1])
                            lwr++;
                        while(lwr < upr && nums[upr] == nums[upr-1])
                            upr--;
                        lwr++; upr--;
                    }else if(lastSum < newSum)
                        lwr++;
                    else
                        upr--;
                }
            }
        }
        return results;

        /*
        Problem: Given a linked list, remove the n-th node from the end of list and return its head.

        Example: Given list, 1->2->3->4->5, n = 2, return 1->2->3->5.
        Can this be done in one pass?

        Solution 1: Find nth to last node by calculating length first, then deleting it. Time: O(n), Space: O(1)
        Solution 2: Use runner technique to find nth to last node, then delete it. Time: O(n), Space: O(1)
         */

        //Solution 2
        public ListNode removeNthFromEnd(ListNode head, int n){
            if(head == null)
                return null;
            ListNode previous = head, slower = head, faster = head;
            for(int i = 0; i < n; i++)
                faster = faster.next;
            while(faster != null){
                previous = slower;
                slower = slower.next;
                faster = faster.next;
            }
            if(slower == head)
                head = head.next;
            previous.next = slower.next;
            return head;
        }
    }
}
