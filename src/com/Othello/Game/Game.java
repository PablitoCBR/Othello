package com.Othello.Game;

import com.Othello.Board.Board;
import com.Othello.Player.Player;

// Singleton pattern
public class Game {
    private static Game gameInstance = null;

    private  byte[][] _fieldsStatus; // 0 - empty, 1 - black, 2 - white
    private  Board _board;
    private  Player _playerBlack, _playerWhite;

    private  Game(){
        _board = new Board();
        _playerBlack = new Player(true);
        _playerWhite = new Player(false);
        setNewGame();
    }

    // singleton instance returning method
    public static Game getGameInstance(){
        if(gameInstance == null){
            gameInstance = new Game();
        }
        return  gameInstance;
    }

    private void setNewGame(){
        _fieldsStatus = new byte[8][8];
        for(byte[] row : _fieldsStatus){
            for(byte field : row){
                field = 0;
            }
        }
        _fieldsStatus[3][3] = 2;
        _fieldsStatus[3][4] = 1;
        _fieldsStatus[4][3] = 1;
        _fieldsStatus[4][4] = 2;


        _board.setRemainingBlackPawns(32);
        _board.setRemainingWhitePawns(32);

        _board.setFieldIcon(3,3, (byte) 2);
        _board.setFieldIcon(3,4, (byte) 1);
        _board.setFieldIcon(4,3, (byte) 1);
        _board.setFieldIcon(4,4, (byte) 2);

    }

    public void restartGame(){
        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                _board.setFieldIcon(i,j,(byte)0);
            }

        }
        _playerWhite.setRemainingPaws(32);
        _playerBlack.setRemainingPaws(32);
        _board.setRemainingWhitePawns(32);
        _board.setRemainingBlackPawns(32);
        setNewGame();
    }

}
