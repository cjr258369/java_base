package DesignPattern.uml.composition;

/**
 * @date 2022/10/19
 */
public class Person {
    private IDCard card;    //聚合关系
    private Head head = new Head();     //组合关系
}
