package item55;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class OptionalExample {
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty()) {
            throw new IllegalArgumentException("빈 컬렉션");
        }
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

    public static <E extends Comparable<E>> Optional<E> maxOptional(Collection<E> c) {
        if (c.isEmpty()) {
            return Optional.empty();
        }
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return Optional.of(result);
    }

    public static <E extends Comparable<E>> Optional<E> maxStream(Collection<E> c) {
        return c.stream()
                .max(Comparator.naturalOrder())
                ;
    }

    public static void processHandle() {
        Optional<ProcessHandle> parentProcess = new ProcessHandle() {
            @Override
            public long pid() {
                return 0;
            }

            @Override
            public Optional<ProcessHandle> parent() {
                return Optional.empty();
            }

            @Override
            public Stream<ProcessHandle> children() {
                return null;
            }

            @Override
            public Stream<ProcessHandle> descendants() {
                return null;
            }

            @Override
            public Info info() {
                return null;
            }

            @Override
            public CompletableFuture<ProcessHandle> onExit() {
                return null;
            }

            @Override
            public boolean supportsNormalTermination() {
                return false;
            }

            @Override
            public boolean destroy() {
                return false;
            }

            @Override
            public boolean destroyForcibly() {
                return false;
            }

            @Override
            public boolean isAlive() {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public boolean equals(Object other) {
                return false;
            }

            @Override
            public int compareTo(ProcessHandle other) {
                return 0;
            }
        }.parent();

        System.out.println("부모 PID: " + (parentProcess.isPresent() ? String.valueOf(parentProcess.get().pid()) : "N/A"));

        System.out.println("부모 PID: " + parentProcess.map(h -> String.valueOf(h.pid())).orElse("N/A"));

//        parentProcess
//                .stream()
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                ;
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        System.out.println(maxOptional(integers));
        System.out.println(max(integers));

        maxOptional(integers).orElse(3);
        maxOptional(integers).isPresent();
    }

}
