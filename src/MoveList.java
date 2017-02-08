import com.sun.javafx.collections.ListListenerHelper;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by caixinzhu on 2/7/17.
 */
public class MoveList {
    String moveFilePath;
    List<LinkedList<Integer>> moveList;
    static final ColumnMap COLUMN_MAP = new ColumnMap();

    // constructor
    public MoveList(String filePath) {
        try {
            moveFilePath = filePath;
            moveList = new LinkedList();
            populateMoveData(moveList);;

        } catch (IOException e) {
            System.out.println("Error : Can not find the file.");
        }
    }

    // populate all lines in move file into moveList
    public void populateMoveData(List moveList) throws IOException {
        Scanner scanner = new Scanner(new File(moveFilePath));
        scanner.nextLine();
        LinkedList<Integer> move;
        String line;
        while(scanner.hasNext()) {
            line = scanner.nextLine();
            move = parseMoveLine(line);
            moveList.add(move);
        }
    }

    // parse movement information with a line in move file
    public LinkedList<Integer> parseMoveLine(String line) {
        LinkedList<Integer> move = new LinkedList();
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
        columnAndRow[0] = COLUMN_MAP.changeToColumnInteger(point[0]);
        columnAndRow[1] = 8 - Character.getNumericValue(point[1]);
        return columnAndRow;
    }

    // get element at specific index
    public LinkedList<Integer> pointMove(int index) {
        return moveList.get(index);
    }
}
