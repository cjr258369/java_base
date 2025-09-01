package DesignPattern.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class College extends OrganizationComponent{
    
    //这个List中存 Department
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    //构造器
    public College(String name, String desc) {
        super(name, desc);
    }

    //重写add
    @Override
    protected void add(OrganizationComponent organizationComponent) {
        //将来在实际业务中 College 的 add 方法和 University 的 add 方法不一定完全相同
        organizationComponents.add(organizationComponent);
    }

    //重写 remove
    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }

    //输出 这个 University 里面所包含的所有学院
    @Override
    protected void print() {
        System.out.println("========================" + getName() + "========================");
        organizationComponents.forEach(OrganizationComponent::print);
    }
}
