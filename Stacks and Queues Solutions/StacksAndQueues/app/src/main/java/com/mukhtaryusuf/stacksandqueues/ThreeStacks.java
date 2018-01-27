package com.mukhtaryusuf.stacksandqueues;

/**
 * Created by mukhtaryusuf on 1/22/18.
 */

public class ThreeStacks {
    private int stackSize;
    private int[] tops;
    private int[] sizes;
    private int[] arr;

    public ThreeStacks(int s){
        stackSize = s;
        tops = new int[]{-1,-1,-1};
        sizes = new int[]{0,0,0};
        arr = new int[stackSize * 3];
    }

    public void push(int sNumber, int value){
        if(sNumber < 0 || sNumber > 2)
            return;
        if(!isFull(sNumber)){
            tops[sNumber]++;
            int index = (sNumber * stackSize) + tops[sNumber];
            arr[index] = value;
            sizes[sNumber]++;
        }else
            System.out.println("Stack: "+sNumber+" is full");
    }
    public int pop(int sNumber) throws Exception {
        int poppedValue = 0;
        if(sNumber < 0 || sNumber > 2)
            throw new Exception();
        if(!isEmpty(sNumber)){
            int index = (sNumber * stackSize) + tops[sNumber];
            poppedValue = arr[index];
            tops[sNumber]--; sizes[sNumber]--;
        }else {
            System.out.println("Stack: " + sNumber + " is empty");
            throw new Exception();
        }

        return poppedValue;
    }
    public boolean isFull(int stackNumber){
        return (sizes[stackNumber] == stackSize);
    }
    public boolean isEmpty(int stackNumber){
        return (sizes[stackNumber] == 0);
    }

    public void displayStacks(){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }
}
