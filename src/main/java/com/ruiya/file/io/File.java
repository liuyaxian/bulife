package com.ruiya.file.io;

import java.io.File;
import java.io.IOException;

class FileTest {
    public static void main(String[] args) throws IOException {
        File file1 = new File("D\\:liuyaxian\\1111.csv");
//        File file2 = new File("D\\:liuyaxian", "2222.csv");
//        File file3 = new File("D\\:liuyaxian");
        File file4 = new File("3333.csv");

        file4.createNewFile(); //创建文件  必须有IOException异常


    }
}
