package Leetcode.editor.cn;

/**
 * 2023-02-13 09:20:39
 * [1234] - 替换子串得到平衡字符串
 * ReplaceTheSubstringForBalancedString.md
 */
 
//有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。 
// 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。 
// 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。 
// 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。 
//
// 请返回待替换子串的最小可能长度。如果原字符串自身就是一个平衡字符串，则返回 0。 
//
// 示例 1： 
//输入：s = "QWER"
//输出：0
//解释：s 已经是平衡的了。 
//
// 示例 2： 
//输入：s = "QQWE"
//输出：1
//解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
//
// 示例 3： 
//输入：s = "QQQW"
//输出：2
//解释：我们可以把前面的 "QQ" 替换成 "ER"。 
// 
//
// 示例 4： 
//输入：s = "QQQQ"
//输出：3
//解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
// 
// 提示： 
// 1 <= s.length <= 10^5 
// s.length 是 4 的倍数 
// s 中只含有 'Q', 'W', 'E', 'R' 四种字符 

public class ReplaceTheSubstringForBalancedString{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ReplaceTheSubstringForBalancedString().new Solution();
        System.out.println("预期结果：0 , 运行结果：" + solution.balancedString("QWER"));
        System.out.println("预期结果：1 , 运行结果：" + solution.balancedString("QQWE"));
        System.out.println("预期结果：2 , 运行结果：" + solution.balancedString("QQQW"));
        System.out.println("预期结果：3 , 运行结果：" + solution.balancedString("QQQQ"));
        System.out.println("预期结果：4 , 运行结果：" + solution.balancedString("WWEQERQWQWWRWWERQWEQ"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //同向双指针（滑动窗口）的应用 本题只需要窗口外每种字符的数目小于等于平均值即可
        // 复杂度分析
        // 时间复杂度：O(nC)，其中 n 为 s 的长度，C=4。
        // 空间复杂度：O(C)。如果用哈希表实现，可以做到 O(C)。
        
        public int balancedString(String s) {
            int partial = s.length() / 4, ans = s.length(), cnt[] = new int['X'];
            char[] arr = s.toCharArray();
            for(char c : arr) cnt[c]++;
            if(cnt['Q'] == partial && cnt['W'] == partial && cnt['E'] == partial && cnt['R'] == partial){   //已经符合
                return 0;
            }
            for(int left = 0, right = 0; right < s.length(); right++){  // 枚举子串右端点
                --cnt[arr[right]];
                while(cnt['Q'] <= partial && cnt['W'] <= partial && cnt['E'] <= partial && cnt['R'] <= partial){
                    ans = Math.min(ans,right - left + 1);
                    ++cnt[arr[left++]];
                }                
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}