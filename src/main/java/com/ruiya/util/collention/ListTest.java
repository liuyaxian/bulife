package com.ruiya.util.collention;

import java.util.*;

/**
 * @desc:
 * @author: admin
 * @since: 2021/11/24 17:21
 * @history:
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 40000; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
