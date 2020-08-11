# item-1 생성자 대신 정적 팩터리 메서드를 고려하라
#### 정리
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
#### 내용 추가
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
        
# item-2 생성자에 매개변수가 많다면 빌더를 고려하라
#### 정리
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
    
#### 내용 추가
    *공변 반환 타입 :
        리턴 타입은 서브클래스라는 범위 안에서 다양할 수 있다.

# item-3 private 생성자나 열거 타입으로 싱글턴임을 보증하라
#### 정리
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
    
#### 내용 추가
    자바 직렬화 : 외부의 다른 자바 시스템에서 사용할 수 있도록 자바 객체나 데이터를 바이트 형태로 변환하는 기술
    
    * 정적 팩터리 메서드를 사용할 때 싱글턴이 아니게 변경 가능?
    
    ** readResolve
        싱글턴 클래스가 직렬화 가능한 클래스가 되기 위해 Serializable 인터페이스를 구현하는 순간,
        그 클래스는 싱글턴이 아니게 된다. 직렬화를 통해 초기화해둔 인스턴스가 아닌 다른 인스턴스가 반환되기 때문
        readResolve 메서드를 직접 정의하여 역직렬화 과정에서 만들어진 인스턴스 대신에
        기존에 생성된 싱글턴 인스턴스를 반환하도록 하면 된다.
        만약 역직렬화 과정에서 자동으로 호출되는 readObject 메서드가 있더라도
        readResolve 메서드에서 반환한 인스턴스로 대체된다.
        
# item-4 인스턴스화를 막으려거든 private 생성자를 사용하라
#### 정리
    정적 메서드와 정적 필드만을 담은 클래스를 만드는 경우
    1. 기본 타입 값이나 배열 관련 메서드들을 모아놓을 때
    2. 특정 인터페이스를 구현하는 객체를 생성해주는 정적 메서드( 혹은 팩터리 ) 를 모아놓을 때
    3. final 클래스와 관련한 메서드들을 모아놓을 때
    
    이럴 때 private 생성자를 추가하면 클래스의 인스턴스화를 막을 수 있다.
    private 생성자 안에는 AssertionError() 를 throw 해주는 것이 좋다.
    클래스 내부에서 실수로라도 선언하지 못하도록.

# item-5 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
#### 정리
    인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식
    이에 대한 변형으로 생성자에 자원 팩터리를 넘겨주는 방식( 팩터리 메서드 패턴 )이 있다.
    자바 8에서 소개한 *Supplier<T> 인터페이스가 완벽한 예다
    
    의존 객체 주입이 클래스의 유연성, 재사용성, 테스트 용이성 등을 개선해 주지만
    의존성이 수천개씩 되는 프로젝트에서는 코드의 복잡함을 가중시킨다.
    이런 복잡함을 해결하기 위해 의존 객체 주입 프레임 워크 ( 스프링 등 ) 를 사용하면 용이하다.
    
    클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 자원이 클래스 동작에 영향을 준다면 
    싱글턴과 정적 유틸리티 클래스는 사용하지 않는 것이 좋다.
#### 내용 추가
    * Supplier<T> interface
    Supplier 인터페이스는 매개값은 없고 리턴값이 있는 getXXX() 메서드를 가지고 있다.
    이 메서드들은 호출한 곳으로 데이터를 리턴( 공급 ) 하는 역할을 한다.

# item-6 불필요한 객체 생성을 피하라
#### 정리
    똑같은 기능의 객체를 매번 생성하기보다는 객체 하나를 재사용하는 편이 나을 때가 많다.
    정적 팩터리 메서드를 제공하는 불변 클래스에서는 정적 팩터리 메서드를 사용해 불필요한 객체 생성을 피할 수 있다.
    
    '비싼 객체' 가 반복해서 필요하다면 캐싱하여 재사용하길 권한다.
    인스턴스를 클래스 초기화 과정에서 직접 생성해 캐싱해두고, 
    나중에 메서드가 호출될 때마다 이 인스턴스를 재사용한다.
```java
public class RomanNumerals {
    static boolean isRomanNumeral(String s){
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
        +"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }
}
```
```java
public class RomanNumerals {
     private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})"
                +"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    
    static boolean isRomanNumeral(String s){
        return ROMAN.matcher(s).matches();
    }
}
```
    객체가 불변이라면 재사용해도 안전함이 명백하다.
    
    불필요한 객체를 만들어내는 또 다른 예로는 오토박싱이 있다.
```java
public class Sum {
    private static long sum() {
        Long sum = 0L; // 불필요한 박싱
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}
```
    불필요한 Long 선언으로 인스턴스를 계속 만들어낸다.
        - 래퍼 클래스(Wrapper class)는 산술 연산을 위해 정의된 클래스가 아니므로, 인스턴스에 저장된 값을 변경할 수 없습니다.
          단지, 값을 참조하기 위해 새로운 인스턴스를 생성하고, 생성된 인스턴스의 값만을 참조할 수 있습니다.
          실제 연산시 래퍼 클래스를 언박싱 하여 연산한 후 다시 래퍼 클래스를 생성하여 참조함
          
    박싱된 기본 타입보다는 기본 타입을 사용하고, 의도치 않은은 오토박싱이 숨어들지 않도록 주의하자.
    기존 객체를 재사용해야 한다면 새로운 객체를 만들지마라
#### 내용 추가
    * 어댑터
        실제 작업은 뒷단 객체에 위임하고, 자신은 제 2의 인터페이스 역할을 해주는 객체
        - 이 책에서 설명하는 어댑터는 어댑터 패턴과는 관련이 있나?
        책에서 예를 든 Map 인터페이스의 경우 인터페이스에서 제공하는 keySet() 메서드는 Set 인터페이스를 반환한다.
        이 Set 인터페이스 ( 어댑터 ) 는 언제나 같은 기능을 가지고 있기 때문에,
        실제로 반환된 객체가 동일한 객체여도 상관없다.
        
```java
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {

    public Set<K> keySet() {
       Set<K> ks = keySet;
       if (ks == null) {
           ks = new KeySet();
           keySet = ks;
       }
       return ks;
   }
}
```
    실제 HashMap 이 구현하고 있는 keySet 메서드

    Long 객체가 실제로 만들어 지나?
    - 위의 예시 중 Sum 클래스의 Long 객체의 연산 과정에서 실제로 Long 객체가 만들어 진다는 사실을 찾아내기 위해
      주소값을 출력해봤다.
      기본 주소값을 출력하는 메서드는 hashCode() 메서드 이지만 이미 래퍼클래스에서 오버라이드 하여 실제 데이터 값을 출력하게 해놓음
      따라서 System.identityHashCode() 메서드를 사용하여 integer 로 리턴되는 hashCode 출력
      예시에 있는 그대로 출력해봤으나 Long 타입의 sum 을 long 타입으로 수정했을 때도 같은 hashCode 값을 출력
      알고보니 Integer Long 모두 -128 ~ 127 까지의 값에서는 싱글턴 패턴 ? 으로 같은 객체를 리턴하고 있었음
      
######
    실제 Integer 클래스 안에 정의된 IntegerCache 클래스
    컴파일 단계에서 래핑 클래스를 언박싱 후 연산과정이 끝나고 다시 오토박싱 할때 아마? valueOf 메서드를 실행하는 것 같다.
```java
public final class Integer extends Number implements Comparable<Integer> {
    public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }
    
    private static class IntegerCache {
        static final int low = -128;
        static final int high;
        static final Integer cache[];
    
        static {
            // high value may be configured by property
            int h = 127;
            String integerCacheHighPropValue =
                sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
            if (integerCacheHighPropValue != null) {
                try {
                    int i = parseInt(integerCacheHighPropValue);
                    i = Math.max(i, 127);
                    // Maximum array size is Integer.MAX_VALUE
                    h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
                } catch( NumberFormatException nfe) {
                    // If the property cannot be parsed into an int, ignore it.
                }
            }
            high = h;
    
            cache = new Integer[(high - low) + 1];
            int j = low;
            for(int k = 0; k < cache.length; k++)
                cache[k] = new Integer(j++);
    
            // range [-128, 127] must be interned (JLS7 5.1.7)
            assert IntegerCache.high >= 127;
        }
    
        private IntegerCache() {}
    }
}
```
    실제 Long 클래스 안에 정의된 LongCache 클래스
```java
public final class Long extends Number implements Comparable<Long> {

    public static Long valueOf(long l) {
        final int offset = 128;
        if (l >= -128 && l <= 127) { // will cache
            return LongCache.cache[(int)l + offset];
        }
        return new Long(l);
    }

    private static class LongCache {
        private LongCache(){}

        static final Long cache[] = new Long[-(-128) + 127 + 1];

        static {
            for(int i = 0; i < cache.length; i++)
                cache[i] = new Long(i - 128);
        }
    }
}
```
    동일성 검사
```java
public class WrapperClass {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;

        Integer c = 150;
        Integer d = 150;

        int e = 150;
        int f = 150;

        System.out.println(a==b); // true
        System.out.println(c==d); // false
        System.out.println(e==f); // true

    }
}
```
    동일성 검사로 같은 객체인지 아닌지를 알 수 있다.
    
    추가로 VM option 수정 시 캐싱 할 수 있는 범위의 max 값을 유동적으로 변경 할 수 있다고 함.
    https://www.thegeekyway.com/java-autoboxing-xxautoboxcachemax/
    
    -- 해시코드 값은 wrapper class 와 primitive type 일 때 모두 동일하게 캐싱 범위 밖이면 같은 숫자여도 다르게 나타났다.
       생각해보니 당연한거였다.
       
# item-7 다 쓴 객체 참조를 해제하라
#### 정리
    메모리 누수 : 애플리케이션이 필요하지 않은 메모리를 계속 점유하고 있는 현상
    
    가비지 컬렉터를 갖춘 언어에서는 자동으로 다 쓴 객체를 수거해가니 메모리 관리에 신경쓰지 않아도 된다.
    하지만 프로그래머가 눈치 채지 못하는 사용하지 않지만 참조하고 있는 객체들이 발생하는 경우
    점차 가비지 컬렉션 활동과 메모리 사용량이 늘어나 성능이 저하될 것이다.
    객체 참조 하나를 살려두면 가비지 컬렉터는 그 객체뿐 아니라 그 객체가 참조( 참조의 참조가 될 수도 있다. )하는 모든 객체를 회수해가지 못한다.
    방법은 해당 참조를 모두 사용하였을 때 null 처리 ( 참조 해제 ) 를 해주면 된다.
    
    동작의 비활성 영역의 객체가 더 이상 쓸모없다는 건 프로그래머만 아는 사실이다. 
    그러므로 비활성 영역이 되는 순간 null 처리해서 해당 객체를 더는 쓰지 않을 것임을 가비지 컬렉터에 알려야 한다.
    자기 메모리를 직접 관리하는 클래스라면 프로그래머는 항시 메모리 누수에 주의해야 한다.
    
    두번 째는 캐시를 사용하는 경우다. 객체 참조를 캐시에 넣고 나서, 그 사실을 잊는 경우가 있다.
    이럴 때는 **WeakHashMap 을 사용해 캐시를 만들자. 다 쓴 엔트리는 그 즉시 자동으로 제거될 것이다.
    
    세번 째는 리스너 혹은 콜백이다. 클라이언트가 콜백을 등록만 하고 명확히 해지하지 않는다면
    콜백은 계속 쌓여갈 것이다. 이럴 때 콜백을 약한 참조로 저장하면 된다.
    
    메모리 누수는 겉으로 잘 드러나지 않아 오랫동안 잠복하는 사례가 많으므로 예방법을 익혀두는 것이 중요하다. 
