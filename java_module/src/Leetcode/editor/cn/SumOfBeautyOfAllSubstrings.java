package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

/**
 * 2022-12-12 14:23:33
 * [1781] - 所有子字符串美丽值之和
 * SumOfBeautyOfAllSubstrings.md
 */
public class SumOfBeautyOfAllSubstrings{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SumOfBeautyOfAllSubstrings().new Solution();
        System.out.println("预期结果：5 , 运行结果：" + solution.beautySum("aabcb"));
        System.out.println("预期结果：17 , 运行结果：" + solution.beautySum("aabcbaa"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int beautySum(String s) {
            int ans = 0;
            for(int i = 0; i < s.length(); i++){
                int[] cnt = new int[26];
                for(int j = i, maxFreq = 0; j < s.length(); j++){
                    cnt[s.charAt(j) - 'a']++;
                    //方式一：
                    // ans += IntStream.of(cnt).filter(n -> n > 0).max().getAsInt() - IntStream.of(cnt).filter(n -> n > 0).min().getAsInt();
                    //方式二：
                    //ans += IntStream.of(cnt).filter(n -> n > 0).max().orElse(0) - IntStream.of(cnt).filter(n -> n > 0).min().orElse(0);
                    //方式三：
                    //IntSummaryStatistics intSummaryStatistics = IntStream.of(cnt).filter(n -> n > 0).summaryStatistics();
                    //ans += intSummaryStatistics.getMax() - intSummaryStatistics.getMin();
                    
                    //方式四：增加max，时间从 1625 ms 降到 613 ms
                    if(cnt[s.charAt(j) - 'a'] > maxFreq){
                        maxFreq = cnt[s.charAt(j) - 'a'];
                    }
                    //ans += maxFreq - IntStream.of(cnt).filter(n -> n > 0).min().getAsInt();
                    
                    //方式五：改为常规遍历，时间从 613 ms 降到 54 ms
                    int min = s.length();
                    for(int n : cnt){
                        if(n > 0){
                            min = Math.min(n, min);
                        }
                    }
                    ans += maxFreq - min;
                }
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}