package Leetcode.editor.cn;

/**
 * 2023-03-06 09:39:49
 * [1599] - 经营摩天轮的最大利润
 * MaximumProfitOfOperatingACentennialWheel.md
 */
 
//你正在经营一座摩天轮，该摩天轮共有 4 个座舱 ，每个座舱 最多可以容纳 4 位游客 。你可以 逆时针 轮转座舱，但每次轮转都需要支付一定的运行成本runningCost 。摩天轮每次轮转都恰好转动 1 / 4 周。 
// 给你一个长度为 n 的数组 customers ， customers[i] 是在第 i 次轮转（下标从 0 开始）之前到达的新游客的数量。这也意味着你必须在新游客到来前轮转 i 次。每位游客在登上离地面最近的座舱前都会支付登舱成本 boardingCost ，一旦该座舱再次抵达地面，他们就会离开座舱结束游玩。 
// 你可以随时停下摩天轮，即便是 在服务所有游客之前 。如果你决定停止运营摩天轮，为了保证所有游客安全着陆，将免费进行所有后续轮转 。注意，如果有超过 4 位游客在等摩天轮，那么只有 4 位游客可以登上摩天轮，其余的需要等待 下一次轮转 。 
//
// 返回最大化利润所需执行的 最小轮转次数 。 如果不存在利润为正的方案，则返回 -1 。 
//
// 示例 1： 
//输入：customers = [8,3], boardingCost = 5, runningCost = 6
//输出：3
//解释：座舱上标注的数字是该座舱的当前游客数。
//1. 8 位游客抵达，4 位登舱，4 位等待下一舱，摩天轮轮转。当前利润为 4 * $5 - 1 * $6 = $14 。
//2. 3 位游客抵达，4 位在等待的游客登舱，其他 3 位等待，摩天轮轮转。当前利润为 8 * $5 - 2 * $6 = $28 。
//3. 最后 3 位游客登舱，摩天轮轮转。当前利润为 11 * $5 - 3 * $6 = $37 。
//轮转 3 次得到最大利润，最大利润为 $37 。 
//
// 示例 2： 
//输入：customers = [10,9,6], boardingCost = 6, runningCost = 4
//输出：7
//解释：
//1. 10 位游客抵达，4 位登舱，6 位等待下一舱，摩天轮轮转。当前利润为 4 * $6 - 1 * $4 = $20 。
//2. 9 位游客抵达，4 位登舱，11 位等待（2 位是先前就在等待的，9 位新加入等待的），摩天轮轮转。当前利润为 8 * $6 - 2 * $4 = $40 。
//3. 最后 6 位游客抵达，4 位登舱，13 位等待，摩天轮轮转。当前利润为 12 * $6 - 3 * $4 = $60 。
//4. 4 位登舱，9 位等待，摩天轮轮转。当前利润为 * $6 - 4 * $4 = $80 。
//5. 4 位登舱，5 位等待，摩天轮轮转。当前利润为 20 * $6 - 5 * $4 = $100 。
//6. 4 位登舱，1 位等待，摩天轮轮转。当前利润为 24 * $6 - 6 * $4 = $120 。
//7. 1 位登舱，摩天轮轮转。当前利润为 25 * $6 - 7 * $4 = $122 。
//轮转 7 次得到最大利润，最大利润为$122 。
//
// 示例 3： 
//输入：customers = [3,4,0,5,1], boardingCost = 1, runningCost = 92
//输出：-1
//解释：
//1. 3 位游客抵达，3 位登舱，0 位等待，摩天轮轮转。当前利润为 3 * $1 - 1 * $92 = -$89 。
//2. 4 位游客抵达，4 位登舱，0 位等待，摩天轮轮转。当前利润为 is 7 * $1 - 2 * $92 = -$177 。
//3. 0 位游客抵达，0 位登舱，0 位等待，摩天轮轮转。当前利润为 7 * $1 - 3 * $92 = -$269 。
//4. 5 位游客抵达，4 位登舱，1 位等待，摩天轮轮转。当前利润为 12 * $1 - 4 * $92 = -$356 。
//5. 1 位游客抵达，2 位登舱，0 位等待，摩天轮轮转。当前利润为 13 * $1 - 5 * $92 = -$447 。
//利润永不为正，所以返回 -1 。
//
// 提示： 
// n == customers.length 
// 1 <= n <= 10⁵ 
// 0 <= customers[i] <= 50 
// 1 <= boardingCost, runningCost <= 100 

public class MaximumProfitOfOperatingACentennialWheel{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumProfitOfOperatingACentennialWheel().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.minOperationsMaxProfit(new int[]{8,3}, 5, 6));
        System.out.println("预期结果：7 , 运行结果：" + solution.minOperationsMaxProfit(new int[]{10,9,6}, 6, 4));
        System.out.println("预期结果：-1 , 运行结果：" + solution.minOperationsMaxProfit(new int[]{3,4,0,5,1}, 1, 92));
        System.out.println("预期结果：993 , 运行结果：" + solution.minOperationsMaxProfit(new int[]{0,43,37,9,23,35,18,7,45,3,8,24,1,6,37,2,38,15,1,14,39,27,4,25,27,33,43,8,44,30,38,40,20,5,17,27,43,11,6,2,30,49,30,25,32,3,18,23,45,43,30,14,41,17,42,42,44,38,18,26,32,48,37,5,37,21,2,9,48,48,40,45,25,30,49,41,4,48,40,29,23,17,7,5,44,23,43,9,35,26,44,3,26,16,31,11,9,4,28,49,43,39,9,39,37,7,6,7,16,1,30,2,4,43,23,16,39,5,30,23,39,29,31,26,35,15,5,11,45,44,45,43,4,24,40,7,36,10,10,18,6,20,13,11,20,3,32,49,34,41,13,11,3,13,0,13,44,48,43,23,12,23,2}, 43, 54));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析【141 / 141，12 ms，50.2 MB】
        //时间：O（n + s/4） s为 customers 的元素和，即游客总数
        //空间：O（1），仅需常量空间
        public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
            if(4 * boardingCost - runningCost < 0) return -1;   //满载情况下，每轮都不能赚钱，直接返回 -1
            int ans = -1;
            for(int i = 0, waitingPeople = 0, currentPeople = 0, maxProfit = 0, currentProfit = 0; i < customers.length || waitingPeople > 0; i++){
                waitingPeople += (i < customers.length) ? customers[i] : 0;   //如果i走到 customers 结尾，但还有人在等待，则继续循环
                int onBoard = Math.min(waitingPeople, 4);   //本轮可以上摩天轮的人数，最大为4
                waitingPeople -= onBoard;   //等待人数减少
                currentPeople += onBoard;   //上了摩天轮的人数增加
                currentProfit = currentPeople * boardingCost - runningCost * (i + 1); //营运到当前轮次时的收益【不能从0轮开始，所以 i + 1】
                if(currentProfit > maxProfit){  //如果收益最大，则更新轮次为返回值【不能从0轮开始，所以 i + 1】
                    maxProfit = currentProfit;
                    ans = i + 1;                    
                }                
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}