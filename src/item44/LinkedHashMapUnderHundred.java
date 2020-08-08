package item44;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapUnderHundred extends LinkedHashMap<String, Object> implements EldestEntryRemovalFunction<String, Object> {
    @Override
    public boolean remove(Map<String, Object> map, Map.Entry<String, Object> eldest) {
        return size() > 100;
    }
}
