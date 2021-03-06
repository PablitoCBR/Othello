package com.Othello.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import com.Othello.Board.BoardEvents.FieldClickEvent;

public class Board extends JFrame {
    private JPanel _layout;
    private JPanel _board = new JPanel();
    private JPanel _info = new JPanel();
    private JPanel _tools = new JPanel();
    private JToolBar _toolBar, _history;
    public final Field[][] _fields = new Field[8][8];
    private JLabel _whiteRemaining, _blackRemaining, _activePlayer;
    public JButton newGame, saveGame, loadGame, exitGame, help, previous, next;

    public Board(){
        super("Othello");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setResizable(false);

        // layout
        _layout = new JPanel();
        _layout.setLayout(new BorderLayout());

        // tool bars
        this.setToolBars();
        _layout.add(_tools,BorderLayout.PAGE_START);
        // info
        this.setInitialInfo();
        _layout.add(_info, BorderLayout.CENTER);

        // fields 8x8
        this.CreateFields();
        _layout.add(_board, BorderLayout.PAGE_END);

        this.add(_layout);
        setVisible(true);
    }

    public void setRemainingWhitePawns(int amount){
        _whiteRemaining.setText("White: " + amount);
    }
    public void setRemainingBlackPawns(int amount){
        _blackRemaining.setText("Black: " + amount);
    }
    public void setActivePlayer(boolean activePlayer) {
        if(activePlayer)
            _activePlayer.setText("BLACK");
        else _activePlayer.setText("WHITE");
    }
    public void setFieldIcon(int row, int col, byte IconType){
        switch (IconType){
            case 0:
                _fields[row][col].setEmptyField();
                break;
            case 1:
                _fields[row][col].setBlackIcon();
                break;
            case 2:
                _fields[row][col].setWhiteIcon();
                break;
        }
    }


    private void setToolBars(){
        _tools.setLayout(new GridLayout(2,1));
        _toolBar = new JToolBar();
        _toolBar.setLayout(new GridLayout(1,5));
        _toolBar.setFloatable(false);
        _toolBar.setRollover(true);

        newGame = new JButton("New Game");
        saveGame = new JButton("Save");
        loadGame = new JButton("Load");
        help = new JButton("HELP");

        exitGame = new JButton("EXIT");
        exitGame.setMnemonic(KeyEvent.VK_E);
        exitGame.setToolTipText("Exit application");
        exitGame.addActionListener((event) -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to close?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION)
                System.exit(0);
        });


        _toolBar.add(newGame);
        _toolBar.add(saveGame);
        _toolBar.add(loadGame);
        _toolBar.add(help, BorderLayout.EAST);
        _toolBar.add(exitGame, BorderLayout.EAST);
        _tools.add(_toolBar);

        _history = new JToolBar();
        _history.setLayout(new GridLayout(1,2));
        _history.setFloatable(false);
        _history.setRollover(true);
        next = new JButton("NEXT ROUND");
        next.setFont(new Font("Arial", Font.BOLD, 20));
        previous = new JButton("PREVIOUS ROUND");
        previous.setFont(new Font("Arial", Font.BOLD, 20));
        _history.add(previous);
        _history.add(next);
        _tools.add(_history);
    }

    private void setInitialInfo(){
        _info.setLayout(new GridLayout(0,3));

        _whiteRemaining = new JLabel() ;
        _whiteRemaining.setFont(new Font("Arial", Font.BOLD, 30));
        _whiteRemaining.setHorizontalAlignment(JLabel.CENTER);

        _activePlayer = new JLabel();
        _activePlayer.setFont(new Font("Arial", Font.BOLD, 40));
        _activePlayer.setForeground(Color.RED);
        _activePlayer.setHorizontalAlignment(JLabel.CENTER);

        _blackRemaining = new JLabel();
        _blackRemaining.setFont(new Font("Arial", Font.BOLD, 30));
        _blackRemaining.setHorizontalAlignment(JLabel.CENTER);

        _info.add(_whiteRemaining);
        _info.add(_activePlayer);
        _info.add(_blackRemaining);
    }

    private void CreateFields(){
        _board.setLayout(new GridLayout(8,8));
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                _fields[i][j] = new Field(i*10 + j);
                _fields[i][j].addActionListener(new FieldClickEvent());
                _board.add(_fields[i][j]);
            }
        }
    }
}
