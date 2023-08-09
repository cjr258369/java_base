package DSA;

import java.math.BigInteger;
import java.util.Stack;

/**
 * 计算最小公因数的常规方法
 * @author chenjunran
 * @date 2022/7/19
 */
public class computeGcd {

    public static void main(String[] args) {
        //求两个数的公因数
        System.out.println(BigInteger.valueOf(2).gcd(BigInteger.valueOf(5)));
        System.out.println(gcd(6, 9));

        //求一个数组的公因数
        int[] nums = new int[]{9,6,9,3,15};
        System.out.println(BigIntegerGcd(nums));
        System.out.println(myGcd(nums));
    }

    /**
     * 使用 import java.math.BigInteger; 的内置 gcd 函数
     * @param nums 传入 需要求的gcd 数组，或者直接调用方法传入两个数字
     * @return
     */
    public static int BigIntegerGcd(int[] nums){
        int gcd = 0;
        for(int num : nums){
            gcd = BigInteger.valueOf(gcd).gcd(BigInteger.valueOf(num)).intValue();
        }
        return gcd;
    }

    /**
     * 调用 自己用递归手写的 gcd，也可以求两个数的最小公因数
     * @param nums
     * @return
     */
    public static int myGcd(int[] nums){
        Stack<Integer> stack = new Stack();
        for(int n : nums){
            stack.push(n);
        }
        while(stack.size() > 1){
            stack.push(gcd(stack.pop(), stack.pop()));
        }
        return stack.pop();
    }

    //求两数的最小公因数
    public static int gcd(int m, int n){
        return m % n == 0 ? n : gcd(n, m % n);
    }
}
