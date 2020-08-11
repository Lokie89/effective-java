package item48;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Prime_Item45 {
    static final BigInteger ONE = new BigInteger("1");
    static final BigInteger TWO = new BigInteger("2");

    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        primes()
                .map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
//                .forEach(System.out::println)
                .forEach(mp -> System.out.println(mp.bitLength() + ": " + mp))
        ;
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0);

    }
}
