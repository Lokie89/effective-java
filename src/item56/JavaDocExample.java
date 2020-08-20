package item56;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class JavaDocExample {

    /**
     * @return true if this collection is empty
     * @implSpec This implementation returns {@code this.size() == 0}.
     */
    public boolean isEmpty() {
        return true;
    }

}

/**
 * A suspect, such as Colonel Mustard or {@literal Mrs. Peacock}.
 */
class Suspect {
}

/**
 * {@summary A suspect, such as Colonel Mustard or Mrs. Peacock}
 */
class Suspect2 {
}

/**
 * An Object ~~~
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
interface DocMap<K, V> {

}

/**
 * An instrument section of a symphony orchestra.
 */
enum OrchestraSection {
    /**
     * Woodwinds, such as flute, clarinet, and oboe.
     */
    WOODWIND,
    /**
     * Brass instrument, such as french horn and trumpets.
     */
    BRASS,
    ;
}

/**
 * Indicates that the annotated ~~
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTest {
    /**
     * The exception that the annotated ~~
     */
    Class<? extends Throwable> values();
}
