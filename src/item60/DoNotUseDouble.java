package item60;

import java.math.BigDecimal;

public class DoNotUseDouble {

    static void useDouble() {
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = 0.10; funds >= price; price += 0.10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println(itemsBought + " 개 구입");
        System.out.println("잔돈(달러) : " + funds);
    }

    static void useBigDecimal() {
        final BigDecimal TEN_CENTS = new BigDecimal(".10");
        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemsBought++;
        }
        System.out.println(itemsBought + " 개 구입");
        System.out.println("잔돈(달러) : " + funds);
    }

    static void useInt() {
        int itemsBought = 0;
        int funds = 100;
        for (int price = 10; funds >= price; price += 10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println(itemsBought + " 개 구입");
        System.out.println("잔돈(달러) : " + funds);
    }

    public static void main(String[] args) {
        useDouble();
        System.out.println();
        useBigDecimal();
        System.out.println();
        useInt();
    }
}
