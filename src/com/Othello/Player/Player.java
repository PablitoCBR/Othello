package com.Othello.Player;

public class Player {
    private boolean _color; // true = black, false = white
    private int _remainingPaws;

    public Player(boolean color){
        _color = color;
        _remainingPaws = 32;
    }

    public int getRemainingPaws(){
        return  _remainingPaws;
    }
    public void setRemainingPaws(int amount){
        if(amount >= 0){
            this._remainingPaws = amount;
        }
    }

    public boolean getColor() {
        return _color;
    }
}
