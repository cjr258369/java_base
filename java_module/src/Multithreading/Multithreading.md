这个包下，是多线程的实践：

会尝试几种创建线程的方式：（以及常见面试题）
1. 实现 Runnable 接口【优先使用】
2. 实现 Callable 接口【有返回值，可抛出异常】
3. 继承 Thread 类【java 不支持多继承】
4. 使用线程池 【底层都是实现 run 方法】

1,2,3的对比：
1. 采用 Runnable、Callable 接口的方式创建子线程，线程类只是实现了 Runnable 或者 Callable 接口，还可以继承其他类
2. 使用继承 Thread 类的方式创建多线程时，编写简单，如果需要访问当前线程，无需使用 Thread.currentThread()，直接使用 this即可获得当前线程，但由于 java 不支持多继承，所以不能再继承自其他类了。
3. 相比于 Runnable 方法，Callable 方法：A、可以有返回值（支持泛型返回值）。B、可以抛出异常，被外面操作捕获，获取异常信息。C、但需要借助 FutureTask 类，比如获取返回结果。
4. 而 Future 类 ：A、可以对具体的 Runnable 、Callable 接口任务的执行结果进行取消、查询是否完成、获取结果等等。B、FutureTask 是 Future 接口的唯一实现类。
    C、FutureTask 同时实现了 Runnable 、Future接口，它即可作为 Runnable 被线程执行，也可以作为 Future 得到 Callable 的返回值。

常见面试题：

