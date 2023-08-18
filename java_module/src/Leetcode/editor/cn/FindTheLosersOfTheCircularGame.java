package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * 2023-08-16 16:46:21
 * [2682] - 找出转圈游戏输家
 * FindTheLosersOfTheCircularGame.md
 */
 
//n 个朋友在玩游戏。这些朋友坐成一个圈，按 顺时针方向 从 1 到 n 编号。从第 i 个朋友的位置开始顺时针移动 1 步会到达第 (i + 1) 个朋友的位置（1 <= i < n），而从第 n 个朋友的位置开始顺时针移动 1 步会回到第 1 个朋友的位置。 
//
// 游戏规则如下： 
// 第 1 个朋友接球。 
// 接着，第 1 个朋友将球传给距离他顺时针方向 k 步的朋友。 
// 然后，接球的朋友应该把球传给距离他顺时针方向 2 * k 步的朋友。 
// 接着，接球的朋友应该把球传给距离他顺时针方向 3 * k 步的朋友，以此类推。 
//
// 换句话说，在第 i 轮中持有球的那位朋友需要将球传递给距离他顺时针方向 i * k 步的朋友。 
// 当某个朋友第 2 次接到球时，游戏结束。 
//
// 在整场游戏中没有接到过球的朋友是 输家 。 
//
// 给你参与游戏的朋友数量 n 和一个整数 k ，请按升序排列返回包含所有输家编号的数组 answer 作为答案。 
//
// 示例 1： 
//输入：n = 5, k = 2
//输出：[4,5]
//解释：以下为游戏进行情况：
//1）第 1 个朋友接球，第 1 个朋友将球传给距离他顺时针方向 2 步的玩家 —— 第 3 个朋友。
//2）第 3 个朋友将球传给距离他顺时针方向 4 步的玩家 —— 第 2 个朋友。
//3）第 2 个朋友将球传给距离他顺时针方向 6 步的玩家 —— 第 3 个朋友。
//4）第 3 个朋友接到两次球，游戏结束。
//
// 示例 2： 
//输入：n = 4, k = 4
//输出：[2,3,4]
//解释：以下为游戏进行情况：
//1）第 1 个朋友接球，第 1 个朋友将球传给距离他顺时针方向 4 步的玩家 —— 第 1 个朋友。
//2）第 1 个朋友接到两次球，游戏结束。 
//
// 提示： 
// 1 <= k <= n <= 50 

public class FindTheLosersOfTheCircularGame{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
 
        Solution solution = new FindTheLosersOfTheCircularGame().new Solution();
        System.out.println("预期结果：[4,5] , 运行结果：" + Arrays.toString(solution.circularGameLosers2(5, 2)));
        System.out.println("预期结果：[2,3,4] , 运行结果：" + Arrays.toString(solution.circularGameLosers2(4, 4)));
        System.out.println("预期结果：[] , 运行结果：" + Arrays.toString(solution.circularGameLosers2(1, 1)));
        System.out.println("预期结果：[2] , 运行结果：" + Arrays.toString(solution.circularGameLosers2(3, 2)));
        System.out.println("预期结果：[2,3] , 运行结果：" + Arrays.toString(solution.circularGameLosers2(5, 3)));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //直接模拟
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 为给定的数字。一共有 n 个位置，由于每个位置最多只会被访问一次，因此时间复杂度为 O(n)。
        // 空间复杂度：O(n)，其中 n 为给定的数字。一共有 n 个位置，需要记录每个位置是否被访问过，因此空间复杂度为 O(n)。
        
        //下面方法二会有去掉 if 的写法
        public int[] circularGameLosers(int n, int k) {
            HashSet<Integer> set = new HashSet<>();
            for(int i = 0, j = 0; set.add(j); i += k, j = (j + i) % n){
            }
            int ans[] = new int[n - set.size()];
            for(int i = 0, idx = 0; i < n; i++){
                if(!set.contains(i)){
                    ans[idx++] = i + 1;
                }
            }
            return ans;            
        }
        
        //不写 if 的方法，同时为了美观用了 stream ，虽然效率并不高
        public int[] circularGameLosers2(int n, int k) {
            TreeSet<Integer> set = new TreeSet<>();
            for(int i = 1; i <= n; i++){
                set.add(i);
            }
            for(int i = 0, j = 0; set.remove(i + 1); j += k, i = (i + j) % n){
            }
            return set.stream().mapToInt(v -> v).toArray();            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}