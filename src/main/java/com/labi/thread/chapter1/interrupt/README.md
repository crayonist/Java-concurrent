### **线程中断**
---

线程中断：Java中的线程中断是一种线程间的协作模式，通过设置线程的中断标志并不能直接终止该线程的执行，而是被中断的线程根据中断状态自行处理。
1. 几个中断方法：
+  void interrupt()
    