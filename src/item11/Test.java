package item11;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lokie on 2020/07/06
 */
public class Test {
    static Map<Integer, String> map = new HashMap();
    public static void a(){
        Integer a = new Integer(150);
        System.out.println(System.identityHashCode(a));
        map.put(a,"150");
    }

    public static void main(String[] args) {
        a();
        Integer a = new Integer(150);
        Integer b = new Integer(150);
        System.out.println(System.identityHashCode(b));
        System.out.println(map.get(b));
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
