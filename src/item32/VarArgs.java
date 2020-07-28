package item32;

import java.util.List;

/**
 * @author lokie on 2020-07-28
 */
public class VarArgs {
    static void dangerous(List<String>... stringLists){
        List<Integer> intList= List.of(42);
        Object[] objects = stringLists;
        objects[0] = intList;
        String s = stringLists[0].get(0); // ClassCastException
    }
}
