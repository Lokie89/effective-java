package item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lokie on 2020-07-22
 */
public class ChooserGenericList<T> {
    private final List<T> choiceList;

    public ChooserGenericList(Collection<T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public T chooser() {
        Random random = ThreadLocalRandom.current();
        return choiceList.get(random.nextInt(choiceList.size()));
    }
}
