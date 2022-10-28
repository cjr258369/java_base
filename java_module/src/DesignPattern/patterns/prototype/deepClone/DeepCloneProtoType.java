package DesignPattern.patterns.prototype.deepClone;

import java.io.*;

/**
 * @date 2022/10/28
 */
public class DeepCloneProtoType implements Serializable, Cloneable {
    private String name;    //基本类型属性
    private SheepDeepClone1 sheep;  //引用类型属性

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SheepDeepClone1 getSheep() {
        return sheep;
    }

    public void setSheep(SheepDeepClone1 sheep) {
        this.sheep = sheep;
    }

    public DeepCloneProtoType() {
        
    }

    //深拷贝方式一：重写 clone 方法来实现深拷贝。
    @Override
    public DeepCloneProtoType clone() {
        DeepCloneProtoType clone = null;
        try {
            //这里先完成对基本数据类型（属性）的复制
            clone = (DeepCloneProtoType) super.clone();
            //对所有的引用类型，都要单独处理
            clone.setSheep((SheepDeepClone1) sheep.clone());            
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return clone;
    }

    //深拷贝方式二：通过对象的序列化来实现【推荐】。
    public DeepCloneProtoType deepClone(){
        //创建字节流对象
        //输出流
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        //输入流
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        
        try{
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            //把当前对象，以对象流的方式输出【这样的话，如果对象有引用类型的数据，引用类型的成员变量也会以流的形式输出】
            oos.writeObject(this);
            
            //反序列化构造出一个新的对象实例
            //用输入流读进来
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepCloneProtoType copy = (DeepCloneProtoType) ois.readObject();
            return copy;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            //使用try catch 安静的关闭流
            try{
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            }catch (Exception e2){
                e2.printStackTrace();    
            }
        }
    }
}
