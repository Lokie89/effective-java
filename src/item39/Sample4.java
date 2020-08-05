package item39;

import java.util.ArrayList;
import java.util.List;

public class Sample4 {

    @RepeatableExceptionTest(IndexOutOfBoundsException.class)
    @RepeatableExceptionTest(NullPointerException.class)
    public static void doublyBad(){
        List<String> list = new ArrayList<>();
        list.addAll(5, null);}
}
