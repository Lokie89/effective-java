package item69;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ExceptionExample {
    static Mountain[] range = new Mountain[]{
            new Mountain(),
            new Mountain(),
            new Mountain(),
            new Mountain(),
            new Mountain(),
    };

    private static void tryCatch() {
        try {
            int i = 0;
            while (true) {
                range[i++].climb();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("이제 다올랐어");
        }
    }

    private static void forEach() {
        for (Mountain m : range) {
            m.climb();
        }
    }

    private static void iterator() {
        Collection<Mountain> mountains = Arrays.asList(range);
        for (Iterator<Mountain> i = mountains.iterator(); i.hasNext(); ) {
            Mountain mountain = i.next();
            mountain.climb();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            tryCatch();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start); // 9321

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            forEach();
        }
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2); // 3455

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            iterator();
        }
        long end3 = System.currentTimeMillis();
        System.out.println(end3 - start3); // 4749
    }
}

class Mountain {
    public void climb() {
//        System.out.println("올라올라");
    }
}
