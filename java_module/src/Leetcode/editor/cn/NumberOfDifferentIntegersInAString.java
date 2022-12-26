package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * 2022-12-06 10:36:27
 * [1805] - 字符串中不同整数的数目
 * NumberOfDifferentIntegersInAString.md
 */
public class NumberOfDifferentIntegersInAString{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NumberOfDifferentIntegersInAString().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.numDifferentIntegers("a123bc34d8ef34"));
        System.out.println("预期结果：2 , 运行结果：" + solution.numDifferentIntegers("leet1234code234"));
        System.out.println("预期结果：1 , 运行结果：" + solution.numDifferentIntegers("a1b01c001"));
        System.out.println("预期结果：1 , 运行结果：" + solution.numDifferentIntegers("167278959591294"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //无法通过，因为当 String 过长时，Integer.parseInt 就会溢出，比如："167278959591294"
        public int numDifferentIntegers2(String word) {
            HashSet<Integer> set = new HashSet<>();
            for(int left = 0, right = 0; right < word.length();right++){
                if(Character.isDigit(word.charAt(right))){
                    left = right;
                    while(right < word.length() && Character.isDigit(word.charAt(right))){
                        right++;
                    }
                    //当 String 过长时，Integer.parseInt 就会溢出，比如："167278959591294"
                    set.add(Integer.parseInt(word.substring(left, right)));
                }
            }
            System.out.println(set);
            return set.size();
        }
        
        public int numDifferentIntegers(String word) {
            return Arrays.stream(word.replaceAll("[^0-9]", " ").trim().split("\\s+"))
                    .filter(s -> !s.isEmpty())
                    .map(s -> s.replaceFirst("\\b0+", ""))
                    .collect(Collectors.toSet())
                    .size();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}