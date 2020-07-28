package item31;

import java.util.List;

/**
 * @author lokie on 2020-07-28
 */
public class Swap {
    public static void swap(List<?> list, int i, int j) {
//        list.set(i, list.set(j, list.get(i))); // type 을 알 수 없으니 에러
        swapHelper(list,i,j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}
