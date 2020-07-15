package item19;

import java.time.Instant;

public class Sub extends Super {
    private final Instant instant;

    Sub() {
        instant = Instant.now();
    }

    // 재정의 가능 메서드
    @Override
    public void overrideMe() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        Sub sub = new Sub(); // null
        sub.overrideMe();
    }
}
