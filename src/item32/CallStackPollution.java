package item32;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lokie on 2020-07-28
 */
public class CallStackPollution {
    static <T> T[] toArray(T... args) {
        return args;
    }

//    static <T> T[] pickTwo(T a, T b, T c) {
//        switch (ThreadLocalRandom.current().nextInt(3)) {
//            case 0:
//                return toArray(a, b);
//            case 1:
//                return toArray(a, c);
//            case 2:
//                return toArray(b, c);
//        }
//        throw new AssertionError();
//    }

    static <T> List<T> pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return List.of(a, b);
            case 1:
                return List.of(a, c);
            case 2:
                return List.of(b, c);
        }
        throw new AssertionError();
    }

    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    static <T> List<T> flatten(List<List<? extends T>> lists){
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    public static void main(String[] args) {
//        String[] attributes = pickTwo("좋은", "빠른", "저렴한");
        List<String> audience = flatten(List.of("friends","romans","countrymen"));
        List<String> attributeList = pickTwo("좋은", "빠른", "저렴한");
    }
}
