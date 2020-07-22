package item28;

/**
 * @author lokie on 2020-07-22
 */
public class B extends A{
    @Override
    public B getThis() {
        return new B();
    }
}
