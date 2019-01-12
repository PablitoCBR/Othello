package com.Othello.Board.BoardEvents;

import com.Othello.Board.Field;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldClickEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        ((Field)e.getSource()).setClickStatus(true);
    }
}
