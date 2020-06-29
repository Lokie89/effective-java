package item1;

import java.util.Date;

/**
 * @author lokie on 2020/06/29
 */
public class StaticConstructor implements StaticConstructorInterface {

    private static StaticConstructorInterface constructorInterface;
    private Integer number1;
    private Integer number2;

    private StaticConstructor(int number1) {
        this.number1 = number1;
    }

    private StaticConstructor(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public static StaticConstructorInterface from(int number1) {
        return new StaticConstructor(number1);
    }

    public static StaticConstructorInterface of(int number1, int number2) {
        return new StaticConstructor(number1, number2);
    }

    public static StaticConstructorInterface valueOf(int number1) {
        return new StaticConstructor(number1);
    }

    public static StaticConstructorInterface getInstance(int number1) {
        if (constructorInterface == null) {
            constructorInterface = new StaticConstructor(number1);
            return constructorInterface;
        }
        return constructorInterface;
    }

    public static StaticConstructorInterface newInstance(int number1) {
        return new StaticConstructor(number1);
    }

    public static StaticConstructorInterface getStaticConstructorInterface2(int number1) {
        if (constructorInterface == null) {
            constructorInterface = StaticConstructor2.of(number1);
            return constructorInterface;
        }
        return constructorInterface;
    }

    public static StaticConstructorInterface newStaticConstructorInterface2(int number1) {
        return StaticConstructor2.of(number1);
    }

}