#### 내용 추가
    * 다 쓴 참조를 해제하는 가장 좋은 방법은 그 참조를 담은 변수를 유효 범위 (scope) 밖으로 밀어내는 것이다.
      변수의 범위를 최소가 되도록 정의 했다면 이 일은 자연스럽게 이루어진다.
        - 부가내용..
        
    ** WeakHashMap
        - 약한 참조의 특성을 이용하여 해당 키 값을 null 로 수정하면 map 안의 element 가 사라짐
        - http://blog.breakingthat.com/2018/08/26/java-collection-map-weakhashmap/
```java
public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();

        Integer key1 = 1000;
        Integer key2 = 2000;

        String value1 = "value1";
        String value2 = "value2";

        map.put(key1, value1);
        map.put(key2, value2);

        key1 = null;

        System.gc();
        map.entrySet().stream().forEach(el-> System.out.println(el));

        System.out.println();

        HashMap<Integer, String> map2 = new HashMap<>();
        key1 = 1000;
        map2.put(key1, value1);
        map2.put(key2, value2);

        key1 = null;

        System.gc();
        map2.entrySet().stream().forEach(el-> System.out.println(el));
    }
}
```
# item-8 finalizer 와 cleaner 사용을 피하라
#### 정리
    자바에서는 두가지 "객체소멸자" 를 제공한다.
    그 중 finalizer 는 예측할 수 없고, 상황에 따라 위험할 수 있어 일반적으로 불필요하다.
    오동작, 낮은 성능, 이식성 문제 등을 이유로 자바 9에서 deprecated 되면서 cleaner 를 대안으로 소개한다.
    cleaner 는 finalizer 보다는 덜 위험하지만, 여전히 예측할 수 없고, 느리고, 일반적으로 불필요하다.
    즉, finalizer 와 cleaner 로는 제때 실행되어야 하는 작업은 절대 할 수 없다.
    finalizer 와 cleaner 를 얼마나 신속히 수행할지는 전적으로 가비지 컬렉터 알고리즘에 달렸으며,
    알고리즘에 따라 인스턴스의 자원 회수가 제멋대로 이루어 질 수 있기 때문에 큰 오류를 낳을 수 있다.
    이런 문제를 예방할 보편적인 해법은 finalizer 를 사용하지 않는 방법 뿐이다.
    
    자바 언어 명세는 finalizer 나 cleaner 의 수행 시점 뿐 아니라 "수행 여부"조차 보장하지 않는다.
    따라서 상태를 영구적으로 수정하는 작업에서는 절대 finalizer 나 cleaner 에 의존해서는 안 된다.
    
    또, finalizer 동작 중 발생한 예외는 무시되며 처리할 작업이 남았더라도 그 순간 종료된다.
    
    finalizer 와 cleaner 는 심각한 성능 문제도 동반한다.
    finalizer 가 가비지 컬렉터의 효율을 떨어뜨리기 때문이다.
    
    finalizer 를 사용한 클래스는 finalizer 공격에 노출되어 심각한 보안 무제를 일으킬 수도 있다.
    생성자나 직렬화 과정에서 예외가 발생하면, 이 생성되다 만 객체에서 
    악의적인 하위 클래스의 finalizer 가 수행될 수 있게 된다.
    이 finalizer 는 정적 필드에 자신의 참조를 할당하여 가비지 컬렉터가 수집하지 못하게 막을 수 있다.
    final 이 아닌 클래스를 finalizer 공격으로부터 방어하려면 
    아무일도 하지 않는 finalize 메서드를 만들고 final 로 선언하자. 
    
    finalizer 와 cleaner 를 대신해줄 묘안은 AutoCloseable 을 구현한 후 close 메서드를 사용하는 것이다.
    이 때 자신이 닫혔는지를 추적할 수 있게 close 메서드에서 이 객체는 더이상 유효하지 않음을 필드에 기록하고
    다른 메서드는 이 필드를 검사해서 객체가 닫힌 후에 불렀다면 IllegalStateException 을 던지는 것이 좋다.
    
    이런 복잡하고 사용하기 어려운 finalizer 와 cleaner 는 
    자원의 소유자가 close 메서드를 호출하지 않는 것에 대비한 "안전망 역할"을 하며,
    일반 자바 객체가 네이비티 메서드를 통해 기능을 위임한 네이티브 객체인 "네이티브 피어" 를 회수하기 위해 사용된다.
    네이티브 피어는 자바객체가 아니어서 가비지 컬렉터가 회수하지 못하기 때문에 직접 회수한다.
    단, 성능 저하를 감당할 수 있고 네이티브 피어가 심각한 자원을 가지고 있지 않을 때에만 해당된다.
    
    주의 할 점은 청소가 필요한 자원은 절대 사용하는 자원에 대한 레퍼런스를 가지면 안된다.
    자원에 대한 인스턴스를 참조할 경우 순환참조가 생겨 가비지 컬렉터가 회수해가지 않기 때문.
    + 정적이 아닌 중첩 클래스는 자동으로 바깥 객체의 참조를 갖게 됨
    + 람다 역시 바깥 객체의 참조를 갖기 쉬움
    
# item-9 try-finally 보다는 try-with-resources 를 사용하라
#### 정리
    자바 라이브러리에는 close 메서드를 호출해 직접 닫아줘야 하는 자원이 많다. ( 회수 )
    이런 닫기는 클라이언트가 놓치기 쉬워서 예측할 수 없는 성능 문제로 이어지기도 한다.
    
    전통적으로 닫기 자원을 사용해야 하는 객체들은 try-finally 구문을 사용했다.
    예외는 try 블록 ( 객체의 메서드 사용 )과 finally 블록 ( 객체의 close 메서드 사용 ) 모두 발생할 수 있는데
    두 블록에서 예외가 모두 발생할 경우 두번째 예외가 첫 번째 예외를 완전히 집어삼켜 버린다.
    그러면 스택 추적 내역에 첫 번째 예외에 관한 정보는 남지 않게 되어, 실제 시스템에서의 디버깅을 몹시 어렵게 한다.
    
    이러한 문제를 자바 7에서 나온 try-with-resources 구조로 해결할 수 있게 되었다.
    이 구조를 사용하려면 해당 자원이 AutoCloseable 인터페이스를 구현해야 한다.
    try-with-resources 는 짧고 읽기 수월할 뿐 아니라 문제를 진단하기도 좋다.
    위에 예시처럼 try, finally 블록에서 모두 예외가 발생할 경우 
    finally 에서 발생한 예외는 숨겨지고 try 에서 발생한 예외가 기록된다.
    물론 숨계진 예외들도 그냥 버려지지 않고, 스택 추적 내역에 숨겨졌다는 꼬리표를 달고 출력된다.
    getSuppressed 메서드를 사용하여 가져올 수 있음.
    
#### 내용 추가
    AutoCloseable 인터페이스를 구현함.
    try-with-resources 구조를 사용하여 호출
```java
public class TryWithResourcesTest implements AutoCloseable{
    @Override
    public void close() {
        System.out.println("닫혔어!");
    }
}
public class TryWithResourceTestMain {
    public static void main(String[] args) {
        try(TryWithResourcesTest test = new TryWithResourcesTest();) {

        }
    }
}
```
    훨씬 더 간결하며 close 메서드를 자동으로 호출해줌.
    
    e.g ) BufferedReader class
    Reader 를 상속 받고 있으며 Reader 는 AutoCloseable 을 상속하고 있는 Closeable 을 구현하고 있음
    BufferedReader 의 close 메서드
```java
public class BufferedReader extends Reader {
    public void close() throws IOException {
        synchronized (lock) {
            if (in == null)
                return;
            try {
                in.close();
            } finally {
                in = null;
                cb = null;
            }
        }
    }
}
```

# item-10 equals 는 일반 규약을 지켜 재정의하라
#### 정리
    equals 메서드는 재정의하기 쉬워 보이지만 곳곳에 함정이 도사리고 있어서 자칫하면 끔찍한 결과를 초래한다.
    다음과 같은 상황이라면 재정의하지 않는 것이 최선이다.
        - 각 인스턴스가 본질적으로 고유하다. 값을 표현하는 게 아니라 동작하는 개체를 표현하는 클래스 ( Thread 등 )
        - 인스턴스의 논리적 동치성을 검사할 일이 없다.
        - 상위 클래스에서 재정의한 equals 가 하위 클래스에도 딱 들어맞는다.
        - 클래스가 private 이거나 package-private 이고 equals 메서드를 호출할 일이 없다.
        
    equals 는 논리적 동치성을 확인해야 할 때 재정의하여 사용한다.
    보통 값이 두 값 객체를 equals 로 비교하는 프로그래머는 객체가 같은지가 아니라 값이 같은지를 알고 싶어한다.
    equals 가 논리적 동치성을 확인하도록 재정의해두면, *Map의 키와 Set 의 원소로 사용할 수 있게 된다.
    
    equals 메서드를 재정의할 때 따라야 하는 일반 규약
    
    1. 반사성 : null 이 아닌 모든 참조 값 x에 대해, x.equals(x) 는 true
        반사성은 단순히 말하면 객체는 자기 자신과 같아야 한다는 뜻이다.
        
    2. 대칭성 : null 이 아닌 모든 참조 값 x, y 에 대해 x.equals(y) 가 true 면 y.equals(x) 도 true
        대칭성은 두 객체는 서로에 대한 동치 여부에 똑같이 답해야 한다는 뜻
        
    3. 추이성 : null 이 아닌 모든 참조 값 x, y, z 에 대해, x.equals(y) 가 true 이고
               y.equals(z) 도 true 면 x.equals(z) 도 true
        추이성은 첫 번째 객체와 두 번째 객체도 같고, 두 번째 객체와 세 번째 객체가 같다면,
        첫 번째 객체와 세 번째 객체가 같아야 한다는 뜻
        객체지향의 추상화 이점을 포기하지 않는 한 구체 클래스를 확장해 새로운 값을 추가하면서
        equals 규야글 만족시킬 방법은 존재하지 않는다.
        instanceof 검사를 getClass 의 동일 비교를 사용한다면 ( 상속 관계에 있더라도 완벽히 같은 클래스가 아니면 false 반환 )
        가능하지만 이는 리스코프 치환 원칙 ( 어떤 타입의 중요한 속성은 그 하위 타입에서도 중요하다 
        따라서 그 타입의 메서드는 하위 타입에서도 똑같이 잘 작동해야 한다. ) 의 원칙에 위배된다.
        
        이 문제를 해결할 우회 방법 중 하나는 상속 대신 해당 타입을 필드로 갖고 해당 타입을 반환하는 메서드를 만드는 것이다. 
        
    4. 일관성 : null 이 아닌 모든 참조 값 x, y에 대해 x.equals(y) 를 반복해서 호출하면 항상 true 또는 false 반환
        일관성은 두 객체가 같다면 앞으로도 영원히 같아야 한다는 뜻
        클래스가 불변이든 가변이든 equals 의 판단에 신뢰할 수 없는 자원이 끼어들게 해서는 안 된다.
    5. null-아님 : null 이 아닌 모든 참조 값 x에 대해 x.equals(null) 은 false
    
    equals 메서드 구현 방법
        1. == 연산자를 이용해 입력이 자기 자신의 참조인지 확인한다.
            이는 성능 최적화를 위한 로직
        2. instanceof 연산자로 입력이 올바른 타입인지 확인한다.
        3. 입력을 올바른 타입으로 형변환한다.
        4. 입력 객체와 자기 자신의 대응되는 '핵심' 필드들이 모두 일치하는지 하나씩 검사한다.
        
    equals 를 다 구현했다면 대칭적인지, 추이성이 있는지, 일관적인지 를 검사하자.
    
    equals 재정의시 주의사항
        - equals 를 재정의할 떈 hashCode 도 반드시 재정의
        - 너무 복잡하게 해결하려고 하지 말것
        - Object 타입 외에 다른 매개변수를 갖는 equals 메서드는 선언하지 말것
    
