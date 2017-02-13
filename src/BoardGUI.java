import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by caixinzhu on 2/6/17.
 */
public class BoardGUI extends JPanel {
    JButton[][] buttons;
    Board realBoard;
    private Icon imageOfPiece;

    public BoardGUI() {
        realBoard = new Board();
        buttons = new JButton[8][8];

        GridLayout gridLayout = new GridLayout(8, 8);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        setLayout(gridLayout);

        setBorder(new LineBorder(Color.black, 2));
        setVisible(true);
        setSize(600,600);

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
                    buttons[row][col].setBorderPainted(false);
                    col++;
                    buttons[row][col] = new JButton();
                    buttons[row][col].setPreferredSize(new Dimension(55, 55));
                    buttons[row][col].setBackground(Color.gray);
                    buttons[row][col].setOpaque(true);
                    buttons[row][col].setBorder(null);
                    buttons[row][col].setBorderPainted(false);
                }
            } else {
                for (int k = 0; k < 4; col++, k++) {
                    buttons[row][col] = new JButton();
                    buttons[row][col].setPreferredSize(new Dimension(55, 55));
                    buttons[row][col].setBackground(Color.gray);
                    buttons[row][col].setOpaque(true);
                    buttons[row][col].setBorder(null);
                    buttons[row][col].setBorderPainted(false);
                    col++;
                    buttons[row][col] = new JButton();
                    buttons[row][col].setPreferredSize(new Dimension(55, 55));
                    buttons[row][col].setBackground(Color.white);
                    buttons[row][col].setOpaque(true);
                    buttons[row][col].setBorder(null);
                    buttons[row][col].setBorderPainted(false);
                }
            }
        }

        // add buttons in board panel
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                add(buttons[i][j]);
            }
        }

        // initialize the beginning state of game
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 8; j++) {
                Icon iconOfPiece = new ImageIcon(getClass().getResource(realBoard.boardTrace[i][j].imagePath));
                buttons[i][j].setIcon(iconOfPiece);
            }
        }
        for(int i = 6; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Icon iconOfPiece = new ImageIcon(getClass().getResource(realBoard.boardTrace[i][j].imagePath));
                buttons[i][j].setIcon(iconOfPiece);
            }
        }
        setSize(600,600);
    }

    // update the icon on chess board in GUI
    public void boardUpdate(MoveList moveList) {
        ArrayList<Integer> moves =  moveList.pointMove(moveList.step);

        int startCol;
        int startRow;
        int endCol;
        int endRow;

        ListIterator iterator = moves.listIterator();
        while(iterator.hasNext()) {
            startCol = (int) iterator.next();
            startRow = (int) iterator.next();
            endCol = (int) iterator.next();
            endRow = (int) iterator.next();

            if(endCol >= 0) {
                if (realBoard.boardTrace[startRow][startCol] == null) {
                    buttons[startRow][startCol].setIcon(null);
                }

                if (realBoard.boardTrace[startRow][startCol] != null) {
                    imageOfPiece = new ImageIcon(getClass().getResource(realBoard.boardTrace[startRow][startCol].imagePath));
                    buttons[startRow][startCol].setIcon(imageOfPiece);
                }

                if (realBoard.boardTrace[endRow][endCol] == null) {
                    buttons[endRow][endCol].setIcon(null);
                }

                if (realBoard.boardTrace[endRow][endCol] != null) {
                    imageOfPiece = new ImageIcon(getClass().getResource(realBoard.boardTrace[endRow][endCol].imagePath));
                    buttons[endRow][endCol].setIcon(imageOfPiece);
                }
            } else {
                imageOfPiece = new ImageIcon(getClass().getResource(realBoard.boardTrace[startRow][startCol].imagePath));
                buttons[startRow][startCol].setIcon(imageOfPiece);
            }
        }
    }
}