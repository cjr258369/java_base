package Leetcode.editor.cn;

/**
 * 2023-06-12 14:44:45
 * [1483] - 树节点的第 K 个祖先
 * KthAncestorOfATreeNode.md
 */
 
//给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。 
// 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。 
//
// 实现 TreeAncestor 类： 
// TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。 
// getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。 
//
// 示例 1： 
//输入：
//["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
//[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]
//输出：
//[null,1,0,-1]
//解释：
//TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
//treeAncestor.getKthAncestor(3, 1);  // 返回 1 ，它是 3 的父节点
//treeAncestor.getKthAncestor(5, 2);  // 返回 0 ，它是 5 的祖父节点
//treeAncestor.getKthAncestor(6, 3);  // 返回 -1 因为不存在满足要求的祖先节点
//
// 提示： 
// 1 <= k <= n <= 5 * 10⁴ 
// parent[0] == -1 表示编号为 0 的节点是根节点。 
// 对于所有的 0 < i < n ，0 <= parent[i] < n 总成立 
// 0 <= node < n 
// 至多查询 5 * 10⁴ 次 

public class KthAncestorOfATreeNode{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        TreeAncestor solution = new KthAncestorOfATreeNode().new TreeAncestor(7, new int[]{-1, 0, 0, 1, 1, 2, 2});
        System.out.println("预期结果：1 , 运行结果：" + solution.getKthAncestor(3, 1));
        System.out.println("预期结果：0 , 运行结果：" + solution.getKthAncestor(5, 2));
        System.out.println("预期结果：-1 , 运行结果：" + solution.getKthAncestor(6, 3));
        System.out.println("============================");
        TreeAncestor solution2 = new KthAncestorOfATreeNode().new TreeAncestor(5, new int[]{-1,0,0,0,3});
        System.out.println("预期结果：-1 , 运行结果：" + solution2.getKthAncestor2(1, 5));
        System.out.println("预期结果：-1 , 运行结果：" + solution2.getKthAncestor2(3, 2));
        System.out.println("预期结果：-1 , 运行结果：" + solution2.getKthAncestor2(0, 1));
        System.out.println("预期结果：0 , 运行结果：" + solution2.getKthAncestor2(3, 1));
        System.out.println("预期结果：-1 , 运行结果：" + solution2.getKthAncestor2(3, 5));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class TreeAncestor {
        //树上倍增算法。常用于计算最近公共祖先 LCA。本解法的详细来源：https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/solution/mo-ban-jiang-jie-shu-shang-bei-zeng-suan-v3rw/
        
        //1. 思考：最暴力的做法是，从  node 出发，一步步地往上跳，即
        //          node -> parent[node] -> parent[parent[node]] -> ⋯
        //   需要跳  k 次才能到达  node 的第  k 个祖先节点，时间复杂度为  O(k)。 那如何优化这个暴力算法呢？
        //   一个初步的想法是，预处理出每个节点的「爷爷节点」，即父节点的父节点，那么就可以两步两步地往上跳，从而减少一半的跳跃次数（循环次数）。
        //   进一步地，再预处理出爷爷节点的爷爷节点，就可以四步四步地往上跳。
        //   那么请问：一般地，要预处理出哪些节点呢？如何利用这些预处理出的节点，快速地找到第 k 个祖先节点？
        
        //2. 解答：预处理出每个节点的第 2^i  个祖先节点，即第 2,4,8,⋯ 个祖先节点（注意 x 的第 1 个祖先节点就是 parent[x]）。
        //   由于任意 k 可以分解为若干不同的 2 的幂（例如 13=8+4+1），所以只需要预处理出这些 2^i  祖先节点，就可以快速地到达任意第 k 个祖先节点。
        //   例如 k=13=8+4+1=1101 (2) ，可以先往上跳 8 步，再往上跳 4 步和 1 步；也可以先往上跳 1 步，再往上跳 4 步和 8 步。无论如何跳，都只需要跳 3 次就能到达第 13 个祖先节点。
        //   据此，可以得到下面的算法。
        
        //3. 算法：在构造函数 TreeAncestor 中，预处理出每个节点 x 的第 2^i  个祖先节点，记作 pa[x][i]（若第 2^i 个祖先节点不存在则 pa[x][i]=−1）。计算方式如下：
        // 3.1. 先枚举 i，再枚举 x。相当于先算出所有爷爷节点，再算出所有爷爷的爷爷节点，依此类推。
        // 3.2. pa[x][0]=parent[x]，即父节点。
        // 3.3. pa[x][1]=pa[pa[x][0]][0]，即爷爷节点。
        // 3.4. 一般地，pa[x][i+1] = pa[pa[x][i]][i]。如果 pa[x][i]=−1 则 pa[x][i+1]=−1。
        // 3.5. 这里 i+1 至多为 ⌊log 2^n⌋。例如 n=13 时，⌊log 2^13⌋=3，至多需要预处理到第 2 3 个祖先节点。（当然，你也可以先把树高，或者每个节点的深度求出来，再据此做精细地计算。）
        //对于 getKthAncestor，需要找到 k 的二进制表示中的所有 1。可以从小到大枚举 i，如果 k 右移 i 位后的最低位为 1，就说明 k 的二进制从低到高第 i 位是 1，那么往上跳 2^i  步，
        // 将 node 更新为 pa[node][i]。如果 node=−1 则说明第 k 个祖先节点不存在。
        
        
        //复杂度分析复杂度分析
        // 时间复杂度：（预处理）初始化的时间复杂度是 O(nlogn)，单次查询的时间复杂度是 O(logk)。
        // 空间复杂度：（预处理需要）初始化的空间复杂度是 O(nlogn) 的空间，单次查询的空间复杂度是O(1)。       
        
        
        private int[][] pa;
        public TreeAncestor(int n, int[] parent) {
            int m = 32 - Integer.numberOfLeadingZeros(n);   // n 的二进制长度
            pa = new int[n][m];
            for(int i = 0; i < n; i++){
                pa[i][0] = parent[i];
            }
            for(int i = 0; i < m - 1; i++){
                for(int x = 0; x < n; x++){
                    pa[x][i + 1] = pa[x][i] < 0 ? -1 : pa[pa[x][i]][i];
                }
            }    
        }
        
        public int getKthAncestor(int node, int k) {
            int m = 32 - Integer.numberOfLeadingZeros(k);   // k 的二进制长度
            for(int i = 0; i < m && node >= 0; i++){
                if(((k >> i) & 1) > 0){ // k 的二进制从低到高第 i 位是 1
                    node = pa[node][i];                    
                }
                //if(node < 0) break;
            }
            return node;    
        }

        // 另一种写法，不断去掉 k 的最低位的 1
        public int getKthAncestor2(int node, int k) {
            while(k > 0 && node != -1){
                node = pa[node][Integer.numberOfTrailingZeros(k)];
                k &= k - 1;
            }
            return node; 
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}