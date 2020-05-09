package collection;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        System.out.println(Stream.ofNullable(null).count());// 0
        List<String> list1 = Stream.of("a", "", "b", "c", "", "e", "f", "g")
                .dropWhile(s -> !s.isEmpty())
                .collect(Collectors.toList());//
        System.out.println(list1);
        List<String> list2 = Stream.of("a", "", "b", "c", "", "e", "f", "g")
                .takeWhile(s -> !s.isEmpty())
                .collect(Collectors.toList());// a
        System.out.println(list2);
        IntStream.iterate(3, x -> x < 10, x -> x+ 3).forEach(System.out::print);
        Set<Integer> set = Stream.of("a", "ab", "abc")
                .collect(Collectors.flatMapping(v -> v.chars().boxed(),
                        Collectors.toSet()));
        System.out.println(set);
        List<Integer> collect = Stream.of("a", "ab", "abc")
                .collect(Collectors.flatMapping(v -> v.chars().boxed(),
                        Collectors.toList()));
        System.out.println(collect);

    }
}
