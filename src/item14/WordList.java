package item14;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author lokie on 2020/07/08
 */
public class WordList {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        Collections.addAll(s, args);
        System.out.println(s);
    }
}
