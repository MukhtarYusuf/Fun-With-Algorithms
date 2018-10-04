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
