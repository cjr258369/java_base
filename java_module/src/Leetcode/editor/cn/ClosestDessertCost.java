package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-12-04 23:31:13
 * [1774] - 最接近目标价格的甜点成本
 * ClosestDessertCost.md
 */
public class ClosestDessertCost{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ClosestDessertCost().new Solution();
        System.out.println("预期结果：10 , 运行结果：" + solution.closestCost2(new int[]{1,7}, new int[]{3,4}, 10));
        System.out.println("预期结果：17 , 运行结果：" + solution.closestCost2(new int[]{2,3}, new int[]{4,5,100}, 18));
        System.out.println("预期结果：10 , 运行结果：" + solution.closestCost2(new int[]{10}, new int[]{1}, 1));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //回溯，时间复杂度：n * 3 ^ m，因为每个配料都有3种状态【选1个，选2个，不选】
        int res;
        public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
            // 冰淇淋基料的最小值min
            int min = Arrays.stream(baseCosts).min().getAsInt();
            // 如果 min 都大于 target 了，那其他任何方案成本只会比min更大，与target的插值更大，此时直接返回min
            if(min >= target){
                return min;
            }
            // 将 res 初始化 为 min，
            res = min;
            // 对每一种冰淇淋基料，进行配料的回溯
            for(int baseCost : baseCosts){
                // idx 代表待选择配料索引
                backTrack(toppingCosts, 0, baseCost, target);
            }
            // 返回最优方案成本
            return res;
        }

        /**
         * 回溯 costs数组元素选择，每个元素可以不选，可以选1份，可以选2份
         * @param costs 数组
         * @param idx   当前选择哪个元素
         * @param curCost  当前成本值 （递增）
         * @param target   目标成本值
         */
        private void backTrack(int[] costs, int idx, int curCost, int target) {
            // 当前方案成本价已经大于等于我们已有最优方案的差值，后续选择只会使得成本更大，与target的差值更大
            // 我们可以停止继续往下搜索，及时回溯。
            if(curCost - target >= Math.abs(res - target)){
                return;
            }
            // 当前方案成本价与target的差值 小于等于 我们已有最优方案的与target的差值
            if(Math.abs(curCost - target) <= Math.abs(res - target)){
                // 如果当前方案成本更接近target，那么更新最优方案为当前方案
                if(Math.abs(curCost - target) < Math.abs(res - target)){
                    res = curCost;
                }else{
                    // 如果当前方案成本与已有方案同样接近target，那么选择其中更小的成本值
                    res = Math.min(res,curCost);
                }
            }
            // 上面if不能直接return，因为可能继续curCost<target，再选择基料后，更加接近target
            // 如果所有元素已选择完毕，结束
            if(idx >= costs.length){
                return;
            }
            // 对于当前元素（基料），三种选择
            backTrack(costs, idx + 1, curCost, target); //不选
            backTrack(costs, idx + 1, curCost + costs[idx], target);    //只加1份
            backTrack(costs, idx + 1, curCost + 2 * costs[idx], target);    //加2份
        }

        /**
         * 方法二：动态规划
         *
         * 冰淇淋基料最小值为min
         * 从方法一中已经知道，对于 大于 upper=2*target-min的方案，其与target的差值一定大于min与target的差值，可以直接废弃
         *
         * 那么我们可以通过 动态规划来判断是否存在成本在 [min, upper-1] 间的方案
         * 如果存在，从中选择 与target 差值最小的方案即可。
         *
         * base：
         *      因为基料必须选，所以初始时遍历 baseCosts，如果 baseCosts[i] < upper，让 dp[baseCosts[i]] = true
         * 接下来是在基料的基础上选择配料
         * 这里参考0-1背包的写法，假如辅料i的代价为x，在选择辅料i之前dp数组[a1,a2,a3]位置为true
         * 那么选择完辅料i后，dp[a1,a2,a3,a1+x,a2+x,a3+x,a1+2x,a2+2x,a3+2x]为true
         *
         * 也就是说对于辅料i的代价x
         *      遍历dp数组，对于 dp[i] = true，更新 dp[i + x] 为true， dp[i + 2x] 为 true
         * 对下一个辅料进行同样过程
         *
         * 【细节一】：
         *      dp数组必须倒序遍历
         *          对于每个辅料，假如顺序遍历dp
         *          dp[0]=true，更新dp[0+2x]=true， dp[0+x]=true
         *          那么当遍历到dp[x]时，又会更新dp[x+2x]为true，dp[x+x]=true，，？？？这相当于使用了3份辅料i，肯定不对，
         *          因此每次都需要倒序遍历dp
         * 【细节二】
         *      必须在 dp[i] 为 true 的情况下，更新 dp[i + x] 和 dp[i + 2x] 为true
         *      因为 只有方案i存在，那么方案i+x才会存在
         * 【细节三】
         *      因为我们只需要考虑成本在 [min, upper-1] 间的方案，因此对于此范围外的方案一律不用考虑
         * @param baseCosts
         * @param toppingCosts
         * @param target
         * @return
         */
        public int closestCost2(int[] baseCosts, int[] toppingCosts, int target) {
            // 冰淇淋基料的最小值min
            int min = Arrays.stream(baseCosts).min().getAsInt();
            // 如果 min 都大于 target 了，那其他任何方案成本只会比min更大，与target的插值更大，此时直接返回min
            if(min >= target){
                return min;
            }
            // 只需要考虑成本在 [min, upper-1] 间的方案是否存在
            int upper = 2 * target - min;
            boolean[] dp = new boolean[upper];
            // base，基料必选
            for(int baseCost : baseCosts){
                // 不考虑区间外的方案
                if(baseCost < upper){
                    dp[baseCost] = true;
                }  
            }
            
            // dp迭代，在基料的基础上选择辅料
            for(int toppingCost : toppingCosts){
                // 倒序遍历dp
                for(int j = upper - 1; j >= 0; j--){
                    // 每种辅料可以1份或两份，更新对应的dp为true，区间外不予考虑
                    if(dp[j] && (j + toppingCost < upper)){
                        dp[j + toppingCost] = true;
                    }
                    if(dp[j] && j + 2 * toppingCost< upper){
                        dp[j + 2 * toppingCost] = true;
                    }
                }
            }

            // 在 [min, upper-1]所有存在的方案种找出与target最接近的方案
            int ans = min;
            for(int i = min + 1; i < upper; i++){
                if(dp[i]){
                    // 更接近
                    if(Math.abs(i - target) < Math.abs(ans - target)){
                        ans = i;
                    }else if(Math.abs(i - target) == Math.abs(ans - target)){
                        // 同样接近，选择更小成本
                        ans = Math.min(i, ans);
                    }
                }
            }
            // 返回最优方案成本
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}