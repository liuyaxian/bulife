package com.ruiya.javase;

import java.util.Arrays;
import java.util.List;

public class ArrayDemo1<aa> {
    public static void main(String[] args) {

        // 静态初始化

        int [] a = {1,3,4,5,6};
        String [] strArr = {"232222","223","333"};
        // 动态初始化
        int [] a1 = new int[2];
        a1[0] = 1;
        a1[1] = 3;
        // 数组默认的初始化
        // 数组是引用类型， 它的元素相当于类的实例变量， 因此数组一经分配空间， 其中的每个元素也被按照实例变量同样的方式被隐式初始化

        Arrays.fill(strArr, 2, 3, "1");

        System.out.println("strArr = " + toString1(strArr));
        List<String> strings = Arrays.asList(strArr);
        System.out.println("strings = " + strings);
        Arrays.sort(strArr);
        System.out.println("strings = " + toString1(strArr));
    }

    public static String toString1(Object[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(" ");
        }
    }


}
