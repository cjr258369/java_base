package DesignPattern.patterns.adapter.springmvcAdapterSRC;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2022/12/30
 */
public class DispatchServlet_my {
    public static List<HandlerAdapter> handlerAdapters = new ArrayList<>();
    public DispatchServlet_my(){
        handlerAdapters.add(new HttpHandlerAdapter());
        handlerAdapters.add(new SimpleHandlerAdapter());
        handlerAdapters.add(new AnnotationHandlerAdapter());      
    }
    
    public void doDispatch(){
        //此处模拟 SpringMVC 从 request 取 Handler 的对象
        //适配器可以获取到希望的 Controller
        HttpController controller = new HttpController();
        //SimpleController controller = new SimpleController();
        //AnnotationController controller = new AnnotationController();
        
        //得到对应的适配器
        HandlerAdapter adapter = getHandler(controller);
        //通过适配器执行对应 Controller 对应方法
        adapter.handle(controller);
    }
    
    public HandlerAdapter getHandler(Controller controller){
        //遍历：根据得到的Controller 返回对应的适配器
        for (HandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(controller)){
                return adapter;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new DispatchServlet_my().doDispatch();
    }
}
