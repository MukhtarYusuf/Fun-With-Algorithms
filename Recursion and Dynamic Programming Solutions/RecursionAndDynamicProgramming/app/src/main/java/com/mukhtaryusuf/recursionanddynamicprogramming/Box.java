package com.mukhtaryusuf.recursionanddynamicprogramming;

public class Box implements Comparable<Box>{
    public int width;
    public int height;
    public int depth;

    public Box(int width, int height, int depth){
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public int compareTo(Box b){
        if(this.height > b.height)
            return -1;
        else if(this.height < b.height)
            return 1;
        else
            return 0;
    }
}
