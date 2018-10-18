package com.mukhtaryusuf.sortingandsearching;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    /*
    Problem 1: Sorted Merge
     */

    //Solution: Two Pointer Solution from Ends of Arrays
    public void merge(int[] a, int[] b, int length){
        if(a == null || b == null)
            return;
        int index = a.length - 1;
        int aPtr = length - 1;
        int bPtr = b.length - 1;
        while(aPtr >= 0 && bPtr >= 0){
            if(a[aPtr] > b[bPtr])
                a[index--] = a[aPtr--];
            else
                a[index--] = b[bPtr--];
        }
        //Process b if not done. No need to process a
        while(bPtr >= 0)
            a[index--] = b[bPtr--];
    }

    /*
    Problem 2: Group Anagrams
     */

    //Solution: Sorting with HashMap
    public void groupAnagrams(String[] words){
        if(words == null || words.length == 0)
            return;
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            String s = words[i];
            char[] charString = s.toCharArray();
            Arrays.sort(charString);
            String sortedString = String.copyValueOf(charString);
            if(!result.containsKey(sortedString))
                result.put(sortedString, new ArrayList<String>());
            (result.get(sortedString)).add(s);
        }
        int i = 0;
        for(String s : result.keySet()){
            ArrayList<String> anagrams = result.get(s);
            for(String anagram : anagrams)
                words[i++] = anagram;
        }
    }

    /*
    Problem 3: Search in Rotated Array
     */

    //Solution 1: Binary Search to find pivot and Element. Time: O(logn), Space: O(1). Works on Array Without Duplicates
    public int binRotatedSearch(int[] array, int key){
        if(array == null || array.length == 0)
            return -1;
        int pivot = indexBinarySearch(array);
        if(key > array[array.length-1])
            return binarySearch(array, 0, pivot-1, key);
        else
            return binarySearch(array, pivot, array.length-1, key);
    }

    public int indexBinarySearch(int[] array){
        int lower = 0;
        int upper = array.length-1;
        int mid = 0;
        while(lower < upper){
            mid = (lower + upper)/2;
            if(array[mid] < array[array.length-1])
                upper = mid;
            else
                lower = mid + 1;
        }
        return lower;
    }

    public int binarySearch(int[] array, int lower, int upper, int key){
        int mid = 0;
        while(lower <= upper){
            mid = (lower + upper)/2;
            if(array[mid] == key)
                return mid;
            else if(array[mid] > key)
                upper = mid - 1;
            else
                lower = mid + 1;
        }
        return -1;
    }

    //Solution 2: Recursive Modified Binary Search. Works on Array With Duplicates. Time: O(logn), Space: O(logn)
    public int rotatedBinSearch(int[] array, int key){
        if(array == null || array.length == 0)
            return -1;
        return rotatedBinSearch(array, 0, array.length-1, key);
    }

    public int rotatedBinSearch(int[] array, int lower, int upper, int key){
        if(lower > upper)
            return -1;
        int mid = (lower + upper)/2;
        if(array[mid] == key)
            return mid;

        if(array[mid] < array[lower]){
            if(array[mid] < key && key <= array[upper])
                return rotatedBinSearch(array, mid+1, upper, key);
            else
                return rotatedBinSearch(array, lower, mid-1, key);
        }else if(array[mid] > lower){
            if(array[lower] <= key && key < array[mid])
                return rotatedBinSearch(array, lower, mid-1, key);
            else
                return rotatedBinSearch(array, mid+1, upper, key);
        }else if(array[mid] == array[lower]){
            if(array[mid] != array[upper])
                return rotatedBinSearch(array, mid+1, upper, key);
            else{
                int result = rotatedBinSearch(array, lower, mid-1, key);
                if(result == -1)
                    result = rotatedBinSearch(array, mid+1, upper, key);
                return result;
            }
        }
        return -1;
    }

    /*
    Problem 4: Sorted Search, No Size
     */

    //Solution 1: Find Range by exponential increase of upper, and update of lower. Then do binarySearch() of range.
    //Time: O(logn), Space: O(1)
    public int searchNoSize(Listy listy, int key){
        if(listy == null)
            return -1;
        int lower = 0; int upper = 1;
        int value = listy.elementAt(upper);
        while(value != -1 && value < key){
            lower = upper;
            upper *= 2;
            value = listy.elementAt(upper);
        }
        return binarySearch(listy, lower, upper, key);
    }

    public int binarySearch(Listy listy, int lower, int upper, int key){
        int mid = 0;
        while(lower <= upper){
            mid = (lower + upper)/2;
            int midValue = listy.elementAt(mid);
            if(midValue == key)
                return mid;
            else if(midValue == -1 || midValue > key)
                upper = mid - 1;
            else
                lower = mid + 1;
        }
        return -1;
    }

    }
    //Bubble Sort
    public void bubbleSort(int[] array){
        if(array == null || array.length <=1)
            return;

        int n = array.length;
        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < n-1-i; j++){
                if(array[j] > array[j+1])
                    swap(array, j, j+1);
            }
        }
    }

}
