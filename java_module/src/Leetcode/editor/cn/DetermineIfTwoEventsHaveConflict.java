package Leetcode.editor.cn;

/**
 * 2022-10-24 10:37:58
 * [2446] - 判断两个事件是否存在冲突
 * DetermineIfTwoEventsHaveConflict.md
 */
public class DetermineIfTwoEventsHaveConflict{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new DetermineIfTwoEventsHaveConflict().new Solution();
        System.out.println("运行结果：" + solution.haveConflict(new String[]{"01:15","02:00"}, new String[]{"02:00","03:00"}));
        System.out.println("运行结果：" + solution.haveConflict(new String[]{"01:00","02:00"}, new String[]{"01:20","03:00"}));
        System.out.println("运行结果：" + solution.haveConflict(new String[]{"10:00","11:00"}, new String[]{"14:00","15:00"}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean haveConflict(String[] event1, String[] event2) {
        //even1 的开始时间小于 even2 的结束时间，且 even2 的开始时间 小于 even1 的结束时间，则为有相交
        return 60 * Integer.parseInt(event1[0].substring(0, 2)) + Integer.parseInt(event1[0].substring(3)) <= 60 * Integer.parseInt(event2[1].substring(0, 2)) + Integer.parseInt(event2[1].substring(3)) 
                && 60 * Integer.parseInt(event2[0].substring(0, 2)) + Integer.parseInt(event2[0].substring(3)) <= 60 * Integer.parseInt(event1[1].substring(0, 2)) + Integer.parseInt(event1[1].substring(3));

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}