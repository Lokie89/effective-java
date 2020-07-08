package item14;

import java.util.Comparator;

/**
 * @author lokie on 2020/07/06
 */
public final class Item11_PhoneNumber implements Comparable<Item11_PhoneNumber> {
    private final short areaCode, prefix, lineNum;

    public Item11_PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix = rangeCheck(prefix, 999, "프리픽스");
        this.lineNum = rangeCheck(lineNum, 9999, "가입자 번호");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ": " + val);
        }
        return (short) val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Item11_PhoneNumber)) {
            return false;
        }
        Item11_PhoneNumber pn = (Item11_PhoneNumber) obj;
        return pn.lineNum == lineNum && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    private int hashCode;

    @Override
    public int hashCode() {
        // hashCode 작성법
//        int result = Short.hashCode(areaCode);
//        result = 31 * result + Short.hashCode(prefix);
//        result = 31 * result + Short.hashCode(lineNum);
//        return result;

        // Objects 클래스가 제공하는 hashCode 반환 메서드
//        return Objects.hash(areaCode, prefix, lineNum);

        // 해시코드 캐싱
        int result = hashCode;
        if (result == 0) {
            result = Short.hashCode(areaCode);
            result = 31 * result + Short.hashCode(prefix);
            result = 31 * result + Short.hashCode(lineNum);
            hashCode = result;
        }
        return result;
    }

    private static final Comparator<Item11_PhoneNumber> COMPARATOR =
            Comparator.comparingInt((Item11_PhoneNumber pn) -> pn.areaCode)
                    .thenComparingInt(pn -> pn.prefix)
                    .thenComparingInt(pn -> pn.lineNum);

    @Override
    public int compareTo(Item11_PhoneNumber o) {
        return COMPARATOR.compare(this, o);
    }
}
