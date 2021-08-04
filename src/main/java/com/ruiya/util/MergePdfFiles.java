package com.ruiya.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

public class MergePdfFiles {

    /**
     * 复制pdf文档
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @param ranges   复制规则     "1-7"表示复制1到7页、"8-"表示复制从第八页之后到文档末尾
     */
    public static void copyPdf(String sourceFile ,String targetFile, String ranges)throws Exception{
        PdfReader pdfReader = new PdfReader(sourceFile);
        PdfStamper pdfStamper = new PdfStamper(pdfReader , new FileOutputStream(targetFile));
        pdfReader.selectPages(ranges);
        pdfStamper.close();
    }

    /**
     * 多个PDF合并功能
     * @param files     多个PDF的路径
     * @param savePath  生成的新PDF绝对路径
     */
    public static void mergePdfFiles(String[] files, String savePath)  {
        if (files.length > 0) {
            return ;
        }
        try {
            Document document = new Document(new PdfReader(files[0]).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savePath));
            document.open();
            for (String file : files) {
                PdfReader reader = new PdfReader(file);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            document.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        String sourceFile = "E:\\刘亚仙\\ITextTest1.pdf";
        String targetFile = "E:\\刘亚仙\\ITextTest2.pdf";
        String targetFile1 = "E:\\刘亚仙\\ITextTest3.pdf";

        copyPdf(sourceFile, targetFile, "1-7");
        copyPdf(sourceFile, targetFile1, "8-");

        String[] files = {targetFile, "E:\\刘亚仙\\ITextTest.pdf", targetFile1};
        mergePdfFiles(files, "E:\\刘亚仙\\ITextTestadd.pdf");

    }
}
