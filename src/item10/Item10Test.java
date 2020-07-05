package item10;

import java.awt.*;

public class Item10Test {
    public static void main(String[] args) {

        // 대칭성 위반
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "Polish";
        System.out.println("----대칭성----");
        System.out.println(cis.equals(s));
        System.out.println(s.equals(cis));

        // 추이성 위반
        ColorPoint p1 = new ColorPoint(1,2, Color.red);
        Point p2 = new Point(1,2);
        ColorPoint p3 = new ColorPoint(1,2, Color.blue);

        System.out.println("----추이성----");
        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));
        System.out.println(p1.equals(p3));

    }
}
