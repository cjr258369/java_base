package DSA;

import java.util.Arrays;

/**
 * 数位dp相关
 * @date 2023/3/20
 * 
 * wiki：https://oi-wiki.org/dp/number/#%E8%A7%A3%E9%87%8A_2
 * 相关函数的原文解析（含视频连接）：https://leetcode.cn/problems/numbers-with-repeated-digits/solution/by-endlesscheng-c5vg/
 * 一般：将 n 转换成字符串 s，定义 f(i,mask,isLimit,isNum) 表示构造从左往右第 i 位及其之后数位的合法方案数，其参数的含义为
 * i：第 i 位，也即当前要填入数字的是哪一位。
 * mask：表示前面选过的数字集合（哪个位置填了数字二进制位就用1来表示，否则该位为0），换句话说，第 i 位要选的数字不能在 mask 中。
 * 
 * isLimit：表示当前是否受到了 n 的约束。若为真，则第 i 位填入的数字至多为 s[i]，否则可以是 9。如果在受到约束的情况下填了 s[i]，那么后续填入的数字仍会受到 n 的约束。
 *  比如：数字123，如果第一位 1 选择填0，那么中间位可选的数字则是 0 ~ 9，如果第一位填了 1，那么中间位只能填 0 ~ 2，如果填到3，那么就是13x，会比 n 大了，第二位和第三位同理，
 *  因此 isLimit 表示当前位填入的数字是否受到了 n 的约束，也即如果 isLimit 为 true，那么当前位的数字只能填 0 ~ s[i]，而不能是 0 ~ 9。
 *  
 * isNum：表示 i 前面的数位是否填了数字。若为假，则当前位可以跳过（不填数字），或者要填入的数字至少为 1；若为真，则必须填数字，且要填入的数字可以从 0 开始。
 *  主要用于处理 0 这种特殊情况，因为我们可以填数字 0 到任意的一位，比如生成数字：10，这里用了一个0，但如果题目要求可以有前导 0 ，那么实际上是：010，也即如果把前导 0 考虑进去的话，
 *  那么这里的 0 就重复了，那这个结果可能就是不合法的。所以 isNum 用于区分从 第 0 位 到 第 i 位是否填过数字了，也即如果前面都没有填过数字的话，也即都跳过了，那么当前位可以选择的操作是：
 *  1.依旧可以继续跳过。2. 可以填入具体的数字【1 ~ 9】不考虑前导 0 。
 * 
 * 
 * 相关题目：
 *  [2376] - 统计特殊整数：Leetcode.editor.cn.CountSpecialIntegers
 *  [1012] - 至少有 1 位重复的数字：Leetcode.editor.cn.NumbersWithRepeatedDigits
 *  [233] - 数字 1 的个数：Leetcode.editor.cn.NumberOfDigitOne
 *  [面试题 17.06] - 2出现的次数：Leetcode.editor.cn.NumberOf2sInRangeLcci
 *  
 *  下方示例使用：[2376] 来示例
 */
public class digitDp {
    
    private static String s;
    private static int[][] dp;

    public static void main(String[] args) {
        s = "123";
        dp = new int[s.length()][1<<10];
        for(int[] d : dp){
            Arrays.fill(d, -1);
        }
        System.out.println(dfs(0, 0, true, false));
        System.out.println(dfs2(0, 0, true, false));
    }

    /**
     * 递归入口：f(0, 0, true, false)，表示：从 s[0] 开始枚举，一开始集合中没有数字，一开始要受到  n 的约束（否则就可以随意填了，这肯定不行），一开始没有填数字。
     * 返回从 i 开始填数字，i 前面填的数字集合是 mask，此时能构造出的特殊整数的数目
     * @param isLimit 表示前面填的数字是否是 n 对应位上的，如果为 true 那么当前位最多只能为 s[i]，否则可以填 0 ~ 9
     * @param isNum 表示前面是否填过数字了，是否跳过了。true 表示前面填过数字了，那么当前位可以从 0 开始，否则，当前位可以选择：1. 继续跳过；2. 前面没填过数字，没有前导0，那么当前位只能从1开始，可以枚举 1 ~ s[i]
     * 以上4个参数不一定全部都用上，要看情况
     * @return
     */
    private static int dfs(int i, int mask, boolean isLimit, boolean isNum){
        //如果前面构造过数字（isNum 为 true），那么返回 1
        if(i == s.length()){
            return isNum ? 1 : 0;
        }
        int res = 0;
        //前面没填过数字，选择跳过
        if(!isNum){
            //表示跳过了，继续下一位，mask不变，isLimit 和 isNum 为 false
            res = dfs(i + 1, mask, false, false);            
        }
        
        //当前位填入的上限，如果限制，那么当前位最大只能是 0 ~ s[i]，否则可填 0 ~ 9
        int up = isLimit ? s.charAt(i) - '0' : 9;
        //枚举填入的数字，如果前面没填过数字（isNum 为 false），因为没有前导0，那么当前位只能从1开始，可以枚举 1 ~ s[i]，否则可以从 0 ~ s[i]/9
        //枚举的范围就取决于 isNum 和 isLimit。
        for(int x = isNum ? 0 : 1; x <= up; x++){
            //mask该位有没有被使用了【0 - 未使用，1 - 使用了】
            if((mask >> x & 1) == 0){
                res += dfs(i + 1, mask | (1 << x), isLimit && x == up, true);
            }
        }
        return res;
    }
    
    
    /**
     * 一般可以用dp数组来记忆化，进一步优化
     * 外部定义 dp[s.length()][1<<10]
     */
    private static int dfs2(int i, int mask, boolean isLimit, boolean isNum){
        //如果前面构造过数字（isNum 为 true），那么返回 1
        if(i == s.length()){
            return isNum ? 1 : 0;
        }
        if(!isLimit && isNum && dp[i][mask] >= 0){
            return dp[i][mask];
        }
        int res = 0;
        //前面没填过数字，选择跳过
        if(!isNum){
            //表示跳过了，继续下一位，mask不变，isLimit 和 isNum 为 false
            res = dfs(i + 1, mask, false, false);            
        }
        
        //当前位填入的上限，如果限制，那么当前位最大只能是 0 ~ s[i]，否则可填 0 ~ 9
        int up = isLimit ? s.charAt(i) - '0' : 9;
        //枚举填入的数字，如果前面没填过数字（isNum 为 false），因为没有前导0，那么当前位只能从1开始，可以枚举 1 ~ s[i]，否则可以从 0 ~ s[i]/9
        //枚举的范围就取决于 isNum 和 isLimit。
        for(int x = isNum ? 0 : 1; x <= up; x++){
            //mask该位有没有被使用了【0 - 未使用，1 - 使用了】
            if((mask >> x & 1) == 0){
                res += dfs(i + 1, mask | (1 << x), isLimit && x == up, true);
            }
        }
        if(isLimit && !isNum){
            dp[i][mask] = res;
        }
        return res;
    }
    
}
