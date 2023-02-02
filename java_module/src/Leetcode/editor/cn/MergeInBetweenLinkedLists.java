package Leetcode.editor.cn;

import Leetcode.util.ListNode;

/**
 * 2023-01-30 09:21:27
 * [1669] - 合并两个链表
 * MergeInBetweenLinkedLists.md
 */
 
//给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。 
// 下图中蓝色边和节点展示了操作后的结果： 
// 
// 请你返回结果链表的头指针。 
//
// 示例 1： 
//输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
//输出：[0,1,2,1000000,1000001,1000002,5]
//解释：我们删除 list1 中下标为 3 和 4 的两个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
//
// 示例 2： 
//输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
//输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
//解释：上图中蓝色的边和节点为答案链表。
//
// 提示： 
// 3 <= list1.length <= 10⁴ 
// 1 <= a <= b < list1.length - 1 
// 1 <= list2.length <= 10⁴ 

public class MergeInBetweenLinkedLists{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MergeInBetweenLinkedLists().new Solution();
        System.out.println("预期结果：[0,1,2,1000000,1000001,1000002,5] , 运行结果：" + solution.mergeInBetween(ListNode.generalListFromArray(new int[]{0,1,2,3,4,5}), 3, 4, ListNode.generalListFromArray(new int[]{1000000,1000001,1000002})));
        System.out.println("预期结果：[0,1,1000000,1000001,1000002,1000003,1000004,6] , 运行结果：" + solution.mergeInBetween(ListNode.generalListFromArray(new int[]{0,1,2,3,4,5,6}), 2, 5, ListNode.generalListFromArray(new int[]{1000000,1000001,1000002,1000003,1000004})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //直接模拟
        //复杂度分析
        //时间复杂度：O(n+m)，其中 n 是 list1 的长度，m 是 list2 的长度。
        //空间复杂度：O(1)。
        public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
            int idx = 0;
            ListNode start = list1, end;
            while(start != null && ++idx < a){
                start = start.next;                
            }
            end = start.next;
            while(end != null && idx++ < b){
                end = end.next;                
            }
            start.next = list2;
            while(list2.next != null){
                list2 = list2.next;
            }
            list2.next = end.next;
            return list1;            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}