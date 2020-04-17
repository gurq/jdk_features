package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class TestReturnTask {

    public static void main(String[] args) {
        // 创建随机数组成的数组:
        long[] array = new long[50];
        fillArray(array);
        // fork/join task:
        ForkJoinPool pool = ForkJoinPool.commonPool(); // 最大并发数4
        long startTime = System.currentTimeMillis();
        Long result = pool.invoke(new ReturnTask(array, 0, array.length));
        long endTime = System.currentTimeMillis();
        System.out.println("fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }
    
    private static void fillArray(long[] array){
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }
    
}
