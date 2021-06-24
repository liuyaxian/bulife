package com.ruiya.util;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 已有的PDF中添加空白数字签名域
 */
public class AddSignaFieldMap {
    public static void main(String[] args){
        Map<String, Object> map=fillTemplate();
        System.out.println(map.get("pdfs"));
    }
    public static Map<String, Object> fillTemplate() {
        PdfReader reader;
        FileOutputStream out;
        PdfStamper ps;
        Map<String,String> map = new HashMap();
        map.put("Text1","HT188888888888");
        map.put("Text2","李四");//甲
        map.put("Text3","111111111111111111");//乙
        map.put("Text4","188888888888");
        map.put("Text5","王五");//甲
        map.put("Text6","11111111111111111");//乙
        map.put("Text7","188888888888");
        map.put("Text8","111");//甲
        map.put("Text9","2018-11-11");//乙
        map.put("Text10","2018-11-18");
        map.put("Text11","0.05");//甲
        map.put("Text13","阿拉蕾");//乙
        map.put("Text14","888.67");
        map.put("Text15","李四");//甲
        map.put("Text16","王五");
        map.put("Text17","2018-11-11");//甲
        Map<String,Object> os=new HashMap();
        os.put("datemap",map);
//        Map<String,Object> map2= Model.fillTemplate(os);//添加内容完成了，但是签名域没了。
        Map<String,Object> map2= os; //添加内容完成了，但是签名域没了。

        String src="C:/pdf/demo.pdf";//添加空白域完成的PDF
        try {
            out = new FileOutputStream(src);
            reader = new PdfReader(map2.get("pdf")+"");//源文件
            ps  = new PdfStamper(reader, out);
            // 创建数组签名域  (因为添加两个，所以搞了两个坐标系)
            int x = 210, y = 437, width = 60, height = 60; // 坐标系远点位于页面左下角，左下角到右下角为  x 轴，左下角到左上角为 y 轴
            Rectangle areaSignatureRect = new Rectangle(// 签名域区域，由两个对角点构成的矩形区域
                    x, // 点1 x坐标 左边距
                    y, // 点1 y坐标 上边距
                    x+width,// 点2 x坐标, 这个最好是左边距+宽，好调点。
                    y+height // 点2 y坐标, 同样这个最好是上边距+高，好调点。(其实我也懵懵懂.)
            );
            int xs = 210, ys = 290, widths = 60, heights = 60;
            Rectangle areaSignatureRect2 = new Rectangle(// 签名域区域，由两个对角点构成的矩形区域
                    xs, // 点1 x坐标
                    ys, // 点1 y坐标
                    xs+widths,// 点2 x坐标
                    ys+heights // 点2 y坐标
            );
            int pageNo = 5; // PDF 文件的页码从 1 开始，而不是 0。（这个就是你要在哪个页面添加签名域，我的是第五页）
            PdfFormField pdfFormField = PdfFormField.createSignature(ps.getWriter());
            pdfFormField.setFieldName("AREA_SIGNATURE"); // 签名域标识
            pdfFormField.setPage(pageNo);
            pdfFormField.setWidget(areaSignatureRect, PdfAnnotation.HIGHLIGHT_OUTLINE); // 高亮显示

            PdfFormField pdfFormField2 = PdfFormField.createSignature(ps.getWriter());//创建了两个（我也不懂，但是这样就可以）
            pdfFormField2.setFieldName("AREA_SIGNATURE2"); // 签名域标识
            pdfFormField2.setPage(pageNo);
            pdfFormField2.setWidget(areaSignatureRect2, PdfAnnotation.HIGHLIGHT_OUTLINE); // 高亮显示

            ps.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            ps.addAnnotation(pdfFormField, pageNo);
            ps.addAnnotation(pdfFormField2, pageNo);
            System.err.println("生成pdf文件完成~~~~~~~~~~");
            ps.close();//必须要关闭，要不然生成的PDF会是0KB（此代码大部分都是网上摘抄的，以为能用来着。没写这个，让我找大半天）
            Map<String, Object> map3=new HashMap<String,Object>();
            map3.put("pdfs",src);
            return map3;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
