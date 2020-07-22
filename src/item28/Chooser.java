package item28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lokie on 2020-07-22
 */
public class Chooser {
    private final Object[] choiceArray;
    public Chooser(Collection choices){
        choiceArray = choices.toArray();
    }

    public Object chooser(){
        Random random = ThreadLocalRandom.current();
        return choiceArray[random.nextInt(choiceArray.length)];
    }
}
