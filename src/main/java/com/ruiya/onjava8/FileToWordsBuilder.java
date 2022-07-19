package com.ruiya.onjava8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class FileToWordsBuilder {

    // 建造者模式
    Stream.Builder builder = Stream.builder();

    public FileToWordsBuilder(String filePath) throws IOException {
        Files.lines(Paths.get(filePath))
                .skip(0) //// 略过开头的注释行
                .forEach(line -> {

                    for (String s : line.split("[ .?,]+")) {
                        builder.add(s);
                    }
                });
    }

    Stream stream() {
        return builder.build();
    }

    public static void main(String[] args) throws Exception {
        new FileToWordsBuilder("C:\\Users\\liu_y\\Desktop\\自动代码 快捷键.md")
                .stream()
                .limit(1000)
                .map(w -> w + " ")
                .peek(System.out::print)
                .forEach(System.out::print);
    }
}
