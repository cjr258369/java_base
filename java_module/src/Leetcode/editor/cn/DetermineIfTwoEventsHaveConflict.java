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
        System.out.println("运行结果：" + solution.haveConflict2(new String[]{"01:15","02:00"}, new String[]{"02:00","03:00"}));
        System.out.println("运行结果：" + solution.haveConflict2(new String[]{"01:00","02:00"}, new String[]{"01:20","03:00"}));
        System.out.println("运行结果：" + solution.haveConflict2(new String[]{"10:00","11:00"}, new String[]{"14:00","15:00"}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(1)，仅使用常数时间。
        // 空间复杂度：/O(1)，仅使用常数空间。
        
        
        public boolean haveConflict(String[] event1, String[] event2) {
            //even1 的开始时间小于 even2 的结束时间，且 even2 的开始时间 小于 even1 的结束时间，则为有相交
            return 60 * Integer.parseInt(event1[0].substring(0, 2)) + Integer.parseInt(event1[0].substring(3)) <= 60 * Integer.parseInt(event2[1].substring(0, 2)) + Integer.parseInt(event2[1].substring(3)) 
                    && 60 * Integer.parseInt(event2[0].substring(0, 2)) + Integer.parseInt(event2[0].substring(3)) <= 60 * Integer.parseInt(event1[1].substring(0, 2)) + Integer.parseInt(event1[1].substring(3));
        }

        //当两个事件不存在冲突的充要条件是一个事件的结束时间早于另一个事件的开始时间，可以直接用字符串的比较判断两个事件是否存在冲突。
        public boolean haveConflict2(String[] event1, String[] event2) {
            return !(event1[1].compareTo(event2[0]) < 0 || event2[1].compareTo(event1[0]) < 0);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}