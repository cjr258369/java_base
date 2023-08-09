package Leetcode.util;

/**
 * @date 2022/10/20
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        ListNode head = this;
        StringBuilder sb = new StringBuilder().append('[');
        while(head != null){
            sb.append(head.val).append(',');
            head = head.next; 
        }
        sb.deleteCharAt(sb.length() - 1).append(']');
        return sb.toString();
    }

    //通过数组构造链表，并返回头节点
    public static ListNode generalListFromArray(int[] nums){
        ListNode head = new ListNode(), curr = head;
        for(int num : nums){
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return head.next;
    }
}
