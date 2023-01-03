package DesignPattern.JDKSRC.springmvcAdapterSRC;

/**
 * @date 2022/12/30
 */

//多种 Controller 实现
public interface Controller{
    
}


class HttpController implements Controller {
    public void doHttpHandler(){
        System.out.println("http...");
    }
}

class SimpleController implements Controller {
    public void doSimpleHandler(){
        System.out.println("simple...");
    }
}

class AnnotationController implements Controller {
    public void doAnnotationHandler(){
        System.out.println("annotation...");
    }
}
