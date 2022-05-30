package com.ruiya.file.io;

import java.io.*;

public class TestFileInputStream {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            byte write[] = {23,3,4,56,7,8,9,9};
            OutputStream os = new FileOutputStream("test.txt");

            for (int i = 0; i < write.length; i++) {
                os.write(write[i]);
            }
            os.close();





            InputStream   inputStream = new FileInputStream("test.txt");
            int available = inputStream.available();
            for (int i = 0; i < available; i++) {
                System.out.print((char)inputStream.read());
            }
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
