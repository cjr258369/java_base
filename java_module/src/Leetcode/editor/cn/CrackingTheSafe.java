package Leetcode.editor.cn;

import java.util.HashSet;

/**
 * 2023-01-10 09:20:45
 * [753] - 破解保险箱
 * CrackingTheSafe.md
 */
 
// 有一个需要密码才能打开的保险箱。密码是 n 位数, 密码的每一位是 k 位序列 0, 1, ..., k-1 中的一个 。 
// 你可以随意输入密码，保险箱会自动记住最后 n 位输入，如果匹配，则能够打开保险箱。 
// 举个例子，假设密码是 "345"，你可以输入 "012345" 来打开它，只是你输入了 6 个字符. 
// 请返回一个能打开保险箱的最短字符串。 
//
// 示例1: 
// 输入: n = 1, k = 2
// 输出: "01"
// 说明: "10"也可以打开保险箱。
// 
// 示例2: 
// 输入: n = 2, k = 2
// 输出: "00110"
// 说明: "01100", "10011", "11001" 也能打开保险箱。
//
// 提示： 
// n 的范围是 [1, 4]。 
// k 的范围是 [1, 10]。 
// k^n 最大可能为 4096。 

public class CrackingTheSafe{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CrackingTheSafe().new Solution();
        System.out.println("预期结果：01 , 运行结果：" + solution.crackSafe(1, 2));
        System.out.println("预期结果：00110 , 运行结果：" + solution.crackSafe(2, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //欧拉回路的应用题，欧拉路径的概念可参考：https://blog.csdn.net/qq_34292517/article/details/105463522?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-4
        //参考官解，直接使用 Hierholzer 算法。
        //复杂度分析：
        // 时间复杂度：O(n * k^n)。
        // 空间复杂度：O(n * k^n)。
        
        //HashSet<Integer> visited = new HashSet<>();
        //StringBuffer ans = new StringBuffer();
        HashSet<Integer> visited;
        StringBuffer ans;
        int k, higest;
        public String crackSafe(int n, int k) {
            this.k = k;
            higest = (int)Math.pow(10, n - 1);
            visited = new HashSet<>();
            ans = new StringBuffer();
            dfs(0);
            //回归起点
            for(int i = 1; i < n; i++){
                ans.append('0');
            }
            return ans.toString();    
        }
        
        private void dfs(int node){
            for(int x = 0; x < k; x++){
                int nei = node * 10 + x;
                if(visited.add(nei)){
                    dfs(nei % higest);
                    ans.append(x);            
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}