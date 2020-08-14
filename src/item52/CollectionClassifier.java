package item52;

import java.math.BigInteger;
import java.util.*;

/**
 * @author lokie on 2020-08-14
 */
public class CollectionClassifier {

//    public static String classify(Set<?> s) {
//        return "집합";
//    }
//
//    public static String classify(List<?> list) {
//        return "리스트";
//    }
//
//    public static String classify(Collection<?> c) {
//        return "그 외";
//    }

    public static String classify(Collection<?> c){
        return c instanceof Set ? "집합" :
                c instanceof List ? "리스트" : "그 외";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };
        for (Collection<?> c : collections) {
            System.out.println(classify(c));
        }
    }
}
