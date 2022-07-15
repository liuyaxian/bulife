## REPL(Read Eval Print Loop)意为交互式的编程环境。


JShell 是 Java 9 新增的一个交互式的编程环境工具。它允许你无需使用类或者方法包装来执行 Java 语句。
它与 Python 的解释器类似，可以直接 输入表达式并查看其执行结果。

### 执行 JSHELL
```shell
jshell

执行结果
|  欢迎使用 JShell -- 版本 17.0.2
|  要大致了解该版本, 请键入: /help intro

```

```text
/help
|  键入 Java 语言表达式, 语句或声明。
|  或者键入以下命令之一:
|  /list [<名称或 id>|-all|-start]
|       列出您键入的源
|  /edit <名称或 id>
|       编辑源条目
|  /drop <名称或 id>
|       删除源条目
|  /save [-all|-history|-start] <文件>
|       将片段源保存到文件
|  /open <file>
|       打开文件作为源输入
|  /vars [<名称或 id>|-all|-start]
|       列出已声明变量及其值
|  /methods [<名称或 id>|-all|-start]
|       列出已声明方法及其签名
|  /types [<名称或 id>|-all|-start]
|       列出类型声明
|  /imports
|       列出导入的项
|  /exit [<integer-expression-snippet>]
|       退出 jshell 工具
|  /env [-class-path <路径>] [-module-path <路径>] [-add-modules <模块>] ...
|       查看或更改评估上下文
|  /reset [-class-path <路径>] [-module-path <路径>] [-add-modules <模块>]...
|       重置 jshell 工具
|  /reload [-restore] [-quiet] [-class-path <路径>] [-module-path <路径>]...
|       重置和重放相关历史记录 -- 当前历史记录或上一个历史记录 (-restore)
|  /history [-all]
|       您键入的内容的历史记录
|  /help [<command>|<subject>]
|       获取有关使用 jshell 工具的信息
|  /set editor|start|feedback|mode|prompt|truncation|format ...
|       设置配置信息
|  /? [<command>|<subject>]
|       获取有关使用 jshell 工具的信息
|  /!
|       重新运行上一个片段 -- 请参阅 /help rerun
|  /<id>
|       按 ID 或 ID 范围重新运行片段 -- 参见 /help rerun
|  /-<n>
|       重新运行以前的第 n 个片段 -- 请参阅 /help rerun
|
|  有关详细信息, 请键入 '/help', 后跟
|  命令或主题的名称。
|  例如 '/help /list' 或 '/help intro'。主题:
|
|  intro
|       jshell 工具的简介
|  keys
|       类似 readline 的输入编辑的说明
|  id
|       片段 ID 以及如何使用它们的说明
|  shortcuts
|       片段和命令输入提示, 信息访问以及
|       自动代码生成的按键说明
|  context
|       /env /reload 和 /reset 的评估上下文选项的说明
|  rerun
|       重新评估以前输入片段的方法的说明

|

```

## jshell命令的使用
- win+R调出命令行窗口输入jshell
```shell
 System.out.println("hello jshell");
```
- 变量的声明 
```shell
int a = 1;
int b =2 ;
int c = a+b;

```

- 方法的定义和调用 
```shell
 public void eat(String food){ Sytem.out.println("喜欢吃"+food)} 
```

- 方法的重载 和修改
```shell
 public void eat(){} 
```

- 导入包、查看默认导入的包 
```shell
import java.util.Scanner;

/imports

```
- 查看jshell提供的命令 && edit命令的使用
```shell
/
```

```text
结果： 
|  命令 '/' 不明确: /list, /edit, /drop, /save, /open, /vars, /methods, /types, /imports, /exit, /env, /reset, /reload, /history, /debug, /help, /set, /?, /!                      !

|  键入 /help 以获取帮助信息。

/edit
```
- list命令：列出已输入的代码 
```shell
/list
```
```text
s：片段 ID 以 s 开头的是启动代码。
e：片段 ID 以 e 开头的产生了错误。
片段 ID 没有前缀的是有效片段。
```
- 打开磁盘持久化文件 
```shell
> /open C:\JAVA\src\com.runoob.greetings\module-info.java

```
- jshell无需进行异常捕获 



- JShell 中可用的命令。要查看所有可用命令的列表，在提示符下输入 /help。注意，tab 补全也适用于命令。
```shell
/help
```

- Vars、Methods、Types、Imports 和 Reset 命令
  - 使用 /vars 查看声明的所有变量和它们的值。
    ```shell
     /vars
    ```
  - 使用methods 命令列出声明的所有方法和它们的签名。
  ```shell
      /methods
   ```
  - 使用 /types 命令列出所有类型声明。
  ```shell
      /types
   ```
  - 使用 /imports 命令列出当前声明的所有导入。
  ```shell
    /imports
   ```
  - 使用 /reset 命令重置和清理包括变量、方法和类型在内的所有状态
  ```shell
      /reset
   ```  

- edit 命令
  - 可以通过输入 /edit 和片段 ID 来编辑。JShell Edit Pad 会弹出来，
  - 可以根据需要做任何修改。你还可以使用变量名称代替片段 ID。
    ```shell
    /edit 3
    ```
  - 可以给 /edit 传入一个范围或多个 ID，一次编辑多个片段。
    ```shell
     /edit 1-4
     或者
     /edit 1 3-4
    ```

- Drop 命令
  - /drop 用于删除之前的任何片段。
  - 除了编辑行，你还可以选择使用 /drop 命令删除它。
  - 它的用法和 edit 命令一样，你可以使用片段 ID、变量、范围或者它们的组合作为参数
  
    ```shell
    /drop 3
    或者
    /drop saying
      或者
    /drop 3-4
    ```
    
- Save 命令
  - 把之前输入的片段的输出保存到一个文件。
  - 除了保存输出的文件，/save 命令还接收另外的参数，用于指定需要保存的片段 ID。
  - 该参数的用法和 /edit 及 /drop 命令的一样，位于文件名参数之前。

  - 如果未指定任何片段 ID，则保存之前输入的所有片段。
    ```shell
     /save output.txt
     或者
     /save 3-4 output.txt
    ```
  - /save 和 /open 命令（下文介绍）搭配使用会非常有用，可以用于保存当前会话，并稍后恢复
  - 要保存当前会话，包括所有的错误，调用 /save 命令，传入参数 -all。
  ```shell
  /save -all my_jshell_session.txt
  ```  
- Open 命令
  - /open 命令可以打开之前保存的任何输出，并对其重新求值（包括错误！）
  ```shell
    /open my_jshell_session.txt
  ```
  - 为方便使用，JShell 还提供了一些预定义的“文件名”：
    - DEFAULT——包含默认导入片段的文件；
    - PRINTING——包含若干预定义打印方法的文件； 
    - JAVASE——包含所有 Java SE 程序包导入的文件。


- 类路径使用技巧
  - 在加载外部类库时，如果要输入完整的路径会非常恼人。
  - 因此，你可以把当前路径改成所有外部类库所在的路径，
  - 从那个目录启动 jshell，使用星号（用引号引起来）包含所有的 jar 包。
  - 这适用于所有操作系统。
  ```shell
    jshell --class-path "*"
  ```
  - 同样的命令也适用于路径。该命令同样适用于所有的操作系统。
  ```shell
    jshell --class-path "libs/*"
  ```



  