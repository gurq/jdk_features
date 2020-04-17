package completablefuture;

import java.util.concurrent.CompletableFuture;

public class Test {

    public static void main(String[] args) throws Exception {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> queryCode("中国石油", "https://finance.sina.com.cn/code/"));
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> queryCode("中国石油", "https://money.163.com/code/"));

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> fetchPrice((String) code, "https://finance.sina.com.cn/price/"));
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> fetchPrice((String) code, "https://money.163.com/price/"));

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);
        // 用allOf等待所有线程结束
        CompletableFuture<Void> allOf = CompletableFuture.allOf(cfFetchFromSina, cfFetchFrom163);

        // 最终结果:
        cfFetch.thenAccept((result) -> System.out.println("price: " + result));
        allOf.join();
        System.out.println(cfFetchFromSina.get());;
        System.out.println(cfFetchFrom163.get());;
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
        }
        return name;
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "..." + "code : " +code);
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}
