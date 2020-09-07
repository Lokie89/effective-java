package item83;

public class LazyInitialization {

    private final FieldType field = computeFieldValue(); // 일반적인 초기화

    // -------------------------------------------------------

//    private FieldType field;
//    private synchronized FieldType getField(){
//        if(field == null){
//            field = computeFieldValue(); // 지연초기화
//        }
//        return field;
//    }

    // -------------------------------------------------------

//    private static FieldType getField(){
//        return FieldHolder.field; // 정적 필드 지연 초기화
//    }
//
//    private static class FieldHolder{
//        static final FieldType field = computeFieldValue();
//        private static FieldType computeFieldValue(){
//            return new FieldType();
//        }
//    }

    // -------------------------------------------------------

//    private volatile FieldType field;
//
//    private FieldType getField() {
//        FieldType result = field;
//        if (result != null) {
//            return result;
//        }
//        synchronized (this) { // 이중 검사 관용구
//            if (field == null) {
//                field = computeFieldValue();
//            }
//            return field;
//        }
//    }

    private FieldType computeFieldValue() {
        return new FieldType();
    }

}

class FieldType {
}


