package item79;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintZeroToHundred {
    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());
//        set.addObserver((s, e) -> System.out.println(e));

//        set.addObserver(new SetObserver<Integer>() {
//            @Override
//            public void added(ObservableSet<Integer> e, Integer element) {
//                System.out.println(element);
//                if(element == 23){
//                    e.removeObserver(this); // ConcurrentModificationException
//                }
//            }
//        });

        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> e, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    ExecutorService exec = Executors.newSingleThreadExecutor();
                    try {
                        exec.submit(() -> e.removeObserver(this)).get(); // 메인스레드의 락 때문에 해당 스레드가 실행 될 수 없음 (교착상태)
                    } catch (ExecutionException | InterruptedException ex) {
                        throw new AssertionError(ex);
                    } finally {
                        exec.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
