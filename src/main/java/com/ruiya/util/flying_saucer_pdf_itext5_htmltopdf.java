package com.ruiya.util;

/***
 * 第一步 jar
 *  <dependency>
 *       <groupId>org.xhtmlrenderer</groupId>
 *       <artifactId>flying-saucer-pdf-itext5</artifactId>
 *       <version>9.1.18</version>
 *   </dependency>
 * 如果只是html通过url或字符串来转换成pdf的话，只需要上面一个jar足矣
 * 。有时候项目比较庞大引入的jar比较多的时候，可能会跟别的jar起冲突，
 * 我就遇到过flying-saucer-pdf-itext5的9.1.0及以上的版本出现jar冲突而9.1.0以下的版本不会冲突的情况，
 * 如果冲突了就得指定特定的路径，比如：
 * System.setProperty("javax.xml.transform.TransformerFactory",
 *     "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
 *
 *     System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
 *     "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");

 */
public class flying_saucer_pdf_itext5_htmltopdf {
}
