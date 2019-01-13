package com.Othello.Board;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.border.LineBorder;

public class Field extends JButton{
    private ImageIcon _black, _white;
    private int _id; // eg. 26 => row: 2 col: 6
    private boolean _clickStatus;

    public Field(int id){
        this._id = id;
        _black = new ImageIcon(this.getClass().getResource("blackCircle.png"));
        _white = new ImageIcon(this.getClass().getResource("whiteCircle.png"));
        this.setBackground(new Color(17,145,83));
        this.setBorder(new LineBorder(Color.BLACK));
    }

    public void setBlackIcon(){
        this.setIcon(_black);
    }
    public void setWhiteIcon(){
        this.setIcon(_white);
    }
    public void setEmptyField(){
        this.setIcon(null);
    }
    public void setClickStatus(boolean status){
        this._clickStatus = status;
    }
    public boolean getClickStatus(){
        return this._clickStatus;
    }

    public int getId(){
        return _id;
    }
}
