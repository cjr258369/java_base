package DSA;

/**
 * 线段树简介
 * 具体的图文，自行查看原文，下方仅做简单介绍：
 * 详解原文：https://leetcode.cn/problems/range-module/solution/by-lfool-eo50/
 *
 * 线段树相关的部分题目：
 * 715. Range 模块
 * 307. 区域和检索 - 数组可修改
 * 933. 最近的请求次数
 * 732. 我的日程安排表 III
 * 699. 掉落的方块
 *
 * 线段树解决的是「区间和」的问题，且该「区间」会被修改
 * 什么意思呢？举个简单的例子，对于nums = [1, 2, 3, 4, 5]
 * 如果我们需要多次求某一个区间的和，是不是首先想到了利用「前缀和」。关于前缀和的详细介绍可见 前缀和数组
 * 但是如果 nums 会被修改呢？比如：
 * 把第 i 个元素修改成 x
 * 把第 i 个元素增加 x
 * 把区间 [i, j] 内的元素都增加 x
 * 此时，如果我们再使用「前缀和」，就没那么高效了。因为每一次更新，前缀和数组必须也随之更新，时间复杂度为 O(n)
 * 既然「前缀和」在这种场景下没那么高效了，所以就有了今天要介绍的「线段树」
 *
 * 上面提到过：线段树解决的是「区间和」的问题，且该「区间」会被修改，所以线段树主要实现两个方法：「求区间和」&&「修改区间」，且时间复杂度均为 O(logn)
 * 始终记住一句话：线段树的每个节点代表一个区间。
 *
 * 线段树的每个节点代表一个区间，而节点的值就是该区间的和 (其实还可以根据题目问题，改变表示的含义！！)，比如：
 * 数字之和「总数字之和 = 左区间数字之和 + 右区间数字之和」
 * 最大公因数 (GCD)「总 GCD = gcd(左区间 GCD, 右区间 GCD)」
 * 最大值「总最大值 = max(左区间最大值，右区间最大值)」
 * 不符合区间加法的例子：
 * 众数「只知道左右区间的众数，没法求总区间的众数」
 * 01 序列的最长连续零「只知道左右区间的最长连续零，没法知道总的最长连续零」
 *
 * 线段树的基本结构：
 * class Node {
 *     // 左右孩子节点
 *     Node left, right;
 *     // 当前节点值
 *     int val;
 * }
 *
 * 线段树的建立
 * 如果题目中给了具体的区间范围，我们根据该范围建立线段树：
 * public void buildTree(Node node, int start, int end) {
 *     // 到达叶子节点
 *     if (start == end) {
 *         node.val = arr[start];
 *         return ;
 *     }
 *     int mid = (start + end) >> 1;
 *     buildTree(node.left, start, mid);
 *     buildTree(node.right, mid + 1, end);
 *     // 向上更新
 *     pushUp(node);
 * }
 * // 向上更新
 * private void pushUp(Node node) {
 *     node.val = node.left.val + node.right.val;
 * }
 * 但是很多时候，题目中都没有给出很具体的范围，只有数据的取值范围，一般都很大，所以我们更常用的是「动态开点」
 * 「动态开点」一般是在「更新」或「查询」的时候动态的建立节点，具体可见下面的更新和查询操作。
 *
 * 线段树的更新
 * 我看大多数教程都是把更新分为两种：「点更新」和「区间更新」。其实这两种可以合并成一种，因为「点更新」不就是更新长度为 1 的区间嘛！！
 * 更新区间的前提是找到需要更新的区间，所以和查询的思路很相似
 * ....... 自行查看详细文章
 *
 **/
/**
 * @Description: 线段树（动态开点）
 * @author chenjunran
 * @date 2022/6/20
 */
public class SegmentTreeDynamic {
    private int N = (int) 1e9;
    private Node root = new Node();
    class Node {
        Node left, right;
        int val, add;
    }
    public void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            node.val += (end - start + 1) * val;
            node.add = val;
            return ;
        }
        int mid = (start + end) >> 1;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) update(node.left, start, mid, l, r, val);
        if (r > mid) update(node.right, mid + 1, end, l, r, val);
        pushUp(node);
    }
    public int query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r) return node.val;
        int mid = (start + end) >> 1, ans = 0;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) ans += query(node.left, start, mid, l, r);
        if (r > mid) ans += query(node.right, mid + 1, end, l, r);
        return ans;
    }
    private void pushUp(Node node) {
        node.val = node.left.val + node.right.val;
    }
    private void pushDown(Node node, int leftNum, int rightNum) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return ;
        node.left.val += node.add * leftNum;
        node.right.val += node.add * rightNum;
        node.left.add = node.add;
        node.right.add = node.add;
        node.add = 0;
    }
}

//含注释的代码：
class segmentTree {
    class Node {
        // 左右孩子节点
        Node left, right;
        // 当前节点值
        int val;
        // 懒惰标记
        int add;
    }
    //实现下推懒惰标记的函数：
    private void pushDown(Node node, int leftNum, int rightNum) {
        // 动态开点
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        // 如果 add 为 0，表示没有标记
        if (node.add == 0) return ;
        // 当前节点加上标记值
        // 注意：需要✖️该子树所有叶子节点
        node.left.val += node.add * leftNum;
        node.right.val += node.add * rightNum;
        // 把标记下推给孩子节点
        node.left.add = node.add;
        node.right.add = node.add;
        // 取消当前节点标记
        node.add = 0;
    }

    //更新的函数：
    public void update(Node node, int start, int end, int l, int r, int val) {
        // 找到满足要求的区间
        if (l <= start && end <= r) {
            // 区间节点加上更新值
            // 注意：需要乘以该子树所有叶子节点
            node.val += (end - start + 1) * val;
            // 添加懒惰标记
            node.add = val;
            return ;
        }
        int mid = (start + end) >> 1;
        // 下推标记
        pushDown(node, mid - start + 1, end - mid);
        // [start, mid] 和 [l, r] 可能有交集，遍历左孩子区间
        if (l <= mid) update(node.left, start, mid, l, r, val);
        // [mid + 1, end] 和 [l, r] 可能有交集，遍历右孩子区间
        if (r > mid) update(node.right, mid + 1, end, l, r, val);
        // 向上更新
        pushUp(node);
    }
    //线段树的查询
    // 在区间 [start, end] 中查询区间 [l, r] 的结果，即 [l ,r] 保持不变
    // 对于上面的例子，应该这样调用该函数：query(root, 0, 4, 2, 4)
    public int query(Node node, int start, int end, int l, int r) {
        // 区间 [l ,r] 完全包含区间 [start, end]
        // 例如：[2, 4] = [2, 2] + [3, 4]，当 [start, end] = [2, 2] 或者 [start, end] = [3, 4]，直接返回
        if (l <= start && end <= r) return node.val;
        // 把当前区间 [start, end] 均分得到左右孩子的区间范围
        // node 左孩子区间 [start, mid]
        // node 左孩子区间 [mid + 1, end]
        int mid = (start + end) >> 1, ans = 0;
        // 下推标记
        pushDown(node, mid - start + 1, end - mid);
        // [start, mid] 和 [l, r] 可能有交集，遍历左孩子区间
        if (l <= mid) ans += query(node.left, start, mid, l, r);
        // [mid + 1, end] 和 [l, r] 可能有交集，遍历右孩子区间
        if (r > mid) ans += query(node.right, mid + 1, end, l, r);
        // ans 把左右子树的结果都累加起来了，与树的后续遍历同理
        return ans;
    }

    private void pushUp(Node node) {
        node.val = node.left.val + node.right.val;
    }

}