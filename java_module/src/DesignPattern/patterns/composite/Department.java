package DesignPattern.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Department extends OrganizationComponent{

    //叶子节点，已经是最底层了，不用管理其他组织了，所以不需要add，remove等方法
    //所以也不用聚合 List<OrganizationComponent>

    //构造器
    public Department(String name, String desc) {
        super(name, desc);
    }

    //输出 这个 University 里面所包含的所有学院
    @Override
    protected void print() {
        System.out.println(getName());
    }
}
