package com.mukhtaryusuf.recursionanddynamicprogramming;

public class Color {
    int red;
    int green;
    int blue;

    public Color(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public boolean equals(Color color){
        return (this.red == color.red && this.green == color.green && this.blue == color.blue);
    }
}