#### 내용 추가
    *Map의 키와 Set 의 원소로 사용
    Map 을 구현하는 클래스들은 ( e.g) HashMap ) 
    put 메서드를 시행할 때 Key 값의 hashCode 를 비교하고 key 의 equals 도 비교한다??
    https://wedul.site/243 
```java
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
}
```
    - Integer 가 키 값인 HashMap 은 다른 해시코드를 가져도 같은 결과값을 반환
```java
public class Item10Add {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        Integer a = 150;
        Integer b = 150;

        System.out.println(System.identityHashCode(a)); // 1712536284
        System.out.println(System.identityHashCode(b)); // 2080166188


        System.out.println(a.equals(b)); // true

        map.put(a, "150");

        System.out.println(map.get(b)); // 150
    }
}
```
    - 객체가 키 값인 HashMap 의 equals 와 hashCode 를 재정의 했을 때 같은 결과값을 반환
```java
public class MapTestObject {
    String a;
    Integer b;

    public MapTestObject(String a, Integer b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MapTestObject)) {
            return false;
        }
        MapTestObject mapTestObject = (MapTestObject) obj;
        return mapTestObject.a.equals(a) && mapTestObject.b.equals(b);
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }
}

public class MapTestObjectTest {
    public static void main(String[] args) {
        Map<MapTestObject, String> map = new HashMap<>();
        map.put(new MapTestObject("test", 300), "TEST");
        
        // equals 또는 hashCode 재정의 되지 않았을 때 null 반환
        System.out.println(map.get(new MapTestObject("test", 300)));
    }
}
```
# item-11 equals 를 재정의하려거든 hashCode 도 재정의하라
#### 정리
    equals 를 재정의한 클래스 모두에서 hashCode 도 재정의해야 한다.
    
    equals 와 hashCode 에 대한 Object 명세의 규약
        - equals 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 
          그 객체의 hashCode 메서드는 몇 번을 호출해도 일관되게 항상 같은 값을 반환해야 한다.
        - equals(Object) 가 두 객체를 같다고 판단했다면, 두 객체의 hashCode 는 똑같은 값을 반환해야 한다.
        - equals(Object) 가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCode 가 서로 다른 값을 반환할 필요는 없다.
    
    hashCode 를 재정의하지 않으면 논리적 동치인 두 객체가 서로 다른 해시코드를 반환한다.
    HashMap 은 해시코드가 다른 엔트리끼리는 동치성 비교를 시도조차 하지 않도록 최적화되어 있기 때문에 재정의 되지 않은 hashCode 를 갖는
    객체는 같은 객체라 판단하더라도 리턴값이 달라질 수 있다.
    
    좋은 hashCode 를 작성하는 요령
        1. int 변수 result 를 선언한 후 값 c 로 초기화 한다. 이 때 c 는 첫 번째 핵심 필드를 2.a 방식으로 계산한 해시코드
        2. 해당 객체의 나머지 핵심 필드들에 아래 작업들을 수행한다.
            a. 해당 필드의 해시코드 c 를 계산한다.
                - 기본 타입 필드이면 박싱 클래스의 hashCode 메서드 수행
                - 참조 타입 필드면서 이 클래스의 equals 메서드가 이 필드의 equals 를 재귀적으로 호출해 비교한다면,
                  이 필드의 hashCode 를 재귀적으로 호출
                - 배열 타입 필드라면 핵심 원소 가각ㄱ을 별도 필드처럼 다룬다. 모든 원소가 핵심 원소이면 Arrays.hashCode 메서드를 사용한다.
            b. 위의 단계에서 계산한 해시코드 c 로 result 를 갱신한다.
                - result = 31 * result + c;
        3. result 를 반환한다.
        
    위의 hashCode 작성 방법중 result 를 갱신하는 부분 ( 2.b ) 에서 곱셈을 해주는 이유는
    앞의 코드에 따라 변화를 다르게 하기 위함이다. 예를 들어 곱셈 부분이 없다면 
    String field1 = "a", String field2 = "b" 인 객체는 
    String field1 = "b", String field2 = "a" 와 같은 해시코드를 갖게 되기 때문이다.
    곱할 숫자를 31로 정한 이유는 홀수이면서 소수이기 때문이다.
    만약 이 숫자가 *짝수이고 오버플로가 발생한다면 정보를 잃게 된다.
    소수를 곱하는 이유는 명확하지 않지만 전통적으로 그리 해왔다.
    
    다른 방법으로 Objects 클래스의 hashCode 를 계산해주는 정적 메서드인 hash 가 있다.
    한줄로 보기 쉽게 작성할 수 있는 이점이 있으나 속도가 더 느리다.
    
    클래스 불변이고 해시코드를 계산하는 비용이 크다면, 캐싱하는 방식을 고려해야 한다.
     
# item-12 toString 을 항상 재정의하라
#### 정리
    toString 메서드의 일반 규약은 "간결하면서 사람이 읽기 쉬운 형태의 유익한 정보를 반환해야 한다." 이다.
    toString 을 잘 구현한 클래스는 사용하기에 훨씬 즐겁고, 그 클래스를 사용한 시스템은 디버깅하기 쉽다.
    toString 은 직접 호출하지 않더라도 다른 어딘가에서 쓰인다.
    toString 은 그 객체가 가진 주요 정보를 모두 반환하는 것이 좋다.
    대다수의 컬렉션 구현체는 추상 컬렉션 클래스들의 toString 메서드를 상속해 쓴다.

# item-13 clone 재정의는 주의해서 진행하라
#### 정리
    Cloneable 인터페이스는 Object 의 protected 메서드인 clone 의 동작 방식을 결정한다.
    이 클래스의 하위 클래스에서 super.clone 을 호출한다면 잘못된 클래스의 객체가 만들어져,
    결국 하위 클래스의 clone 메서드가 제대로 동작하지 않게 된다.
    제대로 동작하는 clone 메서드를 가진 상위 클래스를 상속해 Cloneable 을 구현하고 싶다고 하면
    super.clone 을 호출하고, 얻은 객체는 원본의 완벽한 복제본이며, 클래스에 정의된 모든 필드는
    원본 필드와 똑같은 값을 갖는다.
    
    clone 메서드는 사실상 생성자와 같은 효과를 낸다.
    즉, clone 은 원본 객체에 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야 한다.
    
    배열의 clone 은 런타임 타입과 컴파일타임 타입 모두가 원본 배열과 똑같은 배열을 반환한다.
    
    만약 clone 이 하위 클래스에서 재정의한 메서드를 호출하면, 하위 클래스는 복제 과정에서 
    자신의 상태를 교정할 기회를 잃게 되어 원본과 복제본의 상태가 달라질 가능성이 크다.
    
    Cloneable 을 구현하는 모든 클래스는 clone 을 재정의해야 한다.
    이때 접근 제한자는 public 으로, 반환 타입은 클래스 자신으로 변경한다.
    이 메서드는 가장 먼저 super.clone 을 호출한 후 필요한 필드르 전부 적절히 수정한다.
    수정 방법은 그 객체의 내부 '깊은 구조' 에 숨어 있는 모든 가변 객체를 복사하고,
    복제본이 가진 객체 참조 모두가 복사된 객체들을 가리키게 한다.
    
    이러한 내부 복사는 항상 최선은 아니다.
    Cloneable 을 이미 구현한 클래스가 아니라면,
    복사 생성자와 복사 팩터리라는 더 나은 객체 복사 방식을 제공할 수 있다.
    
# item-14 Comparable 을 구현할지 고려하라
#### 정리
    compareTo 는 단순 동치성 비교에 더해 순서까지 비교할 수 있으며, 제네릭하다.
    Comparable 을 구현했다는 것은 그 클래스의 인스턴스들에는 자연적인 순서가 있음을 뜻한다.
    compareTo 규약
        1. 두 객체 참조의 순서를 바꿔 비교해도 예상한 결과가 나와야 한다.
        2. 첫 번째가 두 번째보다 크고, 두 번째가 세 번째보다 크면, 첫 번째는 세 번째보다 커야 한다.
        3. 크기가 같은 객체들끼리는 어떤 객체와 비교하더라도 항상 같아야 한다. ( equals 의 결과가 true 이면 compareTo 결과는 0 이어야 함 )
    compareTo 메서드로 수행하는 동치성 검사는 반사성, 대칭성, 추이성을 충족해야 한다.
    
    3번 규약의 예외상황 예
    new BigDecimal("1.0") 과 new BigDecimal("1.00") 은 compareTo 메서드로 비교했을 때 0을 반환 equals 는 false
    
    일반적으로 래핑 클래스가 제공하는 compare 메서드를 사용하여 비교함.
    
# item-15 클래스와 멤버의 접근 권한을 최소화하라
#### 정리
    어설프게 설계된 컴포넌트와 잘 설계된 컴포넌트의 가장 큰 차이는 
    바로 클래스 내부 데이터와 내부 구현 정보를 외부 컴포넌트로부터 얼마나 잘 숨겼느냐다.
    구현과 API 를 깔끔히 분리한다.
    정보 은닉, 혹은 캡슐화라고 하는 이 개념은 소프트웨어 설계의 근간이 되는 원리다.
    정보 은닉의 장점은 시스템 개발 속도를 높이고, 시스템 관리 비용을 낮추고, 성능 최적화에 도움을 주고, 
    소프트웨어 재사용성을 높이고, 큰 시스템을 제작하는 난이도를 낮춰준다.
    
    모든 클래스와 멤버의 접근성을 접근제한자를 통하여 가능한 한 좁혀야 한다.
    그러나 멤버 접근성을 좁히지 못하게 하는 제약이 하나 있는데, 상위 클래스의 메서드를 재정의할 때는
    그 접근 수준을 상위 클래스에서보다 좁게 설정할 수 없다는 것이다.
    
    public 클래스의 인스턴스 필드는 되도록 public 이 아니어야 한다.
    public 가변 필드를 갖는 클래스는 일반적으로 스레드에 안전하지 않다.
    public 클래스의 필드 중 예외로 public 인 필드는 public static final 로 선언되는 상수 필드다.
    상수 필드는 반드시 기본 타입 값이나 불변 객체를 참조해야 한다.

# item-16 public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라.
#### 정리
    public 클래스는 절대 가변 필드를 직접 노출해서는 안 된다.
    그러나 package-private 클래스나 private 중첩 클래스에서는 종종 필드를 노출하는 편이 나을 때도 있다.

