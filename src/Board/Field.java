package Board;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Field extends JButton implements ActionListener{
    ImageIcon black, white;
    byte value = 0; // 0 = empty, 1 = black, 2 = white

    public Field(){
        black = new ImageIcon(this.getClass().getResource("blackCircle.png"));
        white = new ImageIcon(this.getClass().getResource("whiteCircle.png"));
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
//        if(this.value == 0){
//            this.setIcon(white);
//            this.value = 1;
//        }
    }
}
