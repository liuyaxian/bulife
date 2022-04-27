package com.datastructure.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTool {

    private static  final SimpleDateFormat fmt = new SimpleDateFormat("hh:mm:ss");

    public interface  Task{
        void execute();
    }


    public  static void check(String title , Task task){

        if(task == null) {
            return;
        }
        title = (title == null)  ? "" : "([" + title + "])";

        System.out.println(title);

        System.out.println("开始：" + fmt.format(new Date()));

        long bengin = System.currentTimeMillis();
        task.execute();
        long end = System.currentTimeMillis();
        System.out.println("结束时间： "+ fmt.format(new Date()));
        double delta = (end - bengin) / 1000.0;
        System.out.println("耗时：" + delta + "秒");
        System.out.println("---------------结束----------------------------------");
    }
}
