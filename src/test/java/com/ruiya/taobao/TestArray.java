package com.ruiya.taobao;

import java.util.Arrays;
import java.util.List;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/14 14:51
 * @history:
 */
public class TestArray {

    public static void main(String[] args) {
        List<String> mobilenos = Arrays.asList(new String[]{"12","443","222","333"});
        String[] strings = mobilenos.toArray(new String[0]);
        int length = strings.length;
        System.out.println("length = " + length);

    }
}
