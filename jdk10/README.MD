# JDK10新特性导读

## 1. 官方文档

> https://www.oracle.com/technetwork/java/javase/10-relnote-issues-4108729.html

参考文档

> https://www.ibm.com/developerworks/cn/java/the-new-features-of-Java-10/

## 2. 不用怎么关心的改动

### 2.1 禁用JRE上次使用情况跟踪的系统属性 

引入了新的系统属性，`jdk.disableLastUsageTracking` 以禁用正在运行的VM的JRE上次使用情况跟踪。可以使用`-Djdk.disableLastUsageTracking=true`或在命令行中设置此属性 `-Djdk.disableLastUsageTracking`。设置了此系统属性后，无论在中`com.oracle.usagetracker.track.last.usage`设置的属性值如何，都将禁用JRE上次使用情况跟踪`usagetracker.properties`。

## 3. 重点关心的改动

### 3.1. Optional增强

增加Optional.orElseThrow()方法

    见代码
    
### 3.2 Collection增强

增加 `copyOf` 方法

    见代码
    
