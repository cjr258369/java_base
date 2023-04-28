package Leetcode.editor.cn;

import Leetcode.util.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * 2023-04-10 09:30:46
 * [1019] - 链表中的下一个更大节点
 * NextGreaterNodeInLinkedList.md
 */
 
//给定一个长度为 n 的链表 head
// 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
// 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。 
//
// 示例 1： 
//输入：head = [2,1,5]
//输出：[5,5,0]
//
// 示例 2： 
//输入：head = [2,7,4,3,5]
//输出：[7,0,5,5,0]
//
// 提示： 
// 链表中节点数为 n 
// 1 <= n <= 10⁴ 
// 1 <= Node.val <= 10⁹ 

public class NextGreaterNodeInLinkedList{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NextGreaterNodeInLinkedList().new Solution();
        System.out.println("预期结果：[5,5,0] , 运行结果：" + Arrays.toString(solution.nextLargerNodes2(ListNode.generalListFromArray(new int[]{2, 1, 5}))));
        System.out.println("预期结果：[7,0,5,5,0] , 运行结果：" + Arrays.toString(solution.nextLargerNodes2(ListNode.generalListFromArray(new int[]{2, 7, 4, 3, 5}))));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //自己暴力，最坏情况应该是 O(n^2),
        public int[] nextLargerNodes(ListNode head) {
            ArrayList<Integer> temp = new ArrayList<>();
            while(head != null){
                temp.add(head.val);
                head = head.next;
            }
            int[] res = new int[temp.size()];
            for(int i = 0; i < temp.size(); i++){
                for(int j = i + 1; j < temp.size(); j++){
                    if(temp.get(j) > temp.get(i)){
                        res[i] = temp.get(j);
                        break;
                    }
                }
            }
            return res;    
        }
        
        //单调栈一
        public int[] nextLargerNodes2(ListNode head) {
            ArrayList<Integer> ans = new ArrayList<>();
            Deque<int[]> stack = new ArrayDeque<>();
            int idx = -1;
            while(head != null){
                idx++;
                ans.add(0);
                while(!stack.isEmpty() && stack.peek()[0] < head.val){
                    ans.set(stack.pop()[1], head.val);
                }
                stack.push(new int[]{head.val, idx});
                head = head.next;
            }
            int res[] = new int[ans.size()];
            for(int i = 0; i < ans.size(); i++){
                res[i] = ans.get(i);
            }
            return res;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}