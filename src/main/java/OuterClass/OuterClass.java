package OuterClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/7 14:27
 * @history:
 */
public class OuterClass {
    public static String staticField = "静态变量";
    public String field = "实例变量";
    static {
        System.out.println("静态语句块");
    }
    {
        System.out.println("普通语句块");
    }

    class InnerClass {
    }

    static class StaticInnerClass {
    }

    public static void main(String[] args) {
//         InnerClass innerClass = new InnerClass(); // 'OuterClass.this' cannot be referenced from a static context
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();
        StaticInnerClass staticInnerClass = new StaticInnerClass();
    }

    List<Object> objectList = new ArrayList<>();
    List<String> stringList;
    int []  arr = {2,45,46,};

}
