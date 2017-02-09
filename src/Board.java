import java.util.*;

/**
 * Created by caixinzhu on 2/7/17.
 */
/*
 * Board is back-end of this program
 * Piece's place is changed following MoveList which shows which piece and where it goes
 */

public class Board {
    Piece[][] boardTrace;

    // constructor
    public Board() {
        // initialize the beginning state of game
        // black pieces
        boardTrace = new Piece[8][8];
        boardTrace[0][0] = new Piece("black_rook","resources/black_rook.png");
        boardTrace[0][1] = new Piece("black_knight","resources/black_knight.png");
        boardTrace[0][2] = new Piece("black_bishop","resources/black_bishop.png");
        boardTrace[0][3] = new Piece("black_queen","resources/black_queen.png");
        boardTrace[0][4] = new Piece("black_king","resources/black_king.png");
        boardTrace[0][5] = new Piece("black_bishop","resources/black_bishop.png");
        boardTrace[0][6] = new Piece("black_knight","resources/black_knight.png");
        boardTrace[0][7] = new Piece("black_rook","resources/black_rook.png");
        // pawn
        for(int col = 0; col < 8; col++) {
            boardTrace[1][col] = new Piece("black_pawn","resources/black_pawn.png");
        }

        // white pieces
        boardTrace[7][0] = new Piece("white_rook","resources/white_rook.png");
        boardTrace[7][1] = new Piece("white_knight","resources/white_knight.png");
        boardTrace[7][2] = new Piece("white_bishop","resources/white_bishop.png");
        boardTrace[7][3] = new Piece("white_queen","resources/white_queen.png");
        boardTrace[7][4] = new Piece("white_king","resources/white_king.png");
        boardTrace[7][5] = new Piece("white_bishop","resources/white_bishop.png");
        boardTrace[7][6] = new Piece("white_knight","resources/white_knight.png");
        boardTrace[7][7] = new Piece("white_rook","resources/white_rook.png");
        // pawn
        for(int col = 0; col < 8; col++) {
            boardTrace[6][col] = new Piece("white_pawn","resources/white_pawn.png");
        }
    }

    // pop piece from stack at old place and push it to stack at new place
    public void forwardMove(MoveList moveList) {
        System.out.println("moveList.size: " + moveList.size());
        if(moveList.step < moveList.size()) moveList.step++;
        System.out.println("step: " + moveList.step);
        ArrayList<Integer> moves = moveList.pointMove(moveList.step);
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

            /* save the lost piece in lostPiece stack
            *  if no piece at the destination place
            *  still push a null in lostPiece stack
            */
            moveList.lostPiece.push(boardTrace[endRow][endCol]);

            // put piece from old place to new place following move list
            boardTrace[endRow][endCol] = boardTrace[startRow][startCol];
        }
    }

    public void backMove(MoveList moveList) {
        if(moveList.step != 0) moveList.step--;
        ArrayList<Integer> moves = moveList.pointMove(moveList.step);
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

             /* save the lost piece in lostPiece stack
            *  if no piece at the destination place
            *  still push a null in lostPiece stack
            */
            moveList.lostPiece.push(boardTrace[endRow][endCol]);

            // put piece from old place to new place following move list
            boardTrace[endRow][endCol] = boardTrace[startRow][startCol];
        }
    }
}
