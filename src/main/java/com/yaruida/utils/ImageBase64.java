package com.yaruida.utils;

import com.itextpdf.xmp.impl.Base64;

import java.io.FileInputStream;
import java.io.InputStream;

public class ImageBase64 {

    public static void main(String[] args) {
        System.out.println("args = " + getImageStr("src/main/resources/static/images/official.png"));
    }


    private static String getImageStr(String imagefile){


        InputStream in = null;
        byte []data  = null;
        try {
            in = new FileInputStream(imagefile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  new String(Base64.encode(data));
    }
}


