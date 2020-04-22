package collection;

import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        System.out.println(Stream.ofNullable(null).count());// 0
        Stream.of("a","b","c","","e","f")
                .takeWhile(s->!s.isEmpty())
                .forEach(System.out::print);// a,b,c
    }
}
