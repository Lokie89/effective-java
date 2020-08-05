package item42;

import java.util.function.DoubleBinaryOperator;

/**
 * @author lokie on 2020-07-29
 */
public enum Operation_Item34 {
    PLUS("+", (x, y) -> x + y),
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y),
    ;

    private final String symbol;
    private final DoubleBinaryOperator doubleBinaryOperator;

    Operation_Item34(String symbol, DoubleBinaryOperator doubleBinaryOperator) {
        this.symbol = symbol;
        this.doubleBinaryOperator = doubleBinaryOperator;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public double apply(double x, double y) {
        return doubleBinaryOperator.applyAsDouble(x, y);
    }

}