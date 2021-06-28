package com.ruiya.jdk.jdk8;

public interface InterfaceEg {
    void  method(String str);

    /***
     * 默认方法
     * @param str
     * 使用 Default 关键字
     */
    default void log(String str){
        System.out.println("默认属性");
    }

    // 接口的静态方法
    static boolean isNull(String str) {
        System.out.println("Interface Null Check");
        return str == null ? true : "".equals(str) ? true : false;
    }

}
