package DesignPattern.patterns.flyweight;

public class Client {
    public static void main(String[] args) {
        //创建一个工厂类
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        
        //客户1要一个新闻形式发布的网站
        WebSite webSite1 = webSiteFactory.getWebSiteCategory("新闻");
        webSite1.use(new User("用户1"));
        
        //客户2要一个博客形式发布的网站
        WebSite webSite2 = webSiteFactory.getWebSiteCategory("博客");
        webSite2.use(new User("用户2"));

        //客户3要一个博客形式发布的网站
        WebSite webSite3 = webSiteFactory.getWebSiteCategory("博客");
        webSite3.use(new User("用户3"));

        //客户4要一个博客形式发布的网站
        WebSite webSite4 = webSiteFactory.getWebSiteCategory("博客");
        webSite4.use(new User("用户4"));

        System.out.println("实际产生的对象实例的数量：" + webSiteFactory.getWebSiteCount());
    }
}
