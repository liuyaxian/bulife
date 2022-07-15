package com.ruiya.jdk.jdk8;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 */
public class TestJdk {

    public static void main(String[] args) {
      //  mapForEach();


       new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("dsf");
            }
        }).start();

      new Thread(() -> System.out.println("eee")).start();
    }




    /***
     * map foreach的使用
     */
    public static void mapForEach(){
        Map<String, String> fieldMap = new HashMap<>();
        char [] subAmtChar = "123456789".toCharArray();
        for (int i = 0 ; i< subAmtChar.length ; i++) {
            String  keys = "subAmt" + (subAmtChar.length -i);
            fieldMap.put(keys, String.valueOf(subAmtChar[i]));
        }

        // 传统的Map迭代方式
        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            System.out.println("传统map迭代： "+entry.getKey() + "：" + entry.getValue());
        }
        // JDK8的迭代方式
        fieldMap.forEach((key, value) -> {
            System.out.println("JDK8的迭代 "+ key + "：" + value);
        });
    }




}
