package com.ruiya.regex;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/8 13:55
 * @history:
 */
public class RegexUtils {

    public static void main(String[] args) {
        String msg = "{'msg':{'head':{'version':'4.0','bizcode':'3128','mctcode':'1000','senddate':'20211207','sendtime':'094437','seqid':'66443e4a866b4a30add6c7991e4f36cd'},'body':{'datalist':[{'app_catalog':'基金学堂','catalog':'jjxt','catalogimageurl':'https://resources.zlfund.cn/media/jjxt.png','description':'','id':1318155,'imageaddress':'','imageurl':'','release_time':'2021-06-17','release_timedate':{'date':17,'day':4,'hours':15,'minutes':57,'month':5,'nanos':492936000,'seconds':8,'time':1623916628492,'timezoneOffset':-480,'year':121},'release_timestamp':1623916628492,'source':'中国经济周刊','title':'基金学堂_2'},{'app_catalog':'基金学堂','catalog':'jjxt','catalogimageurl':'https://resources.zlfund.cn/media/jjxt.png','description':'','id':1318154,'imageaddress':'','imageurl':'','release_time':'2021-06-17','release_timedate':{'date':17,'day':4,'hours':15,'minutes':51,'month':5,'nanos':384769000,'seconds':2,'time':1623916262384,'timezoneOffset':-480,'year':121},'release_timestamp':1623916262384,'source':'中国证券业协会','title':'基金学堂_1'},{'app_catalog':'市场动\n" +
                "态','catalog':'scdt','catalogimageurl':'https://resources.zlfund.cn/media/scdt.png','description':'','id':1318153,'imageaddress':'','imageurl':'','release_time':'2021-06-17','release_timedate':{'date':17,'day':4,'hours':15,'minutes':46,'month':5,'nanos':224844000,'seconds':21,'time':1623915981224,'timezoneOffset':-480,'year':121},'release_timestamp':1623915981224,'source':'中国证券业协会','title':'市场动态_2'}],'rstcode':'000000','rstmsg':'成功'}}}";

        System.out.println(msg.matches("/bizcode/"));
        List<String> textList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\"" + "bizcode" + "\\s*\"");
        Matcher matcher = pattern.matcher(msg);
        while (matcher.find()) {
            textList.add(matcher.group(0));
        }

        textList.stream().distinct().forEach(s -> System.out.println("ss:"+s));
    }


    public static String getValue(String source, String key) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        Pattern p = Pattern.compile("\"" + key + "\\s*\"");
        Matcher m = p.matcher(source);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
}
