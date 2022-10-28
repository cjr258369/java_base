package DesignPattern.patterns.prototype.deepClone;

import java.io.Serializable;

/**
 * @date 2022/10/27
 */
public class SheepDeepClone1 implements Serializable, Cloneable{
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;
    private String color;

    public SheepDeepClone1(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //因为该对象的属性，都是基本属性，所以使用默认的 clone 方法
    @Override
    protected Object clone() {
        SheepDeepClone1 sheep = null;
        try {
            sheep = (SheepDeepClone1) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return sheep;
    }

    @Override
    public String toString() {
        return "SheepDeepClone1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
