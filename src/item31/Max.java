package item31;

import java.util.Collection;
import java.util.Objects;

/**
 * @author lokie on 2020-07-28
 */
public class Max {
    public static <E extends Comparable<? super E>> E max(Collection<? extends E> c) {
        if (c.isEmpty()) {
            throw new IllegalArgumentException("컬렉션이 비어 있습니다.");
        }
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }
}
