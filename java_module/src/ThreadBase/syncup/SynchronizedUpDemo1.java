package ThreadBase.syncup;

import org.openjdk.jol.info.ClassLayout;

/**
 * 锁升级过程 源码标志位证明 demo：
 * MarkWord 信息查看技巧
 * 无锁状态：001
 * @author chenjunran
 * @date 2022/8/8
 */
public class SynchronizedUpDemo1 {
    public static void main(String[] args) {
        Object o = new Object();
        //      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //需要从 右下角 往 左上角看，
        //前 25 位 是 unused【0 00000000 00000000 00000000】，
        // 然后 31 位的hashCode，但hashCode需要有调用才能体现出来，没调用默认就都是0，只有调用了hashCode 方法才会生成，【00000000 00000000 00000000 0000000】，
        // 因为如果完全不用hashCode，那就没必要在new 对象时就立马给计算一个。
        // 然后 1 位 unused 【1】，
        // 4 位 的分代年龄【0000】，
        // 最终到了无锁的状态标识【000】
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        //调用一次 hashCode，之后再打印就有hashCode 了
        System.out.println("10进制的HashCode："+o.hashCode());
        //  10进制的HashCode：1937962514
        //  16进制的HashCode：7382f612
        //  2进制的HashCode：1110011100000101111011000010010
        //  java.lang.Object object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //  0     4        (object header)                           01 12 f6 82 (00000001 00010010 11110110 10000010) (-2097802751)
        //  4     4        (object header)                           73 00 00 00 (01110011 00000000 00000000 00000000) (115)
        //  8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
        //  12     4        (loss due to the next object alignment)
        //  Instance size: 16 bytes
        //  Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        //  hashCode 的 16 进制 : 7382f612 对应着 value 从下往上看，
        //  hashCode 的 2 进制 : 1110011100000101111011000010010 对应着 value后面括号内的二进制 从下往上看【但每一段要从左往右看】，1110011 10000010 11110110 00010010
        //打印一下 hashCode 的二进制 和 16 进制，证明一下这段二进制是需要从 右下角 往 左上角看的
        System.out.println("16进制的HashCode：" + Integer.toHexString(o.hashCode()));
        System.out.println("2进制的HashCode：" + Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        //因为无锁状态下的对象头标记为【从右下角 往 左上角看，取最后一段，然后从左往右看，去最后3位锁标记位】：001
    }
}
