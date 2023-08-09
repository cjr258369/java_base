package Leetcode.editor.cn;

import Leetcode.util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 2023-07-14 11:15:34
 * [979] - 在二叉树中分配硬币
 * DistributeCoinsInBinaryTree.md
 */
 
//给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。
// 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
// 返回使每个结点上只有一枚硬币所需的移动次数。 
//
// 示例 1： 
// 输入：[3,0,0]
//输出：2
//解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。
//
// 示例 2： 
// 输入：[0,3,0]
//输出：3
//解释：从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。
//
// 示例 3： 
// 输入：[1,0,2]
//输出：2
//
// 示例 4： 
// 输入：[1,0,0,null,3]
//输出：4
//
// 提示： 
// 1<= N <= 100 
// 0 <= node.val <= N 

public class DistributeCoinsInBinaryTree{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new DistributeCoinsInBinaryTree().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.distributeCoins(TreeNode.deserializeList(new LinkedList<>(Arrays.asList("3", "0", "null", "null", "0")))));
        System.out.println("预期结果：3 , 运行结果：" + solution.distributeCoins(TreeNode.deserializeList(new LinkedList<>(Arrays.asList("0", "3", "null", "null", "0")))));
        System.out.println("预期结果：2 , 运行结果：" + solution.distributeCoins(TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1", "0", "null", "null", "2")))));
        System.out.println("预期结果：4 , 运行结果：" + solution.distributeCoins(TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1", "0", "null", "3", "null", "null","0")))));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 表示二叉树中节点的数目。只需要遍历一遍即可，因此时间复杂度为 O(n)。
        // 空间复杂度：O(n)，其中 n 表示二叉树中节点的数目。递归深度与二叉树的深度有关，其中二叉树的深度最小值为 logn，深度最大值为 n，递归深度最多为 n 层，因此空间复杂度为 O(n)。
        
        int ans;
        public int distributeCoins(TreeNode root) {
            ans = 0;
            //dfs1(root);
            dfs2(root);
            return ans;
        }
        
        private int[] dfs1(TreeNode node){
            if(node == null){
                return new int[2];
            }
            int[] left = dfs1(node.left);
            int[] right = dfs1(node.right);
            int coins = left[0] + right[0] + node.val;  // 子树硬币个数
            int nodes = left[1] + right[1] + 1;   // 子树节点数
            ans += Math.abs(coins - nodes);
            return new int[]{coins, nodes};
        }
        
        //优化后
        private int dfs2(TreeNode node){
            if(node == null){
                return 0;
            }
            int d = dfs2(node.left) + dfs2(node.right) + node.val - 1;
            ans += Math.abs(d);
            return d;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}