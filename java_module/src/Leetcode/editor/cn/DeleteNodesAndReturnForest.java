package Leetcode.editor.cn;

import Leetcode.util.TreeNode;

import java.util.*;

/**
 * 2023-05-30 09:36:41
 * [1110] - 删点成林
 * DeleteNodesAndReturnForest.md
 */
 
//给出二叉树的根节点 root，树上每个节点都有一个不同的值。 
// 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。 
// 返回森林中的每棵树。你可以按任意顺序组织答案。 
//
// 示例 1： 
//输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
//输出：[[1,2,null,4],[6],[7]]
//
// 示例 2： 
//输入：root = [1,2,4,null,3], to_delete = [3]
//输出：[[1,2,4]]
//
// 提示： 
// 树中的节点数最大为 1000。 
// 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。 
// to_delete.length <= 1000 
// to_delete 包含一些从 1 到 1000、各不相同的值。 

public class DeleteNodesAndReturnForest{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new DeleteNodesAndReturnForest().new Solution();
        System.out.println("预期结果：[[1,2,null,4],[6],[7]] , 运行结果：" + solution.delNodes(TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"))), new int[]{3, 5}));
        System.out.println("预期结果：[[1,2,4]] , 运行结果：" + solution.delNodes(TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1", "2", "4", "null", "3"))), new int[]{1, 2, 4}));
        System.out.println("预期结果：[2,1] , 运行结果：" + solution.delNodes(TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1", "2", "3", "null", "null", "null", "4"))), new int[]{1, 2, 4}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 是树的节点数。
        // 空间复杂度：O(n)，其中 n 是树的节点数。
        
        //因为 val 互不相同，所以可以将 set 替换为 boolean 数组，速度更快
                                                                                                                                                                                        
        HashSet<Integer> set;
        List<TreeNode> ans;
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            set = new HashSet<>();
            ans = new LinkedList<>();
            for(int d : to_delete){
                set.add(d);
            }
            if(dfs(root) != null){
                ans.add(root);
            }
            return ans;
        }
        
        private TreeNode dfs(TreeNode node){
            if(node == null){
                return null;
            }
            node.left = dfs(node.left);
            node.right = dfs(node.right);
            if(!set.contains(node.val)){
                return node;
            }
            if(node.left != null){
                ans.add(node.left);
            }
            if(node.right != null){
                ans.add(node.right);
            }
            return null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}