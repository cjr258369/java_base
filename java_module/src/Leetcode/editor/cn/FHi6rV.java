package Leetcode.editor.cn;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 2023-06-21 09:38:26
 * [LCP 41] - 黑白翻转棋
 * FHi6rV.md
 */
 
//在 `n*m` 大小的棋盘中，有黑白两种棋子，黑棋记作字母 `"X"`, 白棋记作字母 `"O"`，空余位置记作 `"."`。
// 当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。
//
//「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 `chessboard`。若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。
//
//**注意：**
//- 若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 **继续** 翻转白棋
//- 输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
//
//**示例 1：**
// 输入：`chessboard = ["....X.","....X.","XOOO..","......","......"]`
// 输出：`3`
// 解释： 可以选择下在 `[2,4]` 处，能够翻转白方三枚棋子。
//
//**示例 2：**
// 输入：`chessboard = [".X.",".O.","XO."]`
// 输出：`2`
// 解释： 可以选择下在 `[2,2]` 处，能够翻转白方两枚棋子。
//
//**示例 3：**
// 输入：`chessboard = [".......",".......",".......","X......",".O.....","..O....","....OOX"]`
// 输出：`4`
// 解释： 可以选择下在 `[6,3]` 处，能够翻转白方四枚棋子。
//
//**提示：**
//- `1 <= chessboard.length, chessboard[i].length <= 8`
//- `chessboard[i]` 仅包含 `"."、"O"` 和 `"X"`

public class FHi6rV{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FHi6rV().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.flipChess(new String[]{"....X.","....X.","XOOO..","......","......"}));
        System.out.println("预期结果：2 , 运行结果：" + solution.flipChess(new String[]{".X.",".O.","XO."}));
        System.out.println("预期结果：4 , 运行结果：" + solution.flipChess(new String[]{".......",".......",".......","X......",".O.....","..O....","....OOX"}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //广度搜索优先
        // 复杂度分析
        // 时间复杂度：O(n^2 × m^2 × max{n,m})，其中 n，m 为给定棋盘的行列数。最多有 n×m 个初始放置黑棋的位置，每一个位置往 8 个方向进行判断是否能翻转白棋的时间复杂度为 O(max{n,m})，所以放置初始黑棋后进行「广度优先搜索」的时间复杂度为 O(n×m×max{n,m})。
        // 空间复杂度：O(n^2 × m^2 )，其中 n，m 为给定棋盘的行列数。主要为每次「广度优先搜索」对初始棋盘进行复制和队列的空间开销。
        
        int[][] dirs = new int[][]{{-1, -1},{-1, 0},{-1, 1},{0, -1},{0, 1},{1, -1},{1, 0},{1, 1}};
        char[][] board;
        public int flipChess(String[] chessboard) {
            int res = 0, m = chessboard.length, n = chessboard[0].length();
            board = new char[m][n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    board[i][j] = chessboard[i].charAt(j);
                }
            }
            
            for(int i = 0; i < m; i++){
                for(int j= 0; j < n; j++){
                    if(board[i][j] == '.'){
                        //System.out.println("原始：" + Arrays.deepToString(board));
                        res = Math.max(res, bfs(i, j));
                    }
                }
            }
            return res;            
        }
        
        //当前位置下黑棋的每一个方向都判断一次
        private int bfs(int r, int c){
            char[][] chessboard = new char[board.length][board[0].length];
            for(int i = 0; i < board.length; i++){
                chessboard[i] = Arrays.copyOf(board[i], board[i].length);
            }
            
            int cnt = 0;
            
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{r, c});
            chessboard[r][c] = 'X';
            
            while(!queue.isEmpty()){
                int[] t = queue.poll();
                for(int[] dir : dirs){
                    if(check2(chessboard, t[0], t[1], dir[0], dir[1])){
                        int x = t[0] + dir[0], y = t[1] + dir[1];
                        while(chessboard[x][y] != 'X'){
                            queue.offer(new int[]{x, y});
                            chessboard[x][y] = 'X';
                            x += dir[0];
                            y += dir[1];
                            cnt++;
                        }
                    }
                }
            }
            return cnt;
        }
        
        //判断当前方向上是否能拦截到白棋
        private boolean check(char[][] board, int x, int y, int dx, int dy){
            x += dx;
            y += dy;
            while(x >= 0 && x < board.length && y >= 0 && y < board[0].length){
                if(board[x][y] == 'X'){
                    return true;
                }else if(board[x][y] == '.'){
                    return false;
                }
                x += dx;
                y += dy;
            }
            return false;           
        }
        
        //减少 if 的一个写法
        private boolean check2(char[][] board, int x, int y, int dx, int dy){
            x += dx;
            y += dy;
            while(x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O'){
                x += dx;
                y += dy;
            }
            //不越界，且最终到达的点是黑棋，则返回 true
            return x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == 'X';         
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}