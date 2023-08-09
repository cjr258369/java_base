package Leetcode.editor.cn;

import Leetcode.util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 2023-05-22 11:05:59
 * [1080] - 根到叶路径上的不足节点
 * InsufficientNodesInRootToLeafPaths.md
 */
 
//给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。
// 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。  叶子节点，就是没有子节点的节点。 
//
// 示例 1： 
//输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
//输出：[1,2,3,4,null,null,7,8,9,null,14]
//
// 示例 2： 
//输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
//输出：[5,4,8,11,null,17,4,7,null,null,null,5]
//
// 示例 3： 
//输入：root = [1,2,-3,-5,null,4,null], limit = -1
//输出：[1,null,-3,4]
//
// 提示： 
// 树中节点数目在范围 [1, 5000] 内 
// -10⁵ <= Node.val <= 10⁵ 
// -10⁹ <= limit <= 10⁹ 

public class InsufficientNodesInRootToLeafPaths{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new InsufficientNodesInRootToLeafPaths().new Solution();
        System.out.println("预期结果：[1,2,3,4,null,null,7,8,9,null,14] , 运行结果：" + solution.sufficientSubset2(TreeNode.deserializeList2(new LinkedList<>(Arrays.asList("1","2","3","4","-99","-99","7","8","9","-99","-99","12","13","-99","14"))), 1));
        System.out.println("预期结果：[5,4,8,11,null,17,4,7,null,null,null,5] , 运行结果：" + solution.sufficientSubset2(TreeNode.deserializeList2(new LinkedList<>(Arrays.asList("5","4","8","11","null","17","4","7","1","null","null","5","3"))), 22));
        System.out.println("预期结果：[1,null,-3,4] , 运行结果：" + solution.sufficientSubset2(TreeNode.deserializeList2(new LinkedList<>(Arrays.asList("1","2","-3","-5","null","4","null"))), -1));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 表示树中节点的数目。对于每个节点我们只需遍历一次即可，因此需要的时间复杂度为 O(n)。
        // 空间复杂度：O(n)，其中 n 表示树中节点的数目。由于递归需要占用空间，此时空间复杂度取决于树的高度，最优情况下树的高度为 log n，此时需要的空间为 O(logn)，最差情况下树(退化为一条单链表)，则高度为 n，此时需要的空间为 O(n)，因此空间复杂度为 O(n)。
        public TreeNode sufficientSubset(TreeNode root, int limit) {
            return checkSufficientLeaf(root, 0, limit) ? root : null;
        }
        
        private boolean checkSufficientLeaf(TreeNode node, int sum, int limit){
            if(node == null){
                return false;
            }
            
            if(node.left == null && node.right == null){
                return sum + node.val >= limit;
            }
            
            boolean left = checkSufficientLeaf(node.left, sum + node.val, limit);
            boolean right = checkSufficientLeaf(node.right, sum + node.val, limit);
            
            if(!left){
                node.left = null;
            }
            if(!right){
                node.right = null;
            }
            
            return left || right;
        }
        
        //优化为只有 1 个函数
        //巧妙的利用了 limit 的减少来达到记录根节点到当前节点的和
        public TreeNode sufficientSubset2(TreeNode root, int limit) {
            limit -= root.val;
            // root 是叶子
            if(root.left == null && root.right == null){
                // 如果 limit > 0 说明从根到叶子的路径和小于 limit，删除叶子，否则不删除
                return limit > 0 ? null : root;                
            }
            if(root.left != null) root.left = sufficientSubset2(root.left, limit);
            if(root.right != null) root.right = sufficientSubset2(root.right, limit);
            return root.left == null && root.right == null ? null : root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}