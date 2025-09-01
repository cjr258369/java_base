package DesignPattern.patterns.flyweight;

public class ConcreteWebSite extends WebSite{
    
    /**
     * 网站发布的形式（类型）
     * 这个 type 是共享部分，是内部状态
     */
    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站的发布形式为：" + type + " , 用户：" + user.getName() + " 正在使用中。");        
    }
}
