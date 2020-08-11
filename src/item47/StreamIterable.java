package item47;

import java.util.stream.Stream;

/**
 * @author lokie on 2020-08-11
 */
public class StreamIterable {
    private void notCompile() {
//        for(ProcessHandle ph : ProcessHandle.allProcesses()::iterate){ // Method reference expression is not expected here
//
//        }
    }

    private void turnNotCompile() {
        for (ProcessHandle ph : (Iterable<ProcessHandle>) ProcessHandle.allProcesses()::iterator) {

        }
    }

    private void adaptorNotCompile() {
        for (ProcessHandle ph : iterableOf(ProcessHandle.allProcesses())) {

        }
    }

    private static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }
}
