package item10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lokie on 2020/07/06
 */
public class Item10Add {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        Integer a = 150;
        Integer b = 150;

        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));


        System.out.println(a.equals(b));

        map.put(a, "150");

        System.out.println(map.get(b));
    }
}
