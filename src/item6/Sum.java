package item6;

public class Sum {
    private static long sum() {
        Long sum = 150L;
        for (long i = 0; i <= 10; i++) {
            sum += 0;
            System.out.println(System.identityHashCode(sum));
        }
        return sum;
    }

    public static void main(String[] args) {
        sum();
    }
}
