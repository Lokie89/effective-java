package item79;

import item18.ForwardingSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObservableSet<E> extends ForwardingSet<E> {

    public ObservableSet(Set<E> s) {
        super(s);
    }

    //    private final List<SetObserver<E>> observers = new ArrayList<>();
    private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>(); // 동시성 컬렉션 라이브러리

    public void addObserver(SetObserver<E> observer) {
//        synchronized (observers) {
//            observers.add(observer);
//        }
        observers.add(observer); // CopyOnWriteArrayList 를 사용하여 synchronized 할 필요 없음
    }

    public boolean removeObserver(SetObserver<E> observer) {
//        synchronized (observers) {
//            return observers.remove(observer);
//        }
        return observers.remove(observer); // CopyOnWriteArrayList 를 사용하여 synchronized 할 필요 없음
    }

    private void notifyElementAdded(E element) {
//        synchronized (observers) {
//            for (SetObserver<E> observer : observers) {
//                observer.added(this, element);
//            }
//        }

//        List<SetObserver<E>> snapshot = null;
//        synchronized (observers) {
//            snapshot = new ArrayList<>(observers);
//        }
//        for (SetObserver<E> observer : snapshot) {
//            observer.added(this, element); // 외계인 메서드 호출을 동기화 블록 바깥으로 옮김
//        }

        for (SetObserver<E> observer : observers) { // CopyOnWriteArrayList 를 사용하여 synchronized 할 필요 없음
            observer.added(this, element);
        }
    }

    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if (added) {
            notifyElementAdded(e);
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c) {
            result |= add(element);
        }
        return result;
    }
}

