package item13;

/**
 * @author lokie on 2020/07/07
 */
public class Yum {

    Integer a;

    public Yum(Yum yum) {
        a = yum.a;
    }

    public static Yum newInstance(Yum yum) {
        return new Yum(yum);
    }
}
