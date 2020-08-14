package item52;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lokie on 2020-08-14
 */
public class OverridingThread {
    public static void main(String[] args) {
        new Thread(System.out::println).start();
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.submit(System.out::println);
    }
}
