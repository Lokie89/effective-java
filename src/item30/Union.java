package item30;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lokie on 2020-07-24
 */
public class Union {

    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2); // Unchecked call to 'addAll(Collection<? extends E>)' as a member of raw type 'java.util.Set'
        return result;
    }

    public static <E> Set<E> union2(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2); // not warning
        return result;
    }

    public static void main(String[] args) {
        Set<String> guys = Set.of("톰", "딕", "해리");
        Set<String> stooges = Set.of("래리", "모에", "컬리");
        Set<String> aflCio = union2(guys, stooges);
        System.out.println(aflCio);
    }
}
