import javax.swing.*;
import java.awt.*;

/**
 * Created by caixinzhu on 2/6/17.
 */
public class BoardGUI extends JPanel {
    JButton[] buttons;
    public BoardGUI() {
        buttons = new JButton[4];
        String[] numbers = new String[4];
        numbers[0] = "0";
        numbers[1] = "1";
        numbers[2] = "2";
        numbers[3] = "3";

        GridLayout gridLayout = new GridLayout(2, 2);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        setLayout(gridLayout);
        setVisible(true);
        setSize(600,600);
//        frame.pack();

        // create 16 pieces
        buttons[0]  = new JButton("1");
        // make square
        buttons[0].setPreferredSize(new Dimension(100, 100));
        add(buttons[0]);

        buttons[1] = new JButton("2");
        buttons[1].setSize(100,100);
        add(buttons[1]);

        JButton p3 = new JButton("3");
        p3.setSize(100,100);
        add(p3);

        JButton p4 = new JButton("4");
        p3.setSize(100,100);
        add(p4);

        setSize(400,400);
    }
}