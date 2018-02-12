package com.mukhtaryusuf.stacksandqueues;

/**
 * Created by mukhtaryusuf on 2/7/18.
 */

public abstract class Animal {
    String name;
    String type;
    int tStamp;

    public String toString(){
        return "Name: " + this.name + "\n" +
                "Type: " + this.type + "\n" +
                "Time Stamp: " + this.tStamp + "\n";
    }
}
