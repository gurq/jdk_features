package forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class TestNoReturnTask {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long startTime = System.currentTimeMillis();
        pool.invoke(new NoReturnTask(0, 50));
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join in " + (endTime - startTime) + " ms.");
        pool.shutdown();//关闭线程池
    }
}
