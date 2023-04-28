package Leetcode.editor.cn;

import Leetcode.util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 2023-02-03 09:13:58
 * [1145] - 二叉树着色游戏
 * BinaryTreeColoringGame.md
 */
 
//有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点 root，树上总共有 n 个节点，且 n 为奇数，其中每个节点上的值从 1 到 n 各不相同。 
//
// 最开始时： 
// 「一号」玩家从 [1, n] 中取一个值 x（1 <= x <= n）； 
// 「二号」玩家也从 [1, n] 中取一个值 y（1 <= y <= n）且 y != x。 
// 「一号」玩家给值为 x 的节点染上红色，而「二号」玩家给值为 y 的节点染上蓝色。 
//
// 之后两位玩家轮流进行操作，「一号」玩家先手。
// 每一回合，玩家选择一个被他染过色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色（「一号」玩家染红色，「二号」玩家染蓝色）。 
//
// 如果（且仅在此种情况下）当前玩家无法找到这样的节点来染色时，其回合就会被跳过。 
// 若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。 
// 现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个 y 值可以确保你赢得这场游戏，则返回 true ；若无法获胜，就请返回 false 。 
//
// 示例 1 ： 
//输入：root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
//输出：true
//解释：第二个玩家可以选择值为 2 的节点。 
//
// 示例 2 ： 
//输入：root = [1,2,3], n = 3, x = 1
//输出：false
// 
// 提示： 
// 树中节点数目为 n 
// 1 <= x <= n <= 100 
// n 是奇数 
// 1 <= Node.val <= n 
// 树中所有值 互不相同 

public class BinaryTreeColoringGame{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new BinaryTreeColoringGame().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.btreeGameWinningMove(TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1","2","4","8","null","null","9","null","null","5","10","null","null","11","null","null","3","6","null","null","7","null","null"))), 11, 3));
        System.out.println("预期结果：false , 运行结果：" + solution.btreeGameWinningMove(TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1","2","null","null","3"))), 3, 1));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路：x 的 左子树大小（lSize）、右子树大小（rSize）、它自己父节点的树大小（n - 1 - lSize - rSize）
        //哪个子树大，就取哪个即：n2 = Math.max( Math.max(lSize, rSize), n - 1 - lSize - rSize)。
        //确定最大值后，玩家1的值为：n - n2，因此玩家2的获胜条件为：n2 > n - n2 ，也即：2 * n2 > n
        
        // 复杂度分析
        // 时间复杂度：O(n)，其中 n 是二叉树的节点数。需要遍历二叉树寻找节点 x 和计算节点 x 的左右子树的节点数，每个节点最多访问一次。
        // 空间复杂度：O(n)，其中 n 是二叉树的节点数。空间复杂度主要是递归调用栈空间，取决于二叉树的高度，平均情况是 O(logn)，最坏情况是 O(n)。
        
        int lSize = 0, rSize = 0, x;
        public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
            this.x = x;
            dfs(root);
            return Math.max(Math.max(lSize, rSize), n - 1 - lSize - rSize) * 2 > n;
        }
        
        public int dfs(TreeNode node){
            if(node == null){
                return 0;
            }
            int ls = dfs(node.left);
            int rs = dfs(node.right);
            if(node.val == x){
                lSize = ls;
                rSize = rs;
            }
            return 1 + ls + rs;            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}