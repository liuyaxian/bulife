

# jdk 16

github
 地址： https://github.com/openjdk/jdk
https://github.com/conwnet/github1s


1、基于值的类警告提议 将原始包装类指定为基于值的类，同时不推荐通过提示新弃用警告促使用户将其构造函数移除。在 Java 平台中对于任何基于值的类实例进行同步的错误尝试，会予以警告。推动这一努力的是 Valhalla 项目，该项目正在以原始类的形式对 Java 编程模型进行重大改进。原始类将实例声明为无身份的，并且可以内联或展平表示形式，其中实例可以在内存位置之间自由复制，并可以使用实例字段的值进行编码。Java 中原始类的设计和实现现在已经足够成熟，可以预见，在将来的发行版中会把 Java 平台的某些类迁移至原始类。这些计划迁移的类在API规范中将被设计成 基于值的类。




2、之前在 JDK 15 中进行过预览，密封类 和接口限制了可以扩展或实现它们的类和接口。这项计划的目标包括：允许类或接口的创建者控制负责实现它的代码，提供比访问修饰符更声明性的方式来限制超类的使用，并通过提供模式分析基础来支持模式匹配的未来发展。




3、默认情况下，JDK 内部结构是强封装的，而关键内部API（例如misc.Unsafe）除外。自 JDK 9 以来默认允许用户选择使用宽松的强封装。作为 Jigsaw 项目 的一部分，此提案的目标包括提高 JDK 的安全性和可维护性，并鼓励开发人员从直接使用内部元素逐渐迁移为使用标准 API，这样开发人员和最终用户都可以轻松地升级到 Java 的未来版本。该建议确实存在主要风险，即现有版本的 Java 代码将无法运行。鼓励开发人员使用 jdeps 工具来识别代码中依赖的 JDK 内部元素，并在可用时切换到 标准替代版本。开发人员可以使用现有的发行版（如JDK 11）来测试现有代码，通过使用 --illegal-access=warn 来识别通过反射访问的内部元素，使用 --illegal-access=debug 来定位错误的代码，并使用 --illegal-access=deny 来进行测试。




4、支持静态类型的纯 Java 方式访问本地代码的 外部链接程序 API。该接口在 JDK 16 中处于孵化阶段，与被提案的外部存储访问接口一起，外部链接程序接口将会大大减少像其他方式绑定本地库容易出错的情况。这项计划目的在于通过用更高级的纯 Java 开发模式来替换 JNI（Java本机接口），以提供与C语言的交互，并随着时间的推移，它将更加灵活并适配支持其它平台（例如32位的x86架构）和其他非C语言编写的外部函数（例如C++编写的外部函数）。它的性能将会比JNI更加优越。




5、将 ZGC（可扩展的低延迟垃圾收集器）线程堆栈处理 从安全点移至并发阶段。该计划的目标包括从ZGC安全点中删除线程堆栈处理，使堆栈处理变得懒性、协同、并发和增量，从ZGC安全点移除所有其它单一线程的 root 处理，并为其它虚拟机子系统提供了一种延迟处理堆栈的机制。ZGC旨在使 HotSpot 中的GC暂停和可伸缩性问题成为过去。到目前为止，随着堆大小和元空间大小变化而伸缩的GC操作已经从安全点操作中移除，并迁到并发阶段，它们包括标记，重定位，引用处理，类卸载和大多 root 处理。GC安全点中唯一仍保留执行的是子集root处理和限时标记终止操作。这些 root 处理包括Java线程堆栈和其它线程 root，由于它们会随线程数量而伸缩所以会存在问题。要消除这些问题，每个线程的处理（包括堆栈扫描）必须移动到并发阶段。通过这项计划，提升延迟的吞吐成本将会微不足道，并且在典型计算机上ZGC安全点内部花销的时间将会少于1毫秒。




6、弹性元空间功能 可将未使用的 HotSpot 虚拟机类元数据（元空间）占用的内存更迅速地返回给操作系统，从而减少元空间占用并简化元空间代码以降低维护成本。元空间存在大量的堆外内存使用问题。该项计划呼吁采用内存分区分配方案来替换现有的内存分配机制，提供一种将内存划分为多个分区以满足内存请求的算法。这种方法已在许多地方使用（例如 Linux 内核等），它将使得在较小块中分配内存以减少类加载器开销的方式变得可行，碎片化也将减少。此外，从操作系统到内存管理区域，记忆内存都将被延迟、按需使用，以减少加载程序占用的空间，这些加载程序从大型区域开始占用，但又不立即使用它们或可能无法充分利用它们。为了充分利用分区分配所提供的弹性，将元空间内存排列成大小一致的颗粒，这些颗粒可以彼此独立地进行提交和不提交。




