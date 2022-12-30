package DesignPattern.patterns.adapter.interfaceAdapter;

/**
 * @date 2022/11/2
 * 
 * 23种设计模式之适配器模式：接口适配器
 * 
 * 在 AbsAdapter 中默认空实现 interface4 的所有方法
 * 使用时，可以使用匿名内部类，作为 它的子类，实现具体需要实现的方法即可。
 * 
 * 源码分析直接看笔记里的 Android 的 AnimatorListener listener
 */
public class Client {
    public static void main(String[] args) {
        AbsAdapter absAdapter = new AbsAdapter() {
            //只需要去覆盖我们需要使用的方法
            @Override
            public void m1() {
                System.out.println("使用了 m1");
            }
        };

        absAdapter.m1();
    }
}
