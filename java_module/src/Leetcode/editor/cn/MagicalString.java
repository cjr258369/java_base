package Leetcode.editor.cn;

/**
 * 2022-10-31 10:40:16
 * [481] - 神奇字符串
 * MagicalString.md
 */
public class MagicalString{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MagicalString().new Solution();
        System.out.println("运行结果：" + solution.magicalString(1));
        System.out.println("运行结果：" + solution.magicalString(2));
        System.out.println("运行结果：" + solution.magicalString(3));
        System.out.println("运行结果：" + solution.magicalString(4));
        System.out.println("运行结果：" + solution.magicalString(5));
        System.out.println("运行结果：" + solution.magicalString(6));
        System.out.println("运行结果：" + solution.magicalString(7));
        System.out.println("运行结果：" + solution.magicalString(8));
        System.out.println("运行结果：" + solution.magicalString(9));
        System.out.println("运行结果：" + solution.magicalString(10));
        System.out.println("运行结果：" + solution.magicalString(100000));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int magicalString(int n) {
            if(n < 4) return 1;
            int ans = 1;
            char[] arr = new char[n];
            arr[0] = '1';
            arr[1] = arr[2] = '2';
            for(int i = 2, j = 3, curNum = 1; j < n; curNum ^= 3, i++){
                int size = arr[i] - '0';
                while(size-- > 0 && j < n){
                    ans += (curNum == 1 ? 1 : 0);
                    arr[j++] = (char)('0' + curNum);
                }
            }
            return ans; 
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}