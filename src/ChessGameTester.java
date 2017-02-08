import javax.swing.*;
import java.util.ListIterator;

/**
 * Created by caixinzhu on 2/6/17.
 */
public class ChessGameTester {
    public static void main(String[] args) {
//        ChessGameGUI chess = new ChessGameGUI();
        Board board = new Board();
        MoveList m1 = new MoveList("resources/test.txt");
        board.forwardMove(m1, 0);
        System.out.println(board.boardTrace[5][5].peek().name);
    }
}
