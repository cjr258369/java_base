package DesignPattern.patterns.adapter.springmvcAdapterSRC;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * @date 2022/12/29
 * 
 * 在 SpringMVC 中， HandleAdapter 就使用了适配器模式
 */
public class AdapterSpringSRC {
    /*
    DispatcherServlet
1. 适配器模式在 SpringMVC 框架应用的源码分析：SpringMVC 中的 HandleAdapter 就使用了适配器模式
2. SpringMVC处理请求的流程：xxx.jpg
3. 使用 HandleAdapter 的原因分析：
    1. 可以看到处理器的类型不同，有多重实现方式，那么调用方式就不是确定的，如果需要直接调用 Controller 方法，需要调用的时候就得不断的使用 if else 来进行判断是哪一种子类，然后执行，那么如果后面要扩展 Controller，就得修改原来的代码，这样就违背了 OCP 原则。
4. 源码分析：
    1. DispatcherServlet
    2. 打开 DispatcherServlet 源码类，当一个请求进来，会执行它的 doDispatch() 方法
    3. 这个方法里面有一个核心方法：mappedHandler = this.getHandler(processedRequest); 依据请求，得到一个 Handler，这里的 Handler 其实就是 Controller 控制器
    4. 通过这个 mappedHandler，下面再获取它对应的适配器：HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler()); 这样做是为了使得不同的 Handler 要使用不同的适配器去调用不同的方法。
    5. 进入这个方法（getHandlerAdapter()）的源码：
        1. 它的返回值是：HandlerAdapter，这是一个接口。
        2. 可以看到它有很多个实现：
        3. getHandlerAdapter() 方法内部写了一个循环，也即当前的 Adapter适配器如果 supports() 当前的请求，那么就返回，而这个 supports 方法是 interface 接口里面的一个方法，所有实现类都做了自己的实现。
    6. 当通过 getHandlerAdapter() 拿到适配器后，继续往下会执行：mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
        1. 适配器的 handle() 方法，handle() 方法也是接口里面的方法，所有实现类，均需要实现。
        2. handle 也是实际的调用具体的 Controller。
    7. 整体流程：
        1. Spring 定义了一个接口，使得每一种 Controller 都有一种对应的适配器实现类。
        2. 适配器代替 Controller 执行相应的方法
        3. 扩展 Controller 时，只需要增加一个适配器类就完成了 SpringMVC 的扩展了。
        
        
        
        更进一步的说明源码：手写 SpringMVC 通过适配器设计模式获取到对应的 Controller 的源码。
     */
    
    
    
}
