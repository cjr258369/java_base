package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2023-04-14 14:13:09
 * [1023] - 驼峰式匹配
 * CamelcaseMatching.md
 */
 
//如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。） 
// 给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串pattern 匹配时， answer[i] 才为 true，否则为 false。 
//
// 示例 1： 
// 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
//输出：[true,false,true,true,false]
//示例：
//"FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
//"FootBall" 可以这样生成："F" + "oot" + "B" + "all".
//"FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer". 
//
// 示例 2： 
// 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
//输出：[true,false,true,false,false]
//解释：
//"FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
//"FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
//
// 示例 3： 
// 输出：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
//输入：[false,true,false,false,false]
//解释： 
//"FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
//
// 提示： 
// 1 <= queries.length <= 100 
// 1 <= queries[i].length <= 100 
// 1 <= pattern.length <= 100 
// 所有字符串都仅由大写和小写英文字母组成。 

public class CamelcaseMatching{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CamelcaseMatching().new Solution();
        System.out.println("预期结果：[true,false,true,true,false] , 运行结果：" + solution.camelMatch(new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}, "FB"));
        System.out.println("预期结果：[true,false,true,false,false] , 运行结果：" + solution.camelMatch(new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}, "FoBa"));
        System.out.println("预期结果：[false,true,false,false,false] , 运行结果：" + solution.camelMatch(new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}, "FoBaT"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //不要脸，直接强行上正则
        public List<Boolean> camelMatch(String[] queries, String pattern) {
            StringBuilder sb = new StringBuilder("[a-z]*");
            for(int i = 0; i < pattern.length(); i++){
                sb.append(pattern.charAt(i)).append("[a-z]*");
            }
            return Arrays.stream(queries).map(query -> query.matches(sb.toString())).collect(Collectors.toList());
        }
        
        //补回一个双指针
        // 复杂度分析
        // 时间复杂度：O(nm)，其中 n 是 queries 的长度，m 是 queries[i] 的长度。
        // 空间复杂度：O(1)。我们忽略返回值的空间复杂度，过程中只使用了常数个变量。
        public List<Boolean> camelMatch2(String[] queries, String pattern) {
            char[] arr = pattern.toCharArray();
            List<Boolean> ans = new ArrayList<>();
            for(String query : queries){
                boolean flag = true;
                int p = 0;
                for(int i = 0; i < query.length(); i++){
                    char c = query.charAt(i);
                    if(p < arr.length && c == arr[p]){
                        p++;
                    }else if(Character.isUpperCase(c)){
                        flag = false;
                        break;
                    }
                }
                if(p < arr.length){
                    flag = false;
                }
                ans.add(flag);
            }
            return ans;
        }
        
    }
    //leetcode submit region end(Prohibit modification and deletion)

}