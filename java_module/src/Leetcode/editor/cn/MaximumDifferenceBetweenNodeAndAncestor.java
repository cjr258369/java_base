package Leetcode.editor.cn;

import Leetcode.util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 2023-04-18 09:13:52
 * [1026] - 节点与其祖先之间的最大差值
 * MaximumDifferenceBetweenNodeAndAncestor.md
 */
 
//给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。 
// （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先） 
//
// 示例 1： 
//输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
//输出：7
//解释： 
//我们有大量的节点与其祖先的差值，其中一些如下：
//|8 - 3| = 5
//|3 - 7| = 4
//|8 - 1| = 7
//|10 - 13| = 3
//在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
//
// 示例 2： 
//输入：root = [1,null,2,null,0,3]
//输出：3
//
// 提示： 
// 树中的节点数在 2 到 5000 之间。 
// 0 <= Node.val <= 10⁵ 

public class MaximumDifferenceBetweenNodeAndAncestor{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumDifferenceBetweenNodeAndAncestor().new Solution();
        System.out.println("预期结果：7 , 运行结果：" + solution.maxAncestorDiff(TreeNode.deserializeList2(new LinkedList<>(Arrays.asList("8", "3", "10", "1",  "6", "null", "14", "null", "null", "4", "7", "13")))));
        System.out.println("预期结果：3 , 运行结果：" + solution.maxAncestorDiff(TreeNode.deserializeList2(new LinkedList<>(Arrays.asList("1", "null", "2", "null",  "0", "3")))));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxAncestorDiff(TreeNode root) {
            return dfs(root, root.val, root.val);
        }
        
        private int dfs(TreeNode root, int min, int max){
            if(root == null){
                return 0;
            }
            int diff = Math.max(Math.abs(root.val - min), Math.abs(root.val - max));
            min = Math.min(min, root.val);
            max = Math.max(max, root.val);
            return Math.max(diff, Math.max(dfs(root.left, min, max), dfs(root.right, min, max)));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}