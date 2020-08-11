package item47;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lokie on 2020-08-11
 */
public class Main {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        SubLists.of(stringList)
                .forEach(System.out::println);

        IterableExample integers = new IterableExample();

        integers.forEach(System.out::println);
    }
}
