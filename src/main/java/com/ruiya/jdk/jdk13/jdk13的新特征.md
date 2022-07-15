350 Dynamic CDS Archives 对appCDS进行性了扩展，允许在Java应用执行结束时动态归档类。归档类包括包括默认的基础层CDS(class data-sharing) 存档中不存在的所有已加载的应用程序类和类库。通过此仿瓷提高了AppCDS的可用性；
351 ZGC: Uncommit Unused Memory 对ZGC进行了增强，在以前的版本中，java GC之后并不会将系统内存释放给OS，因为每次释放都意味着重新调整jvm的内存大小，存在一定的消耗；随着软件的发展，我们发现在很多时候内存是比较昂贵的资源，所以将不用的内存释放回去给OS是非常有必要的；此功能在默认情况下已开始，但可以通过-xx:-zuncommit参数禁用；注意：如果最新内存参数设置比最大内存参数大，那么此功能将隐式禁用。
353 Reimplement the Legacy Socket API 在这个版本中，将使用新的实现来代替java.net.socket和java.net.serversocket API的底层实现。新版本中旧的API还未删除，可以通过配置系统属性"jdk.net.useplansocketimpl"来使用他们。但默认实现是最新版本的。
354 Switch Expressions (Preview) 扩展开关，以便它可以用作语句或表达式，并且两种形式都可以使用传统的情况…：标签（带有贯穿线）或新案例…->标签（没有掉进去），还有一个新的语句，用于从开关表达式中产生值。这些变化将简化日常编码，并为在交换机中使用模式匹配做好准备。这是jdk 13中的一个预览语言特性。
355 Text Blocks (Preview) 向Java语言添加文本块。文本块是一个多行字符串文本，它避免了大多数转义序列的需要，自动以可预测的方式格式化字符串，并在需要时让开发人员控制格式。这是jdk 13中的一个预览语言特性。
此外，JDK8的截止时间为2019年1月份，到期后，Oracle将不再提供补丁及其它的更新服务。官网称可能会更久，JDK9的截止时间是2018年3月，JDK10的截止版本是2018年9月。（详细请前往：http://www.oracle.com/technetwork/java/javase/eol-135779.html?ssSourceSiteId=otncn），JDK 9和 JDK 10都是一个短期版本，故稳定长期的版本可能是JAVA 11(LTS - Long Term Support)版本。
————————————————
版权声明：本文为CSDN博主「我用漂亮的押韵形容被掠夺一空的爱情」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43854141/article/details/122861399