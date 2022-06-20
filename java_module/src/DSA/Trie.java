package DSA;

/**
 * Trie，又称前缀树或字典树，是一棵有根树，其每个节点包含以下字段：
 * 1) 指向子节点的指针数组 children。对于本题而言，数组长度为 26，即小写英文字母的数量。此时 children[0] 对应小写字母 a，children[1] 对应小写字母 b，…，children[25] 对应小写字母 z。
 * 2) 布尔字段 isEnd，表示该节点是否为字符串的结尾。
 *
 * 插入字符串
 * 我们从字典树的根开始，插入字符串。对于当前字符对应的子节点，有两种情况：
 * 1) 子节点存在。沿着指针移动到子节点，继续处理下一个字符。
 * 2) 子节点不存在。创建一个新的子节点，记录在 children 数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
 * 重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾。
 *
 * 查找前缀
 * 我们从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：
 * 1) 子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
 * 2) 子节点不存在。说明字典树中不包含该前缀，返回空指针。
 * 重复以上步骤，直到返回空指针或搜索完前缀的最后一个字符。
 *
 * 若搜索到了前缀的末尾，就说明字典树中存在该前缀。此外，若前缀末尾对应节点的 isEnd 为真，则说明字典树中存在该字符串。
 *
 * 对应LC基础题：208
 * 进阶题：140,472 (关于前缀树（字典树）的一些应用)
 */
public class Trie {
    private Trie[] children;
    private boolean isEnd;

    /**
     * Initialize your data structure here.
     * 初始化前缀树对象
     */
    public Trie(){
        children = new Trie[26];
        isEnd = false;
    }

    /**
     * Inserts a word into the trie.
     * 向前缀树中插入字符串 word 。
     * @param word 需要插入的字符串
     */
    public void insert(String word){
        Trie node = this;
        for(int i=0;i<word.length();i++){
            int index = word.charAt(i) - 'a';
            if(node.children[index] == null){
                node.children[index] =  new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     * 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
     * @param word 需要判断的字符串
     * @return
     */
    public boolean search(String word){
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     * 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
     * @param prefix 需要判断的前缀字符串
     * @return
     */
    public boolean startsWith(String prefix){
        return searchPrefix(prefix) != null;
    }

    /**
     * 查询字符串
     * @param prefix 查询的字符串，返回这个 prefix 的字典树
     * @return
     */
    public Trie searchPrefix(String prefix){
        Trie node = this;
        for(int i=0;i<prefix.length();i++){
            int index = prefix.charAt(i) - 'a';
            if(node.children[index] == null){
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    /**
     * 自测
     * @param args
     */
    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("apple");
        boolean param_2 = obj.search("apple");
        boolean param_3 = obj.search("app");
        boolean param_4 = obj.startsWith("app");
        obj.insert("app");
        boolean param_5 = obj.search("app");
        System.out.println("param_2="+param_2+" , param_3="+param_3+" , param_4="+param_4+" , param_5="+param_5);
    }
}
