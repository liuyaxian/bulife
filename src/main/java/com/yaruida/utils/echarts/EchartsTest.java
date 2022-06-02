package com.yaruida.utils.echarts;

import com.yaruida.veriSign.Base64Helper;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

public class EchartsTest {
    private static final String JSpath = "C:\\Users\\liu_y\\Desktop\\echarts-convert\\echarts-convert1.js";


    public static void main(String[] args) {
        String imgName = "D:\\work\\项目相关\\openAPI\\V453 私募改版 20220328\\" + UUID.randomUUID().toString().substring(0, 4) + ".png ";
        String option = "{xAxis: {type: 'category',data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']},yAxis: {type: 'value'},series: [{data: [820, 932, 901, 934, 1290, 1330, 1320],type: 'line'}]}";
        //String options = "test";
        String base64Img = generateEChart(option,1600,900);
        System.out.println(base64Img);
    }


    public static String generateEChart(String options,int width,int height) {

        String fileName= "test-"+UUID.randomUUID().toString().substring(0, 8) + ".png";
        String imgPath = "D:\\work\\项目相关\\openAPI\\V453 私募改版 20220328\\" +fileName;

        String dataPath = writeFile(options);//数据json
        try {
            File file = new File(imgPath);     //文件路径（路径+文件名）
            if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            String cmd = "C:\\Users\\liu_y\\Desktop\\phantomjs-2.1.1-windows\\bin\\phantomjs" + JSpath + " -infile " + dataPath + " -outfile " + imgPath + " -width " + width + " -height " + height;
            System.out.println("222:"+cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                //System.out.println(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            String base64Img = ImageToBase64(imgPath);

            //deleteFile(imgPath);
            //deleteFile(dataPath);
            return base64Img.replaceAll("\\s*", "");
        }
    }

    public static String writeFile(String options) {
        String dataPath="D:\\work\\项目相关\\openAPI\\V453 私募改版 20220328\\"+ UUID.randomUUID().toString().substring(0, 8) +".json";
        try {
            /* 写入Txt文件 */
            File writename = new File(dataPath); // 相对路径，如果没有则要建立一个新的output.txt文件
            if (!writename.exists()) {   //文件不存在则创建文件，先创建目录
                File dir = new File(writename.getParent());
                dir.mkdirs();
                writename.createNewFile(); // 创建新文件
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(options); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataPath;
    }

    /**
     * 图片文件转为base64
     * @param imgPath
     */
    private static String ImageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        // 返回Base64编码过的字节数组字符串
        return Base64Helper.encode(Objects.requireNonNull(data));
    }

    /**
     * 删除文件
     *
     * @param pathname
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(String pathname){
        boolean result = false;
        File file = new File(pathname);
        if (file.exists()) {
            file.delete();
            result = true;
            System.out.println("文件已经被成功删除");
        }
        return result;
    }
}