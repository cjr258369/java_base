package Leetcode.editor.cn;

import Leetcode.util.ListNode;

import java.util.ArrayList;

/**
 * 2023-07-31 10:08:12
 * [143] - 重排链表
 * ReorderList.md
 */
 
//给定一个单链表 L 的头节点 head ，单链表 L 表示为：
//L0 → L1 → … → Ln - 1 → Ln
// 请将其重新排列后变为：
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
// 示例 1：
//输入：head = [1,2,3,4]
//输出：[1,4,2,3] 
//
// 示例 2：
//输入：head = [1,2,3,4,5]
//输出：[1,5,2,4,3]
//
// 提示：
// 链表的长度范围为 [1, 5 * 10⁴] 
// 1 <= node.val <= 1000

public class ReorderList{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ReorderList().new Solution();
        ListNode node1 = ListNode.generalListFromArray(new int[]{1, 2, 3, 4});
        ListNode node2 = ListNode.generalListFromArray(new int[]{1, 2, 3, 4, 5});
        solution.reorderList2(node1);
        solution.reorderList2(node2);
        System.out.println("预期结果：[1,4,2,3] , 运行结果：" + node1);
        System.out.println("预期结果：[1,5,2,4,3] , 运行结果：" + node2);
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //线性表
        //复杂度分析
        // 时间复杂度：O(N)，其中 N 是链表中的节点数。
        // 空间复杂度：O(N)，其中 N 是链表中的节点数。主要为线性表的开销。
        public void reorderList(ListNode head) {
            ArrayList<ListNode> list = new ArrayList<>();
            ListNode curr = head;
            while(curr != null){
                list.add(curr);
                curr = curr.next;
            }
            int i = 0, j = list.size() - 1;
            while(i < j){
                list.get(i).next = list.get(j);
                i++;
                if(i == j) break;
                list.get(j).next = list.get(i);
                j--;
            }
            list.get(i).next = null;
        }
        
        //寻找链表中点，然后从中点翻转链表，最后两个链表穿插合并
        //复杂度分析
        // 时间复杂度：O(N)，其中 N 是链表中的节点数。
        // 空间复杂度：O(1)。
        public void reorderList2(ListNode head) {
            if(head == null){
                return;
            }
            //找到中点
            ListNode mid = middleNode(head);
            ListNode l1 = head, l2 = mid.next;
            //翻转中点后面的链表
            l2 = reserve(l2);
            mid.next = null;
            //合并
            mergeList(l1, l2);
        }

        private ListNode middleNode(ListNode head) {
            ListNode slow = head, fast = head;
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        private ListNode reserve(ListNode head) {
            ListNode prev = null, curr = head;
            while(curr != null){
                ListNode nextTmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTmp;
            }
            return prev;            
        }

        private void mergeList(ListNode l1, ListNode l2) {
            ListNode l1_tmp, l2_tmp;
            while(l1 != null && l2 != null){
                l1_tmp = l1.next;
                l2_tmp = l2.next;
                
                l1.next = l2;
                l1 = l1_tmp;
                
                l2.next = l1;
                l2 = l2_tmp;                
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}