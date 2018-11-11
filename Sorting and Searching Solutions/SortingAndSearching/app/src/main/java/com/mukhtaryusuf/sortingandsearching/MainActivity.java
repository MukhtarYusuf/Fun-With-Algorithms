package com.mukhtaryusuf.sortingandsearching;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] data = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        int[] data1 = {1, 2, 3, 4, 5, 6, 7};
        int keyElement = 5;
        System.out.println("----------Testing Rotated Binary Search----------");
        System.out.println("Searching: " + Arrays.toString(data) + " for: " + keyElement);
        System.out.println("Result: " + rotatedBinSearch(data, keyElement));

        Listy listy = new Listy();
        int key = 101;

        System.out.println("----------Testing Listy Search----------");
        System.out.println("Searching: " + listy.toString() + " for: " + key);
        System.out.println("Result: " + searchNoSize(listy, key));
    }

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

    /*
    Problem 5: Sparse Search
     */

    //Solution: Modified Binary Search. Time(Best/Average): O(logn), Time(Worst): O(n), Space: O(1)
    public int sparseSearch(String[] strings, String key){
        if(strings == null || strings.length == 0)
            return -1;
        int lower = 0; int upper = strings.length-1;
        int mid = 0;
        while(lower <= upper){
            mid = (lower + upper)/2;
            String midString = strings[mid];
            if(midString.isEmpty()){
                mid = findNearest(strings, mid, lower, upper);
                if(mid == -1)
                    return -1;
            }else{
                if(midString.equals(key))
                    return mid;
                else if(midString.compareTo(key) < 0)
                    lower = mid + 1;
                else
                    upper = mid - 1;
            }
        }
        return -1;
    }
    public int findNearest(String[] strings, int mid, int lower, int upper){
        int left = mid - 1;
        int right = mid + 1;
        while(lower <= left || upper >= right){
            if(lower <= left && !strings[left].isEmpty())
                return left;
            if(upper >= right && !strings[right].isEmpty())
                return right;
            left--;
            right++;
        }
        return -1;
    }

    /*
    Problem 6: Sort Big File - Imagine you have a 20GB file with one String per line. Explain how you would sort the file
     */

    //Solution 1: Use quickSort() because it doesn't require additional memory
    //Solution 2: Divide the file into xMB chunks, where x is the available memory. We sort each chunk, then merge
    //              them back one by one.

    /*
    Problem 9: Sorted Matrix Search
     */

    //Solution 1: Works on fully ordered matrix where the start of a new row is greater than the end of previous row
    //              Time: O(log(m)log(n)), Space: O(1)
    public boolean sortedMatrixSearch(int[][] mat, int key){
        if(mat == null || mat.length == 0)
            return false;
        int rowIndex = rowSearch(mat, key);
        if(rowIndex == -1)
            return false;
        int columnIndex = colSearch(mat[rowIndex], key);
        if(columnIndex == -1)
            return false;
        return true;
    }

    public int rowSearch(int[][] mat, int key){
        int lower = 0; int upper = mat.length-1;
        int left = 0; int right = mat[0].length-1;
        int mid = 0;
        while(lower <= upper){
            mid = (lower + upper)/2;
            if(key < mat[mid][left])
                upper = mid - 1;
            else if(key > mat[mid][right])
                lower = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public int colSearch(int[] array, int key){
        int lower = 0;
        int upper = array.length-1;
        int mid = 0;
        while(lower <= upper){
            mid = (lower + upper)/2;
            if(key < array[mid])
                upper = mid - 1;
            else if(key > array[mid])
                lower = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    //Solution 2: Works on ordered matrix where start of row might not be greater than end of previous row
    //          Time: O(m+n), Space: O(1)
    public boolean sortedMatrixSearch1(int[][] mat, int key){
        if(mat == null || mat.length == 0)
            return false;
        int row = 0;
        int col = mat[0].length-1;
        while(row < mat.length && col >= 0){
            if(mat[row][col] == key)
                return true;
            else if(mat[row][col] > key)
                col--;
            else
                row++;
        }
        return false;
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

    //Selection Sort
    public void selectionSort(int[] array){
        if(array == null || array.length <= 1)
            return;

        int n = array.length;
        for(int i = 0; i < n-1; i++){
            int minIndex = i;
            for(int j = i+1; j < n; j++){
                if(array[j] < array[minIndex])
                    minIndex = j;
            }
            swap(array, i, minIndex);
        }
    }

    //Quick Sort
    public void quickSort(int[] array, int lower, int upper){
        if(lower < upper){
            int index = partition(array, lower, upper);
            quickSort(array, lower, index-1);
            quickSort(array, index+1, upper);
        }
    }

    public int partition(int[] array, int lower, int upper){
        int pivot = array[upper];
        int tail = 0;
        for(int i = 0; i < upper; i++){
            if(array[i] <= pivot) {
                swap(array, tail, array[i]);
                tail++;
            }
        }
        swap(array, tail, upper);
        return tail;
    }

    public void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