# item-17 변경 가능성을 최소화하라.
#### 정리
    불변 클래스란 간단히 말해 그 인스턴스의 내부 값을 수정할 수 없는 클래스다.
    불변 인스턴스에 간직된 정보는 고정되어 객체가 파괴되는 순간까지 절대 달라지지 않는다.
    
    클래스를 불변으로 만들려면 다섯가지 규칙을 따르면 된다.
    1. 객체의 상태를 변경하는 메서드 ( 변경자 ) 를 제공하지 않는다.
    2. 클래스를 확장할 수 없도록 한다.
    3. 모든 필드를 final 로 선언한다.
    4. 모든 필드를 private 으로 선언한다.
    5. 자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다.
    
    피연산자에 함수를 적용해 그 결과를 반환하지만, 피연산자 자체는 그대로인 프로그래밍 패턴을 함수형 프로그래밍이라 한다.
    이와 달리, 절차적 혹은 명령형 프로그래밍에서는 메서드에서 피연산자인 자신을 수정해 자신의 상태가 변하게 된다.
    또한 메서드 이름으로 ( add 같은 ) 동사 대신 ( plus 같은 ) 전치사를 사용한 점에도 주목하자.
    이는 해당 메서드가 객체의 값을 변경하지 않는다는 사실을 강조하려는 의도다.
    함수형 프로그래밍 방식으로 프로그래밍하면 코드에서 불변이 되는 영역의 비율이 높아진다.
    
    - 불변 클래스의 이점
        불변 객체는 단순하며, 근본적으로 스레드 안전하여 따로 동기화할 필요 없고, 안심하고 공유할 수 있다.
        
        불변 클래스는 자주 사용되는 인스턴스를 캐싱하여 같은 인스턴스를 중복 생성하지 않게 해주는 정적 팩터리를 제공할 수 있다.
        
        불변 객체는 자유롭게 공유할 수 있음은 물론, 불변 객체끼리는 내부 데이터를 공유할 수 있다.
        객체를 만들 때 다른 불변 객체들을 구성요소로 사용하면 이점이 많다.
        값이 바뀌지 않는 구성요소들로 이뤄진 객체라면 그 구조가 아무리 복잡하더라도 불변식을 유지하기 훨씬 수월하기 때문이다.
        
        불변 객체는 그 자체로 실패 원자성 ( 메서드에서 예외가 발생한 후에도 그 객체는 여전히 메서드 호출 전과 똑같은 상태여야 한다 ) 를 제공한다.
    
    불변 클래스의 단점은 값이 다르면 반드시 독립된 객체로 만들어야 한다는 것이다. ( 새로운 객체 생성으로 성능 저하 )
    이를 대처하는 방법은 예상되는 연산을 예측하여 기본 기능으로 제공하는 가변 동반 클래스를 따로 두는 것이다.
    e.g ) String 과 StringBuilder
    
    자신을 상속하지 못하게 하는 가장 쉬운 방법은 final 클래스로 선언 하는 것이지만,
    더 유연한 방법으로 생성자를 private 으로 만들고 public 정적 팩터리를 제공하는 방법이다.
    정적 팩터리 방식은 다수의 구현 클래스를 활용하여 유연성을 제공하고, 
    이에 더해 다음 릴리스에서 객체 캐싱 기능을 추가해 성능을 끌어올릴 수도 있다.
    
    Serializable 을 구현하는 불변 클래스의 내부에 가변 객체를 참조하는 필드가 있다면 readObject 나 readResolve 메서드를 반드시 제공,
    또는 ObjectOutputStream.writeUnshared 와 ObjectInputStream.readUnshared 메서드를 사용해야 함.
    아니면 역직렬화
    
    클래스는 꼭 필요한 경우가 아니라면 불변이어야 한다.
    성능 때문에 어쩔수 없다면 불변 클래스와 쌍을 이루는 가변 동반 클래스를 publci 클래스로 제공하도록 하자.
    
    불변으로 만들 수 없는 클래스라도 변경할 수 있는 부분을 최소한으로 줄이자.
    다른 합당한 이유가 없다면 모든 필드는 private final 이어야 한다.
    생성자는 불변식 설정이 모두 완료된, 초기화가 완벽히 끝난 상태의 객체를 생성해야 한다.
    확실한 이유가 없다면 생성자와 정적 팩터리 외에는 그 어떤 초기화 메서드도 public 으로 제공해서는 안 된다.
    객체를 재활용할 목적으로 상태를 다시 초기화하는 메서드도 안 된다.

# item-18 상속보다는 컴포지션을 사용하라.
#### 정리
    상위 클래스와 하위 클래스를 모두 같은 프로그래머가 통제하는 패키지 안에서라면 상속도 안전한 방법이다.
    그러나 상속은 메서드 호출과 달리 캡슐화를 깨뜨린다.
    상위 클래스는 릴리스마다 내부 구현이 달라질 수 있으며, 그 여파로 코드 한 줄 건드리지 않은 하위 클래스가 오동작할 수 있다.
    이러한 이유로 상위 클래스 설계자가 확장을 충분히 고려하고 문서화도 제대로 해두지 않으면 
    하위 클래스는 상위 클래스의 변화에 발맞춰 수정돼야만 한다.
```java
public abstract class AbstractCollection<E> implements Collection<E> {
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add(e))
                modified = true;
        return modified;
    }
}
```
    위의 AbstractCollection 클래스의 addAll 메서드는 내부의 add 메서드를 호출하는 과정을 담고있다.
    이에 AbstractCollection 클래스를 상속하는 하위 클래스가 add를 정의한다면 그 add 메서드가 호출된다.
    그럼 addAll 메서드도 함께 영향을 받게 된다.
    
    addAll 메서드를 다른 식으로 재정의 할 수도 있다.
    그러나 상위 클래스의 메서드 동작을 다시 구현한느 방식은 어렵고, 시간도 더 들며, 자칫 오류를 내거나 성능을 떨어뜨릴 수 있다.
    또한 하위 클래스에서는 접근할 수 없는 private 필드를 써야 하는 상황이라면 이 방식으로는 구현이 불가능하다.
    
    메서드 재정의 대신 새로운 메서드를 추가하는 방법도 있다.
    그러나 운 없게도 하필 하위 클래스에 추가한 메서드와 시그니처가 같고 반환 타입이 다르다면 클래스는 컴파일조차 되지 않는다.
    
    이 문제들을 피해가려면 기존 클래스를 확장하는 대신, 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스를 참조하게 해야 한다.
    기존 클래스가 새로운 클래스의 구성요소로 쓰인다는 뜻에서 이러한 설계를 컴포지션 ( composition, 구성 ) 이라 한다.
    새 클래스의 인스턴스 메서드들은 ( private 필드로 참조하는 ) 기존 클래스의 대응하는 메서드를 호출해 그 결과를 반환한다.
    이 방식을 전달 ( forwarding ) 이라 하며, 새 클래스의 메서드들은 전달 메서드 ( forwarding method ) 라 부른다.
    새로운 클래스는 기존 클래스의 내부 구현 방식의 영향에서 벗어나며, 심지어 기존 클래스에 새로운 메서드가 추가되더라도 영향받지 않는다.
    
    이런 인스턴스를 감싸고 있는 클래스를 래퍼 클래스라 하며, 인스턴스의 기능에 새로운 기능을 덧붙인다하여 데코레이터 패턴이라 부른다.
    컴포지션과 전달의 조합은 넓은 의미로 위임 ( delegation )이라고 부른다.
    
    전달 메서드들을 작성하는 게 지루하겠지만, 재사용할 수 있는 전달 클래스를 인터페이스당 하나씩만 만들어두면 
    원하는 기능을 덧씌우는 전달 클래스들을 아주 손쉽게 구현할 수 있다.
    
    클래스 A 를 상속하는 클래스 B 를 ㅈ가성하려 한다면 " B 가 정말 A 인가? " 를 자문해보자.
    
    컴포지션을 써야 할 상황에서 상속을 사용하는 건 내부 구현을 불필요하게 노출하는 꼴이다.
    그 결과 API 가 내부 구현에 묶이고 그 클래스의 성능도 영원히 제한된다.
    더 심각한 문제는 클라이언트가 노출된 내부에 직접 접근할 수 있다는 점이다.
    
    컴포지션으로는 이런 결함을 숨기는 새로운 API 를 설계할 수 있지만, 상속은 상위 클래스의 API 를 ' 결함까지 ' 그대로 승계한다.

# item-19 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라.
#### 정리
    상속을 염두에 두지 않고 설계했고 상속할 때의 주의점도 문서화해놓지 않은 '외부' 클래스를 상속할 때의 위험을 경고했다.
    여기서 ' 외부 ' 란 프로그래머의 통제권 밖에 있어서 언제 어떻게 변경될지 모른다는 뜻이다.
    
    상속을 고려한 설계의 문서화란 메서드를 재정의하면 어떤 일이 일어나는지를 정확히 정리하여 문서로 남기는 것이다.
    상속용 클래스는 재정의할 수 있는 메서드들을 내부적으로 어떻게 이용하는지 문서로 남겨야 한다.
    재정의 가능 메서드를 호출할 수 있는 모든 상황을 문서로 남겨야 한다.
    상속이 캡슐화를 해치기 때문에 클래스를 안전하게 상속할 수 있도록 하려면 내부 구현 방식을 설명해야만 한다.
    
    클래스의 내부 동작 과정 중간에 끼어들 수 있는 훅( hook ) 을 잘 선별하여 protected 메서드 형태로 공개해야 할 수도 있다.
    
    널리 쓰일 클래스를 상속용으로 설계한다면 어러분이 문서화한 내부 사용 패턴과, protected 메서드와 필드를 구현하면서
    선택한 결정에 영원히 책임져야 함을 잘 인식해야 한다.
    
    상속용 클래스의 생성자는 직접적으로든 간접적으로든 재정의 가능 메서드를 호출해서는 안 된다.
    상위 클래스의 생성자가 하위 클래스의 생성자보다 먼저 실행되므로 
    하위 클래스에서 재정의한 메서드가 하위 클래스의 생성자보다 먼저 호출된다.
    
    Cloneable 과 Serializable 인터페이스는 상속용 설계의 어려움을 한층 더해준다.
    clone 과 readObject 메서드는 생성자와 비슷한 효과를 낸다.
    clone 과 readObject 모두 직접적으로든 간접적으로든 재정의 가능 메서드를 호출해서는 안 된다.
    
    Serializable 을 구현한 상속용 클래스가 readResolve 나 writeReplace 메서드를 갖는다면 
    이 메서드들은 private 이 아닌 protected 로 선언해야 한다.
    private 으로 선언한다면 하위 클래스에서 무시되기 때문이다.
    
    이런 많은 오류들로 인해 상속을 설계하지 않고 만든 클래스는 상속을 금지하는것이 좋다.
    상속을 금지하는 방법은 앞에서 다룬것처럼 final 로 선언, 또는 private 생성자와 정적팩터리 메서드 이다.
    
    구체 클래스가 표준 인터페이스를 구현하지 않았는데 상속을 금지하면 사용하기에 불편함이 있다.
    이런 클래스라도 상속을 꼭 허용해야겠다면 클래스 내부에서는 재정의 가능 메서드를 사용하지 않게 만들고
    이를 문서화 하는 것이다.
    
    재정의 가능 메서드를 사용하는 코드를 제거할 수 있는 방법은 각각의 재정의 가능 메서드를 private '도우미 메서드' 로 옮기고,
    이 도우미 메서드를 호출하도록 수정한다. 
    그런 다음 재정의 가능 메서드를 호출하는 다른 코드들도 모두 이 도우미 메서드를 직접 호출하도록 수정하면 된다.    

