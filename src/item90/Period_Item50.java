package item90;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lokie on 2020-08-13
 */
public final class Period_Item50 implements Serializable {
    private final Date start;
    private final Date end;

    /**
     * @param start 시작 시간
     * @param end   종료 시작; 시작 시각보다 뒤여야 한다.
     * @throws IllegalArgumentException 시작 시각이 종료 시각보다 늦을 때 발생한다.
     * @throws NullPointerException     start 나 end 가 null 이면 발생한다.
     */
    public Period_Item50(Date start, Date end) {

        // 복사본 사용
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());


        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " 가 " + end + "보다 늦다.");
        }
//        this.start = start;
//        this.end = end;
    }

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }

//    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
//        s.defaultReadObject();
//
//        // 불변식이 만족하는지 검사한다.
//        if (start.compareTo(end) > 0) {
//            throw new InvalidObjectException(start + "가 " + end + "보다 늦다.");
//        }
//    }

    @Override
    public String toString() {
        return start + " - " + end;
    }

    private static class SerializationProxy implements Serializable {
        private final Date start;
        private final Date end;

        SerializationProxy(Period_Item50 period) {
            this.start = period.start;
            this.end = period.end;
        }

        private static final long serialVersionUID = 234098243823485285L;

        private Object readResolve() {
            return new Period_Item50(start, end);
        }

    }

    // 직렬화 프록시 패턴용 writeReplace 메서드
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    // 직렬화 프록시 패턴용 readObject 메서드
    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("프록시가 필요합니다.");
    }

}
