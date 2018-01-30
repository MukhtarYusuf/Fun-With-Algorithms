package com.mukhtaryusuf.stacksandqueues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }
}