# item-20 추상 클래스보다는 인터페이스를 우선하라.
#### 정리
    인터페이스와 추상 클래스의 가장 큰 차이는 추상 클래스가 정의한 타입을 구현하는 클래스는 
    반드시 추상 클래스의 하위 클래스가 되어야 한다는 점이다.
    자바는 단일 상속만 지원하기 때문에, 추상 클래스 방식은 새로운 타입을 정의하는 데 커다란 제약을 안게 된다.
    반면 인터페이스가 선언한 메서드를 모두 정의하고 그 규약을 잘 지킨 클래스라면 다른 어떤 클래스를 상속했든 같은 타입으로 취급된다.
    
    인터페이스는 주된 타입 외에도 특정 선택적 행위를 제공한다고 선언하는 효과를 준다.
    인터페이스로는 계층구조가 업슨 타입 프레임워크를 만들 수 있다.
    
    인터페이스는 기능을 향상시키는 안전하고 강력한 수단이 된다.
    
    인터페이스 메서드 중 구현 방법이 명백한 것이 있다면, 디폴트 메서드로 제공할 수 있다.
    디폴트 메서드에는 제약이 있다. equals 와 hashCode 같은 Object 메서드를 제공해서는 안된다.
    또 인터페이스는 인스턴스 필드를 가질 수 없고,  public 이 아닌 정적 멤버도 가질 수 없다.
    
    인터페이스와 추상 골격 구현 클래스를 함께 제공하는 식으로 인터페이스와 추상 클래스의 장점을 모두 취하는 방법도 있다.
    인터페이스로는 타입을 정의하고, 골격 구현 클래스는 나머지 메서드들까지 구현한다.
    골격 구현은 그 인터페이스로 나름의 구현을 만들려는 프로그래머의 일을 상당히 덜어준다.
    골격 구현 클래스의 아름다움을 추상클래스처럼 구현을 도와주는 동시에,
    추상 클래스로 타입을 정의할 때 따라오는 심각한 제약에서는 자유롭다는 점에 있다.
    
    골격 구현 작성은 인터페이스를 잘 살펴 다른 메서드들의 구현에 사용되는 기반 메서드들을 선정한다.
    기반 메서드들을 사용해 직접 구현할 수 있는 메서드를 모두 디폴트 메서드로 제공한다.
    만약 인터페이스의 메서드 모두가 기반 메서드와 디폴트 메서드가 된다면 골격 구현 클래스를 별도로 만들 필요가 없다.

# item-20 인터페이스는 구현하는 쪽을 생각해 설계하라
#### 정리
    자바 8버전 이전에는 인터페이스 설계시 더이상 추가될 메서드는 없다는 개념을 가지고 인터페이스를 설계했지만,
    자바 8버전 이후부터는 구현 클래스들을 수정하지 않고 메서드를 추가할 수 있도록 인터페이스의 디폴트 메서드가 추가됐다.
    
    디폴트 메서드를 사용할 때는 고민해야 할 점들이 있다.
    인터페이스에서 메서드를 추가할 경우 실제 구현체에 강제로 해당 메서드를 구현해야 하는 강제성이 생겼지만, 
    디폴트 메서드를 추가할 경우에는 반대로 구현체가 이 메서드를 사용하는데 적절한지를 판단해야 한다.
    적절한지를 판단하고 사용하는데 적절하지 않다고 하면 재정의 메서드를 통하여 디폴트 메서드를 호출 하기 전 필요한 작업을 수행해야 한다.
    
    책에서 예로 든 SynchronizedCollection 으로 설명을 하면,
    이 클래스는 모든 메서드에서 주어진 락 객체로 동기화한 후 내부 컬렉션 객체에 기능을 위임하는 래퍼 클래스로
    Collection 인터페이스를 구현하고 있으며, Collcetion 인터페이스는 default method 인 removeIf(Predicate filter) 를 가지고 있다.
    SynchronizedCollection 의 설명처럼 모든 메서드에서 주어진 락 객체로 동기화를 해야 하는데,
    실제 구현 클래스에서는 removeIf 메서드를 가지고 있지 않으므로 해당 메서드는 동기화에 성공하지 못한다.
    따라서 여러 스레드가 SynchronizedCollection 객체를 공유하는 환경에서 Exception 이 발생할 수 있다.
    
    이런 예상하지 못하는 경우가 발생할 수 있기 때문에, 기존 인터페이스에 디폴트 메서드를 추가하는 일은 꼭 필요한 경우가 아니면 피해야 한다.
    물론 새로운 인터페이스를 만드는 경우라면 표준적인 메서드 구현을 제공하는데는 아주 유용한 수단이다.
    그러나 위와 같이 디폴트 메서드가 이후 구현체들에 영향을 끼칠것을 심사숙고하여 메서드를 추가하는 과정이 꼭 필요하다.   

# item-22 인터페이스는 타입을 정의하는 용도로만 사용하라
#### 정리
    인터페이스는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할을 한다.
    e.g ) Interface instance = new InstanceImple();
    인터페이스는 오직 이 용도로만 사용해야 한다.
    ( 행동을 정의하여 그 행동에 맞는 분류를 위해 사용한다고 생각했음 이게 객체지향에서의 정의 ? )
    
    이런 용도에 맞지않게 사용하는 것이 상수 인터페이스, static final 필드로 가득찬 인터페이스 이다.
    상수 인터페이스의 상수 필드들은 실제 해당 인터페이스의 구현체 안에서 사용할 수 있다.
    이런 경우는 내부에서 사용할 상수 객체들을 바깥, 즉 클라이언트에서 자유자재로 사용할 수 있는 API 로 제공하므로
    실제로는 알 필요 없는 내부 구현을 공개하는 꼴이 되어버린다.
    이런 상수들은 클라이언트에서 오히려 혼란을 주며, 클라이언트 코드들이 상수값에 종속되어 버리는 경우도 발생한다.
    
    이런 좋지않은 인터페이스 패턴은 지양하고, 상수를 공개하는 것이 목적이라면 상수 인터페이스를 구현하지 않고
    연관이 깊은 해당 클래스 또는 해당 인터페이스 자체에 추가해야 한다.

# item-23 태그 달린 클래스보다는 클래스 계층구조를 활용하라
#### 정리
    두 가지 이상의 의미를 표현할 수 있으며, 그 중 현재 표현하는 의미를 태그 값으로 알려주는 클래스를 태그 달린 클래스라 부른다.
    이런 클래스는 쓸데없는 코드가 많고, 가독성이 나쁘며, 
    필드를 final로 선언하려면 해당 의미에 쓰이지 않는 필드들까지 생성자에서 초기화 해야 한다.
    또한 다른 의미를 표현하기 위해서는 코드를 수정해야 한다.
    게다가 인스턴스의 타입만으로는 현재 어떤 상태를 표현하는지를 알 수 없다.
    이런 태그달린 클래스는 장황하고, 오류를 내기 쉽고, 비효율적이다.
    
    이러한 태그달린 클래스를 계층 구조 클래스로 변경하자
    루트 클래스 ( 공통 ) 를 추상 클래스로 정의하고
    태그값에 따라 달라지는 메서드를 추상 메서드로 추가하고
    태그값에 따라 달라지지 않는 메서드는 일반 메서드로 추가한다.
    공통 필드들도 루트 클래스에 추가한다.
    태그 값을 정의하는 클래스를 루트 클래스의 하위 클래스로 정의한 다음,
    해당 태그 값에 필요한 필드 ( 원래는 한 클래스에 몰려있었을 ) 들을 정의하고
    루트 클래스에 선언한 추상 메서드를 구현 ( 해당 필드를 사용하여 ) 한다. 
    
    계층 구조 클래스의 장점은 루트 클래스를 건드리지 않고 프로그래머들이 독립적으로 계층구조를 확장하고
    함께 사용할 수 있다.
    타입 사이의 자연스러운 계층 관계를 반영할 수 있어서 유연성은 물론 컴파일 타임 타입 검사 능력을 높여준다는 장점도 있다.

# item-24 멤버 클래스는 되도록 static 으로 만들라
#### 정리
    중첩 클래스의 종류는 
    정적 멤버 클래스, ( 비정적 ) 멤버 클래스, 익명 클래스, 지역 클래스
    네 가지 이다.
    
    정적 멤버 클래스는 내부 클래스로 static 선언 된 클래스로,
    일반 탑 레벨 클래스와 다른 점은 다른 클래스의 내부에 선언된다는 점과 
    정적 멤버 클래스를 내부에 선언한 바깥 클래스의 private 멤버에도 접근할 수 있다는 점이다.
    
    ( 비정적 ) 멤버 클래스는 내부 클래스로 선언 된 static 하지 않은 클래스로,
    멤버 클래스의 인스턴스와 바깥 클래스의 인스턴스 관계는 멤버 클래스가 인스턴스화 될 때 확립되며,
    이 때 바깥클래스와의 관계 정보 ( 참조 ) 를 갖게 된다.
    이러한 숨은 외부 참조는 메모리를 소비하며, 가비지 컬렉션이 바깥 클래스의 인스턴스를 수거하지 못하게 되는 메모리 누수가 생길 수 있다.
    
    이렇기 때문에 만약 내부 클래스에서 바깥 클래스에 대한 참조가 필요 없는 경우,
    정적 멤버 클래스를 선언하는 편이 좋다.
    
    * 멤버 클래스가 공개된다면 혹시라도 향후 릴리스에서 static 을 붙이면 하위 호환성이 깨진다.
    
    익명 클래스란 이름 없는 클래스로
    쓰이는 시점에 선언과 동시에 인스턴스가 생성되며, 오직 비정적인 문맥에서 사용될 때만 바깥 클래스의 인스턴스를 참조할 수 있다.
    구현 또는 상속 선언 부가 없으므로 ?, 한 인터페이스 또는 한 클래스만 구현, 상속 할 수 있다.
    
    지역 클래스는 지역 변수를 선언할 수 있는 곳이면 선언 가능하며,
    유효 범위도 지역 변수와 같다.
    멤버 클래스처럼 이름이 있고, 반복해서 사용가능하며, 비정적 문맥에서 사용 될 때만 바깥 인스턴스를 참조할 수 있다.

# item-25 톱레벨 클래스는 한 파일에 하나만 담으라
#### 정리
    한 파일에 여러 클래스를 선언 할 경우 다른 파일에서 중복 클래스 선언이 이루어 질 수 있으므로,
    한 파일에 톱 클래스 레벨은 하나의 클래스만 정의하고, 내부적으로 파생된 ( 사용 될 ) 클래스이면 정적 멤버 클래스를 선언하는 편이 좋다.

# item-26 로 타입은 사용하지 말라
#### 정리
    클래스와 인터페이스 선언에서 타입 매개변수가 쓰이면 이를 제네릭 클래스 혹은 제네릭 인터페이스라 한다.
    로 타입이란 제네릭 타입에서 타입 매개변수를 사용하지 않은 타입을 말한다.
    e.g ) Set<String> -> 제네릭 타입 선언, Set -> 로 타입
    
    로 타입은 위험하다.
    로 타입은 Object 를 제네릭 타입으로 갖는 타입과는 다르게 제네릭 타입에서 완전히 물러선 모양이고
    Object 를 제네릭 타입으로 갖는다면 어떤 매개변수든지 수용할 수 있다는 뜻이다.
    그리고 비한정적 와일드카드 타입인 ? 제네릭타입은 null 외엔 어떤 원소도 넣을 수 없다.
    
    이처럼 불안정한 로 타입은 자바 5 이전에 작성된 코드들을 수용할 수 있게 하기 위해
    아직까지 제거되지 않고 남아있다.
    
    로 타입을 사용해야하는 몇몇 경우도 있는데
    class 리터럴에는 로 타입을 써야하고, instanceof 연산자에서는 로 타입을 권장한다.
    class 리터럴은 자바 명세에 매개변수화 타입을 사용하지 못하게 했고,
    instanceof 연산자는 로타입과 비한정적 와일드카드 외에는 적용하지 못하게 되어있으며,
    와일드카드의 <?> 는 로타입과 똑같이 동작하기 때문에 생략하는 편이 더 낫다.

