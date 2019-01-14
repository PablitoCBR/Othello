package com.Othello.Game;

// Singleton pattern
public class Judge {
    private static Judge _judgeInstance = null;

    private Judge(){}

    public static Judge getJudgeInstance(){
        if(_judgeInstance == null){
            _judgeInstance = new Judge();
        }
        return _judgeInstance;
    }

    // Check if there is any possible route from that field
    public boolean verifyMove(int row, int col, boolean playerColor, byte[][] fieldsStatus){
        byte opponentColor;
        if(playerColor) // if player = black => enemy = white
            opponentColor = 2;
        else opponentColor = 1 ; // if player = white => enemy = black

        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++)
                if(Math.abs(i) + Math.abs(j) != 0)
                    if(row + i >= 0 && row + i < 8)
                        if(col + j >= 0 && col + j < 8)
                            if(fieldsStatus[row + i][col + j] == opponentColor)
                                if(checkPath(row, col,  i, j, fieldsStatus, playerColor)) // check possible direction
                                    return true;

        return false;
    }

    // Verify path when near chosen field was found enemy pawn
    public boolean checkPath(int row, int col, int directionRow,
                              int directionCol  , byte[][] fieldsStatus, boolean playerColor){
        byte color;
        if(playerColor) // if player = black => color = black
            color = 1;
        else  color = 2;

        row += directionRow;
        col += directionCol;

        while(row >= 0 && row < 8 && col >= 0 && col < 8){
           if(fieldsStatus[row][col] == color)
               return true;
           else if(fieldsStatus[row][col] == 0)
               return false;
            row += directionRow;
            col += directionCol;
        }
        return false;
    }
}

