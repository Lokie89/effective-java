package item61;

import java.util.Comparator;

public class AutoBoxing {
    static Comparator<Integer> naturalOrder =
            (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);


    static Comparator<Integer> naturalOrder2 = (iBoxed, jBoxed) -> {
        int i = iBoxed, j = jBoxed;
        return i < j ? -1 : (i == j ? 0 : 1);
    };

    static Integer c;

    public static void main(String[] args) {
        Integer a = new Integer(42);
        Integer b = new Integer(42);
        System.out.println(naturalOrder.compare(a, b)); // 1 이 반환됨
        System.out.println(naturalOrder2.compare(a, b)); // 0 이 반환됨

        if (c == 0) { // NullPointException
            System.out.println("Unbelievable");
        }
    }
}
