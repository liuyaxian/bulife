package com.ruiya.javase;

import java.io.*;

public class FileIo {

    public static void main(String[] args) {

        String filePath  = "C:\\Users\\Administrator\\Desktop\\1001883566_SQW359_riskbook_202109101324019397004807.pdf";
        try{
            System.out.println(" getFileContent(filePath) = " +  getFileContent(filePath));

        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public static String getFileContent(String filePath) throws IOException {
        // 返回读取指定资源的输入流
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
            String s = "";
            StringBuffer sb = new StringBuffer();
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            return sb.toString();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch(Exception e) {
            }
        }
    }


//    public static byte[] getFileContent(String filePath) throws IOException {
//        // 返回读取指定资源的输入流
//        OutputStream br = null;
//        try {
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
//            String s = "";
//            StringBuffer sb = new StringBuffer();
//            while ((s = br.readLine()) != null) {
//                sb.append(s);
//            }
//            return sb.toString();
//        } finally {
//            try {
//                if (br != null) {
//                    br.close();
//                }
//            } catch(Exception e) {
//            }
//        }
//    }

}
