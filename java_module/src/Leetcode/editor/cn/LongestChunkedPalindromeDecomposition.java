package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.Random;

/**
 * 2023-04-12 10:19:47
 * [1147] - 段式回文
 * LongestChunkedPalindromeDecomposition.md
 */
 
//你会得到一个字符串 text 。你应该把它分成 k 个子字符串 (subtext1, subtext2，…， subtextk) ，要求满足: 
// subtext[i] 是 非空 字符串 
// 所有子字符串的连接等于 text ( 即subtext[1] + subtext[2] + ... + subtext[k] == text ) 
// 对于所有 i 的有效值( 即 1 <= i <= k ) ，subtext[i] == subtext[k - i + 1] 均成立 
//
// 返回k可能最大值。 
//
// 示例 1： 
//输入：text = "ghiabcdefhelloadamhelloabcdefghi"
//输出：7
//解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
//
// 示例 2： 
//输入：text = "merchant"
//输出：1
//解释：我们可以把字符串拆分成 "(merchant)"。
//
// 示例 3： 
//输入：text = "antaprezatepzapreanta"
//输出：11
//解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
//
// 提示： 
// 1 <= text.length <= 1000 
// text 仅由小写英文字符组成 

public class LongestChunkedPalindromeDecomposition{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LongestChunkedPalindromeDecomposition().new Solution();
        System.out.println("预期结果：7 , 运行结果：" + solution.longestDecomposition3("ghiabcdefhelloadamhelloabcdefghi"));
        System.out.println("预期结果：1 , 运行结果：" + solution.longestDecomposition3("merchant"));
        System.out.println("预期结果：11 , 运行结果：" + solution.longestDecomposition3("antaprezatepzapreanta"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //贪心：每次找最短相同的前后缀
        // 复杂度分析
        // 时间复杂度：O(n^2)，其中 n 为字符串的长度。最坏情况下无法分割，需要执行 O(n) 次长为 O(n) 的字符串比较，所以时间复杂度为 O(n^2)。
        // 空间复杂度：O(n) 或 O(1)。Go 语言切片不会有拷贝，所以空间复杂度为 O(1)。当然，也可以手动比较字符串，这样无需生成子串。
        //下方使用字符串滚动哈希算法，能优化到O(n)
        
        //递归写法
        public int longestDecomposition(String text) {
            if(text.isEmpty()) return 0;
            // 枚举前后缀长度
            for(int i = 1, n = text.length(); i < n / 2; i++){
                //找到相同的最短前后缀后，立刻分割
                if(text.substring(0, i).equals(text.substring(n - i))){
                    return 2 + longestDecomposition(text.substring(i, n - i));
                }
            }
            // 无法分割
            return 1;    
        }
        
        //迭代写法
        public int longestDecomposition2(String text) {
            int ans = 0;
            while(!text.isEmpty()){
                int i = 1, n = text.length();
                // 枚举前后缀
                while(i <= n / 2 && !text.substring(0, i).equals(text.substring(n - i))){
                    i++;
                }
                // 无法分割
                if(i > n / 2){
                    ans++;
                    break;
                }
                // 分割出 s[:i] 和 s[n-i:]
                ans += 2;
                text = text.substring(i, n - i);
            }
            return ans;  
        }
        
        //字符串滚动哈希的相关介绍：https://leetcode.cn/problems/longest-happy-prefix/solution/zui-chang-kuai-le-qian-zhui-by-leetcode-solution/
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 为字符串 text 的长度，预处理字符串哈希的时间复杂度为 O(n)，双指针的时间复杂度为 O(n)。
        // 空间复杂度：O(n)，其中 n 为字符串 text 的长度，主要为预处理字符串哈希的空间开销。
        long[] pre1, pre2, pow1, pow2;
        static final int MOD1 = 1000000007, MOD2 = 1000000009;
        int base1, base2;
        Random random = new Random();
        
        public int longestDecomposition3(String text) {
            init(text);
            int n = text.length(), ans = 0, l = 0, r = n - 1;
            while(l <= r){
                int len = 1;
                while(l + len - 1 < r - len + 1){
                    if(Arrays.equals(getHash(l, l + len - 1), getHash(r - len + 1, r))){
                        ans += 2;
                        break;
                    }
                    len++;
                }
                if(l + len - 1 >= r - len + 1){
                    ans++;                    
                }
                l += len;
                r -= len;                
            }
            return ans;
        }
        
        private void init(String s){
            base1 = 1000000 + random.nextInt(9000000);
            base2 = 1000000 + random.nextInt(9000000);
            while(base1 == base2){
                base2 = 1000000 + random.nextInt(9000000);
            }
            
            int n = s.length();
            pow1 = new long[n];
            pow2 = new long[n];
            pre1 = new long[n + 1];
            pre2 = new long[n + 1];
            pow1[0] = pow2[0] = 1;
            pre1[1] = pre2[1] = s.charAt(0);
            for(int i = 1; i < n; i++){
                pow1[i] = (pow1[i - 1] * base1) % MOD1;
                pow2[i] = (pow2[i - 1] * base2) % MOD2;
                pre1[i + 1] = (pre1[i] * base1 + s.charAt(i)) % MOD1;
                pre2[i + 1] = (pre2[i] * base2 + s.charAt(i)) % MOD2;
            }
            
        }
        private long[] getHash(int l, int r){
            //long a = (pre1[r + 1] - ((pre1[l] * pow1[r + 1 - l]) % MOD1) + MOD1) % MOD1;
            //long b = (pre2[r + 1] - ((pre2[l] * pow2[r + 1 - l]) % MOD2) + MOD2) % MOD2;
            return new long[]{(pre1[r + 1] - ((pre1[l] * pow1[r + 1 - l]) % MOD1) + MOD1) % MOD1, (pre2[r + 1] - ((pre2[l] * pow2[r + 1 - l]) % MOD2) + MOD2) % MOD2};
        }
        
    }
    //leetcode submit region end(Prohibit modification and deletion)

}