package com.Othello.Game.Helpers;

public class FieldStatusTemp {
    public boolean color;
    public byte colorByte;
    public int row;
    public int col;

    public FieldStatusTemp(int row, int col, boolean color){
        this.col = col;
        this.row = row;
        this.color = color;
        if(color)
            this.colorByte = 1;
        else this.colorByte = 2;
    }
}
