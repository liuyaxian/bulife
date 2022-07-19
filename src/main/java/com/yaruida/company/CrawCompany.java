package com.yaruida.company;

import com.alibaba.csp.ahas.shaded.com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.beans.IntrospectionException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CrawCompany {


    private static Document getDocument(String url1) throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        Document document = Jsoup.connect(url1).proxy(proxy).timeout(4000).get();
        return document;
    }

    public static List<Company> getCompany(String url)  throws Exception {
        List<Company> companyList = new ArrayList<>();
        Document document = null;
        try {
            document = getDocument(url);
            Elements elements = document.select("#page-container > div > div.index_search-main__4nIOp > section > main > div.index_search-list-wrap__wi3T0 > div.index_list-wrap___axcs > div");


            for (Element element : elements) {
                Company company = new Company();
                company.setCompany(element.select("div > div.index_search-item__W7iG_ > div.index_search-item-center__Q2ai5 > div.index_header__x2QZ3 > div.index_name__qEdWi > a > span").text());
                company.setBusinessType(element.select("div > div.index_search-item__W7iG_ > div.index_search-item-center__Q2ai5 > div.index_info-row__xbtyD.index_line-row__R3mCi > div.index_info-col__UVcZb.index_narrow__QeZfV > span").text());
                company.setManager(element.select("div > div.index_search-item__W7iG_ > div.index_search-item-center__Q2ai5 > div.index_info-row__xbtyD.index_line-row__R3mCi > div.index_info-col__UVcZb.index_wider__gQok0 > a").text());
                company.setRegisterDate(element.select("div > div.index_search-item__W7iG_ > div.index_search-item-center__Q2ai5 > div.index_info-row__xbtyD.index_line-row__R3mCi > div:nth-child(3) > span").text());
                company.setSurvivalStatus(element.select("div > div.index_search-item__W7iG_ > div.index_search-item-center__Q2ai5 > div.index_header__x2QZ3 > div.index_tag-common__YTBxL.index_tag-normal-bg__J1uuI").text());

                String tag = element.select("div > div.index_search-item__W7iG_ > div.index_search-item-center__Q2ai5 > div:nth-child(4) > div:nth-child(1) > span.label").text();
                if(StringUtils.isNotBlank(tag) && tag.contains("电话")){
                    String tagvalue = element.select("div > div.index_search-item__W7iG_ > div.index_search-item-center__Q2ai5 > div:nth-child(4) > div > span:nth-child(2)").text();
                    String[] s = tagvalue.split("登录查看");
                    company.setMobileno(s[0]);
                    company.setEmail(s[1]);
                    company.setAddress(element.select("div > div.index_search-item__W7iG_ > div.index_search-item-center__Q2ai5 > div:nth-child(5) > div > span:nth-child(2)").text());
                } else {
                    company.setAddress(element.select("div > div.index_search-item__W7iG_ > div.index_search-item-center__Q2ai5 > div:nth-child(4) > div > span:nth-child(2)").text());
                }
                String score = element.select("div > div.index_search-item__W7iG_ > div.index_search-item-right__1dr_1 > div.index_score-box__gzdmS > div > div > span.index_score-num__t8t6m").text();
                company.setScore(StringUtils.isNotBlank(score)? Integer.valueOf(score) : 0);
                companyList.add(company);
            }

        } catch (IOException e) {
            System.out.println("e" + e.getMessage());
        }
        return companyList;
    }





    public static void createExcel(List<Company> companyList) throws IOException, InstantiationException, IllegalAccessException, IntrospectionException {

        String [] rowHead = new Company().rowHead;

        //创建HSSFWorkbook对象
        XSSFWorkbook wb =  new  XSSFWorkbook();
        //创建HSSFSheet对象
        XSSFSheet sheet = wb.createSheet( "Company" );
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
        for (int i = 0; i < companyList.size(); i++) {
            //创建HSSFRow对象
            XSSFRow row = sheet.createRow(i+1);

            Company company = companyList.get(i);
            for (int j = 0; j < rowHead.length; j++) {
                if (j ==0){
                    //设置单元格的值
                    XSSFCell cell=row.createCell(j);
                    cell.setCellValue(i+1);
                } else {
                    //设置单元格的值
                    XSSFCell cell=row.createCell(j);
                    //设置单元格的值
                    Object [] object = company.csvLine();
                    Object o = object[j];
                    if (o instanceof Float){
                        cell.setCellValue((float)object[j]);
                    } else if (o instanceof Integer){
                        cell.setCellValue((int)object[j]);
                    } else {
                        cell.setCellValue((String)object[j]);
                    }
                }
            }
        }


        //输出Excel文件
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String  date =  format.format(new Date());
        FileOutputStream output= new  FileOutputStream( "D:\\"+ date+ "company.xls" );
        wb.write(output);
        output.flush();

    }



    private static Document getDocumentPost(String url1) throws Exception {
        String data = "{'word':'work','sortType':'0','pageSize':'50', 'referer':'search','key':'key','sessionNo':'1658220566.50769391','pageNum':'1'}";
        Map mapTypes = JSON.parseObject(data);
        Connection connect = Jsoup.connect(url1);
        //请求头设置，特别是cookie设置
        connect.header("authority", "capi.tianyancha.com");
        connect.header("method", "POST");
        connect.header("path", "/cloud-tempest/web/searchCompanyV3?_=1658220579096");
        connect.header("scheme", "https");
        connect.header("accept", "application/json, text/plain, */*");
        connect.header("accept-encoding", "gzip, deflate, br");
        connect.header("referer", "https://www.tianyancha.com/");
        connect.header("origin", "https://www.tianyancha.com");

        connect.header("content-type", "application/json");
        connect.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))");

        return connect.data(mapTypes).ignoreContentType(true).timeout(4000).post();
    }


    public static void main(String[] args) throws Exception {
        String url = "https://www.tianyancha.com/search?key=%E6%B7%B1%E5%9C%B3%E5%B8%82%E9%BE%99%E5%B2%97%E5%8C%BA%E5%9D%AA%E5%9C%B0%E8%A1%97%E9%81%93%E5%9D%AA%E8%A5%BF%E7%A4%BE%E5%8C%BA%E9%BE%99%E5%B2%97%E5%A4%A7%E9%81%93%28%E5%9D%AA%E5%9C%B0%E6%AE%B5%291000%E5%8F%B7%E5%90%9B%E8%83%9C%E7%86%99%E7%8F%91%E5%B1%B1%E8%8A%B1%E5%9B%AD5%E6%A0%8BC%E5%BA%A7&sessionNo=1658214691.90131732";
        List<Company> companyList = getCompany(url);

        String url1 = "https://www.tianyancha.com/search?key=%E6%B7%B1%E5%9C%B3%E5%B8%82%E9%BE%99%E5%B2%97%E5%8C%BA%E5%9D%AA%E5%9C%B0%E8%A1%97%E9%81%93%E5%9D%AA%E8%A5%BF%E7%A4%BE%E5%8C%BA%E9%BE%99%E5%B2%97%E5%A4%A7%E9%81%93%28%E5%9D%AA%E5%9C%B0%E6%AE%B5%291000%E5%8F%B7%E5%90%9B%E8%83%9C%E7%86%99%E7%8F%91%E5%B1%B1%E8%8A%B1%E5%9B%AD5%E6%A0%8BC%E5%BA%A7&sessionNo=1658220566.50769391&pageNum=2";
        createExcel(getCompany(url1));
    }

}