7、启用C++ 14语言功能，以允许在 JDK C++源代码中使用 C++ 14 功能，并提供关于这些允许在 HotSpot 虚拟机代码中使用的功能的具体指南。通过JDK 15，我们知道在 JDK 中 C++代码使用的语言特性已限于 C++ 98/03语言标准。自 JDK 11，源代码就已升级为支持使用更新版本的C++标准进行构建。这包括能够使用支持 C++ 11/14语言功能的最新版本的编译器进行构建。这项提案不推荐对在 HotSpot 之外使用的C++代码样式或用法进行更改，但是要利用C++语言的特性，一些构建时的更改是必须的，这取决于平台编译器。




8、孵化阶段的矢量API，是 JDK 中配备的一个孵化模块jdk.incubator.vector，用于表达矢量计算————编译为所支持的 CPU 架构上的最佳硬件指令。以实现优于同等标量计算的性能。矢量API提供了一种使用Java编写复杂矢量算法的机制，该机制使用 HotSpot 虚拟机中预先存在的支持连同一套用户模型进行矢量化，使其更可预测且更具健壮性。该提案的目标包括提供一个清晰简洁的API来表达一系列向量计算，通过支持多个 CPU 架构实现平台无关性，以及在 x64 和 AArch64 架构上提供可靠的运行时编译和性能。优雅降级也是一个目标，在这个目标中，如果向量计算在运行时不能完全表示为硬件向量指令序列，那么向量计算将优雅地降级，并且仍然可以正常工作，原因可能是某个架构不支持某些指令，或者是其它CPU架构不受支持。




9、将JDK移植到 Windows/AArch64 平台。随着新的服务器级和消费类 AArch64（ARM64）硬件的发布，加上需求原因 Windows/AArch64 已经成为一个重要的平台。虽然移植本身已经基本完成，但该项提案的重点是将端口集成到主线JDK库中。




10、在 x64 和 AArch64 架构上，将 JDK移植到 Alpine Linux 和其他使用 musl 作为其主要C库的 Linux 发行版。Musl是 ISOC 和 Posix 标准中描述的标准库功能的Linux实现。 Alpine Linux 由于镜像较小而被广泛应用于云部署、微服务和容器环境中。Linux 版本的 Docker 容器镜像小于6MB。让 Java 在这种设置中开箱即用，并允许Tomcat、Jetty、Spring和其它流行的框架在本机环境中正常工作。通过使用 jlink 来减少 Java 运行时的大小，用户可以创建一个更小的镜像，以运行特定的应用程序。




