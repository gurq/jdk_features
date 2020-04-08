package forkjoin;

import java.util.Date;
import java.util.concurrent.RecursiveAction;

public class NoReturnTask extends RecursiveAction {

    //每个小任务，最多只打印50个数
    private static final int threshold = 5;
    //打印任务的开始
    private int start;
    //打印任务的结束
    private int end;

    public NoReturnTask() {
        // TODO Auto-generated constructor stub
    }

    public NoReturnTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= threshold) {
            for (int i = start; i < end; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date()+Thread.currentThread().getName() + "i的值:" + i);
            }
        } else {
            //当end与start之间的差大于threshold，及打印的数超过50个时，
            //将大任务分解成2个小任务
            int middle = (start + end) / 2;
            NoReturnTask left = new NoReturnTask(start, middle);
            NoReturnTask right = new NoReturnTask(middle, end);
            //并行执行两个小任务
            invokeAll(left, right);
        }
    }
}
