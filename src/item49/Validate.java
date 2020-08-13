package item49;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author lokie on 2020-08-13
 */
public class Validate {

    /**
     * ( 현재 값 mod m ) 값을 반환한다.
     * 이 메서드는 항상 음이 아닌 BigInteger 를 반환한다는 점에서 remainder 메서드와 다르다.
     *
     * @param m 계수 ( 양수여야 한다. )
     * @return 현재 값 mod m
     * @throws ArithmeticException m 이 0 보다 작거나 같으면 발생한다.
     */
    public BigInteger mod(BigInteger m) {
        if (m.signum() <= 0) {
            throw new ArithmeticException("계수(m) 은 양수여야 한다. " + m);
        }
        return m;
    }

    private String strategy;

    public void setStrategy(String strategy) {
        this.strategy = Objects.requireNonNull(strategy, "전략");
    }

    private static void sort(long a[], int offset, int length) {
        assert a != null;
        assert offset >= 0 && offset <= a.length;
        assert length >= 0 && length <= a.length - offset;
    }

    // 의미없이 그냥 씀
    public void checkIndex(long a[], int offset, int length) {
        Objects.checkIndex(a.length, length);
        Objects.checkFromIndexSize(2, offset, length);
        Objects.checkFromToIndex(2, offset, length);
    }
}
