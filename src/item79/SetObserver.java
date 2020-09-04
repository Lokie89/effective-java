package item79;

@FunctionalInterface
public interface SetObserver<E> {
    void added(ObservableSet<E> e, E element);
}
