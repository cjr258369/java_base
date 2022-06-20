package DSA;

/**
 * 大数相加的实现
 * 如果存在大数相加，溢出long精度的情况，则需模拟普通的竖式计算，两个String逐位相加。
 *
 * lc 上面，求链表和也是类似做法
 *
 */
public class StringAdd {

    public static void main(String[] args) {
        System.out.println(stringAdd("123","456"));
        System.out.println(stringAdd("1","119"));
        System.out.println(stringAdd("1","99"));
    }

    /**
     * 假设传入的s1 ， s2 均为数字
     * @param s1 数字1
     * @param s2 数字2
     * @return 返回 s1 + s2 的计算结果
     */
    public static String stringAdd(String s1, String s2){
        //根据长度从低位向高位相加
        int len1 = s1.length();
        int len2 = s2.length();

        //存储进位
        int carry = 0;

        StringBuilder ans = new StringBuilder();
        while(len1>0 || len2>0){
            int n1 = len1>0 ? s1.charAt(len1-1) - '0' : 0;
            int n2 = len2>0 ? s2.charAt(len2-1) - '0' : 0;
            //存储当前两位的和
            int sum = n1 + n2 + carry;
            //是否需要进位
            carry = sum / 10;
            ans.insert(0 , sum%10);

            if(len1>0) len1--;
            if(len2>0) len2--;
        }
        //累计结束，还要看一下是否有进位
        if(carry > 0) ans.insert(0,carry);
        return ans.toString();
    }
}
