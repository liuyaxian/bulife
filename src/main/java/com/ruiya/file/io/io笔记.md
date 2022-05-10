# io 流
- input output java 管道
- 数据进行传输的管道， 
- 传输 ： 输入流（数据到程序中）
-         输出流（把程序输出）

# 文件（File）
- 在计算机中所有的数据都是文件存储的（与系统无关）
~~~~
java中文件是一个对象，  java中所有东西都是对象。
java.io.File 包下
文件和目录路径名的抽象表示形式，
文件就是磁盘上的文件在java语言中的表示对象
class 类 -- 对象  -- *.class 在内存中的表现形式
File对的类， 就是磁盘上所有的文件， 在java语言中的对象的表现形式。
java 中 \ 表示转义字段开始，
System.out.println(File.separator);
System.out.println(File.pathSeparator);
System.out.println(File.pathSeparatorChar);
~~~~
- File创建三种方式
~~~~
File file1 = new File("D\\:liuyaxian\\1111.csv");
File file2 = new File("D\\:liuyaxian", "2222.csv");
File file3 = new File("D\\:liuyaxian");
File file4 = new File(file3, "3333.csv");
~~~~
## 文件类的成员方法
- 创建功能

~~~~
file.createNewFile(); 创建文件  必须有IOException异常
只能创建文件， 不能创建文件夹
创建的文件的路径必须存在，否则抛异常
file.createNewFile("ff\\新建文件夹"); //创建的是没有后缀的文件 

.mkdir();  不能创建多层文件夹的 只能一层 单级
.mkdirs(); 可以支持多层文件夹  多级
只能创建文件夹不能创建文件，
File file2 = new File("D:\\liuyaxian\\erec.txt") ;
file2.mkdirs(); 创建的是文件夹不是文件，注意

File file = new File();
 路径必须存在 file.createNewFile(); 
     重复执行， false
     如果没有指定位置，文件创建到项目下。
     如果路径不存在，异常 io
     文件夹与文件名不同
.mkdir();  不能创建多层文件夹的 只能一层
.mkdirs(); 可以支持多层文件夹
File file = new File("D:\\liuyaxian") ;
File file2 = new File("D:\\liuyaxian\\erect\\erere") ;

File file3 = new File("erere") ;
file3.createNewFile() // 创建文件当时文件没有后缀名
file3.mkdir() // 创建文件夹 当前项目下

~~~~

- 删除功能
~~~~
.delete()  删除文件或者文件夹

重复执行不会报错，第一次存在true 之后返回： false；
删除有文件的文件夹。不支持级联删除
不能删除有文件或者有文件夹的文件夹。
~~~~~
- 重命名功能
~~~~
File.renameTo(File filename)  
功能：
    1、可以给文件或者文件夹改名
    2、在不同文件夹下 ， 移动位置并且修改名 ， 剪贴功能
File file = new file("q");
-- 当前项目下重命名文件名
file.renameTo(new file("qqqq"));
 --在不同文件夹下 ， 移动位置并且修改名 ， 剪贴功能
file.renameTo(new file("D:\\liuyaxian\\aa.txt"));
~~~~

- 判断功能
~~~~
File file = new File("aa");
  file.createNewFile();
  file.mkdir(); 
1、  file.isDirectory(); 是否目录
2、  file.isFile();是否文件
3、  file.exists() ;是否存在
4、  file.canRead(); 是否可读 （一般是普通文件， 文件夹是特殊的文件）
5、  file.canWrite(); 是的可写
6、  file.isHidden(); 是的隐藏
~~~~
- 获取功能
~~~~ 
File file  = new File("AA");
路径不区分大小写， 文件名称分隔符 反斜杠， 是转义字符， \\
路径是File 是将file 路径封装为File对象， 不考虑路径的真假情况，
路径可存在可以不存在， 
可以已文件结尾也可以已文件夹结尾
路径可以是相对路径可以以是绝对路径
1、文件的绝对路径：（磁盘位置） 文件从磁盘的盘符位置开始  以盘符开始的路径
    String absolute =    file.getAbsolutePath();
2、文件的相对路径 (相对于当前项目)
    File file  = new File("a\\c.txt");
    String path = file.getPath();
file.tostring =  file.getPath();
3、获取文件名 （不带文件夹的文件名）
    String name = file.getName();
4、获取长度， 字节数(文件夹的长度为0 ，不到一个字节)
    file.length();  字节
    文件夹没有长度(没有大小)， 只有文件有长度
5、获取最后一次修改的时间，毫秒值(文件和文件夹)
    long time =  file.lastModified();
    Date date = new Date(time);
    date.toLocaleString();
6、获取指定目录下的所有文件或者文件夹的名称数组
    String [] list()
如果构造方法中给出的路径不存在，路径不是一个目录，会抛出异常

7、 获取指定目录下的所有文件或者文件夹的File数组
    File [] listFiles()
~~~~

## io流的分类
~~~~
1、向流（单向流）
   （1）输入流  读read input
   （2）输出流  写write output
2、传输的内容分：
   （1）字节数据: 图片、音频、视频
        a、字节输入流： InputStream
        b、字节输出流:  OutputStream
   （2）字符数据 ： adbc
        a、字符输入流 Reader
        b、字符输出流 Writer
  不管是字节流还是字符流，底层流动都是字节！！！！
