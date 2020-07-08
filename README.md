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
    
# item-1
#### 정리
#### 내용 추가