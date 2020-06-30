#### item-1 생성자 대신 정적 팩터리 메서드를 고려하라
###### 정리
    일반 생성자 대신 정적 팩터리 메서드를 사용 했을 때의 장단점
    
    장점
    1. 이름을 가질 수 있다.
        - 기존 생성자들은 매개 변수에 따라 구분하는 방법만 있었을 뿐
          그 생성자의 사용처에 대해서는 명확히 알기 힘들었다.
          팩터리 메서드를 사용하면 메서드명으로 사용처를 알 수 있다.
    2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
        - 인스턴스를 "통제" 할 수 있다. e.g ) *플라이 웨이트 패턴
    3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
        - 생성자가 반환할 수 있는 객체를 자유롭게 바꿀 수 있다.
    4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반한할 수 있다.
    5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
    
    단점
    1. 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.
        - 상속을 위해선 public 이나 protected 접근자가 필요하기 때문에 
    2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.
        - 규칙적인 생성자와는 달리 새로운 방법을 만들어 내는 것이기 때문에 따로 찾아내야 한다.
###### 내용 추가
    통상적인? 팩터리 메서드 규칙
    from : 매개 변수를 하나 받아 해당 타입의 인스턴스 반환
    of : 여러 매개 변수를 받아 적합한 타입의 인스턴스 반환
    valueOf : from 과 of 의 자세한 버전
    instance 혹은 getInstance : ( 매개 변수를 받는다면 ) 매개변수로 명시한 인스턴스를 반환, 같은 인스턴스 보장하지 않음
    create 혹은 newInstance : 위와 같지만 매번 새로운 인스턴스를 생성해 반환
    getType : getInstance 와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 사용. 
              "Type" 은 팩터리 메서드가 반환할 객체의 타입 e.g ) FileStore fs = Files.getFileStore(path)
    newType : newInstance 와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 사용.
              "Type" 은 팩터리 메서드가 반환할 객체의 타입 e.g ) BufferedReader br - Files.newBufferedReader(path);
    type : getType 과 newType 의 간결한 버전

    *플라이 웨이트 패턴 : 
        객체의 내부에서 참조하는 객체를 직접 만드는 것이 아닌
        없으면 만들고, 만들어져있다면 해당 객체를 공유하는 식으로 객체를 구성하는 방법
        https://know-one-by-one.tistory.com/44
        
#### item-2 생성자에 매개변수가 많다면 빌더를 고려하라
###### 정리
    정적 팩터리와 생성자 둘 다 가지고 있는 제약은 매개변수가 많을 때에 대응하기 어렵다는 것이다.
    점층적 생성자 패턴도 쓸 수는 있지만, 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다.
    
    자바빈즈 패턴은 매개변수가 없는 생성자로 객체를 만든 후, 세터 메서드를 통해 원하는 매개변수의 값을 결정하는 방식이다.
    자바빈즈 패턴의 단점은 객체하나를 만들려면 메서드를 여러번 호출해야 하고,
    객체가 완전히 생성되기 전까지는 일관성 ( 매개변수의 유효성 ) 이 무너진 상태에 놓이게 된다.
    이 단점을 보완하고자 생성이 끝난 객체를 얼리는 ( freezing ) 방법을 사용했는데 실제로 프로그래머가 이 메서드를 호출했는지
    컴파일러는 알 수 있는 방법이 없다.
    
    그래서 나온 것이 빌더 패턴이다.
    빏더 패턴은 필수 매개변수 만으로 생성자를 호출하여 빌더 객체를 얻는다.
    그 이후 빌더 객체가 제공하는 메서드들로 원하는 선택 매개 변수를 설정한다.
    마지막으로 빌드 메서드를 호출해 객체를 생성한다.
    
```java
class Main{
    NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
            .calories(100).sodium(35).carbohydrate(27).build();
}
```
    빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기에 좋다.
    하위 클래스의 메서드가 상위 클래스의 메서드가 정의한 반환 타입이 아닌,
    그 하위 타입을 반환하는 기능을 *공변 반환 타이핑 이라 한다.
    
    빌더를 사용하면 가변인수 매개변수를 여러개 사용할 수 있다.
    빌더 패턴은 빌더 하나로 여러 객체를 순회하면서 만들 수 있고, 빌더에 넘기는 매개변수에 따라 다른 객체를 만들 수도 있다.
    
###### 내용 추가
    *공변 반환 타입 :
        리턴 타입은 서브클래스라는 범위 안에서 다양할 수 있다.

