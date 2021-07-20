package pachong;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * https://www.bilibili.com/video/BV1UK411w7We?p=4&spm_id_from=pageDriver
 */

public class ImageCrawl {

    private static String url = "https://mobile.yangkeduo.com/goods.html?goods_id=173407725000&refer_page_name=mall_cart&refer_page_id=69221_1626606958438_lh1zwm1a6j&refer_page_sn=69221&_x_share_id=ab1xv35183vkged25rblue4qje71dg9v";
    public static void main(String[] args) throws IOException {
//        apacheHttpClient();
        Document document = Jsoup.connect(url).get();

      Elements elements  =   document.select("img.pdd-lazy-image");
    for (int i =0 ; i < elements.size(); i ++) {
        Elements imgElements = elements.get(i).select("data-src");
        Connection.Response response = Jsoup.connect(imgElements.attr(" data-src"))
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36")
                .referrer("https://mobile.yangkeduo.com")
                .ignoreContentType(true).execute();
        // ip 限制，
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(response.bodyAsBytes());
        FileUtils.copyInputStreamToFile(byteArrayInputStream, new File("D:/liuyaxian/"  + ".png"));
    }
    }


    private static void apacheHttpClient() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        // 使用用户代理防止被
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36");
        HttpResponse response = null;
        try {
            response = client.execute(httpGet) ;
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
            // 正则表达式

        }catch (Exception e){

        }finally {

        }
    }
}
