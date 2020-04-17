package atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

public class Atomic {
    private static LongAccumulator longAccumulator = new LongAccumulator((left, right) -> left + right, 1);
    private static LongAdder longAdder = new LongAdder();
    private static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) {
        longAdder.add(1);
        System.out.println(longAdder.sum());
        atomicLong.addAndGet(1);
        System.out.println(atomicLong.get());
        longAccumulator.accumulate(1);
        System.out.println(longAccumulator.longValue());
    }

}
