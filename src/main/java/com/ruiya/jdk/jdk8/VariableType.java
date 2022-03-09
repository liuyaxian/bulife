package com.ruiya.jdk.jdk8;

import com.ruiya.net.Url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VariableType {


    public static void main(String[] args) throws Exception {
        urlTest();
        urljdk8Test();
//        variableDeclara();
        varforloop();
        loops();
    }

    public static void  urlTest() throws Exception {

        URL url = new URL("https://baidu.com");

        URLConnection connection = url.openConnection();
        Reader reader = new BufferedReader(new InputStreamReader( connection.getInputStream()));
        reader.close();
    }

    public static void  urljdk8Test() throws Exception {

        var url = new URL("https://baidu.com");

        var connection = url.openConnection();
        var reader = new BufferedReader(new InputStreamReader( connection.getInputStream()));

        reader.close();
    }



    public static  void variableDeclara() throws Exception {
        var list = new ArrayList<String>();    // infers ArrayList<String>
        var stream = list.stream();            // infers Stream<String>
        var path = Paths.get("fileName");        // infers Path
        var bytes = Files.readAllBytes(path);  // infers bytes[]
    }



    public static  void varforloop(){
        List<String> myList = Arrays.asList("a", "b", "c");
        for (var element : myList) {
            System.out.printf("ss:", element);
        }  // infers String
    }



    public static void loops(){
        for (var counter = 0; counter < 10; counter++)  {
            System.out.printf("count:", counter);
        }   // infers int
    }
}
