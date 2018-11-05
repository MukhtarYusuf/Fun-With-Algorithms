package com.mukhtaryusuf.sortingandsearching;

import java.util.Arrays;
import java.util.Random;

public class Listy {
    private int[] array;

    public Listy(){
        Random random = new Random();
        int MAX_LENGTH = 50000;
        int SIZE = random.nextInt(MAX_LENGTH);
        int BOUNDARY = random.nextInt(SIZE);
        array = new int[SIZE];
        Arrays.fill(array, -1);
        for(int i = 0, value = 1; i < BOUNDARY; i++, value+=2){
            array[i] = value;
        }
    }

    public int elementAt(int i){
        if(i >= array.length)
            return -1;
        return array[i];
    }

    public String toString(){
        return Arrays.toString(array);
    }
}
