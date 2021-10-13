package com.ruiya.util;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreak;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.lowagie.text.DocumentException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Html2PdfUtil {

    public static void main(String[] args) throws Exception {
        String fileurl = "E:\\刘亚仙\\ITextTest4.pdf";
        createPdf(htmlToStr(), fileurl);

//        Image image = null;
//        List<float[]> ls =  getKeyWords(fileurl,  image);
//            //  返回关键字所在的坐标和页数 float[0] >> X; float[1] >> Y; float[2] >> page
//            ls.forEach(floats -> System.out.println("坐标：X:"+ floats[0] +"   Y: " +floats[1] + "  page:" +floats[2]));
//
////        //报告生成日期
////        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
////        String generationdate = dateformat.format(new Date());


        imageTemplate();
    }

    public static void imageTemplate() throws Exception {
        // 模板文件路径
        String templatePath = "E:\\刘亚仙\\ITextTest4.pdf";
        // 生成的文件路径
        String targetPath = "E:\\刘亚仙\\ITextTest5.pdf";
        // 书签名
        String fieldName = "field";
        // 图片路径
        String imagePath = "src/main/resources/static/sign.png";
        // 读取模板文件
        InputStream input = new FileInputStream(new File(templatePath));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));
        // 提取pdf中的表单
        AcroFields form = stamper.getAcroFields();
        form.addSubstitutionFont(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));

        List<float[]> ls =  getKeyWords(templatePath,  null);
        //  返回关键字所在的坐标和页数 float[0] >> X; float[1] >> Y; float[2] >> page

        // 通过域名获取所在页和坐标，左下角为起点
        float[] floats = ls.get(0);
        float x = floats[0];
        float y = floats[1];
        int pageNo = (int) floats[2];
//        form.setField("field", "SIGNATURE");
//        Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
        // 读图片
        Image image = Image.getInstance(imagePath);
        // 获取操作的页面
        PdfContentByte under = stamper.getOverContent(pageNo);
//        // 根据域的大小缩放图片
        image.scaleToFit(170, 170);

        // 添加图片
        image.setAbsolutePosition(x-80, y);
        under.addImage(image);

        stamper.close();
        reader.close();
    }


    /**
     * 字体所在目录
     */
    private static final String FONT_RESOURCE_DIR = "simsun.ttf";

    /**
     * @param htmlContent html文本
     * @param dest        目的文件路径，如 /xxx/xxx.pdf
     * @throws IOException IO异常
     */
    public static void createPdf(String htmlContent, String dest) throws IOException, DocumentException {
        ConverterProperties props = new ConverterProperties();
         props.setCharset("UTF-8");
        FontProvider fp = new FontProvider();
        fp.addStandardPdfFonts();

        // .ttf 字体所在目录
//        String resources = Html2PdfUtil.class.getResource(FONT_RESOURCE_DIR).getPath();
        String resources = FONT_RESOURCE_DIR ;
        // return FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED, 231231);
        fp.addFont(resources);
        fp.addDirectory(resources);
        props.setFontProvider(fp);
        // html中使用的图片等资源目录（图片也可以直接用url或者base64格式而不放到资源里）
//         props.setBaseUri("src/main/resources/static/images/bg.png");


        List<IElement> elements = HtmlConverter.convertToElements(htmlContent, props);
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf, PageSize.A4.rotate(), false);
        for (IElement element : elements) {
            // 分页符
            if (element instanceof HtmlPageBreak) {
                document.add((HtmlPageBreak) element);
                //普通块级元素
            } else {
                document.add((IBlockElement) element);
            }
        }
        document.close();
    }

    public static String htmlToStr(){
        StringBuilder strline = new StringBuilder("");
        File fin = new File("src/main/resources/static/index.html");
        try(
                RandomAccessFile accessFile = new RandomAccessFile(fin, "r");
                FileChannel fcin = accessFile.getChannel();
        ) {
            Charset charset = Charset.forName("UTF-8");
            int bufSize = 100000;
            java.nio.ByteBuffer rBuffer =  ByteBuffer.allocate(bufSize);
            //
            String enterStr = "\n";
            byte[] bs = new byte[bufSize];

            StringBuilder strBuf = new StringBuilder("");
            while (fcin.read(rBuffer) != -1) {
                int rSize = rBuffer.position();
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();
                String tempString = new String(bs, 0, rSize, charset);
                tempString = tempString.replaceAll("\r", "");

                int fromIndex = 0;
                int endIndex = 0;
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);
                    line = strBuf.toString() + line;
                    strline.append(line.trim());

                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }
                if (rSize > tempString.length()) {
                    strline.append(tempString.substring(fromIndex, tempString.length()));
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                } else {
                    strline.append(tempString.substring(fromIndex, rSize));
                    strBuf.append(tempString.substring(fromIndex, rSize));
                }
            }
            return strline.toString().replaceAll("\"", "'");
        } catch( Exception e) {
        }
        return "";
    }


    /**
     * 后记：此方法是获取图片的坐标与页码信息，如果想获取文本的坐标与页码信息，请把renderText（）中的注释去掉，getKeyWords（）的参数换一下即可。
     * 返回关键字所在页码和坐标
     * @param filePath  PDF位置
     * @param image     要定位的图片
     * @return  List<float[]>  返回关键字所在的坐标和页数 float[0] >> X; float[1] >> Y; float[2] >> page
     */
    public static List<float[]> getKeyWords(String filePath, final Image image) {
        final List<float[]> arrays = new ArrayList<float[]>();
        PdfReader pdfReader;
        try {
            pdfReader = new PdfReader(filePath);
            int pageNum = pdfReader.getNumberOfPages();
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);

            for (int i = 1; i <= pageNum; i++) {

                final int finalI = i;
                pdfReaderContentParser.processContent(i, new RenderListener() {
                    //此方法是监听PDF里的文字内容，有重复情况会都把坐标和页码信息都存入arrays里
                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                            String text = textRenderInfo.getText(); // 整页内容

                            if (null != text && text.contains("SIGNATURE")) {
                                Rectangle2D.Float boundingRectange = textRenderInfo
                                        .getBaseline().getBoundingRectange();
                                float[] resu = new float[3];
                                resu[0] = (float)boundingRectange.getCenterX();
                                resu[1] = (float)boundingRectange.getCenterY();
                                resu[2] = finalI;
                                arrays.add(resu);
                            }
                    }

                    //此方法是监听PDF里的图片内容
                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
//                        PdfImageObject image0;
//                        try {
//                            image0 = arg0.getImage();
//                            byte[] imageByte = image0.getImageAsBytes();
//                            Image imageInPDF = Image.getInstance(imageByte);
//                            if(image0!=null && imageInPDF.equals(image)){
//                                float[] resu = new float[3];
//                                // 0 => x;  1 => y;  2 => z
//                                //z的值始终为1
//                                resu[0] = arg0.getStartPoint().get(0);
//                                resu[1] = arg0.getStartPoint().get(1);
//                                resu[2] = finalI;
//                                arrays.add(resu);
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (BadElementException e) {
//                            e.printStackTrace();
//                        }

                    }
                    @Override
                    public void endTextBlock() {

                    }
                    @Override
                    public void beginTextBlock() {

                    }
                });
            }
            pdfReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrays;
    }

}
