package com.io.file;

import java.io.File;
import java.io.IOException;

public class FileCreate {

    public static void main(String[] args) {
        firstCreateFile();

    }

   public static void firstCreateFile(){
        File file = new File("D:\\frist.txt");
       try {
           file.createNewFile();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

}



