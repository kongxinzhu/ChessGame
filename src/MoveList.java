import com.sun.javafx.collections.ListListenerHelper;
import com.sun.javafx.perf.PerformanceTracker;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    Map<String, Integer> promotionMap;
    int step;

    // constructor
    public MoveList(String filePath) {
        try {
            // create standard of character switching to integer
            columnMap = new HashMap();
            promotionMap = new HashMap();

            columnMap.put('A', 0);
            columnMap.put('B', 1);
            columnMap.put('C', 2);
            columnMap.put('D', 3);
            columnMap.put('E', 4);
            columnMap.put('F', 5);
            columnMap.put('G', 6);
            columnMap.put('H', 7);

            // create map from promotion string to integer
            promotionMap.put("Q", -1);
            promotionMap.put("K", -2);
            promotionMap.put("B", -3);
            promotionMap.put("R", -4);

            moveFilePath = filePath;
            moveList = new ArrayList();
            moveList.add(null);
            populateMoveData(moveList);

            // initialize the step
            step = 0;

            // store the pieces in the destination place
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
        int[] columnAndRow;

        // new an array of String for 4 places in case of O-O-O and O-O
        String[] twoPoints = new String[4];
        ArrayList<Integer> move = new ArrayList();
        String[] twoPeople = line.split("\\s++");
        for(int i = 0; i < twoPeople.length; i++) {
            String[] twoPointsBuffer = twoPeople[i].split("-");

            // O-O-O and O-O case
            if(twoPointsBuffer[0].equals("O")) {
                switch (twoPointsBuffer.length) {
                    // O-O-O case
                    case 3:
                        // check it's white or black side
                        if (i == 0) {
                            twoPoints[0] = "E1";
                            twoPoints[1] = "C1";
                            twoPoints[2] = "A1";
                            twoPoints[3] = "D1";
                        }
                        if (i == 1) {
                            twoPoints[0] = "E8";
                            twoPoints[1] = "C8";
                            twoPoints[2] = "A8";
                            twoPoints[3] = "D8";
                        }
                        break;

                    // O-O case
                    case 2:
                        // check it's white or black side
                        if (i == 0) {
                            twoPoints[0] = "E1";
                            twoPoints[1] = "G1";
                            twoPoints[2] = "H1";
                            twoPoints[3] = "F1";
                        }
                        if (i == 1) {
                            twoPoints[0] = "E8";
                            twoPoints[1] = "G8";
                            twoPoints[2] = "H8";
                            twoPoints[3] = "F8";
                        }
                }
                for (int j = 0; j < 4; j++) {
                    columnAndRow = parseColumnRow(twoPoints[j]);
                    for (int p = 0; p < columnAndRow.length; p++) {
                        move.add(columnAndRow[p]);
                    }
                }
            } else {
                // regular case
                for (int p = 0; p < twoPointsBuffer.length; p++) {
                    twoPoints[p] = twoPointsBuffer[p];
                }

                for (int j = 0; j < 2; j++) {
                    columnAndRow = parseColumnRow(twoPoints[j]);
                    for (int p = 0; p < 2; p++) {
                        move.add(columnAndRow[p]);
                    }
                }

                Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(twoPeople[i]);
                if (m.find()) {
                    String promotionType = m.group(1);
                    move.add(move.get(move.size() - 2));
                    move.add(move.get(move.size() - 2));
                    move.add(promotionMap.get(promotionType));
                    move.add(promotionMap.get(promotionType));
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

    // get number of moves in moveList
    public int size() {
        return moveList.size();
    }
}
