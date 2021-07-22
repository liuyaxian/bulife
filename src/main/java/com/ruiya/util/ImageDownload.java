package com.ruiya.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageDownload {

    public static void main(String[] args) throws Exception {
//        File dirFile = new File("E:\\刘亚仙\\个人信息\\shopee\\耳环\\20210721-001");
//        ArrayList<String> list = Dir(dirFile);
//    for (String str : list){
//        String filePath = str + "\\1.txt";
//        String pathFileName = str;
//        List<String> urls = readFileImage(filePath);
//
//        // 将图片地址写入文件中
//        for (int i = 0; i < urls.size(); i++) {
//            strRwFile(urls.get(i),  pathFileName + "\\产品详情.txt");
//            downloadPicture(urls.get(i), pathFileName + "\\" + i + ".jpeg");
//        }
//        System.out.println("图片数：" + urls.size());
//    }


        String filePath = "E:\\刘亚仙\\个人信息\\shopee\\耳环\\22-网红夸张大气珍珠耳环女S925纯银针气质霸气高级感耳钉2021年新款\\1.txt";
        String path = "E:\\刘亚仙\\个人信息\\shopee\\耳环\\";
        String fileName = "22-网红夸张大气珍珠耳环女S925纯银针气质霸气高级感耳钉2021年新款";
        List<String> urls = readFileImage(filePath);

        // 将图片地址写入文件中
        for (int i = 0; i < urls.size(); i++) {
            strRwFile(urls.get(i), path + fileName + "\\产品详情.txt");
//            String [] strs = urls.get(i).split(".");
//            String subFix = strs[strs.length - 1];
            downloadPicture(urls.get(i), path + fileName + "\\" + i + ".jpeg");
        }
        System.out.println("图片数：" + urls.size());
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
                        new FileInputStream(file));
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

    public static ArrayList<String> dirAllStrArr = new ArrayList<String>();

    /**
     * 遍历文件夹名
     */
    public static void DirAll(File dirFile) throws Exception {

        if (dirFile.exists()) {
            File files[] = dirFile.listFiles();
            for (File file : files) {
                // 如果遇到文件夹则递归调用。
                if (file.isDirectory()) {
                    // 递归调用
                    DirAll(file);
                } else {
                    // 如果遇到文件夹则放入数组
                    if (dirFile.getPath().endsWith(File.separator)) {
                        dirAllStrArr.add(dirFile.getPath() + file.getName());
                    } else {
                        dirAllStrArr.add(dirFile.getPath() + File.separator
                                + file.getName());
                    }
                }
            }
        }
    }

    // 这里是仅仅查询当前路径下的所有文件夹、文件并且存放其路径到文件数组
    // 由于遇到文件夹不查询其包含所有子文件夹、文件，因此没必要用到递归
    public static ArrayList<String> Dir(File dirFile) throws Exception {
        ArrayList<String> dirStrArr = new ArrayList<String>();

        if (dirFile.exists()) {
            // 直接取出利用listFiles()把当前路径下的所有文件夹、文件存放到一个文件数组
            File files[] = dirFile.listFiles();
            for (File file : files) {
                // 如果传递过来的参数dirFile是以文件分隔符，也就是/或者\结尾，则如此构造
                if (dirFile.getPath().endsWith(File.separator)) {
                    dirStrArr.add(dirFile.getPath() + file.getName());
                } else {
                    // 否则，如果没有文件分隔符，则补上一个文件分隔符，再加上文件名，才是路径
                    dirStrArr.add(dirFile.getPath() + File.separator
                            + file.getName());
                }
            }
        }
        return dirStrArr;
    }
}
