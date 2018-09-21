package com.mukhtaryusuf.stacksandqueues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.EmptyStackException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThreeStacks ts = new ThreeStacks(4);
        ts.push(0, 1);
        ts.push(1, 2);
        ts.push(2, 3);
        ts.push(0, 4);
        ts.push(0, 5);
        ts.displayStacks();
        System.out.println();
        int p = 0;
        try {
            p = ts.pop(0);
            System.out.println("Popped value: "+p);
            p = ts.pop(0);
            System.out.println("Popped value: "+p);
            p = ts.pop(1);
            System.out.println("Popped value: "+p);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ts.displayStacks();
        System.out.println();

        ThreeStacks1 threeStacks1 = new ThreeStacks1(9);
        threeStacks1.displayStack();
        System.out.println();
        int poppedValue = 0;
        try {
            threeStacks1.push(0, 1);
            threeStacks1.push(0, 2);
            threeStacks1.push(0, 3);
            threeStacks1.push(0, 4);
            threeStacks1.push(0, 5);
            threeStacks1.push(1, 6);
            threeStacks1.push(0, 7);
            threeStacks1.push(2, 8);
            threeStacks1.push(0, 9);
            threeStacks1.displayStack();
            System.out.println();
            threeStacks1.push(0, 10);
            System.out.println();
            poppedValue = threeStacks1.pop(1);
            System.out.println(poppedValue);
            poppedValue = threeStacks1.pop(0);
            System.out.println(poppedValue);
            poppedValue = threeStacks1.pop(0);
            System.out.println(poppedValue);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Stack stack = new Stack();
        stack.push(5);
        stack.push(6);
        stack.push(5);
        stack.push(9);
        stack.push(1);
        stack.push(4);

        try{
            //stack.pop();
            stack.pop();
            System.out.println("Minimum is: " + stack.min());
        }catch(EmptyStackException e){
            System.out.println(e.getMessage());
        }
        StacksOfStacks<Integer> sos = new StacksOfStacks<>();
        sos.push(1);
        sos.push(2);
        sos.push(3);
        sos.push(4);
        sos.push(5);
        sos.push(6);
        sos.push(7);

        while(!sos.isEmpty()){
            int i = sos.pop();
            System.out.println(i);
//            System.out.println(sos.pop());
//            System.out.println(sos.pop());
//            System.out.println(sos.pop());
//            System.out.println(sos.pop());
//            System.out.println(sos.pop());
//            System.out.println(sos.pop());
            System.out.println(!sos.isEmpty());
        }

        StacksOfStacks1<Integer> sos1 = new StacksOfStacks1<>();
        sos1.push(1);
        sos1.push(2);
        sos1.push(3);
        sos1.push(4);
        sos1.push(5);
        sos1.push(6);
        sos1.push(7);
        sos1.push(8);
        sos1.push(9);
        int value = sos1.popAtIndex(1);
        System.out.println("Popped value: " + value);
        value = sos1.popAtIndex(1);
        System.out.println("Popped value: " + value);
        value = sos1.popAtIndex(2);
        System.out.println("Popped value: " + value);
        while(!sos1.isEmpty()){
            int i = sos1.pop();
            System.out.println("Popped value: " + i);
        }
        java.util.Stack<Integer> someStack = new java.util.Stack<>();
        someStack.push(5);
        someStack.push(4);
        someStack.push(9);
        someStack.push(3);
        someStack.push(1);
        someStack.push(10);
        someStack.push(20);
        someStack.push(15);

        sortStack(someStack);
        while(!someStack.isEmpty())
            System.out.println("Unsorted stack value: " + someStack.pop());

        System.out.println();
        System.out.println("----------Testing for animal shelter----------");
        AnimalShelter as = new AnimalShelter();
        Animal a1 = new Dog("a1");
        Animal a2 = new Dog("a2");
        Animal a3 = new Cat("a3");
        Animal a4 = new Cat("a4");
        Animal a5 = new Dog("a5");

        as.enqueue(a3);
        as.enqueue(a2);
        as.enqueue(a4);
        as.enqueue(a5);
        as.enqueue(a1);


//        while(!as.isEmpty()){
//            System.out.println(as.dequeueAny());
//        }
        System.out.println(as.dequeueAny());
        System.out.println(as.dequeueCat());
        System.out.println(as.dequeueCat());
        System.out.println(as.dequeueDog());

//        ArrayList<Integer> al = new ArrayList<>();
//        al.add(1); al.add(2); al.add(3); al.add(4); al.add(5);
//        System.out.println("----------Testing Array List----------");
//        while(!al.isEmpty()){
//            for(Integer a : al)
//                System.out.println(a);
//            System.out.println();
//            System.out.println("Removed element: " + al.remove(0));
//        }

        System.out.println("----------Testing Valid Par----------");
        validPar(2);

        int[] data = new int[]{4,6,9,1,2};
        System.out.println("----------Array before sorting----------");
        printArray(data);
        System.out.println("----------Array after sorting----------");
        bubbleSort(data);
        printArray(data);
    }

    public void sortStack(java.util.Stack<Integer> s){
        if(s == null)
            return;
        if(s.isEmpty())
            return;
        java.util.Stack<Integer> bStack = new java.util.Stack<>();
        int temp = 0;
        while(!s.isEmpty()){
            if(bStack.isEmpty())
                bStack.push(s.pop());
            else {
                if (s.peek() < bStack.peek()) {
                    temp = s.pop();
                    while (!bStack.isEmpty() && temp < bStack.peek()) {
                        s.push(bStack.pop());
                    }
                    bStack.push(temp);
                } else
                    bStack.push(s.pop());
            }
        }
        while(!bStack.isEmpty())
            s.push(bStack.pop());
    }

    public void validPar(int n){
        if(n <= 0)
            return;

        char[] arr = new char[n*2];
        recValidPar(arr, 0, n,n);
    }

    public void recValidPar(char[] a, int index, int leftCount, int rightCount){
        if(leftCount < 0 || rightCount < leftCount)
            return;
        if(leftCount == 0 && rightCount == 0){
            System.out.println(a);
            return;
        }

        a[index] = '(';
        recValidPar(a, index + 1, leftCount - 1, rightCount);

        a[index] = ')';
        recValidPar(a,index+1,leftCount, rightCount-1);
    }

}
