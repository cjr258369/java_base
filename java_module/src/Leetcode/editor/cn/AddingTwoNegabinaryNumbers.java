package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 2023-05-18 09:29:18
 * [1073] - 负二进制数相加
 * AddingTwoNegabinaryNumbers.md
 */
 
//给出基数为 -2 的两个数 arr1 和 arr2，返回两数相加的结果。
// 数字以 数组形式 给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。例如，arr = [1,1,0,1] 表示数字 (-2)^3 + (-2)^2 + (-2)^0 = -3。数组形式 中的数字 arr 也同样不含前导零：即 arr == [0] 或 arr[0] == 1。 
// 返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。 
//
// 示例 1： 
//输入：arr1 = [1,1,1,1,1], arr2 = [1,0,1]
//输出：[1,0,0,0,0]
//解释：arr1 表示 11，arr2 表示 5，输出表示 16 。
//
// 示例 2： 
//输入：arr1 = [0], arr2 = [0]
//输出：[0]
//
// 示例 3： 
//输入：arr1 = [0], arr2 = [1]
//输出：[1]
//
// 提示： 
// 1 <= arr1.length, arr2.length <= 1000 
// arr1[i] 和 arr2[i] 都是 0 或 1 
// arr1 和 arr2 都没有前导0 

public class AddingTwoNegabinaryNumbers{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new AddingTwoNegabinaryNumbers().new Solution();
        System.out.println("预期结果：[1,0,0,0,0] , 运行结果：" + Arrays.toString(solution.addNegabinary2(new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 1})));
        System.out.println("预期结果：[0] , 运行结果：" + Arrays.toString(solution.addNegabinary2(new int[]{0}, new int[]{0})));
        System.out.println("预期结果：[1] , 运行结果：" + Arrays.toString(solution.addNegabinary2(new int[]{0}, new int[]{1})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //c、c++、java 对于大数相加都容易溢出，除了 Python，因此改用下方的直接相加，这段代码就不往下写了
        public int[] addNegabinary(int[] arr1, int[] arr2) {
            //转回十进制
            int a = 0, b = 0;
            for(int i = arr1.length - 1, base = 1; i >= 0; i--){
                a += arr1[i] * base;
                base *= -2;
            }
            for(int i = arr2.length - 1, base = 1; i >= 0; i--){
                b += arr2[i] * base;
                base *= -2;
            }
            //从十进制转回 -2 进制，此处可以参考：[1017. 负二进制转换](https://leetcode.cn/problems/convert-to-base-2/)
            System.out.println("a = " + a + " , b = " + b);
            return arr1;    
        }

        //按低位加到高位
        
        //复杂度分析
        // 时间复杂度：O(m+n)，其中 m 和 n 分别是数组 arr1  和 arr2 的长度。
        // 空间复杂度：O(1) 或 O(m+n)。注意这里不包括返回值占用的空间。在最后将答案反转时，如果直接在原数组上进行反转，那么使用的空间为 O(1)；如果使用语言的 API 构造新数组进行反转（例如 Python 中的切片 [::-1] 操作），使用的空间为 O(m+n)。
        public int[] addNegabinary2(int[] arr1, int[] arr2) {
            LinkedList<Integer> ans = new LinkedList<>();
            for(int i = arr1.length - 1, j = arr2.length - 1, carry = 0; i >= 0 || j >= 0 || carry != 0; i--, j--){
                int x = carry;
                x += (i >= 0 ? arr1[i] : 0);
                x += (j >= 0 ? arr2[j] : 0);
                if(x >= 2){
                    ans.add(x - 2);
                    carry = -1;
                }else if(x >= 0){
                    ans.add(x);
                    carry = 0;
                }else{
                    ans.add(1);
                    carry = 1;                    
                }                
            }
            //移除前导0
            while(ans.size() > 1 && ans.get(ans.size() - 1) == 0){
                ans.remove(ans.size() - 1);
            }
            //生成答案，需要逆向生成，因为是从低位 加到高位，但入 ans 的顺序是逆向的
            int res[] = new int[ans.size()];
            for(int i = 0, j = ans.size() - 1; j >= 0; j--, i++){
                res[i] = ans.get(j);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}