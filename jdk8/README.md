# JDK8新特性导读

## 1. 官方文档

> https://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html
>
> https://www.oracle.com/cn/java/technologies/8-whats-new.html

## 2 不用怎么关心的改动

### 2.1 Compact Profiles

减少了Java的内存占用

> https://docs.oracle.com/javase/8/docs/technotes/guides/compactprofiles/

### 2.2 JDK 8 Security Enhancements

安全性增强

> https://docs.oracle.com/javase/8/docs/technotes/guides/security/enhancements-8.html

### 2.3 JavaFX

JavaFX更新了，明明上一般才更新了Swing，后面还会对GUI的控件更新的，我并不考虑写GUI，就放在这里了，感兴趣的自己去了解

> https://docs.oracle.com/javase/8/javase-clienttechnologies.htm

### 2.4 Tools Enhancements in JDK 8

-XX:+CheckEndorsedAndExtDirs、-XX:+ResourceManagement等一些java、jjs、javaDoc工具，其中一些是商业版（JDK）中才有的，免费版（openJDK）没有

> https://docs.oracle.com/javase/8/docs/technotes/tools/enhancements-8.html

### 2.5 Internationalization Enhancements in JDK 8

国际化方面的增强：支持到Unicode 6.2.0. 、Calendar格式化的样式、Locale支持了更多方法

> https://docs.oracle.com/javase/8/docs/technotes/guides/intl/enhancements.8.html

### 2.6 Java Scripting Enhancements

脚本增强

> https://docs.oracle.com/javase/8/docs/technotes/guides/scripting/enhancements.html#jdk8

### 2.7 Enhancements to Pack200

Pack200引擎已进行了相应更新，以确保有效压缩Java SE 8类文件

> https://docs.oracle.com/javase/8/docs/technotes/guides/pack200/enhancements.html

### 2.8 JDBC

移除JDBC-ODBC Bridge

> https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/

### 2.9 Java DB

包含了Java DB(Derby) 10.10，Java自带的轻量级数据库

> 但是我并没有在JDK里找到

### 2.10 Networking Enhancements in Java SE 8

网络增强

> https://docs.oracle.com/javase/8/docs/technotes/guides/net/enhancements-8.0.html

### 2.11 Java XML - JAXP

增加了用于解析XML的JAXP 1.6

### 2.12 Java虚拟机

AES加密方面增强

### 2.13 Java Mission Control

一个Java监控工具，升级到5.3

## 3 重点关心的改动

### 3.1 新语言特性

#### 3.1.1 Lambda

大家对 lambda 表达式应该掌握的比较好了，这个算是最常用的JDK的特性了，看官方文档就行：

> https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html


#### 3.1.2 更强的类型推断

JDK7

    List<String> stringList = new ArrayList<>();
    stringList.add("A");
    stringList.addAll(Arrays.<String>asList());
    
JDK8

    List<String> stringList = new ArrayList<>();
    stringList.add("A");
    stringList.addAll(Arrays.asList());
    
#### 3.1.3 Java Date-Time Packages

引入了LocalDate和LocalDateTime，（据说）目标就是为了干掉Date、Calendar。

Date时间类是可变类，这就意味着在多线程环境下对共享 Date变量进行操作时，必须由程序员自己来保证线程安全。

而LocalDate和LocalDateTime是final的，不会有线程安全问题。

个人意见是JDK8以后不要再用Date了，直接使用LocalDate和LocalDateTime，配合我封装的LocalDateTimeUtil效果更佳！

> 见代码

#### 3.1.4 java.lang and java.util Packages

并行数组排序

> 经测试，3W左右和传统排序互有胜负，5W左右开始有优势

标准编码和解码 Base64

    Base64.getDecoder().decode(param);
    Base64.getEncoder().encode(param);

无符号算术支持

    Long.parseUnsignedLong(param);
    Long.toUnsignedString(param);
    Long.divideUnsigned(param);
    Long.compareUnsigned(param1,param2);
    Integer.parseUnsignedInt(param);
    Integer.toUnsignedString(param);
    Integer.divideUnsigned(param);
    Integer.compareUnsigned(param1,param2);

> 这些方法比使用BigDecimal和BigInteger用于算术运算的速度更快， 因为它们包括对原始数据类型的优化。

### 3.2 IO & NIO

对标准（java.nio.charset.Charset）和扩展的字符集实现进行了一些改进.

全新的基于 Solaris 事件端口机制的面向 Solaris 的 SelectorProvider 实现.(不是默认启用的，如需使用请将系统属性java.nio.channels.spi.Selector 的值设置为 sun.nio.ch.EventPortSelectorProvider)

减小 <JDK_HOME>/jre/lib/charsets.jar 文件的大小

提高了 java.lang.String(byte[], *) 构造函数和 java.lang.String.getBytes() 方法的性能。

### 3.3 并发

#### 3.3.1 java.util.concurrent新增两个接口和四个类

    接口：
    CompletableFuture.AsynchronousCompletionTask
    CompletionStage<T>
    
    类：
    CompletableFuture<T>
    ConcurrentHashMap.KeySetView<K,V>
    CountedCompleter<T>
    CompletionException
    
 Java8提供了一种函数风格的异步和事件驱动编程模型CompletableFuture，该模型不同于以往Java版本，不会造成堵塞Blocking。过去，Java 5并发包主要聚焦于异步任务处理，其模型特点是基于一个生产者线程，不断地创造任务，通过一个堵塞Blocking队列传递给任务的消费者，这个模型在Java 7和Java 8以后使用了另外一种任务执行模型，同时将一个任务的数据分解到子集中，这些子集能够分别被同样的子任务独立地处理。这种风格后面的基础库包就是 fork/join框架。
 
 我没有能力详解这个，大家得自行学习：
 
 > https://blog.csdn.net/sl1992/article/details/100677099

#### 3.3.2 java.util.concurrent.ConcurrentHashMap

增加了新方法，主要就是为了支持stream

#### 3.3.3 java.util.concurrent.atomic

新增了四个原子类：

    DoubleAccumulator、LongAccumulator、DoubleAdder、LongAdder

对AtomicLong等类的改进，在高并发的环境下表现更好

> https://segmentfault.com/a/1190000015865714?utm_source=tag-newest

#### 3.3.4 java.util.concurrent.ForkJoinPool

增加了两个新方法getCommonPoolParallelism()和commonPool()，为了避免每次调用的时候都新建一个线程池，现在开发者建议只要使用Fork/Join，都用这个commonPool

> A static {@link #commonPool()} is available and appropriate for
> most applications. The common pool is used by any ForkJoinTask that
> is not explicitly submitted to a specified pool. Using the common
> pool normally reduces resource usage (its threads are slowly
> reclaimed during periods of non-use, and reinstated upon subsequent
> use)

#### 3.3.5 java.util.concurrent.locks.StampedLock

为了进一步提升并发执行效率，Java 8引入了新的读写锁：StampedLock

StampedLock和ReadWriteLock相比，改进之处在于：读的过程中也允许获取写锁后写入！这样一来，我们读的数据就可能不一致，所以，需要一点额外的代码来判断读的过程中是否有写入，这种读锁是一种乐观锁。

乐观锁的意思就是乐观地估计读的过程中大概率不会有写入，因此被称为乐观锁。反过来，悲观锁则是读的过程中拒绝有写入，也就是写入必须等待。显然乐观锁的并发效率更高，但一旦有小概率的写入导致读取的数据不一致，需要能检测出来，再读一遍就行。

### 3.4 Optional

官方文档中并没有提新引入了这个类（或者我没看到），导致我给遗漏了，这里补上

> 见代码 optional.Test

