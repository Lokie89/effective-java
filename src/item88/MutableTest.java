package item88;

import java.util.Date;

public class MutableTest {
    public static void main(String[] args) {
        MutablePeriod mp = new MutablePeriod();
        Period_Item50 p = mp.period;
        Date pEnd = mp.end;

        pEnd.setYear(78);
        System.out.println(p);

        pEnd.setYear(69);
        System.out.println(p);
    }
}
