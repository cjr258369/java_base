package ThreadBase.cas;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User zs = new User("张三", 22);
        User ls = new User("李四", 28);

        //要先装进去，上面只定义了能装什么类型的数据，这里要把具体的对象装进去
        atomicReference.set(zs);

        //老配方：atomicReference.compareAndSet（zs,ls）,先比较内存里面的值是不是 "张三"，是的话就修改为 "李四"，最后打印 get 出来的值
        System.out.println(atomicReference.compareAndSet(zs, ls) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(zs, ls) + "\t" + atomicReference.get().toString());
    }
}

class User{
    String userName;
    int age;
    public User(String userName, int age){
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
