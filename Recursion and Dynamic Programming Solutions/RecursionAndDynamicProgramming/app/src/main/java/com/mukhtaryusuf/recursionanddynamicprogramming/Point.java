package com.mukhtaryusuf.recursionanddynamicprogramming;

public class Point {
    int row;
    int col;

    public Point(int row, int col){
        this.row = row;
        this.col = col;
    }

    public String toString(){
        return "[row: " + this.row + " col: " + this.col + "]";
    }
}
