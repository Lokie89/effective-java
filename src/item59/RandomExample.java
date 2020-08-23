package item59;

import java.util.Random;

public class RandomExample {
    static Random random = new Random();

    static int random(int n) {
        return Math.abs(random.nextInt()) % n;
    }

    static int low() {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0; i < 1000000; i++) {
            if (random(n) < n / 2) {
                low++;
            }
        }
        return low;
    }


    public static void main(String[] args) {
//        System.out.println(random(8));
        System.out.println(low());
    }
}
