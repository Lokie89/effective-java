package item18;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lokie on 2020-07-15
 */
public class InstrumentedHashSet<E> extends /*HashSet<E>*/ ForwardingSet<E> {

    private int addCount = 0;

    public InstrumentedHashSet(Set<E> s) {
        super(s);
    }

//    public InstrumentedHashSet(int initCap, float loadFactor) {
//        super(initCap, loadFactor);
//    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
