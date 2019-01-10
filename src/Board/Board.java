package Board;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Board extends JFrame {
    private JPanel _layout;
    private JPanel _board = new JPanel();
    private JPanel _info = new JPanel();
    private Field _fields[][] = new Field[8][8];
    private JLabel _white, _black;

    public Board(){
        super("Othello");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 850);
        setResizable(false);

        // Inject menu bar
        this.setJMenuBar(this.CreateMenuBar());

        // layout
        _layout = new JPanel();
        _layout.setLayout(new BoxLayout(_layout, BoxLayout.PAGE_AXIS));

        // info
        this.setInitialInfo();
        _layout.add(_info);

        // fields 8x8
        this.CreateFields();
        _layout.add(_board);

        this.add(_layout);
        setVisible(true);
    }

    private JMenuBar CreateMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        var menu = new JMenu("Game");

        var newGame = new JMenuItem("New");
        newGame.setToolTipText("Start new game");

        var saveGame = new JMenuItem("Save");
        saveGame.setToolTipText("Save actual game");

        var loadGame = new JMenuItem("Load");
        loadGame.setToolTipText("Load saved game");

        var exitGame = new JMenuItem("EXIT");
        exitGame.setMnemonic(KeyEvent.VK_E);
        exitGame.setToolTipText("Exit application");
        exitGame.addActionListener((event) -> System.exit(0));

        menu.add(newGame);
        menu.add(saveGame);
        menu.add(loadGame);
        menu.add(exitGame);

        menuBar.add(menu);

        return  menuBar;
    }

    private void setInitialInfo(){
        _info.setLayout(new GridLayout(1,2));
        _info.setMaximumSize(new Dimension(800,50));
        _white = new JLabel("White:  32") ;
        _white.setFont(new Font("Arial", Font.BOLD, 20));
        _black = new JLabel("Black:  32");
        _black.setFont(new Font("Arial", Font.BOLD, 20));
        _info.add(_white);
        _info.add(_black);
    }

    private void CreateFields(){
        _board.setLayout(new GridLayout(8,8));
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                _fields[i][j] = new Field();
                _board.add(_fields[i][j]);
            }
        }
    }
}
