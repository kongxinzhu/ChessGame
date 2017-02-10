import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by caixinzhu on 2/6/17.
 */
public class ChessGameGUI extends JFrame implements ActionListener {
    BoardGUI board;
    MoveList moveList;
    JButton previous;
    JButton next;

    public ChessGameGUI(MoveList moveList) {

        // set title of window
        setTitle("Chess Game");

        this.moveList = moveList;

        // contentPane contains boardPanel and buttonPanel
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        // boardPanel contains board
        JPanel boardPanel = new JPanel();
        boardPanel.setBorder(new EmptyBorder(20,20,20,10));
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));

        board = new BoardGUI();

        // position the board in center of boardPanel
        boardPanel.add(Box.createVerticalGlue());
        boardPanel.add(board);
        boardPanel.add(Box.createVerticalGlue());

        // buttonPanel contains two buttons which are prev and next
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // position buttonPanel in the center
        buttonPanel.add(Box.createVerticalGlue());
        previous = new JButton("previous");

        next = new JButton("next");
        buttonPanel.add(previous);
        buttonPanel.add(next);
        buttonPanel.add(Box.createVerticalGlue());

        // add listener
        next.addActionListener(this);
        previous.addActionListener(this);

        // buttonPanelCenter.add(buttonPanel);
        contentPane.add(Box.createHorizontalGlue());
        contentPane.add(boardPanel);
        contentPane.add(buttonPanel);
        contentPane.add(Box.createHorizontalGlue());

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        Object source = e.getSource();
        if(source == next && moveList.step < moveList.size() - 2) {
            moveList.step++;
            board.realBoard.forwardMove(moveList);
            board.boardUpdate(moveList);
        }
        if(source == previous && moveList.step > 0) {
            board.realBoard.backMove(moveList);
            board.boardUpdate(moveList);
            moveList.step--;
        }
    }
}
