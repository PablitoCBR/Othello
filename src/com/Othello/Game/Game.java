package com.Othello.Game;

import com.Othello.Board.Board;
import com.Othello.Board.Field;
import com.Othello.Player.Player;
import com.Othello.Game.Helpers.FieldStatusTemp;
import com.Othello.Game.Helpers.RoundData;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Game {
    private  byte[][] _fieldsStatus; // 0 - empty, 1 - black, 2 - white
    private  Board _board;
    private  Player _playerBlack, _playerWhite;
    private boolean _activePlayer; // true = black, false = white
    private Judge _judge = Judge.getJudgeInstance();
    private BoardUpdater _boardUpdater = BoardUpdater.getBoardUpdaterInstance();
    private boolean _isBlackMovePossible, _isWhiteMovePossible;
    private History _history;

    public  Game(){
        this._board = new Board();
        this._history = new History();
        for(Field fields[] : _board._fields){
            for(Field field : fields ){
                field.addChangeListener(new FieldListener());
            }
        }
        this._fieldsStatus = new byte[8][8];
        this._playerBlack = new Player(true);
        this._playerWhite = new Player(false);
        this.setNewGame();

        this._board.newGame.addActionListener((event) -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Start new game?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION)
                this.reset();
        });
        this._board.previous.addActionListener(new PreviousAction());
    }

    private void setNewGame(){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                this._fieldsStatus[i][j] = 0;

        this._fieldsStatus[3][3] = 2;
        this._fieldsStatus[3][4] = 1;
        this._fieldsStatus[4][3] = 1;
        this._fieldsStatus[4][4] = 2;

        this._board.setRemainingBlackPawns(32);
        this._board.setRemainingWhitePawns(32);
        this._board.setActivePlayer(true);

        this._board.setFieldIcon(3,3, (byte) 2);
        this._board.setFieldIcon(3,4, (byte) 1);
        this._board.setFieldIcon(4,3, (byte) 1);
        this._board.setFieldIcon(4,4, (byte) 2);

        this._isBlackMovePossible = true;
        this._isWhiteMovePossible = true;

        this._activePlayer = true;
    }

    private void reset(){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                _board.setFieldIcon( i, j,(byte)0);

        this._playerWhite.setRemainingPaws(32);
        this._playerBlack.setRemainingPaws(32);
        _history.clearHistory();
        this.setNewGame();
    }

    private void updateInfo(){
        this._board.setRemainingBlackPawns(_playerBlack.getRemainingPaws());
        this._board.setRemainingWhitePawns(_playerWhite.getRemainingPaws());
        _board.setActivePlayer(_activePlayer);
    }

    private void updateFieldsAndPawnsLeft(int row, int col, boolean activePlayer){
        int pawnsChange = 0;
        List<FieldStatusTemp> fieldsToUpdate = _boardUpdater.getFieldsToUpdate(row, col, activePlayer, _fieldsStatus);
        for(FieldStatusTemp fieldToUpdate : fieldsToUpdate){
            pawnsChange++;
            this._board.setFieldIcon(fieldToUpdate.row, fieldToUpdate.col, fieldToUpdate.colorByte);
            this._fieldsStatus[fieldToUpdate.row][fieldToUpdate.col] = fieldToUpdate.colorByte;
        }
        if(activePlayer) {
            this._playerBlack.setRemainingPaws(_playerBlack.getRemainingPaws() - pawnsChange);
            this._playerWhite.setRemainingPaws(_playerWhite.getRemainingPaws() + pawnsChange);
        }
        else{
            this._playerBlack.setRemainingPaws(_playerBlack.getRemainingPaws() + pawnsChange);
            this._playerWhite.setRemainingPaws(_playerWhite.getRemainingPaws() - pawnsChange);
        }
    }

    // Search for any possible move
    private boolean checkPossibilityOfMove(boolean player){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(this._fieldsStatus[i][j] == (byte)0)
                    if(this._judge.verifyMove(i, j, player, this._fieldsStatus))
                        return true;
            }
        }
        return false;
    }

    private void CheckEndOfGame(){
        if(!_isWhiteMovePossible && !_isBlackMovePossible){
            if(_playerBlack.getRemainingPaws() < _playerWhite.getRemainingPaws())
                JOptionPane.showMessageDialog(null, "No more moves! Black won!");
            else JOptionPane.showMessageDialog(null, "No more moves! White won!");
            reset();
        }
        if(_playerWhite.getRemainingPaws() == 0){
            JOptionPane.showMessageDialog(null, "White won!");
            reset();
        }
        if(_playerBlack.getRemainingPaws() == 0){
            JOptionPane.showMessageDialog(null, "Black won!");
            reset();
        }
    }

    private void CheckIfTourIsLost(){
        if(_activePlayer){
            if(!_isBlackMovePossible){
                JOptionPane.showMessageDialog(null, "No possible moves! Black loses his tour!");
                _activePlayer = false;
                _board.setActivePlayer(false);
            }
        }
        else{
            if(!_isWhiteMovePossible){
                JOptionPane.showMessageDialog(null, "No possible moves! White loses his tour!");
                _activePlayer = true;
                _board.setActivePlayer(true);
            }
        }
    }

    private RoundData getActualRoundData(){
        return new RoundData(_fieldsStatus , _activePlayer,
                _playerWhite.getRemainingPaws(), _playerBlack.getRemainingPaws());
    }


    private void LoadRound(RoundData data){
        byte[][] fieldsStatus = data.getFieldsStatus();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                this._fieldsStatus[i][j] = fieldsStatus[i][j];
                this._board.setFieldIcon(i, j, this._fieldsStatus[i][j]);
            }
        }
        this._activePlayer = data.getActivePlayer();
        this._playerWhite.setRemainingPaws(data.getWhitePawnsLeft());
        this._playerBlack.setRemainingPaws(data.getBlackPawnsLeft());
        updateInfo();
    }

    private void AfterMoveChecks(){
        _isBlackMovePossible = checkPossibilityOfMove(true);
        _isWhiteMovePossible = checkPossibilityOfMove(false);
        CheckEndOfGame();
        CheckIfTourIsLost();
        updateInfo();
    }

    //////////////////////////
    //// Action Listeners ////
    //////////////////////////
    private class FieldListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e){
            if(((Field)e.getSource()).getClickStatus()){
                int row = ((Field)e.getSource()).getId() / 10;
                int col = ((Field)e.getSource()).getId() % 10;

                if(_fieldsStatus[row][col] == 0){
                    if(_activePlayer){
                        if(_judge.verifyMove(row, col, true, _fieldsStatus)) {
                            try {
                                _history.addRoundData(getActualRoundData());
                            } catch (Exception ex){ System.out.println(ex.getMessage()); }

                            _board.setFieldIcon(row, col, (byte) 1);
                            _fieldsStatus[row][col] = 1;
                            _playerBlack.setRemainingPaws(_playerBlack.getRemainingPaws() - 1);
                            updateFieldsAndPawnsLeft(row, col, true);
                            _activePlayer = false;
                            AfterMoveChecks();
                        }
                    }
                    else{
                        if(_judge.verifyMove(row, col, false, _fieldsStatus)) {
                            try {
                                _history.addRoundData(getActualRoundData());
                            } catch (Exception ex){ System.out.println(ex.getMessage()); }

                            _board.setFieldIcon(row, col, (byte) 2);
                            _fieldsStatus[row][col] = 2;
                            _playerWhite.setRemainingPaws(_playerWhite.getRemainingPaws() - 1);
                            updateFieldsAndPawnsLeft(row, col, false);
                            _activePlayer = true;
                            AfterMoveChecks();
                        }
                    }
                }
                ((Field)e.getSource()).setClickStatus(false);
            }
        }
    }

    private class PreviousAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                LoadRound(_history.getPreviousRound(getActualRoundData()));
            } catch (Exception ex){ System.out.println(ex.getMessage());}
        }
    }
}


