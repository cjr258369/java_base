package Leetcode.editor.cn;

/**
 * 2023-04-17 09:29:45
 * [2409] - 统计共同度过的日子数
 * CountDaysSpentTogether.md
 */
 
//Alice 和 Bob 计划分别去罗马开会。
// 给你四个字符串 arriveAlice ，leaveAlice ，arriveBob 和 leaveBob 。Alice 会在日期arriveAlice 到 leaveAlice 之间在城市里（日期为闭区间），而 Bob 在日期 arriveBob 到 leaveBob 之间在城市里（日期为闭区间）。每个字符串
//都包含 5 个字符，格式为 "MM-DD" ，对应着一个日期的月和日。
// 请你返回 Alice和 Bob 同时在罗马的天数。 
//
// 你可以假设所有日期都在 同一个 自然年，而且 不是 闰年。每个月份的天数分别为：[31, 28, 31, 30, 31, 30, 31, 31, 30,31, 30, 31] 。 
//
// 示例 1： 
//输入：arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob= "08-19"
//输出：3
//解释：Alice 从 8 月 15 号到 8 月 18 号在罗马。Bob 从 8 月 16 号到 8 月 19 号在罗马，他们同时在罗马的日期为 8 月 16、17 和 18 号。所以答案为 3 。
//
// 示例 2： 
//输入：arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob= "12-31"
//输出：0
//解释：Alice 和 Bob 没有同时在罗马的日子，所以我们返回 0 。
//
// 提示： 
// 所有日期的格式均为 "MM-DD" 。 
// Alice 和 Bob 的到达日期都 早于或等于 他们的离开日期。 
// 题目测试用例所给出的日期均为 非闰年 的有效日期。 

public class CountDaysSpentTogether{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountDaysSpentTogether().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.countDaysTogether("08-15","08-18","08-16","08-19"));
        System.out.println("预期结果：0 , 运行结果：" + solution.countDaysTogether("10-01","10-31","11-01","12-31"));
        System.out.println("预期结果：1 , 运行结果：" + solution.countDaysTogether("08-02","08-02","08-02","08-02"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //int[] days     = new int[]{0, 31, 28, 31, 30,  31,   30,  31,  31,  30,  31,  30, 31};
        int[] daysPreSum = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
        
        //直接前缀和：分别计算出每个日子是一年中的第几天后求差
        
        //我们可以设计一个函数 calculateDayOfYear 来计算输入中的每个日子在一年中是第几天。
        // 计算输入中的每个日子在一年中是第几天时，可以利用前缀和数组来降低每次计算的复杂度。
        // 知道每个日子是一年中的第几天后，可以先通过比较算出两人到达日子的最大值，离开日子的最小值，然后利用减法计算重合的日子。
        
        // 复杂度分析
        // 时间复杂度：O(1)，最耗时的操作是计算前缀和，因为一年中只有 12 个月，因此时间复杂度是常数。
        // 空间复杂度：O(1)，最消耗空间的是存储每个月日子的数组和它的前缀和数组，因为一年中只有 12 个月，因此空间复杂度是常数。
        public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
            int a = daysPreSum[Integer.parseInt(arriveAlice.substring(0,2)) - 1] + Integer.parseInt(arriveAlice.substring(3));
            int b = daysPreSum[Integer.parseInt(leaveAlice.substring(0,2)) - 1] + Integer.parseInt(leaveAlice.substring(3));
            int c = daysPreSum[Integer.parseInt(arriveBob.substring(0,2)) - 1] + Integer.parseInt(arriveBob.substring(3));
            int d = daysPreSum[Integer.parseInt(leaveBob.substring(0,2)) - 1] + Integer.parseInt(leaveBob.substring(3));
            return Math.max(0, Math.min(b, d) - Math.max(a, c) + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}