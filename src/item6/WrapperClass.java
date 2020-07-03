package item6;

/**
 * @author lokie on 2020/07/03
 */
public class WrapperClass {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;

        Integer c = 150;
        Integer d = 150;

        int e = 150;
        int f = 150;

        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));

        System.out.println(System.identityHashCode(c));
        System.out.println(System.identityHashCode(d));

        System.out.println(System.identityHashCode(e));
        System.out.println(System.identityHashCode(f));

        System.out.println(a==b);
        System.out.println(c==d);
        System.out.println(e==f);

    }
}
