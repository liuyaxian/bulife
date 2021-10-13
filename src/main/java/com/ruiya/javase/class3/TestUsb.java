package com.ruiya.javase.class3;

public class TestUsb {
    public static void main(String[] args) {
        String S1 = "WEWE";
        String S2 = "324";
        Class<? extends String> aClass = S1.getClass();

        Class<? extends String> aClass1 = S2.getClass();
        if (aClass == aClass1){
            // 属于共一个类型
            System.out.println("属于同一个类型" );
        }else{
            System.out.println("属于不同类型" );
        }
    }

}
