**线程创建的方式：**
- 1.继承Thread类
- 2.实现Runable接口
- 3.实现Callable接口（FutureTask的方式）

**优缺点：**
- Thread:
    + 缺点：　
        + Java为单继承，继承Thread类后无法再继承；
        + 任务与代码没有分离，当多个线程执行一样的任务时需要多份任务代码；
        + 没法拿到任务的返回结果。
    + 优点：
        + 继承的方式很方便可以传参，如通过构造函数或者setProperty的方式；
        Runable的方式只能使用主线程的final变量；
- Runable:
    + 缺点：没法拿到任务的返回结果。
    + 优点：解决Java单继承的限制；多个线程能使用一份任务代码。
- Callable(FutureTask)：
    + 能够拿到任务的返回值，FutureTask实际上继承与Runable接口。