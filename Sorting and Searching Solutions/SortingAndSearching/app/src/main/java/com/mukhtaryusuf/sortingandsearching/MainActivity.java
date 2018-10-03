package com.mukhtaryusuf.sortingandsearching;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
