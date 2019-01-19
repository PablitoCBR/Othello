package com.Othello.Player;

public class Player {
    private int _remainingPaws;

    public Player(){
        _remainingPaws = 32;
    }

    public int getRemainingPaws(){
        return  _remainingPaws;
    }
    public void setRemainingPaws(int amount){
        if(amount >= 0){
            this._remainingPaws = amount;
        } else this._remainingPaws = 0;
    }
}
