package com.ruiya.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageDownload {

    public static void main(String[] args) {
        String filePath = "E:\\刘亚仙\\个人信息\\shopee\\耳环\\22-网红夸张大气珍珠耳环女S925纯银针气质霸气高级感耳钉2021年新款\\1.txt";
        String path = "E:\\刘亚仙\\个人信息\\shopee\\耳环\\";
        String fileName = "22-网红夸张大气珍珠耳环女S925纯银针气质霸气高级感耳钉2021年新款";
        List<String> urls = readFileImage(filePath);

        // 将图片地址写入文件中
        for (int i = 0; i < urls.size(); i++) {
            strRwFile(urls.get(i), path + fileName+ "\\产品详情.txt");
            downloadPicture(urls.get(i), path + fileName + "\\" + i + ".png");
        }
        System.out.println("图片数："+urls.size());
    }


    // 从文件中读取图片保存到本地
    private static List<String> readFileImage(String filePath) {
        List<String> list = new ArrayList<String>();
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";

        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                // 判断文件是否存在，
                // 文件流转字节流
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String lineTxt = "";
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    Pattern p_image;
                    Matcher m_image;

                    p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
                    m_image = p_image.matcher(lineTxt);
                    String img = "";
                    while (m_image.find()) {
                        // 得到<img />数据
                        img = m_image.group();
                        // 匹配<img>中的src数据
                        Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
                        while (m.find()) {
                            if (m.group(1).startsWith("https://avatar2-2.pddpic.com")
                                    || m.group(1).startsWith("https://avatar2.pddpic.com")
                                    || m.group(1).startsWith("https://promotion-3.pddpic.com")
                                    || m.group(1).startsWith("https://t16img.yangkeduo.com/")
                                    || m.group(1).endsWith("edb32246-5a55-4265-9f26-203056697336.png")
                                    || m.group(1).endsWith("9c739764-08de-4858-8ecc-9ec088f54fe9.png")
                                    || m.group(1).endsWith("46e1fd46-df2b-40eb-8446-cfe5f5d732ab.png?imageView2/2/w/1300/q/80")
                                    || m.group(1).endsWith("322b80d7-c322-4a10-9903-541ebd39887d.png")
                                    || m.group(1).endsWith("16ec2b19-f030-4e90-9dd2-3499180dd045_suffix.jpeg?imageView2/2/w/1300/q/80")
                            ) {
                                continue;
                            }
                            System.out.println(m.group(1));
                            list.add(m.group(1));
                        }
                    }

//                    if (lineTxt.contains(".jpeg")){
//                        list.add(lineTxt);
//                    }
                }
                bufferedReader.close();
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    //链接url下载图片
    private static void downloadPicture(String urlList, String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void rwFile() {
        FileWriter fw = null;
        BufferedReader br = null;
        try {
// 定义FileWriter对象，关联文件f:\text.txt，用来向文件写内容
            fw = new FileWriter("f:\\text.txt", true);
// 定义bufferedReader对象，用来读取d:\1.txt文件内容
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("d:\\1.txt"), "UTF-8"));
            String line = null;
// 每次读取一行内容，循环读取，读到文件末尾结束
            while ((line = br.readLine()) != null) {
//                System.out.println("文件内容: " + line);
                fw.write(line);
// 刷新缓冲流，
                fw.flush();
            }
// 关闭I/O流
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fw != null) {
                try {
                    fw.close();

                } catch (IOException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();

                }
            }
        }
    }

    public static void strRwFile(String urls, String fileName) {
        FileWriter fw = null;
        try {
            // 定义FileWriter对象，关联文件f:\text.txt，用来向文件写内容
            fw = new FileWriter(fileName, true);
//                System.out.println("文件内容: " + urls);
            fw.append("\n");
                fw.write(urls);
                // 刷新缓冲流，
                fw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();

                }
            }
        }
    }
}
