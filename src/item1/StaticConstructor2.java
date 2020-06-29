package item1;

/**
 * @author lokie on 2020/06/29
 */
public class StaticConstructor2 implements StaticConstructorInterface {

    private Integer number1;
    private StaticConstructor2(int number1){
        this.number1 = number1;
    }

    public static StaticConstructor2 of(int number1){
        return new StaticConstructor2(number1);
    }

}
