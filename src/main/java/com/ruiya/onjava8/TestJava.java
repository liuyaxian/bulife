package com.ruiya.onjava8;

import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestJava {


    /***
     * 获取环境变量
     */
    public void getEvement(){
        // 环境信息
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
        Runtime.getRuntime().gc();
        for (Map.Entry<String, String> stringStringEntry : System.getenv().entrySet()) {
            System.out.printf("\n key:%s", stringStringEntry.getKey());
            System.out.printf("\n value:%s", stringStringEntry.getValue());
        }
    }

    public static void testStream(){
        // ints 产生一个流

        new Random(23)
                .ints(0,21)
                .distinct()
                .limit(9)
                .sorted()
                .forEach(System.out::println);
    }

    public static void createStream(){
        Stream.of(new Bubble(1), new Bubble(2), new Bubble(3))
                .forEach(System.out::println);
        Stream.of("It's ", "a ", "wonderful ", "day ", "for ", "pie!")
                .forEach(System.out::print);
        System.out.println();
        Stream.of(3.14159, 2.718, 1.618)
                .forEach(System.out::println);

        Map<String, Double> m = new HashMap<>();
        m.put("pi", 3.14159);
        m.put("e", 2.718);
        m.put("phi", 1.618);
        m.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).forEach(System.out::println);


        Random rand =  new Random(100);

        show(rand.ints().boxed());
        show(rand.longs().boxed());
        show(rand.doubles().boxed());
        // 控制上限和下限：
        show(rand.ints(10, 20).boxed());
        show(rand.longs(50, 100).boxed());
        show(rand.doubles(20, 30).boxed());
        // 控制流大小：
        show(rand.ints(2).boxed());
        show(rand.longs(2).boxed());
        show(rand.doubles(2).boxed());
        // 控制流的大小和界限
        show(rand.ints(3, 3, 9).boxed());
        show(rand.longs(3, 12, 22).boxed());
        show(rand.doubles(3, 11.5, 12.3).boxed());

        System.out.println(IntStream.range(1, 100).sum());
        repeat(3, () -> System.out.println("Looping!"));

        Stream.generate(() -> "duplicate")
                .limit(3)
                .forEach(System.out::println);


        new TestJava().numbers()
                .skip(20) // 过滤前 20 个
                .limit(10) // 然后取 10 个
                .forEach(System.out::println);


    }

    private static int[] rints = new Random(47).ints(0, 1000).limit(100).toArray();
    public static IntStream rands() {
        return Arrays.stream(rints);
    }

    public static  void show(Stream stream) {
        stream.limit(4)
                .forEach(System.out::println);
        System.out.println("++++++++");
    }



    public static void repeat(int n, Runnable action) {
        IntStream.range(0, n).forEach(i -> action.run());
    }


    int x = 1;
    Stream numbers(){
        // iterate() 生成一个斐波那契数
        return Stream.iterate(0, i -> {
            int result = x + i;
            x = i;
            return result;
        });
    }

    public static void testArrays(){
        System.out.println(rands().findFirst().getAsInt());
        System.out.println(
                rands().parallel().findFirst().getAsInt());
        System.out.println(rands().findAny().getAsInt());
        System.out.println(
                rands().parallel().findAny().getAsInt());
    }

    public static void main(String[] args) {
        testArrays();
    }

}


@Data
class Bubble {
    public final int i;

    public Bubble(int n) {
        i = n;
    }

    @Override
    public String toString() {
        return "Bubble(" + i + ")";
    }

    private static int count = 0;
    public static Bubble bubbler() {
        return new Bubble(count++);
    }
}
