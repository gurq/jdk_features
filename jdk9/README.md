# JDK9新特性导读

## 1. 官方文档

> https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-C23AFD78-C777-460B-8ACE-58BE5EA681F6

参考文档：

> https://www.ibm.com/developerworks/cn/java/the-new-features-of-Java-9/

## 2 不用怎么关心的改动

### 2.1 JShell

一个全新的命令行工具，可以在里面直接写代码调用并运行

> http://openjdk.java.net/jeps/222

### 2.2 Jcmd

命令行工具

> https://docs.oracle.com/javase/9/tools/jcmd.htm#JSWOR743

### 2.3 JLink

模块化打包工具

> https://docs.oracle.com/javase/9/tools/jlink.htm#JSWOR-GUID-CECAC52B-CFEE-46CB-8166-F17A8E9280E9

### 2.4 改进安全性能

Java 9 新增了 4 个 SHA- 3 哈希算法，SHA3-224、SHA3-256、SHA3-384 和 S HA3-512。另外也增加了通过 java.security.SecureRandom 生成使用 DRBG 算法的强随机数

> https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-71A09701-7412-4499-A88D-53FA8BFBD3D0h 

### 2.5 AWT

为了能写出更好的可视化程序，Java对AWT的易用性/性能/兼容性等方面做了诸多改进

> https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-F3F094FE-6542-441B-AA5D-766AE21D0A59

## 3 重点关心的改动

### 3.1 模块化

之前通过Maven或者Gradle可以实现项目模块化，现在Java自己原生也支持模块化了。

并且现在可以在打包的时候只打包用到的方法，打出来的Jar包更小，运行时占用内存更小。

    见代码

### 3.2 集合

增加新的初始化方法，见代码【注意：是不可变集合】

    List.of(); 
    List.of("Hello", "World"); 
    List.of(1, 2, 3);
    Set.of(); 
    Set.of("Hello", "World"); 
    Set.of(1, 2, 3);
    Map.of();
    Map.of("Hello", 1, "World", 2);

stream增加了新的方法，见代码

### 3.3 Optional增加新方法

见代码

### 3.4 新增进程管理ProcessHandle

Java9之前，对本地进程是没法看的，现在可以查看本地的进程了（但是仍然不可以操作）

见代码

### 3.5 新增响应式编程Flow

响应式编程(Reactive Stream-响应式流/反应流)之前在.net，RXJava已经得到了广泛的运用，是一套基于发布/订阅模式的数据处理规范。

从Java9开始，基于响应式编程的设计理念，完成了响应式编程的Java实现，即Flow

    Subscription 接口定义了连接发布者和订阅者的方法
    Publisher<T> 接口定义了发布者的方法
    Subscriber<T> 接口定义了订阅者的方法
    Processor<T,R> 接口定义了处理器
    
    见代码
    
参考文档：

> https://www.cnblogs.com/1994july/p/12384558.html
> https://yanbin.blog/java-9-talk-reactive-stream/#more-8877

### 3.6 变量句柄和方法句柄

对反射的增强，只是我感觉似乎没有直接获取Class方便，是否要使用句柄要看使用场景吧

    arrayConstructor：创建指定类型的数组。
    arrayLength：获取指定类型的数组的大小。
    varHandleInvoker 和 varHandleExactInvoker：调用 VarHandle 中的访问模式方法。
    zero：返回一个类型的默认值。
    empty：返回 MethodType 的返回值类型的默认值。
    loop、countedLoop、iteratedLoop、whileLoop 和 doWhileLoop：创建不同类型的循环，包括 for 循环、while 循环 和 do-while 循环。
    tryFinally：把对方法句柄的调用封装在 try-finally 语句中。

### 3.7 CompletableFuture 增加新方法

    completeAsync 使用一个异步任务来获取结果并完成该 CompletableFuture
    orTimeout 在 CompletableFuture 没有在给定的超时时间之前完成，使用 TimeoutException 异常来完成 CompletableFuture
    completeOnTimeout 与 orTimeout 类似，只不过它在超时时使用给定的值来完成 CompletableFuture
    Thread.onSpinWait 方法在当前线程需要使用忙循环来等待时，可以提高等待的效率

### 3.8 IO新特性

增加了三个更方便读写流的方法

    readAllBytes：读取 InputStream 中的所有剩余字节。
    readNBytes： 从 InputStream 中读取指定数量的字节到数组中。
    transferTo：读取 InputStream 中的全部字节并写入到指定的 OutputStream 中 。

### 3.9 JVM

> https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-B6CD8C25-FD93-4CAA-9286-19A39CC0F26A

#### 3.9.1 日志

Java9引入了新的日志框架，对JVM日志和GC日志做了统一。可以使用新的命令行选项-Xlog 来控制 JVM 上 所有组件的日志记录。该日志记录系统可以设置输出的日志消息的标签、级别、修饰符和输出目标等

#### 3.9.2 GC

- 增强G1垃圾回收器的可用性、确定性和性能，并将G1设置为Java9开始的默认垃圾回收器

- 删除了Java8就废弃的GC组合：DefNew + CMS / ParNew + SerialOld / Incremental CMS

- 废弃了CMS垃圾回收器

### 3.10 接口改进

现在接口允许实现自己的private方法，不需要被实现该接口的类实现且不可被调用

    见代码



















