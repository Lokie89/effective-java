package item38;

/**
 * @author lokie on 2020-07-31
 */
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
