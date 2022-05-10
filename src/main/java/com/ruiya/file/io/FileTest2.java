package com.ruiya.file.io;

import java.io.File;
import java.io.IOException;

public class FileTest2 {
    public static void main(String[] args) throws IOException {
        File fie = new File("D:\\liuyaxian\234.txt") ;
        Boolean rests = fie.createNewFile();
        System.out.println(rests);
        File file2 = new File("dfd.txt");
        boolean red  = file2.createNewFile();
        System.out.printf(String.valueOf(red));

    }
}
