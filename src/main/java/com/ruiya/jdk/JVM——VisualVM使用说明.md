## 官网：

http://visualvm.github.io/download.html

##概述
- VisualVM 是一款免费的\集成了多个 JDK 命令行工具的可视化工具，它能为您提供强大的分析能力，对 Java 应用程序做性能分析和调优。
- VisualVM提供了包括生成和分析海量数据、跟踪内存泄漏、监控垃圾回收器、执行内存和 CPU 分析，同时它还支持在 MBeans 上进行浏览和操作等功能。
- 相比JConsole，感觉功能更强大，且可集成各类插件，使其更强大。Jconsole算是VisualVM的子集吧。另外VisualVM也有JConsole的插件；
- 相比Arthas，它最大的特点肯定就是图形化了。Arthas必须得命令敲着走，且命令众多，不易上手（还全是英文……），并且它是JDK自带的。
- 对于eclipse和idea（VisualVM Launcher），也有相应插件，可在软件界面快速打开visualvm。


### 对于性能分析，主要几个点即是：

- 监控：实时CPU监控、内存监控、线程监控、其他监控；
- 转储：从内存中获得当前状态数据并存储到文件用于后续分析，一般是线程信息转储、类加载信息转储，以及堆上对象的转储；
- 快照：cpu情况快照、内存情况快照；
- 分析：程序中函数的调用关系、运行时间、内存分配及使用情况、载入的类、存在的对象信息等。
- JFR：可以查看JFR文件，即jmc的飞行记录。




