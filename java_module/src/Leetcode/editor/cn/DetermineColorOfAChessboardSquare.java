package Leetcode.editor.cn;

/**
 * 2022-12-08 09:30:26
 * [1812] - 判断国际象棋棋盘中一个格子的颜色
 * DetermineColorOfAChessboardSquare.md
 */
public class DetermineColorOfAChessboardSquare{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new DetermineColorOfAChessboardSquare().new Solution();
        System.out.println("预期结果：false , 运行结果：" + solution.squareIsWhite("a1"));
        System.out.println("预期结果：true , 运行结果：" + solution.squareIsWhite("a2"));
        System.out.println("预期结果：false , 运行结果：" + solution.squareIsWhite("a3"));
        System.out.println("预期结果：true , 运行结果：" + solution.squareIsWhite("b1"));
        System.out.println("预期结果：false , 运行结果：" + solution.squareIsWhite("b2"));
        System.out.println("预期结果：true , 运行结果：" + solution.squareIsWhite("b3"));
        System.out.println("预期结果：true , 运行结果：" + solution.squareIsWhite("h3"));
        System.out.println("预期结果： false , 运行结果：" + solution.squareIsWhite("c7"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean squareIsWhite(String coordinates) {
            return coordinates.charAt(0) % 2 + coordinates.charAt(1) % 2 == 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}