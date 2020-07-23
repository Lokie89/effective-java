package item29;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author lokie on 2020/07/23
 */
public class Stack_Item7<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;


    // 배열 elements 는 push(E) 로 넘어온 E 인스턴스만 담는다.
    // 따라서 타입 안정성을 보장하지만,
    // 이 배열의 런타임 타입은 E[] 가 아닌 Object[] 다!
    @SuppressWarnings("unchecked")
    public Stack_Item7() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY]; // 실체화 불가한 타입으로는 배열을 구현할 수 없음.
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
