package Leetcode.editor.cn;

import Leetcode.util.ListNode;

/**
 * 2023-08-07 10:23:34
 * [24] - 两两交换链表中的节点
 * SwapNodesInPairs.md
 */
 
//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//
// 示例 1： 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
//
// 示例 2： 
//输入：head = []
//输出：[]
//
// 示例 3： 
//输入：head = [1]
//输出：[1]
//
// 提示： 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 

public class SwapNodesInPairs{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SwapNodesInPairs().new Solution();
        System.out.println("预期结果：[2,1,4,3] , 运行结果：" + solution.swapPairs2(ListNode.generalListFromArray(new int[]{1,2,3,4})));
        System.out.println("预期结果：[] , 运行结果：" + solution.swapPairs2(ListNode.generalListFromArray(new int[]{})));
        System.out.println("预期结果：[1] , 运行结果：" + solution.swapPairs2(ListNode.generalListFromArray(new int[]{1})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //递归：复杂度分析
        // 时间复杂度：O(n)，其中 n 是链表的节点数量。需要对每个节点进行更新指针的操作。
        // 空间复杂度：O(n)，其中 n 是链表的节点数量。空间复杂度主要取决于递归调用的栈空间。
        public ListNode swapPairs(ListNode head) {
            if(head == null || head.next == null){
                return head;
            }
            ListNode newHead = head.next;
            head.next = swapPairs(newHead.next);
            newHead.next = head;
            return newHead;
        }
        
        //迭代：复杂度分析
        // 时间复杂度：O(n)，其中 n 是链表的节点数量。需要对每个节点进行更新指针的操作。
        // 空间复杂度：O(1)。
        public ListNode swapPairs2(ListNode head) {
            ListNode dummyNode = new ListNode(-1, head), temp = dummyNode;
            while(temp.next != null && temp.next.next != null){
                ListNode node1 = temp.next, node2 = temp.next.next;
                temp.next = node2;
                node1.next = node2.next;
                node2.next = node1;
                
                temp = node1;
            }
            return dummyNode.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}