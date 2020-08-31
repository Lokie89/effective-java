package item78;

import java.util.concurrent.atomic.AtomicLong;

public class SerialNumber {
    //    private static volatile long nextSerialNumber = 0L;
    private static final AtomicLong nextSerialNumber = new AtomicLong();

    public static long generateSerialNumber() {
//        return nextSerialNumber++;
        return nextSerialNumber.getAndIncrement();
    }
}
