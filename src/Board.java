import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Created by caixinzhu on 2/7/17.
 */
public class Board {
    Stack<Piece>[][] boardTrace;

    // pieces
    static final Piece BLACK_ROOK = new Piece("black_rook","resources/black_rook.png");
    static final Piece BLACK_KGIGHT = new Piece("black_knight","resources/black_knight.png");
    static final Piece BLACK_BISHOP = new Piece("black_bishop","resources/black_bishop.png");
    static final Piece BLACK_KING = new Piece("black_king","resources/black_king.png");
    static final Piece BLACK_QUEEN = new Piece("black_queen","resources/black_queen.png");
    static final Piece BLACK_PAWN = new Piece("black_pawn","resources/black_pawn.png");

    static final Piece WHITE_ROOK = new Piece("white_rook","resources/white_rook.png");
    static final Piece WHITE_KGIGHT = new Piece("white_knight","resources/white_knight.png");
    static final Piece WHITE_BISHOP = new Piece("white_bishop","resources/white_bishop.png");
    static final Piece WHITE_KING = new Piece("white_king","resources/white_king.png");
    static final Piece WHITE_QUEEN = new Piece("white_queen","resources/white_queen.png");
    static final Piece WHITE_PAWN = new Piece("white_pawn","resources/white_pawn.png");


    // constructor
    public Board() {
        // 64 stacks on board
        boardTrace  = new Stack[8][8];
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++ ) {
                boardTrace[row][col] = new Stack<Piece>();
            }
        }

        // initialize the beginning state of game
        boardTrace[0][0].push(BLACK_ROOK);
        boardTrace[0][1].push(BLACK_KGIGHT);
        boardTrace[0][2].push(BLACK_BISHOP);
        boardTrace[0][3].push(BLACK_QUEEN);
        boardTrace[0][4].push(BLACK_KING);
        boardTrace[0][5].push(BLACK_BISHOP);
        boardTrace[0][6].push(BLACK_KGIGHT);
        boardTrace[0][7].push(BLACK_ROOK);
        for(int col = 0; col < 8; col++) {
                boardTrace[1][col].push(BLACK_PAWN);
        }

        boardTrace[7][0].push(WHITE_ROOK);
        boardTrace[7][1].push(WHITE_KGIGHT);
        boardTrace[7][2].push(WHITE_BISHOP);
        boardTrace[7][3].push(WHITE_QUEEN);
        boardTrace[7][4].push(WHITE_KING);
        boardTrace[7][5].push(WHITE_BISHOP);
        boardTrace[7][6].push(WHITE_KGIGHT);
        boardTrace[7][7].push(WHITE_ROOK);
        for(int col = 0; col < 8; col++) {
            boardTrace[6][col].push(WHITE_PAWN);
        }
    }

    // pop piece from stack at old place and push it to stack at new place
    public void forwardMove(MoveList moveList, int step) {
        LinkedList<Integer> moves = moveList.pointMove(step);
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

            boardTrace[endRow][endCol].push(boardTrace[startRow][startCol].pop());
        }
    }

    public void backMove(MoveList moveList, int step) {
        LinkedList<Integer> moves = moveList.pointMove(step);
        int startCol;
        int startRow;
        int endCol;
        int endRow;

        ListIterator iterator = moves.listIterator(moves.size());

        while(iterator.hasPrevious()) {
            startCol = (int) iterator.previous();
            startRow = (int) iterator.previous();
            endCol = (int) iterator.previous();
            endRow = (int) iterator.previous();

            boardTrace[endRow][endCol].push(boardTrace[startRow][startCol].pop());
        }
    }
}
