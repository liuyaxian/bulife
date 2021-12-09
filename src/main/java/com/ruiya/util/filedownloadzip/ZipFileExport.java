package com.ruiya.util.filedownloadzip;

import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/1 15:40
 * @history:
 */
public class ZipFileExport {


    public static void main(String[] args) {
        HttpServletResponse response = new MockHttpServletResponse();
//        downImgClient(response, "ewerwrrwerwe", "E:\\刘亚仙");

        // zipFileNameEn
        String zipFileNameEn = "2刘亚仙23";
        // 根据客户号 批次号 认证类型获取 图片地址
        List<String> list = new ArrayList<>();
        list.add("C:\\Users\\Administrator\\Desktop\\3.png");
        list.add("C:\\Users\\Administrator\\Desktop\\27.png");
        list.add("C:\\Users\\Administrator\\Desktop\\签名.png");
        String s = "E:\\刘亚仙";
        FileDownload(response, list, s, zipFileNameEn);

        }



    /**
     * 文件导出下载到----客户端
     * @param response
     * @param filename
     * @param path
     */

    public static void downImgClient(HttpServletResponse response, String filename, String path){
        if (filename != null) {
            FileInputStream inputStream = null;
            BufferedInputStream bs = null;
            ServletOutputStream servletOutputStream = null;
            try {
                response.setHeader("Content-Type","application/octet-stream");
                response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.addHeader("charset", "utf-8");
                response.addHeader("Pragma", "no-cache");
                String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
                response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);
                File file = new File(path);
                inputStream = new FileInputStream(file);
                bs =new BufferedInputStream(inputStream);
                servletOutputStream = response.getOutputStream();
                writeBytes(bs, servletOutputStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (servletOutputStream != null) {
                        servletOutputStream.close();
                        //servletOutputStream = null;
                    }
                    if (bs!=null){
                        bs.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                        //inputStream = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }
    //writeBytes()构造方法
    private  static void writeBytes(InputStream in, OutputStream out) throws IOException {
        byte[] buffer= new byte[1024];
        int length = -1;
        while ((length = in.read(buffer))!=-1){
            out.write(buffer,0,length);

        }
        in.close();
        out.close();
    }
    /**
     * 单文件导出下载
     * @param response
     * @param filename
     * @param path
     */
    public void downImg(HttpServletResponse response, String filename, String path ){
        if (filename != null) {
            FileInputStream is = null;
            BufferedInputStream bs = null;
            OutputStream os = null;
            try {
                File file = new File(path);
                if (file.exists()) {
                    is = new FileInputStream(file);
                    bs =new BufferedInputStream(is);
                    os = response.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while((len = bs.read(buffer)) != -1){
                        os.write(buffer,0,len);
                    }
                }else{
                    String error = "下载的文件资源不存在";
                    System.out.println(error);
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }finally {
                try{
                    if(is != null){
                        is.close();
                    }
                    if( bs != null ){
                        bs.close();
                    }
                    if( os != null){
                        os.flush();
                        os.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
//            downImgClient(response,filename,path);
        }
    }
    /**
     * 多文件打包下载
     * @param response
     * @param paths  文件路径集合
     * @param directoryPath //临时存放--服务器上--zip文件的目录
     */
    public static void FileDownload(HttpServletResponse response, List<String> paths, String directoryPath, String zipFileNameEn) {

        File directoryFile=new File(directoryPath);
        if(!directoryFile.isDirectory() && !directoryFile.exists()){
            directoryFile.mkdirs();
        }
        //设置最终输出zip文件的目录+文件名
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyyMMddHHmmss");
        String zipFileName =zipFileNameEn+ formatter.format(new Date())+".zip";
        String strZipPath = directoryPath+"/"+zipFileName;

        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        File zipFile = new File(strZipPath);
        try{
            //构造最终压缩包的输出流
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i<paths.size() ;i++){
                //解码获取真实路径与文件名
                String realFilePath = java.net.URLDecoder.decode(paths.get(i),"UTF-8");

//                String realFileName = java.net.URLDecoder.decode(names.get(i),"UTF-8");
                File file = new File(realFilePath);
                String realFileName = file.getName();
                if(file.exists())
                {
                    zipSource = new FileInputStream(file);//将需要压缩的文件格式化为输入流
                    /**
                     * 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样这里的name就是文件名,
                     * 文件名和之前的重复就会导致文件被覆盖
                     */
                    ZipEntry zipEntry = new ZipEntry(realFileName);//在压缩目录中文件的名字
                    zipStream.putNextEntry(zipEntry);//定位该压缩条目位置，开始写入文件到压缩包中
                    bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                    int read = 0;
                    byte[] buf = new byte[1024 * 10];
                    while((read = bufferStream.read(buf, 0, 1024 * 10)) != -1)
                    {
                        zipStream.write(buf, 0, read);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if(null != bufferStream) {
                    bufferStream.close();
                }

                if(null != zipStream){
                    zipStream.flush();
                    zipStream.close();
                }
                if(null != zipSource){
                    zipSource.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //判断当前压缩文件是否生成存在：true-把该压缩文件通过流输出给客户端后删除该压缩文件
        if(zipFile.exists()){
            //发送给客户端
            downImgClient(response,zipFileName,strZipPath);
            //删除本地存储的文件
            zipFile.delete();
        }

    }
}
