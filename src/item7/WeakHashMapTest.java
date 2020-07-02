package item7;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author lokie on 2020/07/02
 */
public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();

        Integer key1 = 1000;
        Integer key2 = 2000;

        String value1 = "value1";
        String value2 = "value2";

        map.put(key1, value1);
        map.put(key2, value2);

        key1 = null;

        System.gc();
        map.entrySet().stream().forEach(el-> System.out.println(el));

        System.out.println();

        HashMap<Integer, String> map2 = new HashMap<>();
        key1 = 1000;
        map2.put(key1, value1);
        map2.put(key2, value2);

        key1 = null;

        System.gc();
        map2.entrySet().stream().forEach(el-> System.out.println(el));
    }
}
