package ThreadBase.atomics;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

public class LongAdderDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.sum());

        //LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);
        //用非 lambda 表达式的方式写，应该能更明确一些，可以自行 debug 进去，看具体 left 是什么值， right 是什么值。
        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        }, 0);
        // 0 + 1
        longAccumulator.accumulate(1);
        // 1 + 3
        longAccumulator.accumulate(3);
        System.out.println(longAccumulator.get());
    }
}
