package com.ruiya.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
public class PdfFileTemplate {
    //    public static void main(String[] args) throws Exception {
    //        getDocument();
    //        System.out.println("结束！");
    //    }

        public static void main(String[] args) {
            String filePath = "E:\\刘亚仙\\ITextTest.pdf";
            String savePath = "E:\\刘亚仙\\ITextTest1.pdf";
            String password = "123";
            pdfEncrypt(filePath, savePath, password);
        }

        /**
         * 第一个参数是页面大小。 接下来的参数分别是左，右，上和下页边距。
         * 该文档的类型尚未定义。
         * 这取决于您创建的编写器的类型。
         * 对于我们的示例，我们选择com.itextpdf.text.pdf.PdfWriter。
         * 其他作家是HtmlWriter，RtfWriter，XmlWriter以及其他一些作家。
         * 他们的名字充分说明了他们的目的。
         */

        public static void getDocument() throws IOException, DocumentException {
            // 清单1.文档对象的实例化

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            document.addTitle("资产证明");
            //清单2.创建PdfWriter对象
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\刘亚仙\\ITextTest.pdf"));
            document.open();
            //清单3.创建段落对象
            /***
             * 您刚刚看到了如何在PDF文档中添加纯文本。 接下来，我们需要在文档中添加一些复杂的元素。 让我们从创建一个新的章节开始。 章节是一个特殊部分，从新页面开始，默认情况下显示数字。
             */
            Anchor anchorTarget = new Anchor("First page of the document.");
            anchorTarget.setName("BackToTop");
            Paragraph paragraph1 = new Paragraph();
            paragraph1.setSpacingBefore(50);
            paragraph1.add(anchorTarget);
            document.add(paragraph1);
            document.add(new Paragraph("Some more text on the first page with different color and font type.",
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));
            // 清单4.创建章对象
            /**
             * 在清单4的代码中，我们创建了一个新的章对象chapter1 ，标题为“ This is Chapter 1”。 将编号深度设置为0将不会在页面上显示章节编号。
             *
             * 节是章节的子元素。 在清单5的代码中，我们创建了一个标题为“这是第1章的第1节”的部分。 要在此部分下添加一些文本，我们创建另一个段落对象someSectionText ，并将其添加到该部分对象中。
             */
            Paragraph title1 = new Paragraph("Chapter 1", FontFactory.getFont(FontFactory.HELVETICA,
                    18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
            Chapter chapter1 = new Chapter(title1, 1);
            chapter1.setNumberDepth(0);
            //清单5.创建节对象
            /**
             * 在添加表之前，让我们看一下文档的外观。 添加以下两行以关闭图2中的文档。 然后编译并执行程序以生成PDF文档： document.add(chapter1);document.close(); 。
             */
            Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",
                    FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                            new CMYKColor(0, 255, 255, 17)));
            Section section1 = chapter1.addSection(title11);
            Paragraph someSectionText = new Paragraph("This  text comes as part of section 1 of chapter 1.");
            section1.add(someSectionText);
            someSectionText = new Paragraph("Following is a 3 X 2 table.");
            section1.add(someSectionText);
            document.add(chapter1);
    // 清单6.创建表对象
            /**8
             * 在清单6的代码中，我们创建了一个具有三列的PDFPTable对象t ，并继续添加行。 接下来，我们创建三个具有不同文本的PDFPcell对象。
             * 我们继续将它们添加到表中。 它们将从第一列开始添加到第一行，然后移至同一行的下一列。
             * 该行完成后，下一个单元格将添加到下一行的第一列。 只需提供单元格的文本，
             * 例如t.addCell("1.1");也可以将单元格添加到表中t.addCell("1.1"); 。 最后，将表对象添加到节对象。
             * 最后，让我们看看如何将列表添加到PDF文档。 列表包含许多ListItem 。
             * 列表可以编号或不编号。 将第一个参数传递为true意味着您要创建编号列表。
             */
            PdfPTable t = new PdfPTable(3);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);
            PdfPCell c1 = new PdfPCell(new Phrase("Header1"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Header2"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Header3"));
            t.addCell(c3);
            t.addCell("1.1");
            t.addCell("1.2");
            t.addCell("1.3");
            section1.add(t);
            // 清单7.创建列表对象
            List l = new List(true, false, 10);
            l.add(new ListItem("First item of list"));
            l.add(new ListItem("Second item of list"));
            section1.add(l);
            /**
             * 我们已经将所有内容添加到了chapter1对象中。 现在，我们在java项目中添加一个图像。 我们可以使用以下Image方法之一缩放图像：
             * scaleAbsolute（）
             * scaleAbsoluteWidth（）
             * scaleAbsoluteHeight（）
             * scalePercentage（）
             * scaleToFit（）
             */
            // 在清单8中 ，我们使用了scaleAbsolute。 然后将图像对象添加到该部分。
            // 清单8.将图像添加到主文档
            /**
             * iText中的com.itextpdf.text.Anchor类表示指向外部网站或文档内部的链接。
             * 锚点（链接）可以像网页中的链接一样单击。 要添加锚点，
             * 我们需要创建一个新的锚点并将引用设置为清单3中创建的Anchor目标。
             * 然后将锚点添加到该部分，并将该部分添加到文档。
             * src/main/resources/static/sign.png  static/sign.png
             */
            Image image2 = Image.getInstance("src/main/resources/static/sign.png");
            image2.scaleAbsolute(120f, 120f);
            section1.add(image2);

