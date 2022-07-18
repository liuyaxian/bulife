
##官网
https://zhaoqize.github.io/puppeteer-api-zh_CN/
##简介
- Puppeteer 是一个 Node 库， 
- 它提供了一个高级 API 来通过 DevTools 协议控制 Chromium 或 Chrome。
- Puppeteer 默认以 headless 模式运行，但是可以通过修改配置文件运行“有头”模式。


## 能做什么
- 浏览器中手动执行的绝大多数操作都可以使用 Puppeteer 来完成！ 
下面是一些示例： 
  - 生成页面 PDF。
  抓取 SPA（单页应用）并生成预渲染内容（即“SSR”（服务器端渲染））。 
  - 自动提交表单，进行 UI 测试，键盘输入等。 
  - 创建一个时时更新的自动化测试环境。 使用最新的 JavaScript 和浏览器功能直接在最新版本的Chrome中执行测试。 
  - 捕获网站的 timeline trace，用来帮助分析性能问题。 
  - 测试浏览器扩展。


## 使用

### 安装
```java
npm i puppeteer
# or "yarn add puppeteer"

run `npm fund` for details
```
- 自 1.7.0 版本以来，我们都会发布一个 puppeteer-core 包，这个包默认不会下载 Chromium。
```java
npm i puppeteer-core
# or "yarn add puppeteer-core"
```
`puppeteer-core` 是一个的轻量级的 Puppeteer 版本，用于启动现有浏览器安装或连接到远程安装。

### 使用
Note:
- Puppeteer 至少需要 Node v6.4.0，下面的示例使用 async / await，它们仅在 Node v7.6.0 或更高版本中被支持。
- Puppeteer 使用起来和其他测试框架类似。你需要创建一个 Browser 实例，打开页面，然后使用 Puppeteer 的 API。
- Example - 跳转到 https://example.com 并保存截图至 example.png:

- 文件为 example.js


