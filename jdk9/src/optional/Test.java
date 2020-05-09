package optional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        ifOrElse(null);
        or(null);
        stream();
    }

    private static void ifOrElse(String param){
        Optional.ofNullable(param)
                .ifPresentOrElse(System.out::println,()-> System.out.println("空"));
    }

    private static void or(Integer param) {
        Optional<Integer> or = Optional.ofNullable(param).or(()->Optional.of(2));
        System.out.println(or.get());
    }

    private static void stream() {
        List<Object> list = Optional.ofNullable(null)
                .stream()
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
