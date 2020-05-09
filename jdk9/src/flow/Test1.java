package flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        //1.创建 生产者Publisher JDK9自带的 实现了Publisher接口 默认256防背压
        SubmissionPublisher publisher = new SubmissionPublisher();

        //创建 Processor 即是发布者也是订阅者
        MyProcessor myProcessor = new MyProcessor();

        //2.创建 订阅者 Subscriber，需要自己去实现内部方法
        Flow.Subscriber subscriber = new Flow.Subscriber() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("订阅成功。。");
                subscription.request(1);
                System.out.println("订阅方法里请求一个数据");
            }

            @Override
            public void onNext(Object item) {
                System.out.println("从MyProcessor 接收到的 item = " + item);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("【onError 出现异常】");
                subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("【onComplete 所有数据接收完成】");
            }
        };

        //3。发布者和订阅者 建立订阅关系 就是回调订阅者的onSubscribe方法传入订阅合同
        //建立关系 发布者和处理器， 此时处理器扮演 订阅者
        publisher.subscribe(subscriber);
//        publisher.subscribe(myProcessor);

        //建立关系 处理器和订阅者  此时处理器扮演
//        myProcessor.subscribe(subscriber);

        //4.发布者 生成数据
        for (int i = 1; i <= 1000; i++) {
            System.out.println(String.format("【生产数据 %d 】", i ));
            //submit是一个阻塞方法，此时会调用订阅者的onNext方法
            publisher.submit(i);
        }

        //5.发布者 数据都已发布完成后，关闭发送，此时会回调订阅者的onComplete方法
        publisher.close();

        //主线程睡一会
        Thread.currentThread().join(500000);
    }
}
