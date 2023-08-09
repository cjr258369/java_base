package Leetcode.editor.cn;

import Leetcode.util.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 2023-07-03 09:29:08
 * [445] - 两数相加 II
 * AddTwoNumbersIi.md
 */
 
//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 示例1： 
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
//
// 示例2： 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[8,0,7]
//
// 示例3： 
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
// 提示： 
// 链表的长度范围为 [1, 100] 
// 0 <= node.val <= 9 
// 输入数据保证链表代表的数字无前导 0 
//
// 进阶：如果输入链表不能翻转该如何解决？ 

public class AddTwoNumbersIi{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new AddTwoNumbersIi().new Solution();
        System.out.println("预期结果：[7,8,0,7] , 运行结果：" + solution.addTwoNumbers2(ListNode.generalListFromArray(new int[]{7,2,4,3}), ListNode.generalListFromArray(new int[]{5,6,4})));
        System.out.println("预期结果：[8,0,7] , 运行结果：" + solution.addTwoNumbers2(ListNode.generalListFromArray(new int[]{2,4,3}), ListNode.generalListFromArray(new int[]{5,6,4})));
        System.out.println("预期结果：[0] , 运行结果：" + solution.addTwoNumbers2(ListNode.generalListFromArray(new int[]{0}), ListNode.generalListFromArray(new int[]{0})));
        System.out.println("预期结果：[1,0] , 运行结果：" + solution.addTwoNumbers2(ListNode.generalListFromArray(new int[]{5}), ListNode.generalListFromArray(new int[]{5})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //可以利用栈先进后出的特性，省去我下面用list时，需要增加一个下标来取元素，栈的话，只要栈不为空则直接取栈顶元素即可。所以 方法2 用栈重写了一下
        //复杂度分析
        // 时间复杂度：O(max(m,n))，其中 m 和 n 分别为两个链表的长度。我们需要遍历两个链表的全部位置，而处理每个位置只需要 O(1) 的时间。
        // 空间复杂度：O(m+n)，其中 m 和 n 分别为两个链表的长度。空间复杂度主要取决于我们把链表内容放入栈中所用的空间。
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ArrayList<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
            while(l1 != null){
                list1.add(l1.val);
                l1 = l1.next;
            }
            while(l2 != null){
                list2.add(l2.val);
                l2 = l2.next;
            }
            
            int carry = 0;
            ListNode ans = new ListNode(), last = null;
            for(int i = list1.size() - 1, j = list2.size() - 1; i >= 0 || j >= 0; i--, j--){
                int v1 = i >= 0 ? list1.get(i) : 0;
                int v2 = j >= 0 ? list2.get(j) : 0;
                
                int sum = v1 + v2 + carry;
                ListNode curr = new ListNode(sum % 10);
                carry = sum / 10;
                curr.next = last;
                last = curr;
                ans.next = last;
            }
            
            if(carry > 0){
                ListNode temp = new ListNode(carry);
                temp.next = ans.next;
                ans.next = temp;
            }
            return ans.next;    
        }

        //方式2：利用栈先进后出的特性
        public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
            Deque<Integer> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
            while(l1 != null){
                d1.push(l1.val);
                l1 = l1.next;
            }
            while(l2 != null){
                d2.push(l2.val);
                l2 = l2.next;
            }
            
            int carry = 0;
            ListNode ans = null;
            while(!d1.isEmpty() || !d2.isEmpty() || carry != 0){
                int v1 = d1.isEmpty() ? 0 : d1.pop();
                int v2 = d2.isEmpty() ? 0 : d2.pop();
                int sum = v1 + v2 + carry;
                carry = sum / 10;
                sum %= 10;
                ListNode last = new ListNode(sum, ans);
                ans = last;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}