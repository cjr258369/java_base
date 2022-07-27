package ThreadBase.atomics;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author chenjunran
 * @date 2022/7/27
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        //AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[5]);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);
        //AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1,2,3,4,5});

        for(int i = 0; i < atomicIntegerArray.length(); i++){
            System.out.println(atomicIntegerArray.get(i));
        }

        int tmpInt = 0;
        //这里是把 0 下标的旧值返回，并设置 新值进去
        tmpInt = atomicIntegerArray.getAndSet(0, 123);
        System.out.println(tmpInt + "\t" +atomicIntegerArray.get(0));

        //这里是对 0 号下标的数据自增 1
        tmpInt = atomicIntegerArray.getAndIncrement(0);
        System.out.println(tmpInt + "\t" +atomicIntegerArray.get(0));
    }
}
