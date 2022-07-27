package ThreadBase.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 单线程环境下，使用 AtomicStampedReference 解决 ABA 问题
 * @author chenjunran
 * @date 2022/7/27
 */
public class AtomicStampedDemo {
    public static void main(String[] args) {
        Book javaBook = new Book(1, "javaBook");

        AtomicStampedReference<Book> atomicStampedReference = new AtomicStampedReference<>(javaBook, 1);
        System.out.println("查看当前的引用："+atomicStampedReference.getReference()+" ， 查看当前流水号："+atomicStampedReference.getStamp());

        Book mysqlBook = new Book(2, "mysqlBook");
        boolean b = atomicStampedReference.compareAndSet(javaBook, mysqlBook, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println("compareAndSet 执行结果：" + b + " , 再查看当前的引用："+atomicStampedReference.getReference()+" ， 再查看当前流水号："+atomicStampedReference.getStamp());

        //解决：ABA 的经典问题，如果有线程再把mysqlBook 换回 JavaBook，如果其他线程仅检查内容，那肯定没变，但如果检查流水号，就发现流水号变了。
        boolean c = atomicStampedReference.compareAndSet(mysqlBook, javaBook, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println("compareAndSet 执行结果：" + c + " , 再查看当前的引用："+atomicStampedReference.getReference()+" ， 再查看当前流水号："+atomicStampedReference.getStamp());

    }
}

class Book{
    private int id;
    private String bookName;

    public Book(int id, String bookName){
        this.id = id;
        this.bookName = bookName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
