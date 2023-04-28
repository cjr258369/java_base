package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 2023-03-03 09:03:23
 * [1487] - 保证文件名唯一
 * MakingFileNamesUnique.md
 */
 
//给你一个长度为 n 的字符串数组 names 。你将会在文件系统中创建 n 个文件夹：在第 i 分钟，新建名为 names[i] 的文件夹。 
// 由于两个文件 不能 共享相同的文件名，因此如果新建文件夹使用的文件名已经被占用，系统会以 (k) 的形式为新文件夹的文件名添加后缀，其中 k 是能保证文件名唯一的 最小正整数 。 
// 返回长度为 n 的字符串数组，其中 ans[i] 是创建第 i 个文件夹时系统分配给该文件夹的实际名称。 
//
// 示例 1： 
// 输入：names = ["pes","fifa","gta","pes(2019)"]
//输出：["pes","fifa","gta","pes(2019)"]
//解释：文件系统将会这样创建文件名：
//"pes" --> 之前未分配，仍为 "pes"
//"fifa" --> 之前未分配，仍为 "fifa"
//"gta" --> 之前未分配，仍为 "gta"
//"pes(2019)" --> 之前未分配，仍为 "pes(2019)"
//
// 示例 2： 
// 输入：names = ["gta","gta(1)","gta","avalon"]
//输出：["gta","gta(1)","gta(2)","avalon"]
//解释：文件系统将会这样创建文件名：
//"gta" --> 之前未分配，仍为 "gta"
//"gta(1)" --> 之前未分配，仍为 "gta(1)"
//"gta" --> 文件名被占用，系统为该名称添加后缀 (k)，由于 "gta(1)" 也被占用，所以 k = 2 。实际创建的文件名为 "gta(2)" 。
//"avalon" --> 之前未分配，仍为 "avalon"
//
// 示例 3： 
// 输入：names = ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]
//输出：["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece(4)"]
//解释：当创建最后一个文件夹时，最小的正有效 k 为 4 ，文件名变为 "onepiece(4)"。
//
// 示例 4： 
// 输入：names = ["wano","wano","wano","wano"]
//输出：["wano","wano(1)","wano(2)","wano(3)"]
//解释：每次创建文件夹 "wano" 时，只需增加后缀中 k 的值即可。 
//
// 示例 5： 
// 输入：names = ["kaido","kaido(1)","kaido","kaido(1)"]
//输出：["kaido","kaido(1)","kaido(2)","kaido(1)(1)"]
//解释：注意，如果含后缀文件名被占用，那么系统也会按规则在名称后添加新的后缀 (k) 。
//
// 提示： 
// 1 <= names.length <= 5 * 10^4 
// 1 <= names[i].length <= 20 
// names[i] 由小写英文字母、数字和/或圆括号组成。 

public class MakingFileNamesUnique{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MakingFileNamesUnique().new Solution();
        System.out.println("预期结果：[pes,fifa,gta,pes(2019)] , 运行结果：" + Arrays.toString(solution.getFolderNames(new String[]{"pes", "fifa", "gta", "pes(2019)"})));
        System.out.println("预期结果：[gta,gta(1),gta(2),avalon] , 运行结果：" + Arrays.toString(solution.getFolderNames(new String[]{"gta","gta(1)","gta","avalon"})));
        System.out.println("预期结果：[onepiece,onepiece(1),onepiece(2),onepiece(3),onepiece(4)] , 运行结果：" + Arrays.toString(solution.getFolderNames(new String[]{"onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"})));
        System.out.println("预期结果：[wano,wano(1),wano(2),wano(3)] , 运行结果：" + Arrays.toString(solution.getFolderNames(new String[]{"wano","wano","wano","wano"})));
        System.out.println("预期结果：[kaido,kaido(1),kaido(2),kaido(1)(1)] , 运行结果：" + Arrays.toString(solution.getFolderNames(new String[]{"kaido","kaido(1)","kaido","kaido(1)"})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(∑  i=0 n−1 mi )，其中 mi 表示字符串 names[i] 的长度，n 表示数组 names 的长度。外层循环每次运行时间为 O(mi)，
        //      总时间复杂度为 O(∑  i=0 n−1 mi )；内层循环的总运行次数不超过 n 次，总时间复杂度为 O(∑ i=0 n−1 mi )。
        // 空间复杂度：O(∑  i=0 n−1 mi )。保存哈希表需要  O(∑  i=0 n−1  mi  ) 的空间。
        public String[] getFolderNames(String[] names) {
            HashMap<String, Integer> map = new HashMap<>();
            String[] ans = new String[names.length];
            for(int i = 0; i < ans.length; i++){
                String name = names[i];
                if(map.containsKey(name)){
                    int k = map.get(name);
                    while(map.containsKey(addSuffix(name, k))){
                        k++;
                    }
                    ans[i] = addSuffix(name, k);
                    map.put(ans[i], 1);
                    map.put(name, k + 1);
                }else{
                    ans[i] = name;
                    map.put(name, 1);
                }
            }
            return ans;
        }
        
        private String addSuffix(String name, int k){
            return name + "(" + k + ")";
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}