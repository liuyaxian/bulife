package com.ruiya.net;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/14 15:38
 * @history:
 */
public class Url {

    public static void main(String[] args) throws IOException {
        URL url=new URL("https://taobao.com");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        InputStream in=urlConnection.getInputStream();
        FileOutputStream Fout=new FileOutputStream("taobao.com");

        byte[] buff=new byte[1024];
        int len;
        while ((len=in.read(buff))!=-1){
            Fout.write(buff,0,len);
        }

        Fout.close();
        in.close();
        urlConnection.disconnect();
    }
}


