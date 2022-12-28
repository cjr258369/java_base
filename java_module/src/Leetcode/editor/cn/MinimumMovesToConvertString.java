package Leetcode.editor.cn;

/**
 * 2022-12-27 09:36:50
 * [2027] - 转换字符串的最少操作次数
 * MinimumMovesToConvertString.md
 */
public class MinimumMovesToConvertString{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumMovesToConvertString().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.minimumMoves2("XXX"));
        System.out.println("预期结果：2 , 运行结果：" + solution.minimumMoves2("XXOX"));
        System.out.println("预期结果：0 , 运行结果：" + solution.minimumMoves2("OOOO"));
        System.out.println("预期结果：1 , 运行结果：" + solution.minimumMoves2("OXOX"));
        System.out.println("预期结果：2 , 运行结果：" + solution.minimumMoves2("XXXOOXXX"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumMoves(String s) {
            int idx = 0, ans = 0;
            while(idx < s.length()){
                if('O' == s.charAt(idx)){
                    idx++;
                }else{
                    idx += 3;
                    ans++;
                }
            }
            return ans;
        }
        
        //减少判断，效率更优：
        public int minimumMoves2(String s) {
            int ans = 0;
            for(int i = 0; i < s.length(); i++){
                if('X' == s.charAt(i)){
                    i += 2;
                    ans++;
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}