package DesignPattern.JDKSRC;

import java.util.HashMap;
import java.util.Map;

/**
 * 组合模式在JDK集合的源码分析
 * Java 的集合类：HashMap 就使用了组合模式
 * 
 * 总结说明：
 * 1. Map 就是一个抽象的构建（类似我们的Component）
 * 2. HashMap 是一个中间的构建（Composite），实现/继承了相关方法：put，putAll 等
 * 3. Node 是 HashMap 的静态内部类，类似 Leaf 叶子节点，这里就没有 put，putAll 等一些方法
 * 4. static class Node<K, V> implements Map.Entry<K, V>
 */
public class CompositeJdkSRC {
    public static void main(String[] args) {
        //Map 是一个接口，相当于 Component
        //Map 的一个实现类：AbstractMap，他也相当于 Component，相当于做了两层
        //再往下找 AbstractMap 的实现类，终于能看到 HashMap 这些熟悉的身影了，它即继承 AbstractMap 也实现 Map 接口，
        //HashMap 它就类似于我们组合模式代码里面的 大学，学院之类的了
        //而 HashMap 里面的 Node （静态内部类）就是其叶子节点，它既是 HashMap的静态
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(0, "东游记");//直接存放叶子节点（Node）

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "西游记");
        map.put(2, "红楼梦");
        hashMap.putAll(map);//
        
        System.out.println(hashMap);
    }
    
}
