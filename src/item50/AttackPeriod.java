package item50;

import java.util.Date;

/**
 * @author lokie on 2020-08-13
 */
public class AttackPeriod {
    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        end.setYear(78);
        p.end().setYear(78);
    }
}
