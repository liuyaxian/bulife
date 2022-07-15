JDK1.14 新特性
2020-03-17

305 Pattern Matching for instanceof (Preview) instanceof 的模式匹配（预览）：通过 运算符的模式匹配增强 Java 编程语言instanceof 。模式匹配 允许程序中的通用逻辑，即从对象中条件提取组件，可以更简洁和安全地表达。这是 JDK 14 中的预览语言功能。
343 Packaging Tool (Incubator) 打包工具（孵化）：创建用于打包自包含 Java 应用程序的工具。
345 NUMA-Aware Memory Allocation for G1 G1 的NUMA 内存分配优化：通过实施 NUMA 感知内存分配来提高大型机器上的 G1 性能。
349 JFR Event Streaming JFR事件流：公开 JDK Flight Recorder 数据以进行持续监控。
352 Non-Volatile Mapped Byte Buffers 非原子性的字节缓冲区映射：添加新的 JDK 特定文件映射模式，以便FileChannelAPI 可用于创建MappedByteBuffer引用非易失性内存的实例。
358 Helpful NullPointerExceptions 非常有帮助的空指针异常：NullPointerException通过精确描述哪个变量是 来提高 JVM 生成的 s 的可用性null。
359 Records (Preview) Records（预览）：使用记录增强 Java 编程语言。记录为声明类提供了一种紧凑的语法，这些类是浅层不可变数据的透明持有者。这是 JDK 14 中的预览语言功能。
361 Switch Expressions (Standard) Switch 表达式（标准）：扩展switch，因此它可以用作语句或表达式，并且两种形式都可以使用传统case … :标签（有失效）或新case … ->标签（没有失效），还有一个新的语句用于从 a 中产生一个值switch表达。这些更改将简化日常编码，并为在switch. 这是JDK 12和JDK 13中的预览语言功能。
362 Deprecate the Solaris and SPARC Ports 弃用 Solaris 和S PARC 端口：弃用 Solaris/SPARC、Solaris/x64 和 Linux/SPARC 端口，以便在未来的版本中删除它们。
363 Remove the Concurrent Mark Sweep (CMS) Garbage Collector 移除 CMS（Concurrent Mark Sweep）垃圾收集器：删除并发标记清除 (CMS) 垃圾收集器。
364 ZGC on macOS macOS 系统上的 ZGC：将 ZGC 垃圾收集器移植到 macOS。
365 ZGC on Windows Windows 系统上的 ZGC：将 ZGC 垃圾收集器移植到 Windows。
366 Deprecate the ParallelScavenge + SerialOld GC Combination 弃用 ParallelScavenge + SerialOld GC 组合：弃用 Parallel Scavenge 和 Serial Old 垃圾回收算法的组合。
367 Remove the Pack200 Tools and API 移除Pack200 Tools和API
368 Text Blocks (Second Preview) 文本块（第二个预览版）
370 Foreign-Memory Access API (Incubator) 外部存储器API（孵化）
————————————————
版权声明：本文为CSDN博主「我用漂亮的押韵形容被掠夺一空的爱情」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43854141/article/details/122861399