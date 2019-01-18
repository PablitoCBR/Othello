package com.Othello.Game;

import com.Othello.Game.Helpers.RoundData;

import java.io.Serializable;
import java.util.Stack;

public class History implements Serializable {
    private Stack<RoundData> _history;
    private Stack<RoundData> _historyTemp;

    public History(){
        _history = new Stack<>();
        _historyTemp = new Stack<>();
    }

    public void addRoundData(RoundData data){
        if(data != null){
            _history.add(data);
            _historyTemp.clear();
        }
        else throw  new NullPointerException();
    }

    private void addRoundDataWithoutClearTemp(RoundData data){
        if(data != null){
            _history.add(data);
        }else throw  new NullPointerException();
    }

    public RoundData getPreviousRound(RoundData data){
        if(data != null){
            addNextRound(data);
            return  _history.pop();
        }
        else return _history.pop();
    }

    public RoundData getNextRound(RoundData data){
            addRoundDataWithoutClearTemp(data);
            return _historyTemp.pop();
    }
    public void addNextRound(RoundData data){
        if(data != null)
            _historyTemp.add(data);
        else throw new NullPointerException();
    }

    public void clearHistory(){
        _historyTemp.clear();
        _history.clear();
    }

    public boolean isNextPossible(){
        return _historyTemp.size() > 0;
    }

    public boolean isPreviousPossible(){

        return _history.size() > 0;
    }
}
