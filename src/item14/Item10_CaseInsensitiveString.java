package item14;

import java.util.Objects;

public class Item10_CaseInsensitiveString implements Comparable<Item10_CaseInsensitiveString> {
    private final String s;

    public Item10_CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item10_CaseInsensitiveString) {
            return s.equalsIgnoreCase(((Item10_CaseInsensitiveString) obj).s);
        }
        if (obj instanceof String) {
            return s.equalsIgnoreCase((String) obj);
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(Item10_CaseInsensitiveString cis) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
    }
}
