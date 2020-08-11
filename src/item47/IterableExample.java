package item47;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lokie on 2020-08-11
 */
public class IterableExample implements Iterable<Integer> {
    List<Integer> list = new ArrayList<>();

    public IterableExample(){
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Override
    public Iterator<Integer> iterator() {
        return list.iterator();
    }
}
