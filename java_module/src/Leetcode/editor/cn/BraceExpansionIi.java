package Leetcode.editor.cn;

import java.util.*;

/**
 * 2023-03-07 16:22:58
 * [1096] - 花括号展开 II
 * BraceExpansionIi.md
 */
 
//如果你熟悉 Shell 编程，那么一定了解过花括号展开，它可以用来生成任意字符串。 花括号展开的表达式可以看作一个由 花括号、逗号 和 小写英文字母 组成的字符串，定义下面几条语法规则： 
// 如果只给出单一的元素 x，那么表达式表示的字符串就只有 "x"。R(x) = {x} 
// 
// 例如，表达式 "a" 表示字符串 "a"。  而表达式 "w" 就表示字符串 "w"。 
// 当两个或多个表达式并列，以逗号分隔，我们取这些表达式中元素的并集。R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
// 例如，表达式 "{a,b,c}" 表示字符串 "a","b","c"。 
// 而表达式 "{{a,b},{b,c}}" 也可以表示字符串 "a","b","c"。 
// 要是两个或多个表达式相接，中间没有隔开时，我们从这些表达式中各取一个元素依次连接形成字符串。R(e_1 + e_2) = {a + b for (a,b) in R(e_1) × R(e_2)}
// 例如，表达式 "{a,b}{c,d}" 表示字符串 "ac","ad","bc","bd"。 
// 表达式之间允许嵌套，单一元素与表达式的连接也是允许的。
// 例如，表达式 "a{b,c,d}" 表示字符串 "ab","ac","ad"。 
// 例如，表达式 "a{b,c}{d,e}f{g,h}" 可以表示字符串 "abdfg", "abdfh", "abefg", "abefh","acdfg", "acdfh", "acefg", "acefh"。 
//
// 给出表示基于给定语法规则的表达式 expression，返回它所表示的所有字符串组成的有序列表。假如你希望以「集合」的概念了解此题，也可以通过点击 “显示英文描述” 获取详情。 
//
// 示例 1： 
//输入：expression = "{a,b}{c,{d,e}}"
//输出：["ac","ad","ae","bc","bd","be"] 
//
// 示例 2： 
//输入：expression = "{{a,z},a{b,c},{ab,z}}"
//输出：["a","ab","ac","z"]
//解释：输出中 不应 出现重复的组合结果。
//
// 提示： 
// 1 <= expression.length <= 60 
// expression[i] 由 '{'，'}'，',' 或小写英文字母组成 
// 给出的表达式 expression 用以表示一组基于题目描述中语法构造的字符串 

public class BraceExpansionIi{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new BraceExpansionIi().new Solution();
        System.out.println("预期结果：[ac, ad, ae, bc, bd, be] , 运行结果：" + solution.braceExpansionII("{a,b}{c,{d,e}}"));
        System.out.println("预期结果：[a, ab, ac, z] , 运行结果：" + solution.braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(nlogn)，其中 n 是 expression 的长度。整个 expression 只会遍历一次，时间复杂度为 O(n)，集合合并以及求积运算的时间复杂度为 O(nlogn)，因此总的时间复杂度为 O(nlogn)。
        // 空间复杂度：O(n)。递归过程所需的栈空间为 O(n)，以及存放中间答案的空间复杂度为 O(n)，因此总的空间复杂度为 O(n)。
        private int idx;
        private String expression;
        public List<String> braceExpansionII(String expression) {
            this.expression = expression;
            this.idx = 0;
            return new ArrayList<>(expr());    
        }

        // item . letter | { expr }
        private Set<String> item(){
            Set<String> res = new TreeSet<>();
            if(expression.charAt(idx)  == '{'){
                idx++;
                res = expr();                
            }else{
                StringBuilder sb = new StringBuilder();
                sb.append(expression.charAt(idx));
                res.add(sb.toString());
            }
            idx++;
            return res;            
        }

        // term . item | item term
        private Set<String> term(){
            // 初始化空集合，与之后的求解结果求笛卡尔积
            Set<String> res = new TreeSet<String> (){{
                add("");                
            }};

            // item 的开头是 { 或小写字母，只有符合时才继续匹配
            while(idx < expression.length() && (expression.charAt(idx) == '{' || Character.isLetter(expression.charAt(idx)))){
                Set<String> sub = item();
                //存储当前的结果
                Set<String> tmp = new TreeSet<>();
                //求笛卡尔积
                for(String left : res){
                    for(String right : sub){
                        tmp.add(left + right);                        
                    }                    
                }
                res = tmp;
            }
            return res;
        }

        // expr . term | term, expr
        private Set<String> expr(){
            Set<String> res = new TreeSet<>();
            while(true){
                // 与 term() 求解结果求并集
                res.addAll(term());
                if(idx < expression.length() && expression.charAt(idx) == ','){
                    idx++;
                }else {
                    break;
                }
                
            }
            return res;            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}