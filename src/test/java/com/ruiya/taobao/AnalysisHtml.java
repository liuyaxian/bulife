package com.ruiya.taobao;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class AnalysisHtml {

    public static void main(String[] args) throws IOException {
        String fileUrl = "D:\\work\\workspace_test\\bulife\\src\\main\\resources\\static\\main_hqdata.html";
        analysis(fileUrl);
    }


    public  static void analysis(String fileUrl) throws IOException {

        String htmlStr = FileUtils.readFileToString(new File(fileUrl), StandardCharsets.UTF_8);
        Document document  = Jsoup.parse(htmlStr);

        // 根据class 得到列表元素
//        Element first = document.getElementById("tow1");

        Elements elementsByClass = document.getElementsByTag("tr");

        List<ShareMarketInfo> shareMarketInfos = new ArrayList<>();
        for (Element byClass : elementsByClass) {
            Elements elementstr = byClass.getElementsByTag("td");
            ShareMarketInfo shareMarketInfo = new ShareMarketInfo();
            if(elementstr.size() == 4){
                shareMarketInfo.setChange(elementstr.get(2).text());
                shareMarketInfo.setChangeRate(elementstr.get(3).text().replace("(","").replace(")",""));
                shareMarketInfo.setIndex(elementstr.get(1).text());
                shareMarketInfo.setName(elementstr.get(0).text().replace(":",""));
                shareMarketInfos.add(shareMarketInfo);
            }
        }

        for (ShareMarketInfo shareMarketInfo : shareMarketInfos) {
            System.out.println("1:" + shareMarketInfo.toString());
        }

    }
}
class ShareMarketInfo {

    /**
     * 指数
     */
    private String index = "";
    /**
     * 指数描述
     */
    private String name = "";
    /**
     * 涨跌
     */
    private String change = "";
    /**
     * 涨跌比例
     */
    private String changeRate = "";
    /**
     * 交易状态
     */
    private String tradeStatus = "";

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(String changeRate) {
        this.changeRate = changeRate;
    }

    @Override
    public String toString() {
        return "ShareMarketInfo{" +
                "index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", change='" + change + '\'' +
                ", changeRate='" + changeRate + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                '}';
    }
}