package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2023-08-15 09:23:50
 * [833] - 字符串中的查找与替换
 * FindAndReplaceInString.md
 */
 
//你会得到一个字符串 s (索引从 0 开始)，你必须对它执行 k 个替换操作。替换操作以三个长度均为 k 的并行数组给出：indices, sources, targets。 
//
// 要完成第 i 个替换操作: 
// 
// 检查 子字符串 sources[i] 是否出现在 原字符串 s 的索引 indices[i] 处。 
// 如果没有出现， 什么也不做 。 
// 如果出现，则用 targets[i] 替换 该子字符串。 
//
// 例如，如果 s = "abcd" ， indices[i] = 0 , sources[i] = "ab"， targets[i] = "eee" ，那么替换的结果将是 "eeecd" 。 
//
// 所有替换操作必须 同时 发生，这意味着替换操作不应该影响彼此的索引。测试用例保证元素间不会重叠 。 
// 例如，一个 s = "abc" ， indices = [0,1] ， sources = ["ab"，"bc"] 的测试用例将不会生成，因为 "ab"和 "bc" 替换重叠。 
//
// 在对 s 执行所有替换操作后返回 结果字符串 。 
//
// 子字符串 是字符串中连续的字符序列。 
//
// 示例 1： 
//输入：s = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
//输出："eeebffff"
//解释：
//"a" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
//"cd" 从 s 中的索引 2 开始，所以它被替换为 "ffff"。
//
// 示例 2： 
//输入：s = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
//输出："eeecd"
//解释：
//"ab" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
//"ec" 没有从原始的 S 中的索引 2 开始，所以它没有被替换。
//
// 提示： 
// 1 <= s.length <= 1000 
// k == indices.length == sources.length == targets.length 
// 1 <= k <= 100 
// 0 <= indexes[i] < s.length 
// 1 <= sources[i].length, targets[i].length <= 50 
// s 仅由小写英文字母组成 
// sources[i] 和 targets[i] 仅由小写英文字母组成 

public class FindAndReplaceInString{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FindAndReplaceInString().new Solution();
        System.out.println("预期结果：eeebffff , 运行结果：" + solution.findReplaceString2("abcd", new int[]{0, 2}, new String[]{"a","cd"}, new String[]{"eee","ffff"}));
        System.out.println("预期结果：eeecd , 运行结果：" + solution.findReplaceString2("abcd", new int[]{0, 2}, new String[]{"ab","ec"}, new String[]{"eee","ffff"}));
        System.out.println("预期结果：jjievdtjfb , 运行结果：" + solution.findReplaceString2("jjievdtjfb", new int[]{4,6,1}, new String[]{"md","tjgb","jf"}, new String[]{"foe","oov","e"}));
        System.out.println("预期结果：abcde , 运行结果：" + solution.findReplaceString2("abcde", new int[]{2,2}, new String[]{"cdef","bc"}, new String[]{"f","fe"}));
                
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //下标排序 + 模拟
        //复杂度分析
        //时间复杂度：O(n+mlogm+ml)，其中 n 是字符串 s 的长度，m 是数组 indices 的长度，l 是数组 sources 和 targets 中字符串的平均长度。
        // 排序需要的时间为 O(mlogm)；
        // 在使用双指针进行遍历的过程中，遍历字符串需要的时间为 O(n)，遍历数组 ops 需要的时间为 O(m)，在最坏情况下需要尝试每一个替换操作，比较和构造最终答案需要的时间为 O(ml)。
        //相加即可得到总时间复杂度 O(n+mlogm+ml)。
        //
        //空间复杂度：O(n+ml)。
        // 数组 ops 需要的空间为 O(m)；
        // 排序需要的栈空间为 O(logm)；
        // 在替换操作中进行比较时，如果使用的语言支持无拷贝的切片操作，那么需要的空间为 O(1)，否则为 O(l)；
        // 在构造最终答案时，如果使用的语言支持带修改的字符串，那么需要的空间为 O(1)（不考虑最终答案占用的空间），否则需要 O(n+ml) 的辅助空间。
        //对于不同语言，上述需要的空间会有所变化。这里取每一种可能的最大值，相加即可得到总空间复杂度 O(n+ml)。
        
        
        //自己写的这版太多 if else 了，下面有优化版
        public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
            //排序
            Integer[] idx = new Integer[indices.length];
            for(int i = 0; i < indices.length; i++){
                idx[i] = i;
            }
            Arrays.sort(idx, (a, b) -> indices[a] - indices[b]);
            StringBuilder ans = new StringBuilder();
            int i = 0;
            for(int j : idx){
                //处理每个需要替换的点的前面的部分
                if(indices[j] > i){
                    ans.append(s, i, indices[j]);
                    i = indices[j];
                }
                //处理每个需要替换的部分，如果替换source 的长度 超过 s 的长度，那也是替换不了的，如最后那个案例
                if(indices[j] + sources[j].length() <= s.length() && sources[j].equals(s.substring(indices[j], indices[j] + sources[j].length()))){
                    ans.append(targets[j]);
                    i = indices[j] + sources[j].length();
                }
            }
            //替换完之后，s 如果还有字符串没拼接，那么就拼接上去
            if(i < s.length()){
                ans.append(s.substring(i));
            }
            return ans.toString();
        }
        
        //使用 while 优化
        public String findReplaceString2(String s, int[] indices, String[] sources, String[] targets) {
            int n = s.length(), m = indices.length;
            //排序
            ArrayList<Integer> ops = new ArrayList<>();
            for (int i = 0; i < indices.length; i++) {
                ops.add(i);
            }
            ops.sort((i, j) -> indices[i] - indices[j]);
            StringBuilder ans = new StringBuilder();
            for (int i = 0, pt = 0; i < n; ) {
                while (pt < m && indices[ops.get(pt)] < i) {
                    pt++;
                }
                boolean succed = false;
                while (pt < m && indices[ops.get(pt)] == i) {
                    if (s.substring(i, Math.max(i + sources[ops.get(pt)].length(), n)).equals(sources[ops.get(pt)])) {
                        succed = true;
                    }
                    pt++;
                }

                if (succed) {
                    ans.append(targets[ops.get(pt)]);
                    i += sources[ops.get(pt)].length();
                } else {
                    ans.append(s.charAt(i++));
                }
            }

            return ans.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}