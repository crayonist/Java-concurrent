### **并发包中的原子操作类（AtomicLong为例）**
---

<h5>AtomicLong是juc包下的原子性递增或递减类，Java8中其内部是使用Unsafe原子类来实现的
<br><br>
+ 一睹下源码：
```java
    public class AtomicLong extends Number implements java.io.Serializable {
        private static final Unsafe unsafe = Unsafe.getUnsafe();
        
        // 存放变量value的偏移量
        private static final long valueOffset;
        
        // 判断JVM是否支持Long类型无锁CAS，native方法
        static final boolean VM_SUPPORTS_LONG_CAS = VMSupportsCS8(); 
        private static native boolean VMSupportsCS8();
        
        static {
            try{
                // 获取value在AtomicLong中的偏移量
                valueOffset = unsafe.objectFieldOffset(AtomicLong.class.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
        
        private volatile long value; // 实际变量值
    }
```
    1.AtomicLong中可以通过unsafe.getUnsafe()是因为AtomicLong也是在rt.jar包下，是通过BootStrap类加载的。
    2.value被生命为volatile，是为了在多线程下保证内存可见性，value是具体存放计数的变量。

+ AtomicLong主要函数
    + incrementAndGet(): 
    原子性设置value值为原始值+1，返回值为递增后的值，相当于++i操作
    ```
      public final long incrementAndGet() {
          return unsafe.getAndAddLong(this, valueOffset, 1L) + 1L;
      }
    ```
    + decrementAndGet():
    原子性设置value值为原始值-1，返回值为递减后的值，相当于--i操作
    ```
       public final long decrementAndGet() {
           return unsafe.getAndAddLong(this, valueOffset, -1L) - 1L; 
       }
    ```
    + getAndIncrement():
    原子性设置value值为原始值+1，返回值为原始值，相当于i++操作
    ```
       public final long getAndIncrement() {
          return unsafe.getAndAddLong(this, valueOffset, 1L);
       }
    ```
    + getAndDecrement():
    ```
       public final long getAndDecrement() {
          return unsafe.getAndAddLong(this, valueOffset, -1L);
       }
    ```
    + 来看一下以上都在内部调用的getAndAddLong()这个unsafe的原子性函数
        + 参数：
            + 第一个参数：AtomicLong实例的引用；
            + 第二个参数：value变量在AtomicLong中的偏移值；
            + 第三个参数：是要设置的第二个变量的值。
        + 在JDK7中的实现：
        ```
          /**
           * 每个线程是先拿到变量的当前值（由于value是volatile变量，所以这里拿到的是最新值）
           * 之后在各自线程的工作内存中对其进行+1操作，
           * 使用CAS修改变量的值，如果设置失败，则循环继续尝试，直到设置成功。
           */
          public final long getAndIncrement() {
              while (true) {
                  long current = get();
                  long next = current + 1;
                  if (compareAndSet(current, next)) 
                      return current;
              }
          }
        ```
        + 在JDK8中被原子类Unsafe内置为getAndAddLong()方法（考虑到其他地方会用到，内置提高了复用性）
        ```
           public final long getAndAddLong(Object paramObject, long paramLong1, long paramLong2) {
              long l;
              do {
                  l = getLongVolatile(paramObject, paramLong1);
              } while (!compareAndSwapLong(paramObject, paramLong1, l, l + paramLong2));
              return l;
           }
        ```
    + compareAndSet(long expect, long update)
    ```
       /**
        * 原子变量中的value值等于expect，则使用update值更新该值并返回true，否则返回false
        */
       public final boolean compareAndSet(long expect, long update) {
          return unsafe.compareAndSwapLong(this, valueOffset, expect, update);
       } 
    ```
    
                