#### item-3 private 생성자나 열거 타입으로 싱글턴임을 보증하라
###### 정리
    싱글턴 생성방법
        1. 생성자는 private 으로 감춰두고, public static 멤버 변수를 만들어 둔 후 사용
            장점은 해당 클래스가 싱글턴임이 명백히 드러나며, 간결하다는 점
```java
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis(){}
}
```
        2. 생성자는 private 으로 감춰두고, 정적 팩터리 메서드를 public static 으로 제공
            장점은 *API 를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다는 점,
                 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다는 점
```java
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();

    private Elvis(){}

    public static Elvis getInstance(){
        return INSTANCE;
    }
}
```
        3. 원소가 하나인 열거 타입으로 선언
            장점 간결하고 추가 노력 없이 직렬화  가능 리플렉션 공격에도 완벽히 막아줌
            그러나 싱글턴이 클래스를 상속해야 한다면 사용할 수 없다.
```java
public enum Elvis {
    INSTANCE;
}
```
    싱글턴의 공통적인 예외는,
    리플렉션 API 인 AccessibleObject.setAccessible 을 사용해 private 생성자를 호출할 수 있다.
    이러한 공격을 방어하려면 생성자를 수정하여 두 번째 객체가 생성되려 할 때 예외를 던지게 하면 된다.
    
    위의 1, 2 번 방식으로 만든 싱글턴 클래스를 직렬화 하려면
    단순히 Serializable 을 구현한다고 선언하는 것만으로는 부족하다.
    모든 인스턴스 필드를 일시적 ( transient ) 이라고 선언하고 **readResolve 메서드를 제공해야 한다.
    그렇지 않으면 직렬화된 인스턴스를 역직렬화할 때마다 새로운 인스턴스가 생긴다.
    
###### 내용 추가
    자바 직렬화 : 외부의 다른 자바 시스템에서 사용할 수 있도록 자바 객체나 데이터를 바이트 형태로 변환하는 기술
    
    * 정적 팩터리 메서드를 사용할 때 싱글턴이 아니게 변경 가능?
    
    ** readResolve
        싱글턴 클래스가 직렬화 가능한 클래스가 되기 위해 Serializable 인터페이스를 구현하는 순간,
        그 클래스는 싱글턴이 아니게 된다. 직렬화를 통해 초기화해둔 인스턴스가 아닌 다른 인스턴스가 반환되기 때문
        readResolve 메서드를 직접 정의하여 역직렬화 과정에서 만들어진 인스턴스 대신에
        기존에 생성된 싱글턴 인스턴스를 반환하도록 하면 된다.
        만약 역직렬화 과정에서 자동으로 호출되는 readObject 메서드가 있더라도
        readResolve 메서드에서 반환한 인스턴스로 대체된다.
        
#### item-4 인스턴스화를 막으려거든 private 생성자를 사용하라
###### 정리
    정적 메서드와 정적 필드만을 담은 클래스를 만드는 경우
    1. 기본 타입 값이나 배열 관련 메서드들을 모아놓을 때
    2. 특정 인터페이스를 구현하는 객체를 생성해주는 정적 메서드( 혹은 팩터리 ) 를 모아놓을 때
    3. final 클래스와 관련한 메서드들을 모아놓을 때
    
    이럴 때 private 생성자를 추가하면 클래스의 인스턴스화를 막을 수 있다.
    private 생성자 안에는 AssertionError() 를 throw 해주는 것이 좋다.
    클래스 내부에서 실수로라도 선언하지 못하도록.

#### item-5 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
###### 정리
    인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식
    이에 대한 변형으로 생성자에 자원 팩터리를 넘겨주는 방식( 팩터리 메서드 패턴 )이 있다.
    자바 8에서 소개한 *Supplier<T> 인터페이스가 완벽한 예다
    
    의존 객체 주입이 클래스의 유연성, 재사용성, 테스트 용이성 등을 개선해 주지만
    의존성이 수천개씩 되는 프로젝트에서는 코드의 복잡함을 가중시킨다.
    이런 복잡함을 해결하기 위해 의존 객체 주입 프레임 워크 ( 스프링 등 ) 를 사용하면 용이하다.
    
    클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 자원이 클래스 동작에 영향을 준다면 
    싱글턴과 정적 유틸리티 클래스는 사용하지 않는 것이 좋다.
###### 내용 추가
    * Supplier<T> interface
    Supplier 인터페이스는 매개값은 없고 리턴값이 있는 getXXX() 메서드를 가지고 있다.
    이 메서드들은 호출한 곳으로 데이터를 리턴( 공급 ) 하는 역할을 한다.

#### item-1
###### 정리
###### 내용 추가