# item-27 비검사 경고를 제거하라
#### 정리
    제네릭을 사용하면 컴파일러 경고를 볼 수 있다.
    컴파일러가 알려주는 비검사 경고는 최대한 제거하는 편이 좋다.
    경고를 제거할 수는 없지만 타입이 안전하다고 판단되면 @SuppressWarning("unchecked") 어노테이션을 사용한다.
    @SuppressWarning 어노테이션은 최대한 작은 범위에서 사용하는 편이 좋다.
    최대한 명확한 비검사 경고를 제공하기 위해서다.
    메서드 안의 지역변수에도 할당할 수 있다.
    
    e.g )
    ArrayList 클래스의 toArray 메서드 중 타입 캐스팅 을 사용하는 Arrays.copyOf 메서드를 리턴하기 전
    지역변수를 선언하고 비검사 경고를 제거할 수 있다. 
```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
            
            // 지역변수에서 비검사 경고 제거
            @SuppressWarnings("unchecked") T[] result = (T[]) Arrays.copyOf(elementData, size, a.getClass());
            return result;
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
}
```
    
    비검사 경고를 제거할 때 확실한 타입이 검증되지 않은 상태에서 사용하면 컴파일은 되지만 런타임에서 CastClassException 이 날수 있다.
    또한 확실한 타입이 검증되었으나 비검사 경고를 제거하지 않으면 다른 오류들을 반환하지 않고 비검사 경고만 반환하기 때문에
    자칫 중요한 경고를 알지 못할 수 있다.
    
    비검사 경고를 제거할 때는 주석으로 다음 프로그래머에게 알려줄 이유를 남겨야 한다.

# item-28 배열보다는 리스트를 사용하라
#### 정리
    배열은 공변 ( 상위 ( 부모 ) 타입으로 하위 ( 자식 ) 타입을 치환 할 수 있다 ) 이다.
    제네릭은 불공변이다.
    
    배열은 우리가 흔히 사용하는 타입처럼 부모 <- 자식 의 좁은 타입으로 교체가 가능하지만
    제네릭은 고정된 ( 컴파일 과정에서 확정하는 ) 타입 외에는 사용할 수 없다. ( 교체가 불가하다. )
    
    따라서 배열은 하위 타입의 배열로 구현 할 수 있고, 정의한 타입이 아닌 다른 타입을 사용할 때 런타임에서야 알 수 있지만,
    제네릭을 사용하는 리스트는 어차피 고정된 타입 외에는 사용할 수 없으므로 컴파일 전부터 에러를 반환한다.
    
    배열은 런타임에도 자기 자신이 담고 있는 원소의 타입을 인지하고 확인한다. 따라서 자신이 구현한 타입인지 아닌지를 런타임에도 확인할 수 있다.
    제네릭은 타입 정보가 런타임때 소거되어 런타임 중에는 알 수 없다. 따라서 컴파일 과정에서만 검사하여 미리 걸러낸다.
    
    이러한 차이점으로 인해 제네릭타입의 배열 타입은 함께 사용할 수 없다.
    
    *실체화 불가 타입은 런타임에 컴파일타임보다 더 적은 타입 "정보"를 갖는것을 말한다.
    소거 매커니즘 때문에 매개변수화 타입 가운데 실체화될 수 있는 타입은 List<?> 와 Map<?, ?> 같은 **와일드 카드 타입뿐이다.
    
    제네릭 컬렉션에서는 자신의 원소 타입을 담은 배열을 반환하는 게 보통은 불가능하다.
    제네릭과 가변인수 메서드를 함께 사용할 때 가변인수 매개변수를 담을 배열이 생성되면서 배열이 담을 원소가 실체화 불가 타입 이라면 에러를 반환한다.
    
    타입에 안전하지 않은 배열 보다는 안전한 제네릭을 포함한 리스트를 사용하는 것이 좋다.
    
#### 내용 추가
    *실체화 불가 타입은 런타임에 컴파일타임보다 더 적은 타입 "정보"를 갖는것을 말한다.
        - 아무리 찾아도 이펙티브 자바에서 나온 내용 뿐
        https://jojoldu.tistory.com/25 아주 좋은 예시지만 이해를 못함
    **와일드 카드
        알 수 없는( 알 필요 없는? ) 매개 변수를 표현하는 제네릭
        실제 컴파일때 정의되는 타입에 중점을 두기 보단 그 타입을 가지고 행하는 메서드에 중점을 둠
        예를 들어 List<?> 를 선언하고 받는다면
        실제 들어오는 List 인터페이스의 메서드를 실행하는데에 중점을 둘때 사용함
        
        <? super A> 가능 A 의 super 클래스 삽입 가능
        + 그럼 로타입으로 써도 가능하지만 타입의 불변성을 깨고 싶지 않기 때문에 와일드 카드를 사용함.
        
# item-29 이왕이면 제네릭 타입으로 만들라
#### 정리
    제네릭 타입으로 변경하는 방법은 두가지가 있다.
    첫번째는 사용할 필드를 구현할 때 제네릭 타입으로 구현하는 것,
    두번째는 해당 객체를 사용할 때 제네릭 타입으로 변환하는 것이다.
    
    첫번째 방법이 두번째 방법보다 코드도 짧고 ( 첫번째 방법은 필드를 생성할 때 한번 캐스팅하면 되지만,
    두번째 방법은 객체를 사용할 때마다 캐스팅이 필요함 ), 가독성도 더 좋다.
    그래서 첫번째 방법을 선호하지만, 첫번 째 방법은 배열의 런타임 타입이 컴파일타임 타입과 달라 힙 오염을 일으킨다.
    
    제네릭에는 기본 타입은 올 수 없다. 그래서 래퍼클래스를 사용한다.

# item-30 이왕이면 제네릭 메서드로 만들라
#### 정리
    제네릭은 런타임에 타입 정보가 소거 되므로 하나의 객체를 어떤 타입으로든 매개변수화 할 수 있다.
    하지만 이렇게 하려면 요청한 타입 매개 변수에 맞게 매번 그 객체의 타입을 바꿔주는 정적 팩터리를 만들어야 한다.
    
    항등함수란 입력 값을 수정 없이 그대로 반환하는 특별한 함수이므로, 어떤 타입이든 리턴 타입에 사용해도 타입 안전하다.
    
    자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정할 수 있다. 재귀적 타입 한정

# item-31 한정적 와일드카드를 사용해 API 유연성을 높여라
#### 정리
    가변 파라미터 타입 ( 제네릭 ) 을 사용할 때 소비하는 대상이면 super, 생산하는 대상이면 extends 하라.
    
    매개변수화 타입은 불공변으로 타입 확장이 불가능하다.
    따라서 유연한 설계가 어렵다.
    그렇기 때문에 확장을 포함한 와일드 카드를 적절히 사용하는 편이 설계의 유연함에 도움을 준다.
    
    PECS : Produces Extends, Consumers Super
    
    메서드 선언에 타입 매개변수가 한 번만 나오면 와일드카드로 대체하라.

# item-32 제네릭과 가변인수를 함께 쓸 때는 신중하라
#### 정리
    가변인수 즉 여러개의 같은 타입을 매개변수로 받을 때 제네릭을 함께 사용하는것은 주의해야 한다.
    
    언어 개발자는 제네릭 배열 형태는 허용하지 않았지만, 제네릭과 함께 사용하는 가변인수는 허용하였다.
    그 이유는 가변인수 제네릭 타입은 유용하게 쓰이는 경우가 많기 때문이다.
    @SafeVarargs 애너테이션으로 타입 안전함을 보장한다는 명시를 할 수 있다.
    
    제네릭과 가변인수가 함께 사용될 때는 두가지 경우에만 허용 된다.
    개발자가 안전하다고 판단하고 @SafeVarargs 를 선언하거나 
    제네릭 타입을 사용하는 메서드에서는 가변인자가 아닌 List 타입으로 변환해 넘기는 것이다.

# item-33 타입 안전 이종 컨테이너를 고려하라
#### 정리
    컨테이너 대신 키를 매개변수화한 다음, 컨테이너 에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공하면 된다.
    제네릭 타입 시스템이 값의 타입이 키와 같음을 보장해줄 것이다.
    이런 설계 방식을 타입 안전 이종 컨테이너 패턴이라 함.
    
    예시로 든 Favorites 클래스를 보면, 각 타입의 Class 객체를 매개변수화하여 키 역할로 사용하는데
    이는 class의 클래스가 제네릭이기 때문에 동작한다.
    예컨데 *String.class 는 Class<String> 이고 Interger.class 는 Class<Integer> 이다.
    컴파일 타입 정보와 런타임 타입 정보를 알아내기 위해 메서드들이 주고받는 class 리터럴을 타입 토큰이라 한다.
    여기선 Class<T> type
    
    Favorites 클래스의 favorites 필드는 Map<Class<?>, Object> 타입으로 정의되었는데
    Object 는 결국 키와 값 사이의 타입 관계를 보증하지 않는다는 뜻이다.
    개발자는 이 필드에 담을 키와 값이 같은 타입을 보증할 것이라는 것을 알지만 자바에는 명시할 방법이 없다.
    따라서 더 안정적인 타입을 보장하기 위해서 Class 의 cast 메서드 ( 동적 형변환 ) 를 이용하여 구현할 수 있다.
    이러한 방식을 적용한 메서드는 Collections 의 checkedSet, checkedList, checkedMap 등 이다.
    
    cast 메서드는 실체화 불가 타입에는 사용할 수 없다.
    제네릭을 포함한 클래스는 e.g ) List<String> 과 List<Integer> 는 결국 같은 List 클래스라는 같은 객체를 공유하므로
    객체 내부는 아수라장이 될 것이다.
    이 방법을 슈퍼타입 토큰으로 해결할 수 있는 방법이 있지만, 완벽하지 않은 방법이니 주의해야 한다.
     
```java
public class Favorites {

    Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(type, instance);
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        Integer favoriteInteger = f.getFavorite(Integer.class);
        Class favoriteClass = f.getFavorite(Class.class);

        System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
    }
}
```
    Class 클래스
    ** native
```java
public final class Class<T> implements java.io.Serializable,
                              GenericDeclaration,
                              Type,
                              AnnotatedElement {
    @SuppressWarnings("unchecked")
    @HotSpotIntrinsicCandidate
    public T cast(Object obj) {
        if (obj != null && !isInstance(obj))
            throw new ClassCastException(cannotCastMsg(obj));
        return (T) obj;
    }

    @HotSpotIntrinsicCandidate
    public native boolean isInstance(Object obj);

}
```
#### 내용 추가
    * .class
        Class 를 반환하는 메서드
        Class 에는 리플렉션을 통해 field, method, package, constructor 등 클래스의 정보를 반환해주는
        메서드들이 존재한다.
    ** JNI ( Java Native Interface )
        운영체제의 모든 기능을 JVM 이 담기 못하기 때문에,
        운영체제의 고유기능을 Java 메서드와 연결해주는 것
    *** 슈퍼 타입 토큰
        http://gafter.blogspot.com/2006/12/super-type-tokens.html
        https://yangbongsoo.gitbook.io/study/super_type_token
        
# item-34 int 상수 대신 열거 타입을 사용하라
#### 정리
    정수 열거 패턴 ( 상수 ) 는 단점이 많다.
    타입 안전도 보장할 수 없으며 표현도 좋지 않다.
    int 로 주어진 상수 오렌지를 건네야 할 메서드에 사과 를 던져도 메서드는 오류를 반환하지 않는다.
    자바는 이런 오류의 대안으로 열거 타입을 제시했다.
    
    열거 타입 자체는 클래스이며, 상수 하나당 자신의 인스턴스를 하나씩 만들어 public static final 필드로 공개한다.
    *열거 타입에는 각자의 이름공간이 있어서 이름이 같은 상수도 평화롭게 공존한다.
    열거 타입에 새로운 상수를 추가하거나 순서를 바꿔도 다시 컴파일하지 않아도 된다.
    공개되는 것이 오직 필드의 이름뿐이라, 정수 열거 패턴과 달리 상수 값이 클라이언트로 컴파일되어 각인되지 않기 때문이다.
    
    열거 타입의 정적 필드 중 열거 타입의 생성자에서 접근할 수 있는 것은 상수 변수뿐이다.
    열거 타입 생성자가 실행되는 시점에는 정적 필드들이 초기화 되기 전이라, 자기 자신을 추가하지 못하게 하는 제약이 필요하다.
    
    필요한 원소를 컴파일타임에 다 알 수 있는 상수 집합이라면 항상 열거 타입을 사용하자.

