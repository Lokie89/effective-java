package item42;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lambda {
    List<String> words;

    public void abstractClass() {
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
    }

    public void lambda1() {
        Collections.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
    }

    public void lambda2() {
        Collections.sort(words, Comparator.comparingInt(String::length));
    }

    public void lambda2_listMethod() {
        words.sort(Comparator.comparingInt(String::length));
    }
}
