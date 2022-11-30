package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 2022-11-17 10:49:24
 * [792] - 匹配子序列的单词数
 * NumberOfMatchingSubsequences.md
 */
public class NumberOfMatchingSubsequences{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NumberOfMatchingSubsequences().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.numMatchingSubseq3("abcde", new String[]{"a","bb","acd","ace"}));
        System.out.println("预期结果：2 , 运行结果：" + solution.numMatchingSubseq3("dsahjpjauf", new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        /* 方法一：直接暴力枚举 (会超时)*/
        public int numMatchingSubseq(String s, String[] words) {
            int ans = 0, len = s.length();
            for(String word : words){
                int sPos = 0, wordPos = 0, len1 = word.length();
                while(sPos < len && wordPos < len1){
                    char ch = word.charAt(wordPos);
                    while(sPos < len && s.charAt(sPos) != ch){
                        sPos++;
                    }
                    if(sPos == len){
                        break;
                    }
                    sPos++;
                    wordPos++;
                }
                ans += wordPos == len1 ? 1:0;
            }
            return ans;    
        }
        
        /* 方法二：升序的存储每个字符的位置，二分查找 */
        public int numMatchingSubseq2(String s, String[] words) {
            List<Integer>[] wordPos = new List[26];
            for(int i = 0; i < 26; i++){
                wordPos[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i < s.length(); i++){
                wordPos[s.charAt(i) - 'a'].add(i);
            }
            int ans = words.length;
            for(String word : words){
                if(word.length() > s.length()){
                    ans--;
                    continue;
                }
                int p = -1;
                for(int i = 0; i < word.length(); i++){
                    int idx = word.charAt(i) - 'a';
                    //当前字符没有，或者当前字符的最后一个坐标，比当前坐标还要小，那么意味着它后面也不会有该字符了
                    if(wordPos[idx].isEmpty() || wordPos[idx].get(wordPos[idx].size() - 1) <= p){
                        ans--;
                        break;
                    }
                    p = binarySearch(wordPos[idx], p);
                }
            }
            return ans;    
        }
        
        int binarySearch(List<Integer> arr, int target){
            int left = 0, right = arr.size() - 1;
            while(left < right){
                int mid = left + (right - left)/2;
                if(arr.get(mid) > target){
                    right = mid;
                }else{
                    left = mid + 1;
                }
            }
            return arr.get(left);
        }

        /* 方法三：在方法二的基础上，使用多指针同时对word进行遍历*/
        public int numMatchingSubseq3(String s, String[] words) {
            Queue<int[]>[] queue = new Queue[26];
            for(int i = 0; i < 26; i++){
                queue[i] = new ArrayDeque<int[]>();
            }
            //把 words 的第一个字符入队
            //如果有多个 word 的首字符相同，则出队时，会同时出队。t[0]表示word的下标，t[1]表示当前遍历到的word的哪个字符的索引
            for(int i = 0; i < words.length; i++){
                queue[words[i].charAt(0) - 'a'].offer(new int[]{i,0});
            }
            int ans = 0;
            for(int i = 0; i < s.length(); i++){
                int idx = s.charAt(i) - 'a';
                //把拥有当前字符的全部 words 出队，必须固定 len，不然会出把后面重新入队的也出掉
                int len = queue[idx].size();
                while(len-- > 0){
                    int[] t = queue[idx].poll();
                    //判断当前字符串是否已经判断到结尾了，否则重新入队
                    if(t[1] == words[t[0]].length() - 1){
                        ans++;
                    }else{
                        
                        t[1]++;
                        queue[words[t[0]].charAt(t[1]) - 'a'].offer(t);
                    }                    
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}