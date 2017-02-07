import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by caixinzhu on 2/6/17.
 */
public class ChessGameGUI extends JFrame implements ActionListener {
    BoardGUI board;
    public ChessGameGUI() {
        JPanel panel = new JPanel();
//        Container contentPane = getContentPane();
        panel.setLayout(new FlowLayout());
        setTitle("Chess Game");
        add(panel);
        board = new BoardGUI();
        panel.add(board);

        JButton previous = new JButton("previous");
        previous.addActionListener(this);
        JButton next = new JButton("next");
        panel.add(previous);
        panel.add(next);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pack();
        setSize(600,600);
        setLocationRelativeTo(null);


    }
    @Override
    public void actionPerformed (ActionEvent e) {
        board.buttons[0].setText("changed!");
    }
}
