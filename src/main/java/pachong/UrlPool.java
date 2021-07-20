package pachong;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlPool {
    public static void main(String[] args) {
        getUrl("https://www.nipic.com/");
        //
    }

    private static void getUrl(String baseUrl) {

        Map<String, Boolean> oldMap = new LinkedHashMap<>();
        String oldLinkHost  = "";
        Pattern p1 = Pattern.compile("(https?://)?[^/\\s]*");
        Matcher m = p1.matcher(baseUrl);
        if (m.find()){
            oldLinkHost = m.group();
        }
        oldMap.put(baseUrl, false);
        oldMap =  crawlLinks(oldLinkHost, oldMap);
        for (Map.Entry<String, Boolean> mapping : oldMap.entrySet()){
            System.out.println("lianje:" + mapping.getKey());
        }
    }

    private static  Map<String, Boolean> crawlLinks(String oldLinkHost, Map<String, Boolean> oldMap) {
        Map<String, Boolean> newMap = new LinkedHashMap<>();
        String oldLink = "";
        for (Map.Entry<String, Boolean> mapping : oldMap.entrySet()) {
            System.out.println("链接:" + mapping.getKey());
            if (!mapping.getValue()) {
                oldLink = mapping.getKey();
                try {
                    URL url = new URL(oldLink);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(20000);
                    if (connection.getResponseCode() == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        Pattern p = Pattern.compile("<a.*?href=[\"']?((https?://)?/?[^\"']+)[\"']?.*?>(.+)</a>");
                        Matcher matcher = null;
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            matcher = p.matcher(line);
                            if (matcher.find()) {
                                String newlink = matcher.group(1).trim();
                                if (!newlink.startsWith("http")) {
                                    if (newlink.startsWith("/")) {
                                        newlink = oldLinkHost + newlink;
                                    } else {
                                        newlink = oldLinkHost + "/" + newlink;
                                    }
                                }
                                if (newlink.endsWith("/")) {
                                    newlink = newlink.substring(0, newlink.length() - 1);
                                }
                                if (!oldMap.containsKey(newlink)
                                        && !newMap.containsKey(newlink)
                                        && newlink.startsWith(oldLinkHost)) {
                                    newMap.put(newlink, false);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
                oldMap.replace(oldLink, false, true);
            }
        }
        if (!newMap.isEmpty()){
            oldMap.putAll(newMap);
            oldMap.putAll(crawlLinks(oldLinkHost, oldMap));
        }
        return oldMap;

    }
}
