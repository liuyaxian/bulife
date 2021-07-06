pom文件配置  

# 1、SpringBoot 特点
## 1.1 依赖管理
- 父项目做依赖管理
~~~~ xml
   parent 父项目配置依赖管理， 几乎声明了所有开发中常用的jar的版本号
   <!-- 依赖管理   -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.5</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
 ~~~~

- 开发导入starter场景启动器 【依赖传递原则】
~~~~xml 
1、 见到很多spring-boot-starter-*， * 就是某种场景
2、只要引入starter 这个场景的所有常规需要的依赖我们就都自动引入
3、 springboot 所有支持的场景
https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-starter
4、第三方starter  自己创建的starter不要以 spring-boot开头
第三方场景启动器 thirdpartyproject-spring-boot-starter
5、所有场景启动器最底层的依赖
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-autoconfigure</artifactId>
      <version>2.4.5</version>
      <scope>compile</scope>
    </dependency>
~~~~
- 无需关注本部好，自动版本仲裁
~~~~xml
    1、 引入依赖默认都可以不写版本号
    2、引入非版本仲裁的jar 要写版本号
~~~~
- 可以修改版本号 [就近优先原则]
~~~~ xml
1、查看spring-boot-dependencies 里面规定当前依赖的版本用的key。
 2、在当前项目里面重写配置(properties)
<properties>
    <java.version>1.8</java.version>
    <mysql.version>5.1.3</mysql.version>
</properties>
~~~~

## 1.2、自动配置
- 自动配好tomcat
  * 引入tomcat依赖
  * 配置tomcat   
~~~xml
spring-boot-starter-web jar 引入tomcat
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-tomcat</artifactId>
      <version>2.4.5</version>
      <scope>compile</scope>
    </dependency>
~~~
- 自动配好springmvc
  * 引入了springmvc全套组件
  * 自动配好了springmcv常用功能 
~~~xml
spring-boot-starter-web jar spring-webmvc
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.6</version>
    <scope>compile</scope>
</dependency>
~~~
- 自动配好web详解的功能，如：字符编码问题
   * springboot帮我们配置好了所有web开发常见的场景
- 默认包结构 
    * 主程序所在的包以及其下面所有子包里面的组件都会包默认扫描
    * 无需以前配置的包扫描
    * 想要改变扫描路径  @SpringBootApplication(scanBasePackages = "com.example")
     -  或者使用 @ComponentScan 指定扫描路径 
~~~~ java
        @SpringBootApplication ==>相当于
        @SpringBootConfiguration
        @EnableAutoConfiguration
        @ComponentScan("com.example")
~~~~       


- 各种配置拥有默认值
   * 默认配置最终都是映射到某一个类（例如：HttpEncodingAutoConfiguration）
   * 配置文件的值最终会绑定到每个类上， 这个类会在容器中创建对象
- 按需加载所有自动配置项
* 非常多的starter 
* 引入了哪些场景这个场景的自动配置才会开启。
* springboot 所有的自动配置功能都在这个 spring-boot-autoconfigure 包中
~~~~ xml
  <dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-autoconfigure</artifactId>
  <version>2.4.5</version>
  <scope>compile</scope>
  </dependency> 
~~~~
# 2、容器功能
## 2.1、组件添加
### 1、@Configuration 
- 基本使用
- Full （全）模式与lite（轻对象）模式
 * 示例：
 * 最佳实战：
 ~~~~ xml
  配置类组件之间无依赖关系用lite 模式， 加速容器启动过程， 减少判断。
  配置类组件之间有依赖关系， 方法会被调用得到之前单示例组件， 用full 模式
 //@Configuration(proxyBeanMethods=false)  lite（轻对象）模式
 ~~~~
### 1、@Configuration


#### 

####
####  

 
