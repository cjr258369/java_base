package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 2022-11-07 09:09:42
 * [816] - 模糊坐标
 * AmbiguousCoordinates.md
 */
public class AmbiguousCoordinates{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new AmbiguousCoordinates().new Solution();
        System.out.println("预期结果：[(1, 23), (12, 3), (1.2, 3), (1, 2.3)] , 运行结果：" + solution.ambiguousCoordinates("(123)"));
        System.out.println("预期结果：[(0.001, 1), (0, 0.011)] , 运行结果：" + solution.ambiguousCoordinates("(00011)"));
        System.out.println("预期结果：[(0, 123), (0, 12.3), (0, 1.23), (0.1, 23), (0.1, 2.3), (0.12, 3)] , 运行结果：" + solution.ambiguousCoordinates("(0123)"));
        System.out.println("预期结果：[(10, 0)] , 运行结果：" + solution.ambiguousCoordinates("(100)"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> ambiguousCoordinates(String s) {
            List<String> result = new ArrayList<>();
            s = s.substring(1, s.length() - 1);
            //要从第二位开始，不然第一个为空是不允许的，结尾要预留一位，不然第二个坐标为空，也是不允许的
            for(int splitIdx = 1; splitIdx <= s.length() - 1; splitIdx++){
                List<String> leftCnt = searchNums(s.substring(0, splitIdx));
                if(leftCnt.isEmpty()){
                    continue;
                }
                List<String> rightCnt = searchNums(s.substring(splitIdx));
                if(rightCnt.isEmpty()){
                    continue;
                }
                
                for(String l : leftCnt){
                    for(String r : rightCnt){
                        result.add("(" + l + ", " + r + ")");
                    }
                }
            }
            return result;
        }
        
        public List<String> searchNums(String s){
            List<String> result = new ArrayList<>();
            //不加任何小数点的情况：只要不是0开头的整数，或者单独的 0 ，那么可以不加小数点，作为一个整体的整数：
            if(s.charAt(0) != '0' || "0".equals(s)){
                result.add(s);
            }
            for(int pos = 1; pos < s.length(); pos++){
                //情况一：不能以"00."、"000." 这种开头
                //情况二：如果加了小数点，末尾不能为0，因此这两种情况跳过，其他情况可添加小数点
                if((pos != 1 && s.charAt(0) == '0') || s.charAt(s.length() - 1) == '0'){
                    continue;
                }
                result.add(s.substring(0, pos) + "." + s.substring(pos));
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}