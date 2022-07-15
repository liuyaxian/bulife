官网：
http://www.oracle.com/technetwork/java/javase/downloads/jdk9-doc-downloads-3850606.html


- 模块系统：模块是一个包的容器，Java 9 最大的变化之一是引入了模块系统（Jigsaw 项目）。
-  REPL (JShell)：交互式编程环境。
- HTTP 2 客户端：HTTP/2标准是HTTP协议的最新版本，新的 HTTPClient API 支持 WebSocket 和 HTTP2 流以及服务器推送特性。
- 改进的 Javadoc：Javadoc 现在支持在 API 文档中的进行搜索。另外，Javadoc 的输出现在符合兼容 HTML5 标准。
- 多版本兼容 JAR 包：多版本兼容 JAR 功能能让你创建仅在特定版本的 Java 环境中运行库程序时选择使用的 class 版本。
- 集合工厂方法：List，Set 和 Map 接口中，新的静态工厂方法可以创建这些集合的不可变实例。
- 私有接口方法：在接口中使用private私有方法。我们可以使用 private 访问修饰符在接口中编写私有方法。
- 进程 API: 改进的 API 来控制和管理操作系统进程。引进 java.lang.ProcessHandle 及其嵌套接口 Info 来让开发者逃离时常因为要获取一个本地进程的 PID 而不得不使用本地代码的窘境。
- 改进的 Stream API：改进的 Stream API 添加了一些便利的方法，使流处理更容易，并使用收集器编写复杂的查询。
- 改进 try-with-resources：如果你已经有一个资源是 final 或等效于 final 变量,您可以在 try-with-resources 语句中使用该变量，而无需在 try-with-resources 语句中声明一个新变量。
- 改进的弃用注解 @Deprecated：注解 @Deprecated 可以标记 Java API 状态，可以表示被标记的 API 将会被移除，或者已经破坏。
- 改进钻石操作符(Diamond Operator) ：匿名类可以使用钻石操作符(Diamond Operator)。
- 改进 Optional 类：java.util.Optional 添加了很多新的有用方法，Optional 可以直接转为 stream。
- 多分辨率图像 API：定义多分辨率图像API，开发者可以很容易的操作和展示不同分辨率的图像了。
- 改进的 CompletableFuture API ： CompletableFuture 类的异步机制可以在 ProcessHandle.onExit 方法退出时执行操作。
- 轻量级的 JSON API：内置了一个轻量级的JSON API
- 响应式流（Reactive Streams) API: Java 9中引入了新的响应式流 API 来支持 Java 9 中的响应式编程。


## 模块化系统
- Java 已经 发展超过 20 年(95 年最初发布)，Java 和相关生态在不断丰富的同时也越来越暴露出一些问题:

    - Java 运行环境的膨胀和臃肿，每次JVM启动的时候，至少会有30~60MB的内存 加载，主要原因是JVM需要加载rt.jar，不管其中的类是否被classloader加载，第一步整个jar都会被JVM加载到内存当中去。而模块化就是解决这问题，按需加载。
    - 当代码库越来越大，创建复杂，不同版本的类库交叉依赖导致让人头疼的问题，这些都阻碍了 Java 开发和 运行效率的提升
    - 很难真正地对代码进行封装, 系统并没有对不同JAR 文件之间的依赖关系有个明确的概念。每一个公共类都可以被类路径之下任何其它的公共类所访问到，这样就会导致无意中使用了并不想被公开访问的API



##  jShell命令
[REPL(Read Eval Print Loop)意为交互式的编程环境.md]
[REPL(Read Eval Print Loop)意为交互式的编程环境.md](src/main/java/com/ruiya/jdk/jdk9/REPL(Read Eval Print Loop)意为交互式的编程环境.md)

## 接口的私有方法


















































