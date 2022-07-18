package com.yaruida.energy;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class CrawlFoodData {

    public final static String URL = "https://www.boohee.com/";


    // TODO 1、 jdk 8 map合并  2、 Jsoup 请求超时时间
    public static void main(String[] args) throws IOException, IntrospectionException, InstantiationException, IllegalAccessException {
        Map<String, EnergyInfo> map = new HashMap<>();

        List<EnergyInfo> energyInfos = new ArrayList<>();
        List<String> foodFirstLevel = getFoodFirstLevel();
        for (String s : foodFirstLevel) {
            List<String> foodSecondLevel = getFoodSecondLevel(s);
            for (String url : foodSecondLevel) {
                energyInfos.addAll(getFoodDetail(url));
            }
        }
        // 导入到excel
        createExcel(energyInfos);
    }


    /***
     * 获取食品一级分类
     * @return
     * @throws IOException
     */
    public static List<String> getFoodFirstLevel() throws IOException {
        String url1 = "https://www.boohee.com/food/";
        Document document = getDocument(url1);

        Elements elements = document.select("#main > div.widget-food-category > div > ul > li");

        List<String> urls = new ArrayList<>();
        for (Element element : elements) {
            urls.add(URL + element.select("div.text-box > h3 > a").attr("href"));
        }
        return urls;
    }

    private static Document getDocument(String url1) throws IOException {
        Document document = Jsoup.connect(url1).timeout(1000).get();
        return document;
    }


    /**
     * 获取食品二级分类
     *
     * @param url1
     * @return
     * @throws IOException
     */
    public static List<String> getFoodSecondLevel(String url1) throws IOException {
        Document document = getDocument(url1);

        Elements elements = document.select("#main > div > div.widget-food-list.pull-right > ul > li");

        List<String> urls = new ArrayList<>();
        for (Element element : elements) {
            urls.add(URL + element.select("div.img-box.pull-left > a").attr("href"));
        }
        return urls;
    }


    /***
     * 获取食品详细信息
     * @param url
     * @throws IOException
     */
    public static List<EnergyInfo> getFoodDetail(String url) {
        List<EnergyInfo> energyInfos = new ArrayList<>();
        Document document = null;
        try {
            document = getDocument(url);
            String foodName = document.select("#main > div > div.widget-food-detail.pull-left > div.content > div > ul > li:nth-child(1)").text().replaceAll("别名：", "");
            Elements elements = document.select("#main > div > div.widget-food-detail.pull-left > div.nutr-tag.margin10 > div > dl");
            EnergyInfo energyInfo = new EnergyInfo();
            for (int i = 1; i < elements.size(); i++) {
                Elements imgElements = elements.get(i).select("dl > dd > span");
                for (int i1 = 1; i1 < imgElements.size(); i1++) {
                    Element imgElement = imgElements.get(i1);
                    if (i == 1 && i1 == 1) {
                        energyInfo.setFoodenergy(Float.valueOf(imgElement.text()));
                    }
                    if (i == 1 && i1 == 3) {
                        energyInfo.setCarbohydrate(Float.valueOf(imgElement.text()));
                    }
                    if (i == 2 && i1 == 1) {
                        energyInfo.setFat(Float.valueOf(imgElement.text()));
                    }
                    if (i == 2 && i1 == 3) {
                        energyInfo.setProtein(Float.valueOf(imgElement.text()));
                    }

                    if (i == 3 && i1 == 1) {
                        if ("一".equals(imgElement.text())){
                            continue;
                        }
                        energyInfo.setCellulose(Float.valueOf(imgElement.text()));
                    }
                }
            }
            energyInfo.setFoodName(foodName);
            energyInfos.add(energyInfo);
        } catch (IOException e) {
            System.out.println("e" + e.getMessage());
        }
        return energyInfos;
    }


    /***
     * 获取食品详细信息
     * @param url
     * @throws IOException
     */
    @Deprecated
    public static void getFoodDetailDocument(String url) throws IOException {
        Map<String, EnergyInfo> foodMap = new HashMap<>();

        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        Jsoup.parse(new URL(url), 1000);


        // 2、通过String(HTML片段)
        Document document1 = Jsoup.parseBodyFragment(html);
        // doc.getElementsByTag("body")
        document1.body();

        // 从 url 加载
        Document document = Jsoup.connect(url).get();

        Jsoup.connect(url).data("query", "java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .post();


        // 从 file 加载
//如果不指定baseUri,此时，则会把文件位置作为baseUri

        File input = new File("../tmp/input.html");
        Document doc1 = Jsoup.parse(input, "UTF-8", "http://adamsun.com/");

        Elements elements = doc1.getElementsByTag("a");

        for (Element element : elements) {
            String href = element.attr("href");
            String  text = element.text();
        }

        //finding elements
        doc.getElementsByTag("a");
        doc.getElementById("ee");
        doc.getElementsByClass("classname");
        doc.getElementsByAttribute("key");

        //  element siblings
        doc.siblingElements();
        doc.firstElementSibling();
        doc.lastElementSibling();
        doc.nextElementSibling();
        doc.previousElementSibling();

        // graph
        doc.parent();
        doc.children();
        doc.child(2);

        // element data
        // get
        elements.attr("key");
        // set
        elements.attr("key", "value");

        elements.text();
        elements.size();
        elements.addClass("");
        elements.html();


        elements.select("");
        elements.select("");

        File input1 = new File("/tmp/input.html");
        Document doc11 = Jsoup.parse(input, "UTF-8", "http://example.com/");

        Elements links = doc11.select("a[href]"); // 带href属性的a标签
        Elements pngs = doc11.select("img[src$=.png]");  // img中src以.png结尾

        Element masthead = doc11.select("div.masthead").first();  // div中class为masthead的
        Elements resultLinks = doc11.select("h3.r > a"); //class为r的h3中的a标签

    }





    public static void createExcel(List<EnergyInfo> energyInfos) throws IOException, InstantiationException, IllegalAccessException, IntrospectionException {
        String [] rowHead = {"序号","食物名称【foodName】","热量【foodenergy】", "碳水化合物（克）【carbohydrate】","脂肪(克)【fat】","蛋白质(克)【protein】","纤维素(克)【cellulose】"};

        //创建HSSFWorkbook对象
        XSSFWorkbook wb =  new  XSSFWorkbook();
        //创建HSSFSheet对象
        XSSFSheet sheet = wb.createSheet( "EnergyInfo" );
        //创建HSSFRow对象
        XSSFRow firstRow = sheet.createRow( 0 );

        // 创建表格头：
        for (int i = 0; i < rowHead.length; i++) {
            //设置单元格的值
            XSSFCell cell=firstRow.createCell(i);
            //设置单元格的值
            cell.setCellValue(rowHead[i]);
        }


        // 根据 numsMap 中的数据常见表内容
        for (int i = 0; i < energyInfos.size(); i++) {
            //创建HSSFRow对象
            XSSFRow row = sheet.createRow(i+1);

            EnergyInfo energyInfo = energyInfos.get(i);
            for (int j = 0; j < rowHead.length; j++) {
                if (j ==0){
                    //设置单元格的值
                    XSSFCell cell=row.createCell(j);
                    cell.setCellValue(i+1);
                } else {
                    //设置单元格的值
                    XSSFCell cell=row.createCell(j);
                    //设置单元格的值
                    Object [] object = energyInfo.csvLine();
                    Object o = object[j];
                    if (o instanceof Float){
                        cell.setCellValue((float)object[j]);
                    } else {
                        cell.setCellValue((String)object[j]);
                    }
                }
            }
        }


        //输出Excel文件
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String  date =  format.format(new Date());
        FileOutputStream output= new  FileOutputStream( "D:\\"+ date+ "workbook.xls" );
        wb.write(output);
        output.flush();

    }

}