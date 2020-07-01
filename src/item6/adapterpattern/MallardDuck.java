package item6.adapterpattern;

/**
 * @author lokie on 2020/07/01
 */
public class MallardDuck implements Duck{
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
