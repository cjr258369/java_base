package Leetcode.editor.cn;

/**
 * 2023-02-13 17:00:42
 * [1138] - 字母板上的路径
 * AlphabetBoardPath.md
 */
 
//我们从一块字母板上的位置 (0, 0) 出发，该坐标对应的字符为 board[0][0]。
// 在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]，如下所示。 
// 我们可以按下面的指令规则行动： 
// 如果方格存在，'U' 意味着将我们的位置上移一行； 
// 如果方格存在，'D' 意味着将我们的位置下移一行； 
// 如果方格存在，'L' 意味着将我们的位置左移一列； 
// 如果方格存在，'R' 意味着将我们的位置右移一列； 
// '!' 会把在我们当前位置 (r, c) 的字符 board[r][c] 添加到答案中。  （注意，字母板上只存在有字母的位置。） 
//
// 返回指令序列，用最小的行动次数让答案和目标 target 相同。你可以返回任何达成目标的路径。 
//
// 示例 1： 
//输入：target = "leet"
//输出："DDR!UURRR!!DDD!"
//
// 示例 2： 
//输入：target = "code"
//输出："RR!DDRR!UUL!R!"
//
// 提示： 
// 1 <= target.length <= 100 
// target 仅含有小写英文字母。 

public class AlphabetBoardPath{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new AlphabetBoardPath().new Solution();
        System.out.println("预期结果：DDR!UURRR!!DDD! , 运行结果：" + solution.alphabetBoardPath("leet"));
        System.out.println("预期结果：RR!DDRR!UUL!R! , 运行结果：" + solution.alphabetBoardPath("code"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //比较特殊的点为 z，所以为了保证含有字符 z’ 时能够正常移动，每次移动时优先保证选择上移和左移。
        // 复杂度分析
        // 时间复杂度： O(n×(r+c))，其中 n 表示给定字符串的长度，r 表示字母板的行数， c 表示字母板的列数。每次移动到新的字符生成移动指令时，需要的时间复杂度为 r+c，一共需要生成指令 n 次，因此时间复杂度为 O(n×(r+c))。
        // 空间复杂度：O(1)。除返回值以外不需要额外的申请空间。
        
        public String alphabetBoardPath(String target) {
            int x = 0, y = 0;   // 当前位置
            StringBuilder ans = new StringBuilder();
            for(int i = 0; i < target.length(); i++){
                char c = target.charAt(i);
                int nx = (c - 'a') / 5, ny = (c - 'a') % 5; // 目标位置
                // 竖直
                //新版java，String 可以直接 repeat
                //String v = nx < x ? "U".repeat(x - nx) : "D".repeat(nx - x);  //垂直
                //String h = ny < y ? "L".repeat(y - ny) : "R".repeat(ny - y);  //水平
                String v = "", h = "";
                if(nx < x){
                    for(int j = 0; j < x - nx; j++) ans.append('U');                    
                }
                if(ny < y){
                    for(int j = 0; j < y - ny; j++) ans.append('L');                    
                }
                if(nx > x){
                    for(int j = 0; j < nx - x; j++) ans.append('D');
                }
                if(ny > y){
                    for(int j = 0; j < ny - y; j++) ans.append('R');
                }
                ans.append('!');
                x = nx;
                y = ny;
            }
            return ans.toString();    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}