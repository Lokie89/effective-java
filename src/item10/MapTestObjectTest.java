package item10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lokie on 2020/07/07
 */
public class MapTestObjectTest {
    public static void main(String[] args) {
        Map<MapTestObject, String> map = new HashMap<>();
        map.put(new MapTestObject("test", 300), "TEST");

        System.out.println(map.get(new MapTestObject("test", 300)));
    }
}
