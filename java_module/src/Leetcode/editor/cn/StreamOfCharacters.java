package Leetcode.editor.cn;

/**
 * 2023-03-24 16:12:00
 * [1032] - 字符流
 * StreamOfCharacters.md
 */
 
//设计一个算法：接收一个字符流，并检查这些字符的后缀是否是字符串数组 words 中的一个字符串。 
// 例如，words = ["abc", "xyz"] 且字符流中逐个依次加入 4 个字符 'a'、'x'、'y' 和 'z' ，你所设计的算法应当可以检测到
// "axyz" 的后缀 "xyz" 与 words 中的字符串 "xyz" 匹配。 
//
// 按下述要求实现 StreamChecker 类： 
// StreamChecker(String[] words) ：构造函数，用字符串数组 words 初始化数据结构。 
// boolean query(char letter)：从字符流中接收一个新字符，如果字符流中的任一非空后缀能匹配 words 中的某一字符串，返回true ；否则，返回 false。 
//
// 示例： 
//输入：
//["StreamChecker", "query", "query", "query", "query", "query", "query","query", "query", "query", "query", "query", "query"]
//[[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"],["i"], ["j"], ["k"], ["l"]]
//输出：
//[null, false, false, false, true, false, true, false, false, false, false,false, true]
//
//解释：
//StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
//streamChecker.query("a"); // 返回 False
//streamChecker.query("b"); // 返回 False
//streamChecker.query("c"); // 返回 False
//streamChecker.query("d"); // 返回 True ，因为 'cd' 在 words 中
//streamChecker.query("e"); // 返回 False
//streamChecker.query("f"); // 返回 True ，因为 'f' 在 words 中
//streamChecker.query("g"); // 返回 False
//streamChecker.query("h"); // 返回 False
//streamChecker.query("i"); // 返回 False
//streamChecker.query("j"); // 返回 False
//streamChecker.query("k"); // 返回 False
//streamChecker.query("l"); // 返回 True ，因为 'kl' 在 words 中
//
// 提示： 
// 1 <= words.length <= 2000 
// 1 <= words[i].length <= 200 
// words[i] 由小写英文字母组成 
// letter 是一个小写英文字母 
// 最多调用查询 4 * 10⁴ 次 

public class StreamOfCharacters{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        StreamChecker solution = new StreamOfCharacters().new StreamChecker(new String[]{"cd", "f", "kl"});
        System.out.println("预期结果：false , 运行结果：" + solution.query('a'));
        System.out.println("预期结果：false , 运行结果：" + solution.query('b'));
        System.out.println("预期结果：false , 运行结果：" + solution.query('c'));
        System.out.println("预期结果：true , 运行结果：" + solution.query('d'));
        System.out.println("预期结果：false , 运行结果：" + solution.query('e'));
        System.out.println("预期结果：true , 运行结果：" + solution.query('f'));
        System.out.println("预期结果：false , 运行结果：" + solution.query('g'));
        System.out.println("预期结果：false , 运行结果：" + solution.query('h'));
        System.out.println("预期结果：false , 运行结果：" + solution.query('i'));
        System.out.println("预期结果：false , 运行结果：" + solution.query('j'));
        System.out.println("预期结果：false , 运行结果：" + solution.query('k'));
        System.out.println("预期结果：true , 运行结果：" + solution.query('l'));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class StreamChecker {

        Trie root;
        StringBuilder str;
    
        public StreamChecker(String[] words) {
            root = new Trie();
            for(String word : words){
                root.insert(word);                
            }
            str = new StringBuilder();
        }
        
        public boolean query(char letter) {
            str.append(letter);
            return root.searchSuffix(str.toString());    
        }

        private class Trie {
            private Trie[] children;
            private boolean isEnd;
            public Trie() {
                children = new Trie[26];
                isEnd = false;
            }

            public void insert(String word){
                Trie node = this;
                for(int i = word.length() - 1; i >= 0; i--){
                    int index = word.charAt(i) - 'a';
                    if(node.children[index] == null){
                        node.children[index] =  new Trie();
                    }
                    node = node.children[index];
                }
                node.isEnd = true;
            }

            public boolean searchSuffix(String suffix){
                Trie node = this;
                for(int i = suffix.length() - 1; !node.isEnd && i >= 0; i--){
                    int index = suffix.charAt(i) - 'a';
                    if(node.children[index] == null){
                        return false;
                    }
                    node = node.children[index];
                }
                return node.isEnd;
            }
        }
    }

    /**
     * Your StreamChecker object will be instantiated and called as such:
     * StreamChecker obj = new StreamChecker(words);
     * boolean param_1 = obj.query(letter);
     */
    //leetcode submit region end(Prohibit modification and deletion)
    
    }