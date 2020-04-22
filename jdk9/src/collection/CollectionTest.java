package collection;

import java.util.List;

public class CollectionTest {
    public static void main(String[] args) {
        // 不可变集合
        List<Integer> list = List.of(1, 2);
        list.add(3);
        list.remove(0);
        System.out.println(list);
    }
}
