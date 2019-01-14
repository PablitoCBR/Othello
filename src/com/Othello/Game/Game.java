package com.Othello.Game;

import com.Othello.Board.Board;
import com.Othello.Board.Field;
import com.Othello.Player.Player;
import com.Othello.Game.Helpers.FieldStatusTemp;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;

// Singleton pattern
public class Game {
    private static Game gameInstance = null;

    private  byte[][] _fieldsStatus; // 0 - empty, 1 - black, 2 - white
    private  Board _board;
    private  Player _playerBlack, _playerWhite;
    private boolean _activePlayer; // true = black, false = white
    private Judge _judge = Judge.getJudgeInstance();
    private BoardUpdater _boardUpdater = BoardUpdater.getBoardUpdaterInstance();

    private  Game(){
        _board = new Board();
        for(Field fields[] : _board._fields){
            for(Field field : fields ){
                field.addChangeListener(new FieldListener());
            }
        }
        _fieldsStatus = new byte[8][8];
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
        _board.setActivePlayer(true);

        _board.setFieldIcon(3,3, (byte) 2);
        _board.setFieldIcon(3,4, (byte) 1);
        _board.setFieldIcon(4,3, (byte) 1);
        _board.setFieldIcon(4,4, (byte) 2);

        _activePlayer = true;
    }

    private void reset(){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                _board.setFieldIcon( i, j,(byte)0);

        _playerWhite.setRemainingPaws(32);
        _playerBlack.setRemainingPaws(32);
        setNewGame();
    }

    private void updateInfo(){
        _board.setRemainingBlackPawns(_playerBlack.getRemainingPaws());
        _board.setRemainingWhitePawns(_playerWhite.getRemainingPaws());
    }

    private void updateFieldsAndPawnsLeft(int row, int col, boolean activePlayer){
        int pawnsChange = 0;
        List<FieldStatusTemp> fieldsToUpdate = _boardUpdater.getFieldsToUpdate(row, col, activePlayer, _fieldsStatus);
        for(FieldStatusTemp fieldToUpdate : fieldsToUpdate){
            pawnsChange++;
            _board.setFieldIcon(fieldToUpdate.row, fieldToUpdate.col, fieldToUpdate.colorByte);
            _fieldsStatus[fieldToUpdate.row][fieldToUpdate.col] = fieldToUpdate.colorByte;
        }
        if(activePlayer) {
            _playerBlack.setRemainingPaws(_playerBlack.getRemainingPaws() - pawnsChange);
            _playerWhite.setRemainingPaws(_playerWhite.getRemainingPaws() + pawnsChange);
        }
        else{
            _playerBlack.setRemainingPaws(_playerBlack.getRemainingPaws() + pawnsChange);
            _playerWhite.setRemainingPaws(_playerWhite.getRemainingPaws() - pawnsChange);
        }
    }

    private class FieldListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e){
            if(((Field)e.getSource()).getClickStatus()){
                int row = ((Field)e.getSource()).getId() / 10;
                int col = ((Field)e.getSource()).getId() % 10;
                if(_activePlayer){
                    if(_fieldsStatus[row][col] == 0){
                        if(_judge.verifyMove(row, col, true, _fieldsStatus)){
                            _board.setFieldIcon(row,col, (byte)1);
                            _fieldsStatus[row][col] = 1;
                            _playerBlack.setRemainingPaws(_playerBlack.getRemainingPaws() - 1);
                            updateFieldsAndPawnsLeft(row, col, true);
                            _activePlayer = false;
                            _board.setActivePlayer(false);
                        }
                    }
                }
                else{
                    if(_fieldsStatus[row][col] == 0) {
                        if(_judge.verifyMove(row, col, false, _fieldsStatus)) {
                            _board.setFieldIcon(row, col, (byte) 2);
                            _fieldsStatus[row][col] = 2;
                            _playerWhite.setRemainingPaws(_playerWhite.getRemainingPaws() - 1);
                            updateFieldsAndPawnsLeft(row, col, false);
                            _activePlayer = true;
                            _board.setActivePlayer(true);
                        }
                    }
                }
                ((Field)e.getSource()).setClickStatus(false);
                updateInfo();
            }
        }
    }
}


