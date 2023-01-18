package Leetcode.editor.cn;

/**
 * 2023-01-05 09:07:47
 * [1803] - 统计异或值在范围内的数对有多少
 * CountPairsWithXorInARange.md
 */
 
//给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，请返回 漂亮数对 的数目。 
// 漂亮数对 是一个形如 (i, j) 的数对，其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。 
//
// 示例 1： 
// 输入：nums = [1,4,2,7], low = 2, high = 6
//输出：6
//解释：所有漂亮数对 (i, j) 列出如下：
//    - (0, 1): nums[0] XOR nums[1] = 5 
//    - (0, 2): nums[0] XOR nums[2] = 3
//    - (0, 3): nums[0] XOR nums[3] = 6
//    - (1, 2): nums[1] XOR nums[2] = 6
//    - (1, 3): nums[1] XOR nums[3] = 3
//    - (2, 3): nums[2] XOR nums[3] = 5
//
// 示例 2： 
// 输入：nums = [9,8,4,2,1], low = 5, high = 14
//输出：8
//解释：所有漂亮数对 (i, j) 列出如下：
//    - (0, 2): nums[0] XOR nums[2] = 13
//   - (0, 3): nums[0] XOR nums[3] = 11
//   - (0, 4): nums[0] XOR nums[4] = 8
//   - (1, 2): nums[1] XOR nums[2] = 12
//   - (1, 3): nums[1] XOR nums[3] = 10
//   - (1, 4): nums[1] XOR nums[4] = 9
//   - (2, 3): nums[2] XOR nums[3] = 6
//   - (2, 4): nums[2] XOR nums[4] = 5 
//
// 提示： 
// 1 <= nums.length <= 2 * 10⁴ 
// 1 <= nums[i] <= 2 * 10⁴ 
// 1 <= low <= high <= 2 * 10⁴

public class CountPairsWithXorInARange{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountPairsWithXorInARange().new Solution();
        System.out.println("预期结果：6 , 运行结果：" + solution.countPairs2(new int[]{1,4,2,7}, 2, 6));
        System.out.println("预期结果：8 , 运行结果：" + solution.countPairs2(new int[]{9,8,4,2,1}, 5, 14));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 最高位的二进制位编号为 14
        private static final int HIGH_BIT = 14;
        
        //暴力卡 48/61
        public int countPairs(int[] nums, int low, int high) {
            int ans = 0;
            for(int i = 0; i < nums.length; i++){
                for(int j = i + 1; j < nums.length; j++){
                    int t = nums[i] ^ nums[j];
                    ans += (t >= low && t <= high) ? 1 : 0;
                }
            }
            return ans;
        }
        
        public int countPairs2(int[] nums, int low, int high) {
            int ans = 0;
            Tire root = new Tire();
            for(int n : nums){
                ans += root.query(n, high) - root.query(n, low - 1);
                root.insert(n);
            }
            return ans;
        }
        
        class Tire{
            private Tire[] children = new Tire[2];
            private int cnt;
            
            public Tire(){
                cnt = 0;
            }
            
            public void insert(int n){
                Tire root = this;
                for(int i = HIGH_BIT; i >= 0; i--){
                    int bit = (n >> i) & 1;
                    if(null == root.children[bit]){
                        root.children[bit] = new Tire();                        
                    }
                    root = root.children[bit];
                    root.cnt++;
                }
            }
            
            public int query(int n, int max){
                Tire curr = this;
                int ans = 0;
                for(int i = HIGH_BIT; i >= 0; i--){

                    int n_bit = (n >> i) & 1, max_bit = (max >> i) & 1;
                    //如果 a，b 两个数，（a 即 当前传入的 n），且 max 的当前位为 1
                    // 1. 如果 a 当前位为 0， b 当前位为 1 异或结果为1，那么当前位与max相等，判断不了大小，需要继续往下判断
                    // 2. 如果 a 当前位为 0， b 当前位为 0 异或结果为0，那么当前位小于与max的当前位，后续所有数字均小于，所以ans需要累加
                    // 3. 如果 a 当前位为 1， b 当前位为 0 异或结果为1，那么当前位与max相等，判断不了大小，需要继续往下判断
                    // 4. 如果 a 当前位为 1， b 当前位为 1 异或结果为0，那么当前位小于与max的当前位，后续所有数字均小于，所以ans需要累加
                    //基于上述4中情况，a是传入的，max 是传入的，现在 b 是未知的，那么判断需要稍微绕一下：【需要知道：n ^ b = max, n ^ max = b】
                    //max(max_bit)  a(n_bit)    b(需要执行的操作)
                    //  0             0         b只能为0，因为 0 ^ 0 = 0
                    //  0             1         b只能为1，因为 1 ^ 1 = 0
                    //  1             0         如果b为0，则 0 ^ 0 = 0，所有子节点均小于 max，所以累计ans，且继续向 1 的子节点爬
                    //  1             1         如果b为1，则 1 ^ 1 = 0，所有子节点均小于 max，所以累计ans，且继续向 0 的子节点爬
                    if(max_bit == 1){
                        if(curr.children[n_bit] != null){//无论 b 当前位 为0、1，都需要累计 ans，因为必小于max
                            ans += curr.children[n_bit].cnt;
                        }
                        //向另外一个方向看，如果为空，则剪枝返回：0 ^ 0 = 0, 0 ^ 1 = 1
                        if(curr.children[n_bit ^ 1] == null){
                            return ans;
                        }
                        //另外一个方向不为空，则继续爬
                        curr = curr.children[n_bit ^ 1];                        
                    }else{
                        //只能向同一个方向爬
                        if(null == curr.children[n_bit]){
                            return ans;
                        }
                        curr = curr.children[n_bit];  
                    }
                }
                //如果在过程中，出现某个子节点不存在使得过程无法继续，我们需要立刻返回答案。否则在最后，我们遍历完所有的 15 个二进制位后，到达的最后一个节点中记录的数字是使得 ai⊕aj=x 条件成立的 aj 的个数，也将其累加到答案中。
                //至此，我们求出来所有使得 ai⊕aj≤x 条件成立的 aj 的个数。
                ans += curr.cnt;
                return ans;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}