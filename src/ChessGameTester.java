import javax.swing.*;
import java.util.ListIterator;

/**
 * Created by caixinzhu on 2/6/17.
 */
public class ChessGameTester {
    public static void main(String[] args) {
        MoveList moveList = new MoveList("resources/test.txt");
        ChessGameGUI chess = new ChessGameGUI(moveList);
        Board board = new Board();
    }
}