# item-35 ordinal 메서드 대신 인스턴스 필드를 사용하라
#### 정리
    ordinal() 이라는 메서드로 가져올 수 있는 열거 타입의 위치를 반환하는 메서드를 사용해서
    객체의 어떤 부분을 정의하는 것은 위험하다.
    순서에 종속 될 뿐아니라, 같은 값을 갖는 객체를 더 생성할 수 없기 때문이다.
    따라서 ordinal() 메서드를 이용하는 것보다는 인스턴스 필드를 따로 만들어서 사용하는 편이 좋다( 해야 한다 ).

# item-36 비트 필드 대신 EnumSet 을 사용하라
#### 정리
    열거한 값들이 주로 집합으로 사용될 경우, 각 *상수에 서로 다른 2의 거듭제곱 값을 할당한 정수 열거 패턴을 사용해 왔다.
    비트별 OR 를 사용해 여러 상수를 하나의 집합으로 모을 수 있으며, 이렇게 만들어진 집합을 비트 필드라 한다.
    
    비트 필드 값이 그대로 출력되면 단순한 정수 열거 상수를 출력할 때보다 해석하기 어렵고, 비트 필드에 녹아있는 모든 원소를
    순회하기도 힘들며, 최대 몇 비트가 필요한지 미리 예측해서 사용해야 한다 ( int 쓸건지 long 쓸건지 )
    이런 문제들 때문에 EnumSet 을 사용하는 것이 좋다.
    EnumSet 은 열거 타입 상수의 값으로 구성된 집합을 효과적으로 표현해준다.
    EnumSet도 내부는 비트 벡트로 구현되었다. 원소가 총 64개 이하라면, 
    EnumSet 전체를 long 변수 하나로 표현하여 비트 필드에 비견되는 성능을 보여준다.
    비트를 효율적으로 처리할 수 있는 산술 연산을 써서 구현했다.
#### 내용 추가
    *상수에 서로 다른 2의 거듭제곱 값을 할당한 정수 열거 패턴
```java
public class Text {
    public static final int STYLE_BOLD = 1 << 0;
    public static final int STYLE_ITALIC = 1 << 1;
    public static final int STYLE_UNDERLINE = 1 << 2;
    public static final int STYLE_STRIKETHROUGH = 1 << 3;

    public static void applyStyles(int styles) {
//        System.out.println(styles);
        List<Integer> styleGroup = List.of(STYLE_BOLD, STYLE_ITALIC, STYLE_UNDERLINE, STYLE_STRIKETHROUGH);
        List<Integer> applyStyles = new ArrayList<>();
        while (styles != 0) {
            for (int i = styleGroup.size() - 1; i >= 0; i--) {
                if (styles >= styleGroup.get(i)) {
                    applyStyles.add(styleGroup.get((i)));
                    styles -= styleGroup.get(i);
                }
            }
        }
        System.out.println(applyStyles);
    }

    public static void main(String[] args) {
        applyStyles(STYLE_BOLD | STYLE_ITALIC); // 3
    }
}
```
    https://ko.wikipedia.org/wiki/%EB%B9%84%ED%8A%B8_%ED%95%84%EB%93%9C
    비트 필드(bit field)는 컴퓨터 프로그래밍에 쓰이는 자료 구조이다. 
    수많은 인접 컴퓨터 메모리 위치들로 이루어져 있으며 일련의 비트를 보유하기 위해 할당되며 
    하나의 비트나 여러 비트의 그룹의 주소를 참조할 수 있도록 저장된다.
    비트 필드는 알려진 고정 비트 너비의 정수형을 표현하기 위해 흔히 사용된다.
    
    사용 예시를 찾을 수가 없었음
    책 내용으로 보았을때 해당 값이 들어오면 해당 그룹 ( 상수로 정의한 ) 들을 돌면서
    해당 값에 가장 근접한 ( 보다 크지않은 수중 가장 큰 수 ) 수를 추출하고 그에 맞는 상수를 뽑아내는 식으로
    하면 원하는 데이터를 추출할 수 있을 것 같다.
    
# item-37 ordinal 인덱싱 대신 EnumMap 을 사용하라
#### 정리
    위의 35 와 같은 말로 ordinal 인덱싱을 사용한 배열은 정수값을 사용한다는 것을 직접 보증해야 한다.
    정수는 타입 안전하지 않으며 각 인덱스의 의미를 모르니 출력 때 인덱스에 해당하는 레이블을 직접 달아줘야 한다.
    
    이를 EnumMap 으로 고치면 고유 값인 열거 타입을 키로 받고 그에 해당하는 내용을 값 형태로 가질 수 있으므로
    데이터 처리에 용이하다.

# item-38 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라
#### 정리
    열거 타입을 확장하는 방법은 인터페이스를 사용하는 것이다.
    예전에는 *타입 안전 열거 패턴을 사용하여 확장할 수 있었으나 열거타입은 그럴 수 없다.
    
    대부분의 상황에서 열거 패턴을 확장하는 건 좋지 않다.
    또한 기반 타입과 확장된 타입들의 원소 모두를 순회할 방법도 마땅치 않다.
    
    이럼에도 불구하고 확장이 필요한 열거 타입을 사용해야 한다면 인터페이스를 사용하는 것이 좋다.
    
#### 내용 추가
    *타입 안전 열거 패턴
        블로그 발췌한 내용임
        정의된 클래스를 private 생성자를 두고 상수로써 객체를 생성한다.
        이렇게 정의한 객체는 단 하나뿐인 타입 안전 객체가 생성된다.
```java
public class TypeSafeEnumPattern {
    private final String type;

    private TypeSafeEnumPattern(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static final TypeSafeEnumPattern TERMINAL = new TypeSafeEnumPattern("Terminal");
    public static final TypeSafeEnumPattern PROCESS = new TypeSafeEnumPattern("Process");
    public static final TypeSafeEnumPattern DECISION = new TypeSafeEnumPattern("Decision");

}
```

# item-39 명명 패턴보다 애너테이션을 사용하라
#### 정리
    예전 도구나 프레임워크는 명명패턴을 사용해왔다.
    JUnit 3 으로 예시를 들자면 test 가 이름에 포함된 메서드만 test 를 진행했다.
    이러한 명명 패턴에는 여러가지의 단점이 있다.
    오타가 나면 안되고, 올바른 프로그램 요소에서만 사용된다는 보증이 없으며, 프로그램 요소를 매개변수로 전달할 방법이 없다는 것이다.
    이 모든 이유는 메서드의 실행이 이름에 너무 종속되기 때문이다.
    
    이러한 단점들을 애너테이션이 해결해준다.
    애너테이션 작성 방법은
    @interface 를 정의하고
    @Retention 메타애너테이션으로 유지 정책과
    @Target 메타애너테이션으로 사용될 선언부를 정의한다.
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}
```    
    이렇게 애너테이션을 정의하고 사용할 부분에 태그하면 가능하다.
    또한 요소를 매개변수로 전달하고 싶은 경우에는
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Throwable> value();
}
```
    이와 같은 방법으로 ( 위에선 Class ) 정의하면 된다.
    해당 매개변수는 리플렉션 API 를 통하여 제공받은 메서드의 isAnnotationPresent 메서드를 통하여 확인할 수 있다.
    
    이로써 위에서 명명 패턴의 단점으로 제시한
    오타, 정확한 사용 메서드 지정, 매개변수 전달 이 모두 해결 되었다.
    
    추가로 애너테이션에는 @Repeatable 메타애너테이션도 지정할 수 있는데 이는 여러번 반복하여 사용할 수 있도록 하는것이다.
    작성 방법은 정의한 애너테이션의 배열을 매개변수로 갖는 애너테이션 컨테이너를 따로 만들고
    애너테이션 컨테이너를 @Repeatable 매개변수로 지정한다.
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(RepeatableExceptionContainer.class)
public @interface RepeatableExceptionTest {
    Class<? extends Throwable> value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RepeatableExceptionContainer {
    RepeatableExceptionTest[] value();
}
``` 

# item-40 @Override 애너테이션을 일관되게 사용하라
#### 정리
    내가 재정의할 메서드에는 @Override 애너테이션을 사용하는 편이 좋다.
    재정의한 메서드의 시그니처가 잘못됐을 경우에 IDE 또는 컴파일러가 알려주고
    또한 재정의 해야 할 메서드 ( abstract method ) 를 정의할 때 사용한다면 명시적으로 알려줄 수 있다.
    재정의할 의도를 분명히 나타낼 수 있는 애너테이션으로 생각하면 될 것 이다. 

# item-41 정의하려는 것이 타입이라면 마커 인터페이스를 사용하라
#### 정리
    마커 인터페이스란 아무런 메서드 없이 어떤 속성을 표시하기 위한 인터페이스를 뜻한다.
    실제 구현한 클래스는 아무런 메서드를 구현하지 않아도 되지만 그것만으로 어떤 속성을 나타내는 타입임을 명시할 수 있다.
    마커 애너테이션은 위와 같이 속성을 나타내기 위한 애너테이션을 뜻한다.
    
    마커 인터페이스는 그 자체로 타입을 제한하는데 사용할 수 있으므로 런타임 때가 아닌 컴파일 타임에 미리 예외를 발생시킬수 있다.
    또한 애너테이션보다 더 세밀한 지정이 가능하다. 애너테이션은 정의한 Target 으로 넓혀갈 수 있지만, 인터페이스는 해당 클래스에만
    적용할 수 있기 때문이다. ( 이 내용은 공감이 가지 않음 )
    반대로 마커 애너테이션이 활발히 쓰이는 프레임워크 같은 틀 안에서는 일관성 있게 애너테이션을 쓰는 편이 가독성에 좋다.
    또한 클래스가 아닌 패키지, 메서드, 변수 등에 속성을 추가할 떄는 마커 인터페이스를 사용할 수 없다.
    
    즉, 속성을 달아야 할 대상이 클래스이고 타입으로 지정하여 사용할 경우가 있다면 마커 인터페이스를 사용하는 편이 좋다.

# item-42 익명 클래스보다는 람다를 사용하라
#### 정리
    예전 자바에서 함수 타입을 표현할 때는 추상 메서드를 하나만 담은 인터페이스를 사용했으나,
    JDK 1.1 등장 이후 익명 클래스로 선언하여 사용하는 방법이 주로 쓰였다.
    자바 8 이후에는 람다식이 등장하여 *타입 추론을 통한 간결한 코드가 완성됐다.
    익명 클래스 -> 람다식
```java
public class Lambda {
    List<String> words;

    public void abstractClass() {
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
    }

    public void lambda1() {
        Collections.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
    }

    public void lambda2() {
        Collections.sort(words, Comparator.comparingInt(String::length));
    }

