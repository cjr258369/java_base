package DesignPattern.JDKSRC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @date 2022/10/27
 */
public class PrototypeSpringSRCTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        /* debug流程：
               1. 先进入到 AbstractApplicationContext.java 的 581行：public Object getBean(String name) throws BeansException {
               2. 然后从 return 语句的 getBeanFactory() 进入到：AbstractRefreshableApplicationContext.class 的 78 行：public final ConfigurableListableBeanFactory getBeanFactory() {    这里只是获取一个 bean 的工厂，直接 F7 跳出这个方法。
               3. 然后从 return 语句的 getBean(name) 进入到 AbstractBeanFactory.class 的 96行：return this.doGetBean(name, (Class)null, (Object[])null, false);
               4. 然后进入 doGetBean 方法：AbstractBeanFactory.class 的 111行：
                    1. 然后往下走，走到 164 行，判断：if (mbd.isSingleton()) {
                    2. 如果是因为我们不是配置的单例，所以进入 176 行的：} else if (mbd.isPrototype()) { //判断是否原型模式：
                    3. 然后进入核心代码：182行的：prototypeInstance = this.createBean(beanName, mbd, args);
        */
        Object bean = context.getBean("id01");  //通过 id 获取 PrototypeSpringSRC 的bean
        System.out.println("bean : " + bean);   //应该输出 孙悟空...
        //再获取一次bean
        Object bean2 = context.getBean("id01");
        System.out.println("bean2 : " + bean2); //也应该输出 孙悟空...
        System.out.println(bean == bean2);  //但因为是原型模式的复制，这里应该输出 false，他们只是属性值相同，但并不是同一个对象
    }
}
