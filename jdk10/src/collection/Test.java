package collection;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> sourceList = new ArrayList<>();
        sourceList.add(1);
        sourceList.add(2);
        sourceList.add(3);
        List<Integer> copyList = List.copyOf(sourceList);
        System.out.println(copyList);
        sourceList.add(4);
        copyList.add(4);
    }
}
