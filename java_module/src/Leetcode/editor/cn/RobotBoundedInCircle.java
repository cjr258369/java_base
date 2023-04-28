package Leetcode.editor.cn;

/**
 * 2023-04-11 09:15:43
 * [1041] - 困于环中的机器人
 * RobotBoundedInCircle.md
 */
 
//在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。注意: 
// 北方向 是y轴的正方向。 
// 南方向 是y轴的负方向。 
// 东方向 是x轴的正方向。 
// 西方向 是x轴的负方向。 
//
// 机器人可以接受下列三条指令之一： 
// "G"：直走 1 个单位 
// "L"：左转 90 度 
// "R"：右转 90 度 
//
// 机器人按顺序执行指令 instructions，并一直重复它们。只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。 
//
// 示例 1： 
//输入：instructions = "GGLLGG"
//输出：true
//解释：机器人最初在(0,0)处，面向北方。
//“G”:移动一步。位置:(0,1)方向:北。
//“G”:移动一步。位置:(0,2).方向:北。
//“L”:逆时针旋转90度。位置:(0,2).方向:西。
//“L”:逆时针旋转90度。位置:(0,2)方向:南。
//“G”:移动一步。位置:(0,1)方向:南。
//“G”:移动一步。位置:(0,0)方向:南。
//重复指令，机器人进入循环:(0,0)——>(0,1)——>(0,2)——>(0,1)——>(0,0)。在此基础上，我们返回true。
//
// 示例 2： 
//输入：instructions = "GG"
//输出：false
//解释：机器人最初在(0,0)处，面向北方。
//“G”:移动一步。位置:(0,1)方向:北。
//“G”:移动一步。位置:(0,2).方向:北。
//重复这些指示，继续朝北前进，不会进入循环。在此基础上，返回false。
//
// 示例 3： 
//输入：instructions = "GL"
//输出：true
//解释：机器人最初在(0,0)处，面向北方。
//“G”:移动一步。位置:(0,1)方向:北。
//“L”:逆时针旋转90度。位置:(0,1).方向:西。
//“G”:移动一步。位置:(- 1,1)方向:西。
//“L”:逆时针旋转90度。位置:(- 1,1)方向:南。
//“G”:移动一步。位置:(- 1,0)方向:南。
//“L”:逆时针旋转90度。位置:(- 1,0)方向:东方。
//“G”:移动一步。位置:(0,0)方向:东方。
//“L”:逆时针旋转90度。位置:(0,0)方向:北。
//重复指令，机器人进入循环:(0,0)——>(0,1)——>(- 1,1)——>(- 1,0)——>(0,0)。
//在此基础上，我们返回true。 
//
// 提示： 
// 1 <= instructions.length <= 100 
// instructions[i] 仅包含 'G', 'L', 'R' 

public class RobotBoundedInCircle{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new RobotBoundedInCircle().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.isRobotBounded("GGLLGG"));
        System.out.println("预期结果：false , 运行结果：" + solution.isRobotBounded("GG"));
        System.out.println("预期结果：true , 运行结果：" + solution.isRobotBounded("GL"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] direc = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        //机器人想要摆脱循环，在一串指令之后的状态，必须是不位于原点且方向朝北
        // 复杂度分析
        // 时间复杂度：O(n)，其中 n 是 instructions 的长度，需要遍历 nstructions 一次。
        // 空间复杂度：O(1)，只用到常数空间。
        public boolean isRobotBounded(String instructions) {
            int x = 0, y = 0, direcIdx = 0;
            for(char c : instructions.toCharArray()){
                if(c == 'G'){
                    x += direc[direcIdx][0];
                    y += direc[direcIdx][1];
                }else if(c == 'L'){
                    direcIdx += 3;
                    direcIdx %= 4;
                }else{
                    direcIdx++;
                    direcIdx %= 4;                    
                }
            }
            return direcIdx != 0 || (x == 0 && y == 0);    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}