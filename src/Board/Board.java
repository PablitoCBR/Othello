package Board;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridLayout;

public class Board extends JFrame {
    JPanel panel = new JPanel();
    Field fields[][] = new Field[8][8];

    public Board(){
        super("Othello");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setResizable(false);

        panel.setLayout(new GridLayout(8,8));
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                fields[i][j] = new Field();
                panel.add(fields[i][j]);
            }
        }
        add(panel);

        setVisible(true);
    }
}
