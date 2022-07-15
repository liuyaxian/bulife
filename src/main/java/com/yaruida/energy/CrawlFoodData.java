package com.yaruida.energy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CrawlFoodData {

   public final  static String URL =  "https://www.boohee.com/";


   // TODO 1、 jdk 8 map合并  2、 Jsoup 请求超时时间
    public static void main(String[] args) throws IOException {
        Map<String, EnergyInfo>  map = new HashMap<>();
        List<String>  foodFirstLevel =  getFoodFirstLevel();
        for (String s : foodFirstLevel) {
            List<String> foodSecondLevel  = getFoodSecondLevel(s);
            for (String s1 : foodSecondLevel) {
                map.putAll(getFoodDetail(s1));
            }
        }
        Stream.of(map).forEach(stringEnergyInfoMap -> {
            EnergyInfo energyInfo = stringEnergyInfoMap.get(stringEnergyInfoMap);
            System.out.println(stringEnergyInfoMap+ " --: "+ energyInfo);
        });
    }


    /***
     * 获取食品一级分类
     * @return
     * @throws IOException
     */
    public static List<String>  getFoodFirstLevel() throws IOException {
        String url1 = "https://www.boohee.com/food/";
        Document document = getDocument(url1);

        Elements elements = document.select("#main > div.widget-food-category > div > ul > li");

        List<String> urls = new ArrayList<>();
        for (Element element : elements) {
            urls.add(URL +  element.select("div.text-box > h3 > a").attr("href"));
        }
        return urls;
    }

    private static Document getDocument(String url1) throws IOException {
        Document document = Jsoup.connect(url1).timeout(1000).get();
        return document;
    }


    /**
     * 获取食品二级分类
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
    public static  Map<String, EnergyInfo> getFoodDetail(String url) {
        Map<String, EnergyInfo> foodMap = new HashMap<>();

        Document document = null;
        try {
            document = getDocument(url);
            String foodName =  document.select("#main > div > div.widget-food-detail.pull-left > div.content > div > ul > li:nth-child(1)").text().replaceAll("别名：","");
            Elements elements = document.select("#main > div > div.widget-food-detail.pull-left > div.nutr-tag.margin10 > div > dl");
            EnergyInfo energyInfo = new EnergyInfo() ;
            for (int i = 1; i < elements.size(); i++) {
                Elements imgElements = elements.get(i).select("dl > dd > span");
                for (int i1 = 0; i1 < imgElements.size(); i1++) {
                    if (i == 0 && i1 == 1) {
                        Element imgElement = imgElements.get(i1);
                        energyInfo.setFoodenergy(Float.valueOf(imgElement.text()));
                    }
                    if (i == 0 && i1 == 3) {
                        Element imgElement = imgElements.get(i1);
                        energyInfo.setCarbohydrate(Float.valueOf(imgElement.text()));
                    }
                    if (i == 1 && i1 == 1) {
                        Element imgElement = imgElements.get(i1);
                        energyInfo.setFat(Float.valueOf(imgElement.text()));
                    }
                    if (i == 1 && i1 == 3) {
                        Element imgElement = imgElements.get(i1);
                        energyInfo.setProtein(Float.valueOf(imgElement.text()));
                    }
                    if (i == 2 && i1 == 1) {
                        Element imgElement = imgElements.get(i1);
                        energyInfo.setCellulose(Float.valueOf(imgElement.text()));
                    }
                }
                foodMap.put(foodName, energyInfo);
            }
        } catch (IOException e) {
            System.out.println("e"+e.getMessage());
        }
        return  foodMap;
    }


}