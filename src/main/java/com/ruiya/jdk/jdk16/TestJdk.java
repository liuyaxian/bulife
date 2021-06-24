package com.ruiya.jdk.jdk16;


/***
 *  jdk16 的使用
 *  https://www.oracle.com/news/announcement/oracle-announces-java-16-031621.html
 */
public class TestJdk {
    // JEP 394 instanceof 的模式匹配
    //之前的使用规则
    public void jdkinstanceof(Object obj){
        if (obj instanceof  String){
            String s = (String) obj;
        }
    }
    //jdk16使用规则  模式匹配
    public void jdk16instanceof(Object obj){
        // 类型模式
        if (obj instanceof  String s){
        }
        // 特性二
        boolean flag;
        if (obj instanceof String s && s.length() > 5) {
            char charSequence= 's';
            flag = s.contains(charSequence);
        }
    }


    // JEP 395 records 通过记录增强java编程语言
    // 记录是充当不可变数据的透明载体的类。记录可以被认为是名义元组。


}
