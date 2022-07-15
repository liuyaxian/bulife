- 189: Shenandoah: A Low-Pause-Time Garbage Collector (Experimental) ：新增一个名为 Shenandoah 的垃圾回收器，它通过在 Java 线程运行的同时进行疏散 (evacuation) 工作来减少停顿时间。

230: Microbenchmark Suite：新增一套微基准测试，使开发者能够基于现有的 Java Microbenchmark Harness（JMH）轻松测试 JDK 的性能，并创建新的基准测试。
325: Switch Expressions (Preview) ：切换表达式（预览），对 switch 语句进行扩展，使其可以用作语句或表达式，简化日常代码。
334: JVM Constants API ：JVM 常量 API，引入一个 API 来对关键类文件 (key class-file) 和运行时工件的名义描述（nominal descriptions）进行建模，特别是那些可从常量池加载的常量。
340: One AArch64 Port, Not Two ：删除与 arm64 端口相关的所有源码，保留 32 位 ARM 移植和 64 位 aarch64 移植。
341: Default CDS Archives ：默认生成类数据共享（CDS）存档。
344: Abortable Mixed Collections for G1 ：当 G1 垃圾回收器的回收超过暂停目标，则能中止垃圾回收过程.
346: Promptly Return Unused Committed Memory from G1 ：立即从 G1 返回未使用的已提交内存，改进 G1 垃圾回收器，以便在空闲时自动将 Java 堆内存返回给操作系统。




