package DesignPattern.patterns.singletonPattern;

/**
 * @date 2022/10/20
 * 
 * 在 JDK 中，java.lang.Runtime 就是经典的单例模式
 */
public class TestRumtime {
    public static void main(String[] args) {
        //Runtime
        
        //源码截取：
        //public class Runtime {
        // 类装载就初始化的静态常量
        //    private static java.lang.Runtime currentRuntime = new java.lang.Runtime();
        // 对外提供的公共的获得实例的方法
        //    public static java.lang.Runtime getRuntime() {
        //        return currentRuntime;
        //    }
        // 私有化的构造函数
        //    private Runtime() {}
    }
}
