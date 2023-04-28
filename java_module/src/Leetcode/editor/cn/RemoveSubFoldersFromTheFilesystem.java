package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2023-02-08 09:15:57
 * [1233] - 删除子文件夹
 * RemoveSubFoldersFromTheFilesystem.md
 */
 
//你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。 
// 如果文件夹 folder[i] 位于另一个文件夹 folder[j] 下，那么 folder[i] 就是 folder[j] 的 子文件夹 。 
// 文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：'/' 后跟一个或者多个小写英文字母。 
// 例如，"/leetcode" 和 "/leetcode/problems" 都是有效的路径，而空字符串和 "/" 不是。 
//
// 示例 1： 
//输入：folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
//输出：["/a","/c/d","/c/f"]
//解释："/a/b/" 是 "/a" 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。
//
// 示例 2： 
//输入：folder = ["/a","/a/b/c","/a/b/d"]
//输出：["/a"]
//解释：文件夹 "/a/b/c" 和 "/a/b/d/" 都会被删除，因为它们都是 "/a" 的子文件夹。
//
// 示例 3： 
//输入: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
//输出: ["/a/b/c","/a/b/ca","/a/b/d"] 
//
// 提示： 
// 1 <= folder.length <= 4 * 10⁴ 
// 2 <= folder[i].length <= 100 
// folder[i] 只包含小写字母和 '/' 
// folder[i] 总是以字符 '/' 起始 
// 每个文件夹名都是 唯一 的 

public class RemoveSubFoldersFromTheFilesystem{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new RemoveSubFoldersFromTheFilesystem().new Solution();
        System.out.println("预期结果： [/a, /c/d, /c/f], 运行结果：" + solution.removeSubfolders(new String[]{"/a","/a/b","/c/d","/c/d/e","/c/f"}));
        System.out.println("预期结果： [/a], 运行结果：" + solution.removeSubfolders(new String[]{"/a","/a/b/c","/a/b/d"}));
        System.out.println("预期结果： [/a/b/c,/a/b/ca,/a/b/d], 运行结果：" + solution.removeSubfolders(new String[]{"/a/b/c","/a/b/ca","/a/b/d"}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(nl⋅logn)，其中 n 和 l 分别是数组 folder 的长度和文件夹的平均长度。O(nl⋅logn) 为排序需要的时间，后续构造答案需要的时间为 O(nl)，在渐进意义下小于前者。
        // 空间复杂度：O(1)。在构造答案比较前缀时，我们使用了字符串的截取子串操作，因此需要 O(l) 的临时空间。我们也可以使用一个递增的指针依次对两个字符串的每个相同位置进行比较，
        // 省去这一部分的空间，使得空间复杂度降低至排序需要的栈空间 O(logn)。但空间优化并不是本题的重点，因此上述的代码中仍然采用空间复杂度为 O(l) 的写法。注意这里不计入返回值占用的空间。
        public List<String> removeSubfolders(String[] folder) {
            Arrays.sort(folder);
            List<String> ans = new ArrayList<>();
            String t = "//";
            for(String f : folder){
                if(!f.startsWith(t)){
                    ans.add(f);
                    t = f + "/";
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}