            // 清单9.将Anchor添加到主文档
            /**
             * 没有更多的元素可以添加到chapter1 ，是时候将chapter1添加到主 document 。
             * 与示例应用程序一样，我们还将在此处关闭文档对象。
             */
            Paragraph title2 = new Paragraph("Using Anchor",
                    FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                            new CMYKColor(0, 255, 0, 0)));
            section1.add(title2);
            title2.setSpacingBefore(5000);
            Anchor anchor2 = new Anchor("Back To Top");
            anchor2.setReference("#BackToTop");
            section1.add(anchor2);
            chapter1.add(section1);
            //清单10.在主文档中增加一章
            document.add(chapter1);
            document.close();
        }


        private static boolean pdfEncrypt(String filePath, String savePath, String password) {
            try {
                PdfReader reader = new PdfReader(filePath);
                PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(savePath));
                // 设置密码
                stamper.setEncryption(password.getBytes(), password.getBytes(),
                        PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
                stamper.close();
                reader.close();
    //            logger.debug("pdfEncrypt-加密成功");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
    //            Logger("对【{}】文件加密处理异常:{}",filePath,e.getMessage());
                return false;
            }
        }


        public static void imageTemplate() throws Exception {
            // 模板文件路径
            String templatePath = "E:\\刘亚仙\\ITextTest.pdf";
            // 生成的文件路径
            String targetPath = "E:\\刘亚仙\\ITextTest2.pdf";
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
    //        form.setField("field", "field");
            form.addSubstitutionFont(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));

            // 通过域名获取所在页和坐标，左下角为起点
            int pageNo = form.getFieldPositions(fieldName).get(0).page;
            Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
            float x = signRect.getLeft();
            float y = signRect.getBottom();

            // 读图片
            Image image = Image.getInstance(imagePath);
            // 获取操作的页面
            PdfContentByte under = stamper.getOverContent(pageNo);
            // 根据域的大小缩放图片
            image.scaleToFit(signRect.getWidth(), signRect.getHeight());
            // 添加图片
            image.setAbsolutePosition(x, y);
            under.addImage(image);

            stamper.close();
            reader.close();
        }

//        public static void main(String[] args) throws Exception {
//            //imageTemplate();
//            getITextRenderer(htmlToStr());
//        }

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

     public static void getITextRenderer(String strline) throws Exception{
         String htmlString="";
         htmlString = strline.toString();
                 //.replaceAll("\"", "'").replaceAll("<style>", "<style>body{font-family:SimSun;font-size:14px;}");    //注意这里为啥要写这个，主要是替换成这样的字体，如果不设置中文有可能显示不出来。

         OutputStream os = new FileOutputStream("E:\\刘亚仙\\ITextTest3.pdf");    //生成PDF文件的路径
         ITextRenderer renderer = new ITextRenderer();
         ITextFontResolver font = renderer.getFontResolver();
        // font.addFont("C:/WINDOWS/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
         // 添加中文识别，这里是设置的宋体，Linux下要换成对应的字体
         renderer.setDocumentFromString(htmlString.toString());

         renderer.layout();
         renderer.createPDF(os);
         renderer.finishPDF();
     }
    }
