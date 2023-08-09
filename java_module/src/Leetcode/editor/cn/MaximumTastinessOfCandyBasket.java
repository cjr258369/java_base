package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-06-01 09:19:37
 * [2517] - 礼盒的最大甜蜜度
 * MaximumTastinessOfCandyBasket.md
 */
 
//给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。 
// 商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。返回礼盒的 最大 甜蜜度。 
//
// 示例 1： 
//输入：price = [13,5,1,8,21,2], k = 3
//输出：8
//解释：选出价格分别为 [13,5,21] 的三类糖果。
//礼盒的甜蜜度为 min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8 。
//可以证明能够取得的最大甜蜜度就是 8 。
//
// 示例 2： 
//输入：price = [1,3,1], k = 2
//输出：2
//解释：选出价格分别为 [1,3] 的两类糖果。 
//礼盒的甜蜜度为 min(|1 - 3|) = min(2) = 2 。
//可以证明能够取得的最大甜蜜度就是 2 。
//
// 示例 3： 
//输入：price = [7,7,7,7], k = 2
//输出：0
//解释：从现有的糖果中任选两类糖果，甜蜜度都会是 0 。
//
// 提示： 
// 1 <= price.length <= 10⁵ 
// 1 <= price[i] <= 10⁹ 
// 2 <= k <= price.length 

public class MaximumTastinessOfCandyBasket{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumTastinessOfCandyBasket().new Solution();
        System.out.println("预期结果：8 , 运行结果：" + solution.maximumTastiness(new int[]{13,5,1,8,21,2}, 3));
        System.out.println("预期结果：2 , 运行结果：" + solution.maximumTastiness(new int[]{1,3,1}, 2));
        System.out.println("预期结果：0 , 运行结果：" + solution.maximumTastiness(new int[]{7,7,7,7}, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //贪心 + 二分
        //要求甜蜜度的值最大，可以采用二分查找的方法。先假设一个甜蜜度 mid，然后尝试在排好序的 price 中找出 k 种糖果，并且任意两种相邻的价格差绝对值都大于 mid。
        // 如果可以找到这样的 k 种糖果，则说明可能存在更大的甜蜜度，需要修改二分查找的下边界；如果找不到这样的 k 种糖果，则说明最大的甜蜜度只可能更小，需要修改二分查找的上边界。
        
        public int maximumTastiness(int[] price, int k) {
            Arrays.sort(price);
            //1 2 5 8 13 21
            int left = 0, right = price[price.length - 1] - price[0];
            while(left < right){
                //int mid = left + ((right - left) >> 1);
                int mid = (left + right + 1) / 2;
                //System.out.println("left = " + left + " , right = " + right + " , mid = " + mid);
                if(check(price, k, mid)){
                    left = mid;                    
                }else{
                    right = mid - 1;                    
                }
            }
            return left;            
        }
        
        private boolean check(int[] price, int k, int tastiness){
            int cnt = 0, prev = Integer.MIN_VALUE / 2;
            for(int p : price){
                if(p - prev >= tastiness){
                    cnt++;
                    prev = p;
                }
            }
            return cnt >= k;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}