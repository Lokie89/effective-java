package item58;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

public class ForBug2 {
    enum Face {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        ;
    }

    static Collection<Face> faces = EnumSet.allOf(Face.class);

    public static void main(String[] args) {
        for (Iterator<Face> i = faces.iterator(); i.hasNext(); ) {
            for (Iterator<Face> j = faces.iterator(); j.hasNext(); ) {
                System.out.println(i.next() + " " + j.next()); // 같은 애가 출력 됨
            }
        }
    }
}