11、[提供记录类，作为不可变数据的透明载体。记录类可以认为是名义元组。记录类在 JDK 14 和 JDK 15 中进行了预览。此做法是为了回应有关Java过于冗长拘谨的抱怨。该计划的目标包括设计一个表示简单值集合的面向对象的构造器，帮助开发人员专注于对不可变数据的建模而不是扩展行为，自动实现数据驱动的方法（例如 equals 和 accessors ），并保留长期的 Java 原则，例如名义类型。




12、Unix-Domain 套接字通道 的添加，其中Unix-Domain（AF_UNIX）套接字支持被添加到 nio.channels 包中的套接字通道和服务器套接字通道API中。该计划还扩展了继承的通道机制，以支持Unix-Domain套接字通道和服务器套接字通道。Unix-Domain套接字用于同一主机上的进程间通信。它们在大多数方面与TCP/IP套接字类似，除了它们是通过文件系统路径名而不是IP地址和端口号寻址的。新功能的目标主要是支持Unix平台和Windows通用的Unix-Domain套接字通道的所有功能。Unix-Domain套接字通道在读取/写入行为，连接设置，服务器对传入连接的接受以及在选择器中与其他非阻塞可选通道的复用方面将与现有的TCP/IP通道相同。Unix-Domain套接字比用于本地，进程间通信的TCP/IP回送连接更安全，更高效。




13、外部存储器访问API，允许Java程序安全地访问Java堆以外的外部存储器。外部存储器访问API，以前在JDK 14和JDK 15中都进行过孵化，未来在 JDK 16 中将再次孵化，并加以改良。改良范围包括在 MemorySegment 和 MemoryAddresses 接口之间划分更明确的角色。此项提案的目标包括提供一个可以在各种外部存储（包括本机，持久化介质以及托管堆存储器）上运行的 API。该 API 不会对虚拟机的安全性造成威胁。该项提案的动机是为了让很多 Java程序访问外部存储，像 Ignite、Memcached 以及 MapDB 。遗憾的是 Java API 还没有令人满意的访问外部存储的解决方案。




14、在 JDK 14和 JDK 15中都已预览过 instanceof 操作符的 模式匹配，它将在JDK 16中最终确定。模式匹配使程序中的通用逻辑（即从对象中有条件地提取组件）得以更简洁、安全的表达。




15、提供一款名为 jpackage 的工具，用于独立打包 Java 应用程序。jpackage 在 JDK 14 中被作为孵化工具引入，并在 JDK 15 中仍处于孵化阶段。到了JDK 16，jpackage 将投入生产，支持本地的软件包格式，从而为用户提供自然的安装体验，并允许在打包时指定启动时参数。支持的格式包括 Windows 上的 msi 和 exe ，MacOS 上的 pkg 和 dmg 以及 Linux 上的 deb 和 rpm 。该工具可以直接从命令行或以编程方式调用。新的打包工具解决了这样一种情况：许多Java应用程序需要以全局可用的方式安装在本机平台上，而不是简单地放置在类路径或模块路径上。因此提供适合本机平台的可安装软件包非常有必要。




16、OpenJDK 源代码仓库从 Mercurial 迁移至 Git。推动这一努力会在几方面体现优势：版本控制系统元数据大小方面、可用工具方面以及托管方面。




17、迁移到 GitHub，这个变化是基于 OpenJDK 源代码库从 Mercurial 迁移到 Git，JDK 16源代码仓库将出现在流行的代码共享网站上。Mercurial JDK 和 JDK-sandbox 迁移到 Git、GitHub 和 Skara 的过渡工作已于9月5日完成，现已向用户开放

在网站 jdk.java.net 中可以下载到适用于 Linux、Windows 和 MacOS 的 JDK 16 早期测试版本。和JDK 15一样，JDK 16也会是一个短期版本，仅支持六个月。而计划在2021年9月发布的 JDK 17 将会是一个长期支持（LTS）版本，并获得数年的支持。当前的长期支持（LTS）版本是2018年9月发布的 JDK 11。
总结
相信很多企业或个人，目前都还在使用 JDK 8 这个长期维护版本，最新一个长期维护版本是 JDK 11 ，估计使用的人群也还不是特别多，因为对于企业/个人来说，版本升级的成本太大了，往往我们更加需要的是系统能够稳定安全运作，哪怕是需要牺牲一部分性能。从 JDK8 开始，Java 语言就越显得更加具有攻击性和包容性，版本升级速度和周期也是极其惊人，如今短短几年，已是 JDK 16，所以本人特别看好 Java 在未来市场的占比和技术能力的持续延伸，加油，Java 们。




2021-03-16

338： 矢量 API（孵化器）：提供孵化器模块的初始迭代， jdk.incubator.vector以表达向量计算，这些计算在运行时可靠地编译为支持的 CPU 架构上的最佳向量硬件指令，从而实现优于等效标量计算的性能。
347： 启用 C++14 语言功能：允许在 JDK C++ 源代码中使用 C++14 语言功能，并就哪些功能可以在 HotSpot 代码中使用提供具体指导。
357： 从 Mercurial 迁移到 Git：在GitHub上托管OpenJDK 社区的 Git 存储库。与JEP 357（从 Mercurial 迁移到 Git）一致，这会将所有单存储库 OpenJDK 项目迁移到 GitHub，包括JDK 功能版本和版本 11 及更高版本的JDK 更新版本。
369： 迁移到 GitHub
376： ZGC：并发线程堆栈处理：将 ZGC 线程堆栈处理从安全点移至并发阶段。
380： Unix 域套接字通道：将 Unix-domain ( AF_UNIX) 套接字支持添加到包中的套接字通道和服务器套接字通道API java.nio.channels。扩展继承的通道机制以支持 Unix 域套接字通道和服务器套接字通道。
386： 高山 Linux 端口：将 JDK 移植到 Alpine Linux，以及其他使用 musl 作为主要 C 库的 Linux 发行版，在 x64 和 AArch64 架构上，
387： 弹性元空间：更及时地将未使用的 HotSpot 类元数据（即元空间）内存返回给操作系统，减少元空间占用，并简化元空间代码以降低维护成本。
388： Windows/AArch64 端口：将 JDK 移植到 Windows/AArch64。
389： 外部链接器 API（孵化器）：引入一个 API，该 API 提供对本机代码的静态类型纯 Java 访问。此 API 与 Foreign-Memory API ( JEP 393 ) 一起，将大大简化绑定到本机库的其他容易出错的过程。
390： 基于值的类的警告：将原始包装类指定为基于值的，并弃用它们的构造函数以进行删除，从而提示新的弃用警告。提供有关在 Java 平台中任何基于值的类的实例上不正确尝试同步的警告。
392： 打包工具：提供jpackage工具，用于打包自包含的 Java 应用程序。
393： Foreign-Memory Access API（第三孵化器）：引入 API 以允许 Java 程序安全有效地访问 Java 堆之外的外部内存。
394： instanceof 的模式匹配：通过 运算符的模式匹配增强 Java 编程语言instanceof。 模式匹配 允许程序中的通用逻辑，即从对象中条件提取组件，可以更简洁和安全地表达。
395： 记录：使用记录增强 Java 编程语言，记录是充当不可变数据的透明载体的类。记录可以被认为是名义元组。
396： 默认情况下对 JDK 内部进行强封装：默认情况下强烈封装 JDK 的所有内部元素，除了关键的内部 API，例如sun.misc.Unsafe. 允许最终用户选择自 JDK 9 以来一直默认的宽松强封装。
397： 密封课程（第二次预览）：使用密封的类和接口增强 Java 编程语言 。密封的类和接口限制了哪些其他类或接口可以扩展或实现它们。

