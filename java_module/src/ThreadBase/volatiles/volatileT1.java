package ThreadBase.volatiles;

public class volatileT1 {
    volatile boolean flag;

    //通过在输出文件的路径下执行： javap -v volatileT1.class 反编译，可以看到字节码里添加的内存屏障
    public static void main(String[] args) {
        System.out.println("hello volatile---");
    }
}
