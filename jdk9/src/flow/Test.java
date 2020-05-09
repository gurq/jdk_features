package flow;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        CompletableFuture subTask;
        try (SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>()) {
            subTask = publisher.consume(System.out::print);
            IntStream.rangeClosed(1,3).forEach(publisher::submit);
        }
        subTask.join();
    }
}