~~~~
- 字节输出流： OutputStream
 ~~~~
      OutputStream的子类
        FileOutputStream 方法：
           （1）FileOutputStream(File file); 建一个向指定File对象表示的文件中写入数据的文件输出流。
           （2）FileOutputStream(String name); 创建一个向具有指定名称的文件中写入数据的输出文件流。
            
        实现步骤：
        1、创建流对象（输出管道） FileOutputStream fot = new FileOutputStream("a.txt");
        2、把数据变成字节数据   data.getBytes(); 
        3、用管道传输数据到文件 fot.write(bytes);
        4、关闭管道  fot.close();
  
    FileOutputStream fot = new FileOutputStream("a.txt");
    1、创建了管道
    2、把管道兑到了a.txt文件上
    3、流对象会帮忙创建a.txt
    String data = "ddddd";
    把字符转换为字节数组
    Byte [] bytes = data.getBytes();   
     查看编码
    Arrays.toString(bytes)   
    管道必须关闭
    fot.close();
 ~~~~

- LineNumberReader
~~~~
LineNumberReader 是一个可以获取和设置行号的缓冲字符输入流
LineNumberReader 继承了BufferedReader

构造方法：LineNumberReader(Reader in)
        使用默认输入缓冲区大小创建新的行编号
特殊方法：
        public int getLineNumber（）获取当前行号
        public void setLineNumber（int lineNumber） 设置当前行号
~~~~
- 数据输入输出流
~~~~
应用场景： 网络编程中
 连接 ， 流 io  客户端 write  服务器 读

java.lang.Object
    java.io.OutputStream
        java.io.FilterOutputStream
            java.io.DataOutputStream
 DataInputStream  数据输入输出流是基本输入输出字节流的子类， InputStream OutputStream

 DataOutputStream  数据输出流允许应用程序以合适方式将数据写入输出流中，然后，应用程序可以使用数据输入流将数据读入。
   可以以适当的方式把数据写入流中，数据是8中基本类型 +字符串类型。
写和读的顺序一定
 DataOutputStream dos  = new DataOutputStream(new FileOutputStream("Ds.txt"));
        DataInputStream  dis  = new DataInputStream(new FileInputStream("Ds.txt"));

        dos.write(1);
        dos.writeByte(100);
        dos.writeShort(1);
        dos.writeChar(11);
        dos.writeFloat(0.32F);
        dos.writeDouble(34.1d);
        dos.writeBoolean(true);
        dos.writeLong(324);
        dos.writeUTF("sdfsdfs");
        System.out.println(dis.read());
        System.out.println(dis.readByte());
        System.out.println(dis.readShort());
        System.out.println(dis.readChar());
        System.out.println(dis.readFloat());
        System.out.println(dis.readDouble());
        System.out.println(dis.readBoolean());
        System.out.println(dis.readLong());
        System.out.println(dis.readUTF());
        dos.close();
        dis.close();
        dos.flush();
~~~~
-内存操作流
~~~~
内存操作流一般用于处理临时信息， 因为临时信息不需要保存，使用后就可以删除，
   java.lang.Object
        java.io.OutputStream
              java.io.ByteArrayOutputStream
    1、操作字节数据:
        ByteArrayInputStream(byte [] buf)
            CharArrayReaInputStreamReaderder sar = new CharArrayReader(saw.toCharArray());
        ByteArrayOutputStream()
            bos.write("HEWEWRWER SFA".getBytes());
    2、操作字符数据：
        CharArrayReader
        CharArrayWriter()    
    3、操作字符串
       StringReader    sw.toString() 或者 
       StringWriter    sw.write("eeeeee");
~~~~

- 打印流
~~~~~
   只有输出流，
    字节打印流:
 
    字符打印流:(字符流需要刷新才有数据)
              PrintWriter pw = new PrintWriter("pd.txt");
              pw.write("sdfsdfsfs");
              pw.close();
              pw.flush();
            特殊点：
            1、打印所以数据 基本类型和引用类型（对象tostring）
            2、可以自动刷新。 开启自动刷新功能
        
            PrintStream ps = new PrintStream("dd.txt");
            //打印数据， 不换行
            ps.print("l");
            //打印数据， 换行
            ps.println("l");
            ps.flush();
            ps.close();
  字符流自动刷新：    
  开启自动刷新功能,在构造方法中开启  
    PrintWriter pw = new PrintWriter("pd.txt",  autoFlush);
~~~~~

- 标准输入输出流， （键盘输入， 控制器输出）
~~~~
1、创建输入流对象 InputStream in = System.in 
2、创建输出流对象   PrintStream out = Syste.out

~~~~
- 随机访问流
~~~~
介绍：RandomAccessFile 类是OBJECT类的子类，此类的实例支持对随机访问文件的读取和写入，随机访问文件的行为类似存储在文件系统中的一个大型byte数据，存在指向该隐含数组的光标或索引，称为文件指针。
 
 RandomAccessFile：随机读写访问流
~~~~
- 合并流
~~~~
  SequenceInputStream 类 合并流： 主要功能是将两个文件合并成一个文件
  SequenceInputStream  sis = new SequenceInputStream(is1,is2);
~~~~
- 序列化流反序列流
~~~~
    1、 序列化流与反序列化流
    使用场景： 
      用于从流中读取对象的。操作流 ObjectInputStream 为反序列化流。
        将文本文件中数据或者网络中的数据还原成为对象。


      用于向流中写入对象的操作流ObjectOutputStream 称为序列化流， 
将对象流写入到文本文件中或者在网络中传输。

特点：用于先操作对象，可以将对象写入到文件中，也可以从文件中读取对象。

~~~~
- properties (集合流)      