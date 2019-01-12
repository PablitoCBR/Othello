package com.Othello.Board;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.border.LineBorder;

public class Field extends JButton{
    ImageIcon black, white;
    private int _id; // eg. 26 => row: 2 col: 6

    public Field(int id){
        this._id = id;
        black = new ImageIcon(this.getClass().getResource("blackCircle.png"));
        white = new ImageIcon(this.getClass().getResource("whiteCircle.png"));
        this.setBackground(new Color(17,145,83));
        this.setBorder(new LineBorder(Color.BLACK));
    }

    public void setBlackIcon(){
        this.setIcon(black);
    }
    public void setWhiteIcon(){
        this.setIcon(white);
    }
    public void setEmptyField(){
        this.setIcon(null);
    }

    public int getId(){
        return _id;
    }
}
