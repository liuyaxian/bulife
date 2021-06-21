package com.ruiya.jdk;

/**
 *
 * 由于要方便的在系统中针对多个JDK版本进行切换，可以使用jenv。
 *
 * @author admin
 *
 */
public class Java16 {

    /***
     * java 16特性 17点
     *
     */
    /***
     * 1、基于值的类警告提议 将原始包装类指定为基于值的类，
     * 同时不推荐通过提示新弃用警告促使用户将其构造函数移除。
     * 在 Java 平台中对于任何基于值的类实例进行同步的错误尝试，
     * 会予以警告。推动这一努力的是 Valhalla 项目，
     * 该项目正在以原始类的形式对 Java 编程模型进行重大改进。
     * 原始类将实例声明为无身份的，并且可以内联或展平表示形式，
     * 其中实例可以在内存位置之间自由复制，并可以使用实例字段的值进行编码。
     * Java 中原始类的设计和实现现在已经足够成熟，可以预见，
     * 在将来的发行版中会把 Java 平台的某些类迁移至原始类。
     * 这些计划迁移的类在API规范中将被设计成 基于值的类。
     */

    /**
     * jenv add ${JDK16_Path}
     * jenv global openjdk64-16
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("jdk 16");
    }


}
