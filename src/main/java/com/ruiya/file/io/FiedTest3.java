package com.ruiya.file.io;

import java.io.File;
import java.io.IOException;

public class FiedTest3 {
    public static void main(String[] args) throws IOException {
//        File fie = new File("D:\\liuyaxian\\erect\\") ;
//        boolean f =  fie.mkdir();
//        File fieee = new File("D:\\liuyaxian\\erect\\erere") ;
//        boolean sfsd =  fieee.mkdir();
//        fie.delete();
//        fieee.renameTo("ddd.txt")

//    File file = new File("aa");
//    file.createNewFile();
//    file.mkdir();
//    file.isDirectory(); //是否目录
//    file.isFile();//是否文件
//    file.exists() ;//是否存在
//    file.canRead(); //是否可读
//    file.canWrite();// 是的可写
//    file.isHidden();// 是的隐藏
//        File file = new File("E:\\项目相关\\薛掌柜运营后台\\apidocs\\OpenAPI");
        File file = new File("OpenAPI");
        System.out.println(file.getCanonicalPath());
//        file.createNewFile();
//     String absolute =    file.getAbsolutePath();
//        System.out.println(absolute);
//        file.getAbsolutePath();
//        String path = file.getPath();
//        System.out.println(file.getName());
//        System.out.println(file.length());
//        long time =  file.lastModified();
//        Date date = new Date(time);
//        System.out.println(date.toLocaleString());
//        String [] filename = file.list();
//        System.out.println(filename.length);
//       Arrays.stream(filename).forEach(s -> System.out.println(s));
//        File [] files = file.listFiles();
//        Map<String , Object> map = new HashMap<>();
//
//        Arrays.stream(files).forEach(f ->
//              map.put(f.getName(), f.toURI()));
//        System.out.println(map.keySet());
//        String [] names = file.list();
//        for (String name : names){
//            if(name.endsWith(".md")){
//                System.out.println();
//            }
//        }
    }
}
