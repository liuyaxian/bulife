package com.ruiya.file;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @desc:
 * @author: admin
 * @since: 2022/1/4 9:36
 * @history:
 */
public class JarUrl {

    public static void main(String[] args) throws UnsupportedEncodingException {

        getUrl();

    }

    public static String getUrl() throws UnsupportedEncodingException {
//        String path = JarUrl.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String path = JarUrl.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        System.out.printf("111"+ URLDecoder.decode(path, "UTF-8"));
        String path1 = System.getProperty("java.class.path");
        System.out.println("path1 = " + path1);

        return path;
    }
}
