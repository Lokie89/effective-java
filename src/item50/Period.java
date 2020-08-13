package item50;

import java.util.Date;

/**
 * @author lokie on 2020-08-13
 */
public final class Period {
    private final Date start;
    private final Date end;

    /**
     * @param start 시작 시간
     * @param end 종료 시작; 시작 시각보다 뒤여야 한다.
     * @throws IllegalArgumentException 시작 시각이 종료 시각보다 늦을 때 발생한다.
     * @throws NullPointerException start 나 end 가 null 이면 발생한다.
     */
    public Period(Date start, Date end) {

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
}
