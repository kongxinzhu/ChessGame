import java.util.HashMap;
import java.util.Map;

/**
 * Created by caixinzhu on 2/7/17.
 */
public class ColumnMap {
    Map<Character, Integer> columnMap;

    public ColumnMap() {
        columnMap = new HashMap();
        columnMap.put('A', 0);
        columnMap.put('B', 1);
        columnMap.put('C', 2);
        columnMap.put('D', 3);
        columnMap.put('E', 4);
        columnMap.put('F', 5);
        columnMap.put('G', 6);
        columnMap.put('H', 7);
    }

    // change character to integer
    public int changeToColumnInteger(char character) {
        return columnMap.get(character);
    }

}
