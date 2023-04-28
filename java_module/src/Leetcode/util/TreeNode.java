package Leetcode.util;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * @date 2023/2/3
 * 
 * 树的四种主要的遍历思想为：
 * 前序遍历：根结点 ---> 左子树 ---> 右子树
 * 中序遍历：左子树---> 根结点 ---> 右子树
 * 后序遍历：左子树 ---> 右子树 ---> 根结点
 * 层次遍历：只需按层次遍历即可
 * 
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    
    public TreeNode() {}
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    //前序遍历二叉树，并以 String 形式输出
    @Override
    public String toString() {
        return serializeString(this, new StringBuilder()).toString();
    }
    
    public StringBuilder serializeString(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("null,");
        }else{
            sb.append(root.val).append(',');
            sb = serializeString(root.left, sb);
            sb = serializeString(root.right, sb);
        }
        return sb;
    }
    
    //根据前序遍历出来的字符串list，生成一颗二叉树
    //用法：TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1", "2", "Null", "Null", "3", "4", "Null", "Null", "5"));
    //用法：TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1","2","4","8","Null","Null","9","Null","Null","5","10","Null","Null","11","Null","Null","3","6","Null","Null","7","Null","Null")))
    public static TreeNode deserializeList(List<String> list){
        if(list.isEmpty()){
            return null;
        }
        if("None".equals(list.get(0)) || "Null".equals(list.get(0)) || "null".equals(list.get(0))){
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        root.left = deserializeList(list);
        root.right = deserializeList(list);
        return root;        
    }
    
    //根据层序遍历出来的字符串list，生成一颗二叉树
    //用法：TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1", "2", "Null", "Null", "3", "4", "Null", "Null", "5"));
    //用法：TreeNode.deserializeList(new LinkedList<>(Arrays.asList("1","2","4","8","Null","Null","9","Null","Null","5","10","Null","Null","11","Null","Null","3","6","Null","Null","7","Null","Null")))
    public static TreeNode deserializeList2(List<String> list){
        if(list.isEmpty()){
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        queue.offer(root);
        int idx = 1;
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();
            if(idx >= list.size()){
                break;
            }
            String leftVal = list.get(idx++);
            if(!"None".equals(leftVal) && !"Null".equals(leftVal) && !"null".equals(leftVal)){
                curr.left = new TreeNode(Integer.parseInt(leftVal));
                queue.offer(curr.left);                
            }
            if(idx >= list.size()){
                break;
            }
            String rightVal = list.get(idx++);
            if(!"None".equals(rightVal) && !"Null".equals(rightVal) && !"null".equals(rightVal)){
                curr.right = new TreeNode(Integer.parseInt(rightVal));
                queue.offer(curr.right);
            }
        }
        return root;      
    }
    
    
    
    /**
     * 前序遍历[递归]
     * 根结点 ---> 左子树 ---> 右子树
     * @param root 树的根节点
     */
    public void preOrderTraverse1(TreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.val + " , ");
        preOrderTraverse1(root.left);
        preOrderTraverse1(root.right);        
    }
    
    /**
     * 中序遍历[递归]
     * 左子树---> 根结点 ---> 右子树
     * @param root 树的根节点
     */
    public void inOrderTraverse1(TreeNode root){
        if(root == null){
            return;
        }
        inOrderTraverse1(root.left);
        System.out.println(root.val + " , ");
        inOrderTraverse1(root.right);
    }
    
    /**
     * 后序遍历[递归]
     * 左子树 ---> 右子树 ---> 根结点
     * @param root 树的根节点
     */
    public void postOrderTraverse1(TreeNode root){
        if(root == null){
            return;
        }
        inOrderTraverse1(root.left);
        inOrderTraverse1(root.right);
        System.out.println(root.val + " , ");
    }
    
    /**
     * 层序遍历[迭代]
     * 按层输出，也可以直接取值出来，不按层区分
     * @param root 树的根节点
     */
    public void levelTraverse(TreeNode root){
        if(root == null){
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            System.out.println("new Level ：");
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                System.out.print(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }            
        }        
    }
    
    /**
     * 树的深度[递归] 
     * @param root 树的根节点
     * @return 树的最大深度
     */
    public int treeDept(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max(treeDept(root.left), treeDept(root.right));
    }
}
