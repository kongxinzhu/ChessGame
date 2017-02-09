import com.sun.javafx.collections.ListListenerHelper;
import com.sun.javafx.perf.PerformanceTracker;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by caixinzhu on 2/7/17.
 */

/*
 *  MoveList contains information of move history in a chess game
 *  Element of this list shows moves of each turn for both sides(black and white)
 *  Step plays a role of pointer
 *  Step's value changes when the button pre or next is clicked
 */

public class MoveList {
    String moveFilePath;
    List<ArrayList<Integer>> moveList;
    Stack<Piece> lostPiece;
    Map<Character, Integer> columnMap;
    int step;

    // constructor
    public MoveList(String filePath) {
        try {
            // create standard of character switching to integer
            columnMap = new HashMap();
            columnMap.put('A', 0);
            columnMap.put('B', 1);
            columnMap.put('C', 2);
            columnMap.put('D', 3);
            columnMap.put('E', 4);
            columnMap.put('F', 5);
            columnMap.put('G', 6);
            columnMap.put('H', 7);

            moveFilePath = filePath;
            moveList = new ArrayList();
            populateMoveData(moveList);

            step = -1;
            lostPiece = new Stack();
        } catch (IOException e) {
            System.out.println("Error : Can not find the file.");
        }
    }

    // populate all lines in move file into moveList
    public void populateMoveData(List moveList) throws IOException {
        Scanner scanner = new Scanner(new File(moveFilePath));
        scanner.nextLine();
        ArrayList<Integer> move;
        String line;
        while(scanner.hasNext()) {
            line = scanner.nextLine();
            move = parseMoveLine(line);
            moveList.add(move);
        }
    }

    // parse movement information with a line in move file
    public ArrayList<Integer> parseMoveLine(String line) {
        ArrayList<Integer> move = new ArrayList();
        String[] twoPeaple = line.split("\\s++");
        for(int i = 0; i < twoPeaple.length; i++) {
            String[] twoPoints = twoPeaple[i].split("-");
            for(int j = 0; j < twoPoints.length; j++) {
                int[] columnAndRow = parseColumnRow(twoPoints[j]);
                for(int p = 0; p < columnAndRow.length; p++) {
                    move.add(columnAndRow[p]);
                }
            }
        }
        return move;
    }

    // parse column and row from "StringNumber" format
    public int[] parseColumnRow(String S1) {
        char[] point = S1.toCharArray();
        int[] columnAndRow = new int[S1.length()];
        columnAndRow[0] = columnMap.get(point[0]);
        columnAndRow[1] = 8 - Character.getNumericValue(point[1]);
        return columnAndRow;
    }

    // get element at specific index
    public ArrayList<Integer> pointMove(int index) {
        return moveList.get(index);
    }

    public int size() {
        return moveList.size();
    }
}
