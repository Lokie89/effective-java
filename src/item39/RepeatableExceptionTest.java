package item39;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(RepeatableExceptionContainer.class)
public @interface RepeatableExceptionTest {
    Class<? extends Throwable> value();
}
