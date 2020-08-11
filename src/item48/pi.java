package item48;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class pi {
    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count()
                ;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(pi(100000000));
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0);
    }
}
