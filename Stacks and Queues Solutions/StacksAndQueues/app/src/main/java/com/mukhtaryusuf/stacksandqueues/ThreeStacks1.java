package com.mukhtaryusuf.stacksandqueues;

/**
 * Created by mukhtaryusuf on 1/23/18.
 */

/*This Class is buggy and still needs work*/

public class ThreeStacks1 {
    private int size;
    private int currentIndex;
    private int[] tops;
    private int[] sizes;
    private StackNode[] arr;

    public ThreeStacks1(int s){
        size = s;
        currentIndex = -1;
        tops = new int[]{-1,-1,-1};
        sizes = new int[]{0,0,0};
        arr = new StackNode[size];
        for(int i = 0; i < size; i++){
            arr[i] = new StackNode();
        }
    }

    public void push(int sNumber, int value) throws Exception{
        if(sNumber < 0 || sNumber >2)
            throw new Exception("Invalid Stack Number");

        if(!isFull()){
            int prevTopIn = tops[sNumber];
            StackNode sn = new StackNode();
            sn.prevTopIndex = prevTopIn;
            sn.value = value;
            arr[++currentIndex] = sn;
            tops[sNumber] = currentIndex;
            sizes[sNumber]++;
        }else
            throw new Exception("Stack number: "+sNumber+" is full");
    }

    public int pop(int sNumber) throws Exception{
        if(sNumber < 0 || sNumber > 2)
            throw new Exception("Invalid Stack Number");
        int poppedValue = 0;
        if(!isEmpty(sNumber)){
            int index = tops[sNumber];
            StackNode sn = arr[index];
            poppedValue = sn.value;
            tops[sNumber] = sn.prevTopIndex;
            sizes[sNumber]--;
        }else
            throw new Exception("Stack number: "+sNumber+" is empty");

        return poppedValue;
    }

    public boolean isFull(){
        return (currentIndex == size-1);
    }

    public boolean isEmpty(int stackNumber){
        return (sizes[stackNumber] == 0);
    }

    public void displayStack(){
        for(int i = 0; i < size; i++){
            System.out.print(arr[i].value);
            if(i != size-1)
                System.out.print(", ");
        }
    }
}
