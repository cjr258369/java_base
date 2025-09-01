package DesignPattern.patterns.composite;

import java.util.ArrayList;
import java.util.List;

//这个是 Composite，可以管理 College
public class University extends OrganizationComponent{

    //这个List中存 College
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    //构造器
    public University(String name, String desc) {
        super(name, desc);
    }

    //重写add
    @Override
    protected void add(OrganizationComponent organizationComponent) {
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
