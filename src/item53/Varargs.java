package item53;

public class Varargs {
    static int sum(int... args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }

    static int min(int... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
        }
        int min = args[0];
        for (int i = 1; i < args.length; i++) {
            if (args[i] < min) {
                min = args[i];
            }
        }
        return min;
    }

    static int min2(int firstArg, int... args) {
        int min = firstArg;
        for (int i = 0; i < args.length; i++) {
            if (args[i] < min) {
                min = args[i];
            }
        }
        return min;
    }


    public static void main(String[] args) {
        System.out.println(min2(1,2,3,4,1,3));
    }
}

