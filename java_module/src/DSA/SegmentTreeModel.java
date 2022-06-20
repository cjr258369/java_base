package DSA;

import java.util.ArrayList;
import java.util.List;

/**
 * 线段树
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
 * @author chenjunran
 * @date 2022/5/26
 */
public class SegmentTreeModel {

    /**
     * 线段树的结点
     */
    static class Node {
        //左范围
        private int leftX;
        //右范围
        private int rightX;
        //范围内的最大值
        private int maxValue;
        //懒标记
        private int lazy;
        //左子树和右子树
        Node leftChild, rightChild;
        private int mid;

        public Node(int leftX, int rightX) {
            this.leftX = leftX;
            this.rightX = rightX;
            this.mid = (leftX + rightX) >> 1;
        }
    }

    /**
     * 区间更新
     *
     * @param root  树的根
     * @param left  左边界
     * @param right 右边界
     * @param value 更的新值
     */
    public void update(Node root, int left, int right, int value) {
        //不在范围内 直接返回
        if (root.leftX > right || root.rightX < left) {
            return;
        }
        //修改的区间包含当前结点
        if (root.leftX >= left && root.rightX <= right) {
            root.maxValue += value;
            root.lazy += value;
        } else {
            //动态开点
            lazyCreate(root);
            //下传lazy
            pushDown(root);

            //更新左子树
            if (left <= root.mid) {
                update(root.leftChild, left, right, value);
            }
            //更新右子树
            if (right > root.mid) {
                update(root.rightChild, left, right, value);
            }
            //上传结果
            pushUp(root);
        }
    }

    /**
     * 创建左右子树
     * @param root
     */
    public void lazyCreate(Node root) {
        if (root.leftChild == null) {
            root.leftChild = new Node(root.leftX, root.mid);
        }
        if (root.rightChild == null) {
            root.rightChild = new Node(root.mid + 1, root.rightX);
        }
    }

    /**
     * 下传lazy
     * @param root
     */
    public void pushDown(Node root) {
        if (root.leftChild == null || root.rightChild == null) {
            lazyCreate(root);
        }
        if (root.lazy > 0) {
            root.leftChild.maxValue += root.lazy;
            root.leftChild.lazy += root.lazy;
            root.rightChild.maxValue += root.lazy;
            root.rightChild.lazy += root.lazy;
            root.lazy = 0;
        }
    }

    /**
     * 上传结果
     * @param root
     */
    public void pushUp(Node root) {
        root.maxValue = Math.max(root.leftChild.maxValue, root.rightChild.maxValue);
    }

    //区间查询
    public int query(Node node, int l, int r) {
        if (l > r) {
            return 0;
        }

        if (node.leftX >= l && node.rightX <= r) {
            return node.maxValue;
        }
        pushDown(node);
        int mid = node.leftX + (node.rightX - node.leftX) / 2;
        int v = 0;
        if (l <= mid) {
            v = Math.max(v, query(node.leftChild, l, r));
        }
        if (r > mid) {
            v = Math.max(v, query(node.rightChild, l, r));
        }
        return v;
    }

    /* 使用：案例：LC 699， 对于本题，线段树节点维护的信息有：
    //1. 区间中方块的最大高度 v
    //2. 懒标记 add
    //另外，由于数轴范围很大，达到 10^8，因此我们采用动态开点。
    public List<Integer> fallingSquares(int[][] positions) {
        ArrayList<Integer> ans = new ArrayList<>();
        Node segmentTree = new Node(1, (int)1e9);
        int max = 0;
        for(int[] t : positions){
            int h = query(segmentTree, t[0], t[0] + t[1] - 1) + t[1];
            max = Math.max(max, h);
            ans.add(max);
            update(segmentTree, t[0], t[0] + t[1] - 1, h);
        }
        return ans;
    }
    //注意，因为传入的值已经是更新后的高度值，如果继续用加法会存在叠加的情况，所以模板需要作出下面的变更：
    60 - 61 行：
             root.maxValue = value;
            root.lazy = value;
    103 - 106 行：
            root.leftChild.maxValue = root.lazy;
            root.leftChild.lazy = root.lazy;
            root.rightChild.maxValue = root.lazy;
            root.rightChild.lazy = root.lazy;



       案例：LC 732：
       class MyCalendarThree {
            Node segmentTree = new Node(1, (int)1e9);

            public MyCalendarThree() {

            }

            public int book(int start, int end) {
                update(segmentTree, start, end - 1, 1);
                return segmentTree.maxValue;
            }
       }
    */
}
