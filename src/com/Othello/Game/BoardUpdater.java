package com.Othello.Game;

import java.util.ArrayList;
import java.util.List;
import com.Othello.Game.Helpers.FieldStatusTemp;

// Singleton pattern
public class BoardUpdater {
    private static BoardUpdater boardUpdaterInstance = null;
    private Judge _judge = Judge.getJudgeInstance();

    private BoardUpdater(){}

    public static BoardUpdater getBoardUpdaterInstance(){
        if(boardUpdaterInstance == null)
            boardUpdaterInstance = new BoardUpdater();
        return boardUpdaterInstance;
    }

    public List<FieldStatusTemp> getFieldsToUpdate(int row, int col, boolean activePlayer, byte[][] fieldsStatus){
        ArrayList<FieldStatusTemp> tempFieldsStatus = new ArrayList<FieldStatusTemp>();
        byte opponentColor;
        int helperRow, helperCol;

        if(activePlayer)
            opponentColor = 2;
        else opponentColor = 1;

        List<Pair> directions = getDirections(row, col, activePlayer, fieldsStatus, opponentColor);

        // For each direction where pawns will change color, add each field that will change to to list
        for(Pair direction : directions) {
            helperRow = row + direction.rowAdd;
            helperCol = col + direction.colAdd;

            // Adding fields until match active player color pawn
            while (fieldsStatus[helperRow][helperCol] == opponentColor){
                tempFieldsStatus.add(new FieldStatusTemp(helperRow, helperCol, activePlayer));
                helperRow += direction.rowAdd;
                helperCol += direction.colAdd;
            }
        }
        return tempFieldsStatus;
    }

    // Get all directions where pawns will change color
    private List<Pair> getDirections(int row, int col, boolean activePlayer, byte[][] fieldsStatus, byte opponentColor){
        List<Pair> directions = new ArrayList<Pair>();
        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++)
                if(Math.abs(i) + Math.abs(j) != 0)
                    if(row + i >= 0 && row + i < 8)
                        if(col + j >= 0 && col + j < 8)
                            if(fieldsStatus[row + i][col + j] == opponentColor)
                                if (_judge.checkPath(row, col, i, j, fieldsStatus, activePlayer))
                                    directions.add(new Pair(i, j));
        return directions;
    }

    private class Pair{
        int rowAdd;
        int colAdd;
        Pair(int rowAdd, int colAdd){
            this.colAdd = colAdd;
            this.rowAdd = rowAdd;
        }
    }
}

