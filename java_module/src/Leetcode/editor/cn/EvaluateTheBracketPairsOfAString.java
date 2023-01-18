package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 2023-01-12 09:24:50
 * [1807] - 替换字符串中的括号内容
 * EvaluateTheBracketPairsOfAString.md
 */
 
//给你一个字符串 s ，它包含一些括号对，每个括号中包含一个 非空 的键。 
// 比方说，字符串 "(name)is(age)yearsold" 中，有 两个 括号对，分别包含键 "name" 和 "age" 。  
// 你知道许多键对应的值，这些关系由二维字符串数组 knowledge 表示，其中 knowledge[i] = [key[i], value[i]] ，表示键key[i] 对应的值为 value[i] 。 
//
// 你需要替换 所有 的括号对。当你替换一个括号对，且它包含的键为 keyi 时，你需要： 
// 将 key[i] 和括号用对应的值 value[i] 替换。 
// 如果从 knowledge 中无法得知某个键对应的值，你需要将 keyi 和括号用问号 "?" 替换（不需要引号）。 
// knowledge 中每个键最多只会出现一次。s 中不会有嵌套的括号。 
// 请你返回替换 所有 括号对后的结果字符串。 
//
// 示例 1： 
//输入：s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
//输出："bobistwoyearsold"
//解释：
//键 "name" 对应的值为 "bob" ，所以将 "(name)" 替换为 "bob" 。
//键 "age" 对应的值为 "two" ，所以将 "(age)" 替换为 "two" 。
//
// 示例 2： 
//输入：s = "hi(name)", knowledge = [["a","b"]]
//输出："hi?"
//解释：由于不知道键 "name" 对应的值，所以用 "?" 替换 "(name)" 。
//
// 示例 3： 
//输入：s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
//输出："yesyesyesaaa"
//解释：相同的键在 s 中可能会出现多次。
//键 "a" 对应的值为 "yes" ，所以将所有的 "(a)" 替换为 "yes" 。
//注意，不在括号里的 "a" 不需要被替换。
//
// 提示： 
// 1 <= s.length <= 10⁵ 
// 0 <= knowledge.length <= 10⁵ 
// knowledge[i].length == 2 
// 1 <= keyi.length, valuei.length <= 10 
// s 只包含小写英文字母和圆括号 '(' 和 ')' 。 
// s 中每一个左圆括号 '(' 都有对应的右圆括号 ')' 。 
// s 中每对括号内的键都不会为空。 
// s 中不会有嵌套括号对。 
// key[i] 和 value[i] 只包含小写英文字母。 
// knowledge 中的 key[i] 不会重复。

public class EvaluateTheBracketPairsOfAString{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new EvaluateTheBracketPairsOfAString().new Solution();
        System.out.println("预期结果：bobistwoyearsold , 运行结果：" + solution.evaluate4("(name)is(age)yearsold", Arrays.asList(Arrays.asList("name","bob"),Arrays.asList("age","two"))));
        System.out.println("预期结果：hi? , 运行结果：" + solution.evaluate4("hi(name)", Arrays.asList(Arrays.asList("a","b"))));
        System.out.println("预期结果：yesyesyesaaa , 运行结果：" + solution.evaluate4("(a)(a)(a)aaa", Arrays.asList(Arrays.asList("a","yes"))));
        System.out.println("预期结果：yes , 运行结果：" + solution.evaluate4("(a)", Arrays.asList(Arrays.asList("a","yes"))));
        System.out.println("预期结果：aaaayes , 运行结果：" + solution.evaluate4("aaaa(a)", Arrays.asList(Arrays.asList("a","yes"))));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //所有优化方案的复杂度分析：
        //时间复杂度：O(n + k)，其中 n 是字符串 s 的长度，k 是字符串数组 knowledge 中所有字符串的长度之和。
        //空间复杂度：O(n + k)，其中 n 是字符串 s 的长度，k 是字符串数组 knowledge 中所有字符串的长度之和。保存哈希表 dict 和 key 分别需要 O(k) 和 O(n)。
        
        //用两个指针
        public String evaluate(String s, List<List<String>> knowledge) {
            HashMap<String, String> map = new HashMap<>();
            for(List<String> list : knowledge){
                map.put(list.get(0), list.get(1));
            }
            StringBuffer ans = new StringBuffer();
            for(int left = 0, right = 0; right < s.length(); ){
                while(left < s.length() && '(' != s.charAt(left)){
                    ans.append(s.charAt(left));
                    left++;
                }
                right = left;
                while(right < s.length() && ')' != s.charAt(right)){
                    right++;
                }
                if(left < right){
                    ans.append(map.getOrDefault(s.substring(left + 1, right), "?"));
                }
                left = ++right;
                //System.out.println("left = " + left + " , right = " + right);
            }
            return ans.toString();
        }
        
        //用单个指针
        public String evaluate2(String s, List<List<String>> knowledge) {
            HashMap<String, String> map = new HashMap<>();
            for(List<String> list : knowledge){
                map.put(list.get(0), list.get(1));
            }
            StringBuffer ans = new StringBuffer();
            for(int i = 0; i < s.length(); i++){
                while(i < s.length() && '(' != s.charAt(i)){
                    ans.append(s.charAt(i++));                    
                }
                int cnt = 0;
                while(i < s.length() && ')' != s.charAt(i)){
                    cnt++;
                    i++;
                }
                if(i < s.length()){
                    ans.append(map.getOrDefault(s.substring(i - cnt + 1, i), "?"));
                }
            }
            return ans.toString();
        }
        
        //进一步简化单个指针里面的循环
        public String evaluate3(String s, List<List<String>> knowledge) {
            HashMap<String, String> map = new HashMap<>();
            for(List<String> list : knowledge){
                map.put(list.get(0), list.get(1));
            }
            StringBuffer ans = new StringBuffer();
            for(int i = 0; i < s.length(); i++){
                if('(' != s.charAt(i)){
                    ans.append(s.charAt(i));
                }else{
                    int j = s.indexOf(')', i + 1);
                    ans.append(map.getOrDefault(s.substring(i + 1, j), "?"));
                    i = j;
                }
            }
            return ans.toString();
        }
        
        //测评机效率最优：【105个案例，耗时 27ms - 30 ms 左右】
        public String evaluate4(String s, List<List<String>> knowledge) {
            HashMap<String, String> map = new HashMap<>(knowledge.size());
            knowledge.forEach(pair -> map.put(pair.get(0), pair.get(1)));
            StringBuffer ans = new StringBuffer();
            for(int i = 0; i < s.length(); i++){
                if('(' == s.charAt(i)){
                    int startPos = ++i;
                    while(')' != s.charAt(i)){
                        i++;
                    }
                    ans.append(map.getOrDefault(s.substring(startPos, i), "?"));
                }else{
                    ans.append(s.charAt(i));
                }
            }
            return ans.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}