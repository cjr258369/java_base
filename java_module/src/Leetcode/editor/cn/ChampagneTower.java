package Leetcode.editor.cn;

/**
 * 2022-11-20 15:49:10
 * [799] - 香槟塔
 * ChampagneTower.md
 */
public class ChampagneTower{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ChampagneTower().new Solution();
        System.out.println("预期结果：0.00000 , 运行结果：" + solution.champagneTower(1,1,1));
        System.out.println("预期结果：0.50000 , 运行结果：" + solution.champagneTower(2,1,1));
        System.out.println("预期结果：1.00000 , 运行结果：" + solution.champagneTower(100000009,33,17));
        System.out.println("预期结果：0.18750 , 运行结果：" + solution.champagneTower(25,6,1));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution { 
        //直接暴力模拟：每一层的首尾两个杯子只接收到斜上方杯子溢出的一半，其余的杯子是接收到头顶两个杯子各自溢出的一半之和
        public double champagneTower(int poured, int query_row, int query_glass) {
            double currRow[] = {poured};
            for(int i = 1; i <= query_row; i++){
                double nextRow[] = new double[i + 1];
                for(int j = 0; j < i; j++){
                    double volume = currRow[j];
                    if(volume > 1){
                        nextRow[j] += (volume - 1) / 2;
                        nextRow[j + 1] += (volume - 1) / 2;
                    }
                }
                currRow = nextRow;
            }
            return Math.min(1, currRow[query_glass]);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}