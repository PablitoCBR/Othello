package com.Othello.Game.Helpers;

import com.Othello.Game.History;

import java.io.Serializable;

public class RoundData implements Serializable {
    private byte[][] _fieldsStatus;
    private boolean _activePlayer;
    private int _whitePawnsLeft;
    private int _blackPawnsLeft;
    public RoundData(byte[][] fieldsStatus, boolean activePlayer,
                     int whitePawnsLeft, int blackPawnsLeft){
        this._fieldsStatus = fieldsStatus;
        this._activePlayer = activePlayer;
        this._blackPawnsLeft = blackPawnsLeft;
        this._whitePawnsLeft = whitePawnsLeft;
    }

    public byte[][] getFieldsStatus(){
        return _fieldsStatus;
    }
    public boolean getActivePlayer(){
        return  _activePlayer;
    }
    public int getWhitePawnsLeft(){
        return _whitePawnsLeft;
    }
    public int getBlackPawnsLeft(){
        return  _blackPawnsLeft;
    }
}
