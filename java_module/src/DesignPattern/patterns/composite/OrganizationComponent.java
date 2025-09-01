package DesignPattern.patterns.composite;

public abstract class OrganizationComponent {
    //名字
    private String name;
    //描述
    private String desc;
    
    protected void add(OrganizationComponent organizationComponent){
        //默认实现
        throw new UnsupportedOperationException();
    }
    
    protected void remove(OrganizationComponent organizationComponent){
        //默认实现
        throw new UnsupportedOperationException();        
    }
    
    //构造器
    public OrganizationComponent(String name, String desc) {
        super();
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    //打印方法，做成抽象，大家都需要实现
    protected abstract void print();
}
