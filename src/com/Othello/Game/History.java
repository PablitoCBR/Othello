package com.Othello.Game;

import com.Othello.Game.Helpers.RoundData;
import java.util.Stack;

public class History {
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

    // To get previous we give actual and put it on temp
    public RoundData getPreviousRound(RoundData activeRound){
        if(activeRound != null){
            _historyTemp.add(activeRound);
            return _history.pop();
        }
        throw new NullPointerException();
    }

    public RoundData getNextRound(){
        if(_historyTemp.size() > 0){
            RoundData data = _historyTemp.pop();
            _history.add(data);
            return data;
        }
        throw new NullPointerException();
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
