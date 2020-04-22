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



## 3 重点关心的改动

### 3.1 模块化

之前通过Maven或者Gradle可以实现项目模块化，现在Java自己原生也支持模块化了。

并且现在可以在打包的时候只打包用到的方法，打出来的Jar包更小，运行时占用内存更小。

    见代码

### 3.2 集合

增加新的初始化方法，注意：是不可变集合

    List.of(); 
    List.of("Hello", "World"); 
    List.of(1, 2, 3);
    Set.of(); 
    Set.of("Hello", "World"); 
    Set.of(1, 2, 3);
    Map.of();
    Map.of("Hello", 1, "World", 2);




