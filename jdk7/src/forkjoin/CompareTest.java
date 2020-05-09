package forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class CompareTest {

    public static void main(String[] args) throws Exception{
//        testThreadPool(Executors.newFixedThreadPool(2));
        testThreadPool(Executors.newWorkStealingPool(2));
    }

    private static void testThreadPool(ExecutorService threadPool) throws Exception {
        Future[] outerTasks = IntStream.rangeClosed(1, 2).mapToObj(i ->
                threadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + ", level1 task" + i);
                    Future<?> innerTask = threadPool.submit(() ->
                            System.out.println(Thread.currentThread().getName() + ", level2 task" + i));
                    try {
                        innerTask.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                })).toArray(Future[]::new);
        System.out.println("waiting...");
        for (Future<?> outerTask : outerTasks) {
            outerTask.get();
        }
        System.out.println("done");
    }

}
