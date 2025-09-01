package DesignPattern.patterns.flyweight;

import java.util.HashMap;

//网站工厂类，根据需求返回一个网站
public class WebSiteFactory {
    
    //集合充当池的作用
    private HashMap<String, ConcreteWebSite> pool = new HashMap<>();
    
    //根据网站的类型，返回一个网站，如果没有就创建一个网站并放入到 池 中
    public WebSite getWebSiteCategory(String type){
        if(!pool.containsKey(type)){
            pool.put(type, new ConcreteWebSite(type));
        }
        return pool.get(type);
    }
    
    //获取网站分类的总数，用于一会验证没有分类的时候，是否新建了（也即获取池中有多少个网站类型）
    public int getWebSiteCount(){
        return pool.size();
    }
}
