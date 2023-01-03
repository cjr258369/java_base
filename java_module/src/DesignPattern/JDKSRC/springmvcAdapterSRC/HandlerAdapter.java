package DesignPattern.JDKSRC.springmvcAdapterSRC;

/**
 * @date 2022/12/30
 * 
 * 定义一个 Adapter 接口
 */
public interface HandlerAdapter {
    boolean supports(Object handler);
    void handle(Object handler);
}

//多种适配器
class SimpleHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof SimpleController;
    }

    @Override
    public void handle(Object handler) {
        ((SimpleController)handler).doSimpleHandler();
    }
}

class HttpHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof HttpController;
    }

    @Override
    public void handle(Object handler) {
        ((HttpController)handler).doHttpHandler();
    }
}

class AnnotationHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof AnnotationController;
    }

    @Override
    public void handle(Object handler) {
        ((AnnotationController)handler).doAnnotationHandler();
    }
}
