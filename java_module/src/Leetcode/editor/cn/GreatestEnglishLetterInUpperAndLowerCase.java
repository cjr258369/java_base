package Leetcode.editor.cn;

/**
 * 2023-01-27 11:31:01
 * [2309] - 兼具大小写的最好英文字母
 * GreatestEnglishLetterInUpperAndLowerCase.md
 */

//给你一个由英文字母组成的字符串 s ，请你找出并返回 s 中的 最好 英文字母。返回的字母必须为大写形式。如果不存在满足条件的字母，则返回一个空字符串。 
// 最好 英文字母的大写和小写形式必须 都 在 s 中出现。 
// 英文字母 b 比另一个英文字母 a 更好 的前提是：英文字母表中，b 在 a 之 后 出现。 
//
// 示例 1： 
//输入：s = "lEeTcOdE"
//输出："E"
//解释：
//字母 'E' 是唯一一个大写和小写形式都出现的字母。 
//
// 示例 2： 
//输入：s = "arRAzFif"
//输出："R"
//解释：
//字母 'R' 是大写和小写形式都出现的最好英文字母。
//注意 'A' 和 'F' 的大写和小写形式也都出现了，但是 'R' 比 'F' 和 'A' 更好。
//
// 示例 3： 
//输入：s = "AbCdEfGhIjK"
//输出：""
//解释：
//不存在大写和小写形式都出现的字母。
//
// 提示： 
// 1 <= s.length <= 1000 
// s 由小写和大写英文字母组成 
 
public class GreatestEnglishLetterInUpperAndLowerCase{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new GreatestEnglishLetterInUpperAndLowerCase().new Solution();
        System.out.println("预期结果：E , 运行结果：" + solution.greatestLetter2("lEeTcOdE"));
        System.out.println("预期结果：R , 运行结果：" + solution.greatestLetter2("arRAzFif"));
        System.out.println("预期结果：\"\" , 运行结果：" + solution.greatestLetter2("AbCdEfGhIjK"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        String[] lower = {"z","y","x","w","v","u","t","s","r","q","p","o","n","m","l","k","j","i","h","g","f","e","d","c","b","a"};
        String[] upper = {"Z","Y","X","W","V","U","T","S","R","Q","P","O","N","M","L","K","J","I","H","G","F","E","D","C","B","A"};
        public String greatestLetter(String s) {
            for(int i = 0; i < 26; i++){
                if(s.contains(lower[i]) && s.contains(upper[i])){
                    return upper[i];
                }
            }
            return "";
        }
        
        
        public String greatestLetter2(String s) {
            for(char c = 'Z'; c >= 'A'; c--){
                if(s.contains(String.valueOf(c)) && s.contains(String.valueOf((char)(c + 32)))){
                    return String.valueOf(c);
                }
            }
            return "";
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}