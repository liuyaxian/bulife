package com.ruiya.stream;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BigFileDoc {

    private static final String DATAFILE = "src/main/java/com/ruiya/stream/custinfo_202207110916.csv";
    static List<Point> points = new ArrayList<>();

    public static void m2() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(DATAFILE))) {
            reader.lines().map(s -> s.split(","))
                    .skip(0)
                    .forEach(p -> points.add(new Point(p[0], p[2], p[4])));
        }
    }

    public static void main(String[] args) throws IOException {
        m2();

        // 按工资升序排序（自然排序）
        List<String> newList = points.stream().sorted(Comparator.comparing(Point::getX)).map(Point::getY)
                .collect(Collectors.toList());

        newList.forEach(s -> System.out.println("dd:" +s));

        //升序
        List<Point> up = points.stream().sorted(Comparator.comparing(Point::getX).thenComparing(Point::getX)).collect(Collectors.toList());
        System.out.println(JSON.toJSON(up));

        //降序
        List<Point> down = points.stream().sorted(Comparator.comparing(Point::getX).thenComparing(Point::getX).reversed()).collect(Collectors.toList());
        System.out.println(JSON.toJSON(down));


    }
}
