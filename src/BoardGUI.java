import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

/**
 * Created by caixinzhu on 2/6/17.
 */
public class BoardGUI extends JPanel {
    JButton[][] buttons;
    Board realBoard = new Board();

    public BoardGUI() {
        buttons = new JButton[8][8];

        GridLayout gridLayout = new GridLayout(8, 8);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        setLayout(gridLayout);

        setBorder(new LineBorder(Color.black, 2));
        setVisible(true);
        setSize(600,600);
        //frame.pack();

        // create 64 squares
        for(int row = 0; row < 8; row++) {
            int col = 0;
            if (row % 2 != 0) {
                for (int k = 0; k < 4; col++, k++) {
                    buttons[row][col] = new JButton();
                    buttons[row][col].setPreferredSize(new Dimension(55, 55));
                    buttons[row][col].setOpaque(true);
                    buttons[row][col].setBorder(null);
                    buttons[row][col].setBackground(Color.white);
                    col++;
                    buttons[row][col] = new JButton();
                    buttons[row][col].setPreferredSize(new Dimension(55, 55));
                    buttons[row][col].setBackground(Color.gray);
                    buttons[row][col].setOpaque(true);
                    buttons[row][col].setBorder(null);
                }
            } else {
                for (int k = 0; k < 4; col++, k++) {
                    buttons[row][col] = new JButton();
                    buttons[row][col].setPreferredSize(new Dimension(55, 55));
                    buttons[row][col].setBackground(Color.gray);
                    buttons[row][col].setOpaque(true);
                    buttons[row][col].setBorder(null);
                    col++;
                    buttons[row][col] = new JButton();
                    buttons[row][col].setPreferredSize(new Dimension(55, 55));
                    buttons[row][col].setBackground(Color.white);
                    buttons[row][col].setOpaque(true);
                    buttons[row][col].setBorder(null);
                }
            }
        }


        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                add(buttons[i][j]);
            }
        }

        // initialize the beginning state of game
//        for(int i = 0; i < 2; i++) {
//            for(int j = 0; j < 8; j++) {
//                Icon iconOfPiece = new ImageIcon(getClass().getResource(realBoard.boardTrace[i][j].imagePath));
//                buttons[i][j].setIcon(iconOfPiece);
//            }
//        }

        setSize(600,600);
    }
}