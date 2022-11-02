package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-11-02 14:53:49
 * [1620] - 网络信号最好的坐标
 * CoordinateWithMaximumNetworkQuality.md
 */
public class CoordinateWithMaximumNetworkQuality{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CoordinateWithMaximumNetworkQuality().new Solution();
        
        System.out.println("预期结果：[2,1] , 运行结果：" + Arrays.toString(solution.bestCoordinate(new int[][]{{1,2,5},{2,1,7},{3,1,9}}, 2)));
        System.out.println("预期结果：[23,11] , 运行结果：" + Arrays.toString(solution.bestCoordinate(new int[][]{{23,11,21}}, 9)));
        System.out.println("预期结果：[1,2] , 运行结果：" + Arrays.toString(solution.bestCoordinate(new int[][]{{1,2,13},{2,1,7},{0,1,9}}, 2)));
        System.out.println("预期结果：[0,0] , 运行结果：" + Arrays.toString(solution.bestCoordinate(new int[][]{{42,0,0}}, 7)));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int doubleRadius = radius * radius, max_x = 0, max_y = 0, maxQuality = -1;

        //所有坐标点的最大范围为 0 - 50
        for(int i = 0; i < 52; i++){
            for(int j = 0; j < 52; j++){
                int quality = 0;
                //遍历所有塔与该点的距离，如果在 radius 内，则累计该点的信号值
                for(int[] tower : towers){
                    int d = (tower[0] - i) * (tower[0] - i) + (tower[1] - j) * (tower[1] - j);
                    if(d <= doubleRadius){
                        quality += (tower[2] / (1 + Math.sqrt(d)));
                    }
                }
                
                //判断当前点的信号量 是否大于最大
                if(quality > maxQuality){
                    maxQuality = quality;
                    max_x = i;
                    max_y = j;
                }
            }
        }
        
        return new int[]{max_x, max_y};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}