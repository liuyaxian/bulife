package com.ruiya.util.filedownloadzip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/1 14:30
 * @history:
 */
public class FileDownload {
    private static final Logger log = LoggerFactory.getLogger(FileDownload.class);

    public static void main(String[] args) throws IOException {
        Integer integer = Integer.valueOf(123);

        String path = "C:\\Users\\Administrator\\Desktop\\dddd";
        File zipFile = new File(path);
        // 如果存在该zip文件，则删除
        if (zipFile.exists()) {
            zipFile.deleteOnExit();
        }
        // 创建一个新文件
        zipFile.createNewFile();

       unzip("C:\\Users\\Administrator\\Desktop\\liuyaxian.zip", path) ;

           List<String> list = new ArrayList<>();
        list.add("C:\\Users\\Administrator\\Desktop\\3.png");
        list.add("C:\\Users\\Administrator\\Desktop\\27.png");
        list.add("C:\\Users\\Administrator\\Desktop\\签名.png");

        //fileToZip( list, "liuyaxian.zip");
    }

    //zip打包工具类
    public static String fileToZip(List<String> list, String dirName) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        //desktopPath 为本地桌面路径
        String path = desktopPath + "\\"+dirName;
        try {
            File zipFile = new File(path);
            // 如果存在该zip文件，则删除
            if (zipFile.exists()) {
                zipFile.deleteOnExit();
            }
            // 创建一个新文件
            zipFile.createNewFile();
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));
            byte[] bufs = new byte[1024 * 10];
            for (String attachment : list) {
                // 图片文件流
                File subFile = new File(attachment);
                // 图片已经存在不需要下载
                if (!subFile.exists()) {
                    continue;
                }
                String subFileName =  subFile.getName();
                ZipEntry zipEntry = new ZipEntry(subFileName);
                zos.putNextEntry(zipEntry);
                fis = new FileInputStream(subFile);
                bis = new BufferedInputStream(fis, 1024 * 10);
                int read = 0;
                while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                    zos.write(bufs, 0, read);
                }
            }
            System.out.println("压缩成功");
        } catch (Exception e) {
            log.error("下载图片异常");
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != bis) {
                    bis.close();
                }
                if (null != zos) {
                    zos.close();
                }
            } catch (IOException e) {
                log.error("下载图片异常");
                throw new RuntimeException(e);
            }
        }
        //返回path 流写回去
        return path;
    }


    /**
     * Extract a zip file to the designated directory
     *
     * @param zipFileName   the absolute path of zip file
     * @param targetDirName the absolute path of the target directory
     * @return boolean result
     */
    public static boolean unzip(String zipFileName, String targetDirName) {
        try {
            Path targetExtractPath = Paths.get(targetDirName);
            if (Files.notExists(targetExtractPath)) {
                Files.createDirectories(targetExtractPath);
            }
            ZipFile zipFile = new ZipFile(zipFileName);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            ZipEntry zipEntry;
            Path curZipEntryExtractPath;
            while (entries.hasMoreElements()) {
                zipEntry = entries.nextElement();
                curZipEntryExtractPath = targetExtractPath.resolve(zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    Files.createDirectories(curZipEntryExtractPath);
                } else {
                    Files.copy(zipFile.getInputStream(zipEntry), curZipEntryExtractPath);
                }
            }
        } catch (IOException e) {
            log.error("Extract file [ {} ] to directory [ {} ] failed.", zipFileName, targetDirName, e);
            return false;
        }
        return true;
    }
}
