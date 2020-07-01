package item6.adapterpattern;

/**
 * @author lokie on 2020/07/01
 */
public class Test {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();

        WildTurkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        System.out.println("The turkey says ... ");
        turkey.gobble();
        turkey.fly();

        System.out.println("The Duck says ... ");
        duck.quack();
        duck.fly();

        System.out.println("The TurkeyAdapter says ... ");
        testDuck(turkeyAdapter);
    }

    public static void testDuck(Duck duck){
        duck.quack();
        duck.fly();
    }
}
