package Leetcode.editor.cn;

/**
 * 2022-12-01 10:03:30
 * [1779] - 找到最近的有相同 X 或 Y 坐标的点
 * FindNearestPointThatHasTheSameXOrYCoordinate.md
 */
public class FindNearestPointThatHasTheSameXOrYCoordinate{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FindNearestPointThatHasTheSameXOrYCoordinate().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.nearestValidPoint(3, 4, new int[][]{{1,2},{3,1},{2,4},{2,3},{4,4}}));
        System.out.println("预期结果：0 , 运行结果：" + solution.nearestValidPoint(3, 4, new int[][]{{3,4}}));
        System.out.println("预期结果：-1 , 运行结果：" + solution.nearestValidPoint(3, 4, new int[][]{{2,3}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int nearestValidPoint(int x, int y, int[][] points) {
            int bestId = -1;
            for(int i = 0, min = Integer.MAX_VALUE; i < points.length; i++){
                if(points[i][0] == x || points[i][1] == y){
                    int dist = Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y);
                    if(dist < min){
                        min = dist;
                        bestId = i;
                    }
                }
            }
            return bestId;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}