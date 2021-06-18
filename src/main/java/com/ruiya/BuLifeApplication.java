package com.ruiya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.springframework.boot.SpringApplication.run;

/**
 * @author admin
 */
@SpringBootApplication
public class BuLifeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuLifeApplication.class, args);
//        ConfigurableApplicationContext  context =  SpringApplication.run(BuLifeApplication.class, args);
//        String[] beanNamesForType = context.getBeanNamesForType(BuLifeApplication.class);
//        for (String s : beanNamesForType) {
//            System.out.println("s:" +  s);
//        }
    }
}