    public void lambda2_listMethod() {
        words.sort(Comparator.comparingInt(String::length));
    }
}
```
    람다식은 타입 추론 방식을 통하여 컴파일 단계에서 타입을 결정하기 때문에
    만약 컴파일러가 타입을 결정하지 못한다면 프로그래머가 직접 명시해줘야 한다.
    또한 제네릭을 사용할 경우 타입 정보를 대부분 제네릭에서 얻기 때문에 꼭 명시해줘야 한다.
    
    람다는 간결하지만 길어지면 가독성이 떨어지며, 이름이 없기 때문에 코드 자체로 명확히 설명되지 않을 때는
    사용을 피해야 한다. 또 추상메서드를 하나만 정의한 인터페이스 타입에서만 사용할 수 있다.
    자신을 참조할 수 없으며 자신을 가리키는 this 키워드는 바깥 인스턴스를 가리킨다.
    **직렬화 형태가 구현별로 다를 수 있다.

# item-43 람다보다는 메서드 참조를 사용하라
#### 정리
    람다가 익명 클래스보다 나은 점 중 하나는 간결함이다.
    그러나 람다도 매개변수를 표현하는 부분에서 거추장스러움이 존재한다.
    람다대신 메서드 참조를 이용하면 이 부분도 해결할 수 있다.
    람다 : map.merge(key, 1, (count, incr) -> count + incr)
    메서드참조 : map.merger(key, 1, Integer::sum)
    
    메서드참조는 크게 
    정적 메서드 참조, 한정적 메서드 참조, 비한정적 메서드 참조, 팩토리 메서드 참조 로 나뉜다.
    
    정적 메서드 참조는 Integer 클래스의 sum, parseInt 등으로 함수 객체가 받는 인수와 참조되는 메서드가 받는 인수가 똑같다.
    e.g ) (String x) -> Integer.parseInt(x), (Integer x, Integer y) -> Integer.sum(x, y)
    
    한정적 메서드 참조는 인스턴스 메서드 사용으로 정적 메서드와 같이 함수 객체가 받는 인수와 참조되는 메서드가 받는 인수가 똑같다.
    e.g ) (Long x) -> memberRepository.findById(x), (String x) -> member.setCountry(x)
    
    비한정적 메서드 참조는 함수 객체를 적용하는 시점에 수신 객체를 알려준다.
    e.g ) (Instant x) -> x.toEpochMilli(), (Optional<BigDemical> x) -> x.isPresent()
    
    팩터리 메서드 참조는 클래스 또는 배열 생성자를 사용한다.
    e.g ) () -> new TreeMap<K,V>(), (int len) -> new int[len]
    
    메서드 참조가 람다보다 더 짧고 "명확"할때 사용하는 편이 좋다.
    람다가 더 유용할 경우
    service.execute(GoshThisClassNameIsHumongous::action);
    service.execute(() -> action());
    위에서는 메서드 참조가 더 길어 람다식이 더 유용하다.

# item-44 표준 함수형 인터페이스를 사용하라
#### 정리
    람다를 지원하면서 상위 클래스의 메서드를 재정의하여 사용하는 API 를 작성하는 사례가 많이 바뀌었다.
    해법은 함수 객체를 받는 정적 팩터리나 생성자를 제공하는 것이다.
    
    이러한 함수 객체는 java.util.function 패키지에 다양한 방식으로 준비되어있다.
    UnaryOperator<T> - 1개의 매개변수를 받으며 리턴타입이 매개변수와 같음 - T apply(T t)
    BinaryOperator<T> - 2개의 매개변수를 받으며 리턴타입이 매개변수들과 같음 - T apply(T t1, T t2)
    Predicate<T> - 1개의 매개변수를 받으며 boolean 을 리턴 - boolean test(T t)
    Function<T, R> - 1개의 매개변수를 받으며 리턴타입이 매개변수와 다름 - R apply(T t)
    Supplier<T> - 매개변수를 받지 않음 - T get()
    Consumer<T> - 1개의 매개변수를 받으며 리턴 하지 않음 - void accept(T t)
    
    총 6개의 기본 함수 객체가 정의되어 있으며 부가적으로
    앞에 Bi 로 시작하면 매개변수가 1개 더 늘어나고
    입력과 결과 타입이 모두 기본 타입일 때 SrcToResult 방식으로 
    LongToInt~~ 으로 시작하면 매개변수와 리턴타입을 지정할 수 있다.
    
    인터페이스 중
    - 자주쓰이며, 이름 자체가 용도를 명확히 설명해주거나
    - 반드시 따라야 하는 규약이 있거나
    - 유용한 디폴트 메서드를 제공할 수 있다면
    함수형 인터페이스로 구현해야 하는지를 고민해야한다.
    
    함수형 인터페이스 구현시 @FunctionalInterface 애너테이션을 사용하는 것이 좋다
    이는 다른 개발자에게 람다용으로 설계됐음을 알려주고, 추상 메서드가 한개만 가지고 있어야 컴파일 되도록 하며,
    후에 메서드를 추가하지 못하도록 막아준다.
    
    함수형 인터페이스를 사용할 때 주의사항은, 오버로딩 메서드를 구현 할 때 같은 위치에 인수로 함수형 인터페이스를 받는다면
    클라이언트에게 모호함을 안겨주며, 이 모호함으로 문제가 일어날 수 있다.
    그러므로 다중정의는 주의해서 사용해야 한다.

# item-45 스트림은 주의해서 사용하라
#### 정리
    스트림은 데이터 원소의 시퀀스이다
    스트림 파이프라인은 이 원소들로 수행하는 연산 단계를 표현한다.
    
    파이프라인은 중간 연산과 종단 연산으로 이루어져 있으며,
    중간 연산은 하나 이상이 존재할 수 있으며,
    종단 연산이 없으면 아무일도 하지 않는다.
    
    스트림 파이프라인은 지연 평가된다.
    종단 연산이 호출될 때 지연평가가 이루어지며, 종단 연산에 쓰이지 않는 데이터 원소는 계산에 쓰이지 않는다.
    
    스트림은 메서드 연쇄를 지원하므로 파이프라인 여러개를 연결해 표현식 하나로 만들 수 있다.
    그래서 프로그램이 짧고 깔끔해진다.
    하지만 잘못 사용하면 읽기 어렵고 유지보수도 힘들어진다.
    
    코드를 작성하고 ( 또는 기존 코드 ) 그 코드를 스트림을 사용하도록 리팩터링 한 후,
    비교하여 새로운 코드가 나아 보일때 사용하도록 하자.
    
    다음은 스트림에서 사용할 수 없는 일들의 예다.
    이런 상황이 생기면 스트림을 사용하지 말아야 한다.
    - 람다에서는 final 이거나 사실상 final 인 변수만 읽을 수 있고 지역변수는 수정 불가하다.
    - break 나 continue, throw Exception 이 불가하다.
    
    다음은 스트림에서 사용하면 유용한 일들의 예다.
    - 원소들의 시퀀스를 일관되게 변환한다. ( map )
    - 원소들의 시퀀스를 필터링한다. ( filter )
    - 시퀀스를 하나의 연산을 사용해 결합한다. ( map )
    - 시퀀스를 컬렉션에 모은다. ( collect )
    - 시퀀스에서 특정 조건을 만족하는 워소를 찾는다. ( filter )
    
    스트림 파이프라인은 한 값을 다른 값에 매핑하고 나면 원래의 값은 잃는 구조이기 떄문에
    각 단계의 값들에 동시에 접근하기 어렵다.
    
    스트림을 사용해야 깔끔하고 간결하게 정리할 수 있는 일이 있고,
    반복 방식이 더 알맞은 경우도 있다.
    만약 결정을 내리지 못할 경우에는 둘다 구현 한 후 하나를 선택하라.

# item-46 스트림에서는 부작용 없는 함수를 사용하라
#### 정리
    스트림은 하나의 API 가 아닌 함수형 프로그래밍에 기초한 패러다임이다.
    스트림 패러다임의 핵심은 계산을 일련의 변환으로 재구성하는 부분이다.
    각 변환 단계는 가능한 이전 단계의 결과를 받아 처리하는 순수 함수여야 한다.
    순수 함수란 다른 가변 상태를 참조하지 않고, 함수 스스로도 다른 상태를 변경하지 않는 함수다.
    
    흔히 사용하는 for-each 반복문과 비슷한 forEach 종단 연산을 객체를 변경하는데 사용할 때가 있는데,
    forEach 연산은 스트림 계산 결과를 보고할 때만 사용하고, 계산하는데는 사용하지 않는 것이 좋다.
    위에서 말한 순수 함수를 사용하라는 말에 어긋난다.
    
    Collectors 의 메서드 중 대표적으로 toList, toSet, toMap, groupingBy, joining 이 자주 사용된다.
    
    https://docs.oracle.com/javase/10/docs/api/java/util/stream/Collectors.html
     
# item-47 반환 타입으로는 스트림보다 컬렉션이 낫다
#### 정리
    Stream 인터페이스는 Iterable 인터페이스가 정의한 추상 메서드를 전부 포함할 뿐만 아니라,
    Iterable 인터페이스가 정의한 방식대로 동작한다.
    그럼에도 for-each 로 스트림을 반복할 수 없는 까닭은 바로 Stream 이 Iterable 을 확장하지 않아서다.
    
    객체 시퀀스를 반환하는 메서드를 작성하는데, 이 메서드가 오직 스트림 파이프라인에서만 쓰일 걸 안다면
    마음 놓고 스트림을 반환하게 해주자.
    반대로 반환된 객체들이 반복문에서만 쓰일 걸 안다면 Iterable 을 반환하자.
    공개 API 는 스트림 파이프라인을 사용하는 사람과 반복문에서 쓰려는 사람 모두를 배려해야 한다.
    원소 시퀀스를 반환하는 공개 API 의 반환 타입에는 Collection 이나 그 하위 타입을 쓰는게 일반적으로 최선이다.
    
    단지 컬렉션을 반환한다는 이유로 덩치 큰 시퀀스를 메모리에 올려서는 안 된다.
    반환할 시퀀스가 크지만 표현을 간결하게 할 수 있다면 전용 컬렉션을 구현하는 방안을 검토해보자.
    
    원소 시퀀스를 반환하는 메서드를 작성할 때는, 이를 스트림으로 처리하기를 원하는 사용자와 
    반복으로 처리하길 원하는 사용자가 모두 있음을 떠올리고, 양쪽을 다 만족시키려 노력하자.
    컬렉션을 반환할 수 있다면 그렇게 하고 불가능하면 스트림과 Iterable 중 더 자연스러운 것을 반환하자.
    
#### 내용 추가
    *Iterable (Iterator), Collection, Stream
    Iterable 을 구현한 클래스는 Iterator 인터페이스를 반환하는 iterator() 메서드를 구현해야 한다.
    Iterable 은 Consumer 객체를 받는 ( 람다 사용 가능 ) forEach default 메서드가 있으며,
    Spliterator 를 반환하는 spliterator 메서드를 가지고 있다. ( 이건 나중에 알아보자 )
    
    Iterator 인터페이스는 boolean 을 리턴하는 hasNext() 와 정의한 제네릭 타입을 반환하는 next() 메서드가 있으며
    이를 통하여 순차적? 반복을 지원한다. ( 훑음을 지원 )
    
    Collection 인터페이스는 Iterable 을 확장하고 있다.
    
    Stream 인터페이스는 Iterable 을 확장하고 있지 않지만, Iterable 이 정의한 메서드를 모두 구현하고 있다.
    한마디로 흉내
    
    둘다 결국 반복문을 통해 어떤 로직을 실행하지 않고 내부 메서드를 통해 반환한 Iterator 로 실행할 건데
    클라이언트 ( 메서드 사용하는 곳 ) 에서 어떤 식 ( 스트림파이프라인, collection ) 의 형태로 구현할 지 모르니
    둘다 고려해서 만들어라 라는 뜻임. 

# item-1
#### 정리
#### 내용 추가