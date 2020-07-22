package item28;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lokie on 2020-07-22
 */
public class ChooserGenericArray<T> {
    private final T[] choiceArray;

    public ChooserGenericArray(Collection<T> choices) {
        choiceArray = (T[]) choices.toArray();
    }

    public T chooser() {
        Random random = ThreadLocalRandom.current();
        return choiceArray[random.nextInt(choiceArray.length)];
    }
}
