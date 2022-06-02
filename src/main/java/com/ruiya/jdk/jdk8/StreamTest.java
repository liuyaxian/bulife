package com.ruiya.jdk.jdk8;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/***
 * Stream 概念
 * java.util.stream;
 * https://blog.51cto.com/u_15185289/2783509
 */
//TODO
public class StreamTest {

    public static void main(String[] args) throws IOException {
            char c;
            // 使用 System.in 创建 BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("输入字符, 按下 'q' 键退出。");
//            String str ;
//            // 读取字符
//            do {
//                c = (char) br.read();
//                System.out.println(c);
//            } while (c != 'q');
//            do{
//                str = br.readLine();
//                System.out.println(str);
//
//            } while (!str.equals("end"));


        int b;
        b = 'A';
        System.out.write(b);
        System.out.write('\n');}



    public  static void  testStream1(){

//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//        // 获取对应的平方数
//        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
//
//        numbers.stream().map(i -> i*i);


//        Random random = new Random(3);
//        random.ints().limit(12).forEach(System.out::println);

//        Map<String, Integer> map = new HashMap<>();
//        for (String s : arr) {
//            map.put(s, map.getOrDefault(s, 0) + 1);
//
//        }
////        map.forEach((key, value) -> System.out.println(key + " : " + value));
//        map.forEach((k,v) -> System.out.println("key"+ k + "value"+v));

    }


    /**
     * of 可接收一个泛型对象或可变成泛型集合，构造一个 Stream 对象。
     */
    public static void createStream() {
        Stream<String> stringStream = Stream.of("1", "2", "a", "b");
        stringStream.filter(str -> str.contains("a"))
                .forEach(s -> System.out.println("输出： " + s));

//        stringStream.findFirst();
//        stringStream.findAny();
//        stringStream.count();
//        stringStream.peek(e -> System.out.println(e.toUpperCase())).collect(Collectors.toList());
//        stringStream.forEach(e->System.out.println(e.toUpperCase()));
//        // 是有顺序保证的，
//        stringStream.parallel().forEach(e->System.out.println(e.toUpperCase()));
//        stringStream.limit(2).forEach(e->System.out.println(e));
//        stringStream.skip(2).forEach(e->System.out.println(e));
//
//        Stream<Integer> integerStrea1 = Stream.of(1,2,5,7,8,12,33);
//        List<Integer> list = integerStrea1.filter(s -> s.intValue()>7).collect(Collectors.toList());
//        List<Integer> list1 = integerStrea1.filter(s -> s.intValue()>7).collect(ArrayList::new, ArrayList::add,
//                ArrayList::addAll);
//
////        元素去重，例如下面方法返回元素是 a、b、c，将重复的 b 只保留了一个。
//        Stream<String> a = Stream.of("a", "b", "c","b");
//        a.skip(2).forEach(e->System.out.println(e));
//        // sorted 有两个重载，一个无参数，另外一个有个 Comparator类型的参数。
//        // 无参类型的按照自然顺序进行排序，只适合比较单纯的元素，比如数字、字母等。
//        stringStream.sorted().forEach(e->System.out.println(e));
//        // 有参数的需要自定义排序规则，例如下面这个方法，按照第二个字母的大小顺序排序，最后输出的结果是 a1、b3、c6。
//        a.sorted((x,y)->Integer.parseInt(x.substring(1))>Integer.parseInt(y.substring(1))?1:-1).forEach(e->System.out.println(e));
//        List<User> users = getUserData();
//        Stream<User> stream = users.stream();
//        stream.filter(user -> user.getGender()==0 && user.getAge()>50).forEach(e->System.out.println(e));
//             BeanUtils.copyProperties(user, dto);
//        List<UserDto> userDtos = stream.map(user -> dao2Dto(user)).collect(Collectors.toList());
//        stream.mapToInt();
//        stream.mapToInt();
//        stream.mapToDouble();
//        stream.mapToLong();
//        stream.flatMap();
//        Stream<String[]>
//        Stream<Set<String>>
//        Stream<List<String>>


        List<User> user = getUserData();

//// 返回 userId:List<User>
//        Map<String,List<User>> map = user.stream().collect(Collectors.groupingBy(User::getUserId));

//// 返回 userId:每组个数
//        Map<String,Long> map1 = user.stream().collect(Collectors.groupingBy(User::getUserId,Collectors.counting()));
//        User[] userArray = stream.filter(user -> user.getGender().equals(0) && user.getAge() > 50).toArray(User[]::new);
//        Integer sum = integerStream1.reduce(0,(x,y)->x+y);

//        BeanUtils.copyProperties(user, dto);

//        DateConverter dateConverter = new DateConverter(null);
//        dateConverter.setPatterns(new String[]{"YYYYMMDD"});
//        ConverterUtil.register(dateConverter, Date.class);
    }

    private static void flatMap() {
        List<User> users = getUserData();
        List<User> users1 = getUserData();
        List<List<User>> userList = new ArrayList<>();
        userList.add(users);
        userList.add(users1);
        Stream<List<User>> stream = userList.stream();
//        List<UserDto> userDtos = stream.flatMap(subUserList->subUserList.stream()).map(user -> dao2Dto(user)).collect(Collectors.toList());
    }


    @Data
    @NoArgsConstructor
    static
    class UserDto {
        private int userId;
        private String userName;
        private String phone;
        private int gender;
        private String address;
        private int age;
    }

    @Data
    @NoArgsConstructor
    static
    class User {
        private int userId;
        private String userName;
        private String phone;
        private int gender;
        private String address;
        private int age;
    }

    private static List<User> getUserData() {
        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName(String.format("古时的风筝 %s 号", i));
            user.setAge(random.nextInt(100));
            user.setGender(i % 2);
            user.setPhone("18812021111");
            user.setAddress("无");
            users.add(user);
        }
        return users;
    }

    /**
     * empty 创建一个空的 Stream 对象。 TODO
     */
    public static void createNullStream() {
        Stream<String> stringStream = Stream.of();
    }

    /**
     * concat 连接两个 Stream ，不改变其中任何一个 Steam 对象，返回一个新的 Stream 对象。
     */
    public static void concatStream() {
        Stream<String> a = Stream.of("A", "B", "C");
        Stream<String> b = Stream.of("d", "e", "f");
        Stream<String> c = Stream.concat(a, b);
    }

    /**
     * max
     * 一般用于求数字集合中的最大值，或者按实体中数字类型的属性比较，拥有最大值的那个实体。
     * 它接收一个 Comparator<T>，上面也举到这个例子了，它是一个函数式接口类型，
     * 专门用作定义两个对象之间的比较，例如下面这个方法使用了 Integer::compareTo这个方法引用。
     */
    private static void max() {
        Stream<Integer> integerStream = Stream.of(2, 2, 100, 5);
        Integer max = integerStream.max(Integer::compareTo).get();
        System.out.println(max);
    }

    private static void max2() {
        Stream<Integer> integerStream = Stream.of(2, 2, 100, 5);
        Comparator<Integer> comparator = (x, y) -> (x.intValue() < y.intValue()) ? -1 : ((x.equals(y)) ? 0 : 1);
        Integer max = integerStream.max(comparator).get();
        System.out.println(max);
    }
//    min
//    与 max 用法一样，只不过是求最小值。
//    findFirst
//    获取 Stream 中的第一个元素。
//    findAny
//    获取 Stream 中的某个元素，如果是串行情况下，一般都会返回第一个元素，并行情况下就不一定了。
//    count
//    返回元素个数。
//    Stream<String> a = Stream.of("a", "b", "c");
//    long x = a.count();

}
