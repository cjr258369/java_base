package Leetcode.editor.cn;

import Leetcode.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 2023-08-14 09:35:46
 * [617] - 合并二叉树
 * MergeTwoBinaryTrees.md
 */
 
//给你两棵二叉树： root1 和 root2 。 
//
// 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。 
// 返回合并后的二叉树。 
// 注意: 合并过程必须从两个树的根节点开始。 
//
// 示例 1： 
//输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
//输出：[3,4,5,5,4,null,7]
//
// 示例 2： 
//输入：root1 = [1], root2 = [1,2]
//输出：[2,2]
//
// 提示： 
// 两棵树中的节点数目在范围 [0, 2000] 内 
// -10⁴ <= Node.val <= 10⁴ 

public class MergeTwoBinaryTrees{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MergeTwoBinaryTrees().new Solution();
        System.out.println("预期结果：[3,4,5,null,null,4,null,null,5,null,7,null,null] , 运行结果：" + solution.mergeTrees2(TreeNode.deserializeList2(new LinkedList<>(Arrays.asList("1", "3", "2", "5", "Null"))), TreeNode.deserializeList2(new LinkedList<>(Arrays.asList("2", "1", "3", "Null", "4", "Null", "7")))));
        System.out.println("预期结果：[2,2] , 运行结果：" + solution.mergeTrees2(TreeNode.deserializeList2(new LinkedList<>(Arrays.asList("1"))), TreeNode.deserializeList2(new LinkedList<>(Arrays.asList("1", "2")))));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //深度搜索优先
        // 复杂度分析
        // 时间复杂度：O(min(m,n))，其中 m 和 n 分别是两个二叉树的节点个数。对两个二叉树同时进行深度优先搜索，只有当两个二叉树中的对应节点都不为空时才会对该节点进行显性合并操作，因此被访问到的节点数不会超过较小的二叉树的节点数。
        // 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个二叉树的节点个数。空间复杂度取决于递归调用的层数，递归调用的层数不会超过较小的二叉树的最大高度，最坏情况下，二叉树的高度等于节点数。
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if(root1 == null) return root2;
            if(root2 == null) return root1;
            return new TreeNode(root1.val + root2.val, mergeTrees(root1.left, root2.left), mergeTrees(root1.right, root2.right));    
        }
        
        //广度搜索优先
        // 复杂度分析
        // 时间复杂度：O(min(m,n))，其中 m 和 n 分别是两个二叉树的节点个数。对两个二叉树同时进行广度优先搜索，只有当两个二叉树中的对应节点都不为空时才会访问到该节点，因此被访问到的节点数不会超过较小的二叉树的节点数。
        // 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个二叉树的节点个数。空间复杂度取决于队列中的元素个数，队列中的元素个数不会超过较小的二叉树的节点数。
        public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
            if(root1 == null) return root2;
            if(root2 == null) return root1;
            TreeNode merge = new TreeNode(root1.val + root2.val);
            Queue<TreeNode> queue = new ArrayDeque<>();
            Queue<TreeNode> queue1 = new ArrayDeque<>();
            Queue<TreeNode> queue2 = new ArrayDeque<>();
            queue.offer(merge);
            queue1.offer(root1);
            queue2.offer(root2);
            
            while(!queue1.isEmpty() && !queue2.isEmpty()){
                TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
                if(node1.left != null || node2.left != null){
                    if(node1.left != null && node2.left != null){
                        TreeNode left = new TreeNode(node1.left.val + node2.left.val);
                        node.left = left;
                        queue.offer(left);
                        queue1.offer(node1.left);
                        queue2.offer(node2.left);
                    }else if(node1.left != null){
                        node.left = node1.left;
                    }else if(node2.left != null){
                        node.left = node2.left;
                    }
                }
                
                if(node1.right != null || node2.right != null){
                    if(node1.right != null && node2.right != null){
                        TreeNode right = new TreeNode(node1.right.val + node2.right.val);
                        node.right = right;
                        queue.offer(right);
                        queue1.offer(node1.right);
                        queue2.offer(node2.right);
                    }else if(node1.right != null){
                        node.right = node1.right;
                    }else if(node2.right != null){
                        node.right = node2.right;
                    }
                }
            }
            
            return merge;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}