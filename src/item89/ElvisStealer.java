package item89;

import java.io.Serializable;

// Elvis 클래스의 비휘발성 객체를 훔칠 도둑 클래스
public class ElvisStealer implements Serializable {
    static Elvis_Item3 impersonator;
    private Elvis_Item3 payload;

    private Object readResolve() {
        // resolve 되기 전의 Elvis 인스턴스의 참조를 저장한다.
        impersonator = payload;
        // favoriteSongs 필드에 맞는 타입의 객체를 반환한다.
        return new String[]{"A Fool Such as I"};
    }

    private static final long serialVersionUID = 0L;
}
