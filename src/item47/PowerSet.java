package item47;

import java.util.*;

/**
 * @author lokie on 2020-08-11
 */
public class PowerSet {
    public static final <E> Collection<Set<E>> of(Set<E> s) {
        List<E> src = new ArrayList<>();
        if (src.size() > 30) {
            throw new IllegalArgumentException(
                    "집합에 원소가 너무 많습니다( 최대 30개 ).: " + s
            );
        }
        return new AbstractList<>() {
            @Override
            public Set<E> get(int index) {
                Set<E> result = new HashSet<>();
                for (int i = 0; index != 0; i++, index >>= 1) {
                    if ((index & 1) == 1) {
                        result.add(src.get(i));
                    }
                }
                return result;
            }

            @Override
            public int size() {
                return 1 << src.size();
            }
        };
    }
}
