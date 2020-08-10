package item46;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lokie on 2020-08-10
 */
public class StreamParadigm {
    Map<String, Long> freq = new HashMap<>();

    private void notStreamCode() throws FileNotFoundException {
        File file = new File("");
        try (Stream<String> words = new Scanner(file).tokens()) {
            words.forEach(word -> freq.merge(word.toLowerCase(), 1L, Long::sum));
        }
    }

    private void notStreamCode_Refactor() throws FileNotFoundException {
        File file = new File("");
        try (Stream<String> words = new Scanner(file).tokens()) {
            freq = words
                    .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        }

    }

    private void getTopTenWord() {
        List<String> topTen = freq.keySet().stream()
                .sorted(Comparator.comparing(freq::get).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

//    private void ListToMap() {
//        List<Album> albumList = new ArrayList<>();
//        Stream<Album> albums = albumList.stream();
//        Map<Artist, Album> topHits = albums.collect(
//                Collectors.toMap(Album::artist, a -> a, Collectors.maxBy(Album::sales))
//        );
//    }

}

enum NumberEnum {
    ONE,
    TWO,
    THREE,
    ;

    private static final Map<String, NumberEnum> stringToEnum =
            Stream.of(values())
                    .collect(Collectors.toMap(Object::toString, e -> e));
}

class Artist {

}

class Album {

    public static <T> int sales(T t, T t1) {
        return 0;
    }
}
