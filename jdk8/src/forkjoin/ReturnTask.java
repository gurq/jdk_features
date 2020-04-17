package forkjoin;

import java.util.Date;
import java.util.concurrent.RecursiveTask;

public class ReturnTask extends RecursiveTask<Long> {
    static final int THRESHOLD = 5;
    long[] array;
    int start;
    int end;

    ReturnTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date()+Thread.currentThread().getName() + String.format("compute %d~%d = %d , i = %d", start, end, sum, i));
            }
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        ReturnTask left = new ReturnTask(this.array, start, middle);
        ReturnTask right = new ReturnTask(this.array, middle, end);
        invokeAll(left, right);
        Long leftResult = left.join();
        Long rightResult = right.join();
        Long result = leftResult + rightResult;
        System.out.println("result = " + leftResult + " + " + rightResult + " ==> " + result);
        return result;
    }
}
