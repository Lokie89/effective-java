package item10;

/**
 * @author lokie on 2020/07/06
 */
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
