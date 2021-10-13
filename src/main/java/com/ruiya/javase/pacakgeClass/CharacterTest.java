package com.ruiya.javase.pacakgeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class CharacterTest {

    public static void main(String[] args) throws IOException {
//        char[] a = {'3','a','A','r','s','3','2'};
//        System.out.println("a = " +  Character.codePointAt( a ,1));
//        // 返回由给定 Unicode 字符名称指定的 Unicode 字符的代码点值。
//        /**
//         * 注意：如果一个字符没有被UnicodeData文件（Unicode Consortium 维护的 Unicode 字符数据库的一部分）指定一个名字，它的名字被定义为表达式的结果：
//         * Character.UnicodeBlock.of(codePoint).toString().replace('_', ' ') + " " + Integer.toHexString(codePoint).toUpperCase(Locale.ROOT);
//         * name匹配不区分大小写，删除任何前导和尾随空格字符。
//         * 参数：
//         * name – Unicode 字符名称
//         * 返回：
//         * 由其名称指定的字符的代码点值。
//         * 抛出：
//         * IllegalArgumentException – 如果指定的name不是有效的 Unicode 字符名称。
//         * NullPointerException – 如果name为null
//         */
//
//        String name = Character.getName(4);
//
//        System.out.println("Character.getName(2) = " + Character.getName(2));
//        System.out.println("a = " +  Character.codePointOf(name));

        String imageUrl = "C:\\Users\\Administrator\\Desktop\\logo.png";

        System.out.println("imageUrl = " +  getbase64Url(imageUrl));

        }


    public static String getbase64Url(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        // 加密
//        BASE64Encoder encoder = new BASE64Encoder();
        return "";
    }

}
