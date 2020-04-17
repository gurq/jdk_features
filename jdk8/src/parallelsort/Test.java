package parallelsort;

import localdatetime.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class Test {
    public static void main(String[] args) {
        int size = 30000;
        Integer[] arr1 = getRandomArr(size);
        LocalDateTime start1 = LocalDateTime.now();
        Arrays.parallelSort(arr1);
        LocalDateTime end1 = LocalDateTime.now();
        System.out.println("并行排序耗时：" + LocalDateTimeUtil.formatDuration(start1, end1));

        Integer[] arr2 = getRandomArr(size);
        LocalDateTime start2 = LocalDateTime.now();
        Arrays.sort(arr2);
        LocalDateTime end2 = LocalDateTime.now();
        System.out.println("传统排序耗时：" + LocalDateTimeUtil.formatDuration(start2, end2));
    }

    private static Integer[] getRandomArr(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list.toArray(new Integer[list.size()]);
    }
}
