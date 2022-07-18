# jsoup 官网
https://jsoup.org/apidocs/org/jsoup/Connection.html


##  jsoup 的简介
- jsoup 是一款Java 的HTML解析器，可直接解析某个URL地址、HTML文本内容。
- 它提供了一套非常省力的API，可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。


##主要功能
- 1. 从一个URL，文件或字符串中解析HTML；
- 2. 使用DOM或CSS选择器来查找、取出数据；
- 3. 可操作HTML元素、属性、文本；

- `注意`： jsoup是基于MIT协议发布的，可放心使用于商业项目。


## jsoup 的使用
### 获取 document 的四种方式
#### 1、 通过String （完整的html）
- 提供了两个方法:
```java
Jsoup.parse(String html);
//开发时，一般路径都是相对地址，baseUri的作用就是类似于页面中<base>标签，指定相对地址的基础URL
//如果html中有<base>标签，则只需要调用parse(String html)

Jsoup.parse(String html,String baseUri);

String html = "<html><head><title>First parse</title></head>"
+ "<body><p>Parsed HTML into a doc.</p></body></html>";
  Document doc = Jsoup.parse(html);

```
#### 2、 通过String(HTML片段)
- 使用Jsoup.parse(String html)一般来说会得到相同的结果。 
- 区别就是该方法会将输入的任何片段解析进body元素内，而parse则不一定

```java
//方法:
Jsoup.parseBodyFragment(String html);


String html = "<div><p>Lorem ipsum.</p>";
Document doc = Jsoup.parseBodyFragment(html);
Element body = doc.body();//doc.getElementsByTag("body")
```

#### 3、从URL加载
- Jsoup.connect(String url)获取一个Connection，而get()是执行这个请求，然后处理返回结果。
- 除此之外，Connection还可以设置cookie、请求参数、请求头等等
```java
Jsoup.connect(String url);
Document doc = Jsoup.connect("http://example.com/").get();

Document doc = Jsoup.connect("http://example.com")
  .data("query", "Java")
  .userAgent("Mozilla")
  .cookie("auth", "token")
  .timeout(3000)
  .post();
```
#### 4、从File加载
```java
//如果不指定baseUri,此时，则会把文件位置作为baseUri
Jsoup.parse(File in, String charsetName);
Jsoup.parse(File in, String charsetName, String baseUri);

File input = new File("../tmp/input.html");
Document doc = Jsoup.parse(input, "UTF-8", "http://adamsun.com/");
```


### 获取元素
#### 使用dom 类型的方法
```java
File input = new File("/tmp/input.html");
Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

Element content = doc.getElementById("content");
Elements links = content.getElementsByTag("a");
for (Element link : links) {
  String linkHref = link.attr("href");
  String linkText = link.text();
}
```
- 语法
```java
        //finding elements
        doc.getElementsByTag("a");
        doc.getElementById("ee");
        doc.getElementsByClass("classname");
        doc.getElementsByAttribute("key");

        //  element siblings
        doc.siblingElements();
        doc.firstElementSibling();
        doc.lastElementSibling();
        doc.nextElementSibling();
        doc.previousElementSibling();

        // graph
        doc.parent();
        doc.children();
        doc.child(2);

        // element data
        // get
        elements.attr("key");
        // set
        elements.attr("key", "value");

        elements.text();
        elements.size();
        elements.addClass("");
        elements.html();
```


####  jquery  选择器
- jQuery 中所有选择器都以美元符号开头：$()。

##### 分类
- 元素选择器
  - ("p")
- id 选择器
  - ("#test")
- .class 选择器
  - (".test)


### 获取属性、文本、HTML内容
```java
Node.attr(String key);//获取属性
Element.text();//获取元素内的文本内容
Element.html();//该元素内部html内容
Element.outerHtml();//该元素及其内部html内容

String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
Document doc = Jsoup.parse(html);
Element link = doc.select("a").first();

String text = doc.body().text(); // "An example link"
String linkHref = link.attr("href"); // "http://example.com/"
String linkText = link.text(); // "example""

String linkOuterH = link.outerHtml(); 
    // "<a href="http://example.com"><b>example</b></a>"
String linkInnerH = link.html(); // "<b>example</b>"

```


### 对数据进修改
```java
doc.select("div.comments a").attr("rel", "nofollow");
doc.select("div.masthead").attr("title", "jsoup").addClass("round-box");


        Element div = doc.select("div").first(); // <div></div>
        div.html("<p>lorem ipsum</p>"); // <div><p>lorem ipsum</p></div>
        div.prepend("<p>First</p>");
        div.append("<p>Last</p>");
// 输出: <div><p>First</p><p>lorem ipsum</p><p>Last</p></div>

        Element span = doc.select("span").first(); // <span>One</span>
        span.wrap("<li><a href='http://example.com/'></a></li>");
// 输出: <li><a href="http://example.com"><span>One</span></a></li>


        Element div = doc.select("div").first(); // <div></div>
        div.text("five > four"); // <div>five &gt; four</div>
        div.prepend("First ");
        div.append(" Last");
// 输出: <div>First five &gt; four Last</div>


```

### 处理用户输入的内容，防止跨站脚本攻击
```java
String unsafe = 
  "<p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
String safe = Jsoup.clean(unsafe, Whitelist.basic());
// 此时: <p><a href="http://example.com/" rel="nofollow">Link</a></p>

```

- Whitelist提供了多个常用的过滤方法，来过滤不同类型的标签，但是可以通过调用Whitelist提供的方法在原来的基础上增加或减少标签。
```java

在原来的基础上允许更多的规则通过
*   addTags(java.lang.String...);
*   addAttributes(java.lang.String, java.lang.String...);
*   addEnforcedAttribute(java.lang.String, java.lang.String, java.lang.String);
*   addProtocols(java.lang.String, java.lang.String, java.lang.String...);

在原来的基础上移除部分规则
*   removeTags(java.lang.String...)
*   removeAttributes(java.lang.String, java.lang.String...)
*   removeEnforcedAttribute(java.lang.String, java.lang.String)
*   removeProtocols(java.lang.String, java.lang.String, java.lang.String...)
```









