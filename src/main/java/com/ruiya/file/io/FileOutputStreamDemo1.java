package com.ruiya.file.io;

import java.io.IOException;

/**
 * @author admin
 */
public class FileOutputStreamDemo1 {

    public static void main(String[] args) throws IOException {
//File file = new File("E:\\workspace_xzg\\unionops\\xzg-finance-operation");
//String [] arr = file.list();
//for (String filename : arr){
//    System.out.println(filename);
//}
//        File[] files = file.listFiles();
//        for (File f : files){
//            System.out.println(f);
//        }
//        show();
//        show1();  ALT + shift + enter
//
//        File file = new File("dd");
//        long length = file.length(); // alt + enter
//        boolean mkdirs = file.mkdirs();

//        show();
//        File file = new File("dd");
//        System.out.println(File.separator);
//        System.out.println(File.pathSeparator);
//        System.out.println(File.pathSeparatorChar);

//        Properties prop = new Properties() ;
//        prop.setProperty("dfd","ddddd");
//        FileWriter fw = new FileWriter("D:\\liuyaxian\\2.txt");
//        prop.store(fw, "storeww");
//        System.out.println(prop);
//        fw.close();
//
//        //list
//        PrintWriter pw = new PrintWriter("D:\\liuyaxian\\c.txt") ;
//        prop.list(pw);
//        pw.close();
//        System.out.println(prop);
//
//        //load
//        FileReader fr = new FileReader("D:\\liuyaxian\\2.txt") ;
//        prop.load(fr);
//        fr.close();
//        System.out.println(prop);

//            InputStream is1 = null;//输入流 1
//            InputStream is2 = null;//输入流 2
//            OutputStream os = null;//输出流
//            SequenceInputStream sis = null;//合并流
//            is1 = new FileInputStream("D:\\liuyaxian\\dd.txt");
//            is2 = new FileInputStream("D:\\liuyaxian\\dd2.txt");
//            os = new FileOutputStream("D:\\liuyaxian\\dd3.txt");
//            sis = new SequenceInputStream(is1,is2);//实例化合并流
//            int temp = 0;
//            while ((temp = sis.read())!=-1){//循环输出
//                os.write(temp);//保存内容
//            }
//            sis.close();//关闭合并流
//            is1.close();
//            is2.close();
//            os.close();//关闭输出流


//        // 流对象
//        RandomAccessFile raf1 = new RandomAccessFile(new File("D:\\liuyaxian\\dd.txt"), "rw");
//        RandomAccessFile raf2 = new RandomAccessFile(new File("D:\\liuyaxian\\dd2.txt"), "rw");
//
//        // 读写
//        byte [] buffer = new byte[1024];
//        int len;
//        raf1.seek(3L);
//
//        while((len = raf1.read(buffer)) != -1) {
//            raf2.write(buffer, 0, len);
//        }
//        raf1.close();
//        raf2.close();




//InputStream in = System.in;
//InputStreamReader  inputStreamReader = new InputStreamReader(in);
//BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//File file = new File("D:\\liuyaxian\\123123.txt");
//if(!file.exists()){
//    file.mkdirs();
//}
//FileWriter fileWriter = new FileWriter(file);
//BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//String string = bufferedReader.readLine();
//bufferedWriter.write(string);
//
//bufferedWriter.flush();
//
//bufferedReader.close();
//bufferedWriter.close();
//inputStreamReader.close();
//fileWriter.close();


















//        PrintStream ps = new PrintStream(new FileOutputStream("1232.txt"), true);

//        PrintStream ps = new PrintStream(new PrintStream("2323.txt"), true);
//
//        PrintStream ps = new PrintStream("dd.txt");
        //打印数据， 不换行
//        ps.print("l");
        //打印数据， 换行
//        ps.println("l");
//        ps.flush();
//        ps.close();


//        PrintWriter pw = new PrintWriter("pd.txt");
//        pw.write("sdfsdfsfs");
//        pw.close();
//        pw.flush();

//        ByteArrayOutputStream  baos = new ByteArrayOutputStream();
//        baos.write("sdsfsfs".getBytes());
//        ByteArrayInputStream bais = new ByteArrayInputStream( baos.toByteArray());
//
//        int che = 0;
//        while ((che =bais.read()) != -1){
//            System.out.println(che);
//        }
//        bais.close();
//        baos.close();

//        char ind [] = {'d','d','2'};
//        CharArrayWriter chw = new CharArrayWriter();
//        chw.write(ind);
//
//        CharArrayReader cha1r = new CharArrayReader(chw.toCharArray());
//        int i =0;
//        while ((i = cha1r.read()) != -1){
//            System.out.println((char) cha1r.read());
//        }
//        chw.close();
//        cha1r.close();


// 内存流
//        1、操作字节数据:
//        ByteArrayInputStream(byte [] buf)
//                    char [] data = saw.toCharArray();
//                    CharArrayReader sar = new CharArrayReader(data);
//                    int ch;
//                    while ((ch =sar.read()) != -1) {
//                        System.out.print((char)ch);
//                    }
//        ByteArrayOutputStream()      bos.write("HEWEWRWER SFA".getBytes());
//        2、操作字符数据：
//        CharArrayReader
//                char [] data = saw.toCharArray();
//                CharArrayReader sar = new CharArrayReader(data);
//                int ch;
//                while ((ch =sar.read()) != -1) {
//                    System.out.print((char)ch);
//                }
//        CharArrayWriter()   char [] ches = {'s','e','e','e'}; saw.write(ches);
//        3、操作字符串
//                StringReader    sw.toString() 或者
//                StringWriter    sw.write("eeeeee");

//        StringWriter sw = new StringWriter();
//
//        sw.write("eeeeee");
//        sw.close();
//        System.out.println(sw.toString());
//        StringReader sar = new StringReader(sw.toString());
//        int ch;
//        while ((ch =sar.read()) != -1) {
//            System.out.print((char)ch);
//        }
//        System.out.println();


//        CharArrayWriter  saw = new CharArrayWriter();
//
//        char [] ches = {'s','e','e','e'};
//        saw.write(ches);
//        saw.close();
//        char [] data = saw.toCharArray();
//
//        CharArrayReader sar = new CharArrayReader(data);
//        int ch;
//        while ((ch =sar.read()) != -1) {
//            System.out.print((char)ch);
//        }
//        System.out.println();

        // 创建内存流的字节流
//        ByteArrayOutputStream  bos = new ByteArrayOutputStream();
//        // 写数据
//        bos.write("HEWEWRWER SFA".getBytes());

//        // 关闭流， 临时流，不用关闭
//        bis.close();

        // 得到内存中的数据，
//        内存输入流本身有个方法提供内容
//        byte [] data = bos.toByteArray();
//        System.out.println(new String(data));
//
//
//        ByteArrayInputStream bis = new ByteArrayInputStream(data);
//        int ch;
//        while ((ch =bis.read()) > 0) {
//            System.out.print((char)ch);
//        }
//        System.out.println();




//        DataOutputStream dos  = new DataOutputStream(new FileOutputStream("Ds.txt"));
//        DataInputStream  dis  = new DataInputStream(new FileInputStream("Ds.txt"));
//
//        dos.write(1);
//        dos.writeByte(100);
//        dos.writeShort(1);
//        dos.writeChar(11);
//        dos.writeFloat(0.32F);
//        dos.writeDouble(34.1d);
//        dos.writeBoolean(true);
//        dos.writeLong(324);
//        dos.writeUTF("sdfsdfs");
//        System.out.println(dis.read());
//        System.out.println(dis.readByte());
//        System.out.println(dis.readShort());
//        System.out.println(dis.readChar());
//        System.out.println(dis.readFloat());
//        System.out.println(dis.readDouble());
//        System.out.println(dis.readBoolean());
//        System.out.println(dis.readLong());
//        System.out.println(dis.readUTF());
//
//        dos.close();
//        dis.close();
//        dos.flush();

//        LineNumberReader 是一个可以获取和设置行号的缓冲字符输入流
//        LineNumberReader 继承了 BufferedReader
//
//        构造方法：LineNumberReader(Reader in)
//        使用默认输入缓冲区大小创建新的行编号
//        特殊方法：
//        public int getLineNumber（）获取当前行号
//        public void setLineNumber（int lineNumber） 设置当前行号

//        BufferedReader br = new BufferedReader(new FileReader("na.txt"));
//        LineNumberReader lnr = new LineNumberReader(new FileReader("E:\\sourceCode\\Open\\OpenAPI\\src\\main\\java\\com\\zlfund\\openapi\\pojo\\PortfolioProfitDetailBean.java"));
//        String line =  "";
//        BufferedWriter bw  = new BufferedWriter(new FileWriter( "D:\\liuyaxian\\aaa.txt"));
//        while ((line = lnr.readLine())!= null) {
//            if (line.contains("近3月参考净值日期") ){
//                System.out.println(line);
//            }
//            bw.write(lnr.getLineNumber()+ "  " + line);
//            bw.newLine();
//        }
//        lnr.close();
//        bw.close();

//        File file = new File("D:\\liuyaxian\\aaa.txt");
//        FileOutputStream fot = new FileOutputStream("a.txt");
////        创建了管道
////                把管道兑到了a.txt文件上
////                        流对象会帮忙创建a.txt
//      String data = "a";
////      把字符转换为字节数组
//        byte[] bytes = data.getBytes();
//        // 编码
//        System.out.println(Arrays.toString(bytes));
//        fot.write(bytes);
//        fot.close();
    }
}
