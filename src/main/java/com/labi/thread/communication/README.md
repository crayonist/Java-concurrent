### **线程的通知与等待**
---
- wait(): 

    1 调用wait()的线程要先获取该对象的监视器锁，否则会抛出异常
    ```
    java.lang.IllegalMonitorStateException
    ```
    获取对象监视锁：<br>
    
    (1) 执行该sychronized同步代码块时，使用该共享对象作为锁参数
    ```
      sychronized(共享变量) {
        // ...
      }
    ```
    (2) 调用该共享变量的方法，并且该方法使用了sychronized修饰
    ```
      sychronized void foo(int a, int b) {
        // ...
  　　}
    ```
    
    2 wait:线程挂起<br>
    在调用wait函数线程阻塞挂起后，以下情况会使该线程重新进入就绪状态：
    - 其他线程调用了该共享对象的notify()或notifyAll()方法来唤醒；
    - 其他线程调用了该线程的interrupt()中断方法，则该线程会抛出中断异常java.lang.InterruptedException
    
    
    
- notity():

- 虚假唤醒：
    + 一个线程在wait阻塞等待的状态下，除了其他线程调用notify、notifyAll，也可能会变为运行状态，称为虚假唤醒。
