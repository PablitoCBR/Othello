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

    public boolean verifyMove(int row, int col, boolean playerColor, byte[][] fieldsStatus){
        byte opponentColor;
        if(playerColor)
            opponentColor = 2;
        else opponentColor = 1 ;

        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                if(Math.abs(i) + Math.abs(j) != 0){
                    if(row + i >= 0 && row + i <= 8){
                        if(col + j >= 0 && col + j <= 8){
                            if(fieldsStatus[row + i][col + j] == opponentColor){
                                if(checkPath(row, col,  i, j, fieldsStatus, playerColor)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean checkPath(int row, int col, int directionRow,
                              int directionCol  , byte[][] fieldsStatus, boolean playerColor){
        byte color;
        if(playerColor)
            color = 1;
        else  color = 2;

        do{
           row += directionRow;
           col += directionCol;
           if(fieldsStatus[row][col] == color)
               return true;
           else if(fieldsStatus[row][col] == 0)
               return false;
        }while(row >= 0 && row <= 8 && col >= 0 && col <= 8);

        return false;
    }
}

