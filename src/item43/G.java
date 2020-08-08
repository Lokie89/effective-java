package item43;

public interface G extends G1, G2{
}

interface G1 {
    <E extends Exception> Object m() throws E;
}

interface G2 {
    <F extends Exception> String m() throws Exception;
}

// G를 함수 타입을 표현 <F extends Exception> () -> String throws F
// 함수형 인터페이스를 위한 제네릭 함수 타입은 메서드 참조 표현식으로 구현할 수 있지만,
// 람다식으로는 불가능하다. 제네릭 람다식이라는 문법이 존재하지 않기 때문