package com.ruiya;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author admin
 */
@SpringBootApplication
@MapperScan("com.ruiya.mybatisplus.postgresql.mapper")

public class BuLifeApplication {
    public static void main(String[] args) {
        // 1、返回我们 ioc容器
        ConfigurableApplicationContext run =
                SpringApplication.run(BuLifeApplication.class, args);

	// 2、查看容器里面的组件
		String[] names = run.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}

        // 单实例对象
        //代理对象，
        //@Configuration(proxyBeanMethods=false)
        // 新创方法
        //@Configuration(proxyBeanMethods=false)
//		User bean = run.getBean(User.class);
//		User bean1= run.getBean(User.class);
//		System.out.println(bean1 == bean);

//		User user01 = run.getBean(User.class);
//		Pet pet = user01.getPet();
//		System.out.println(pet.getName() == "eeee");
//		MyConfig bean1 = run.getBean(MyConfig.class);
//		System.out.println(bean1.user01().getAge());
//
//		// 获取组件
//		String[] beanNamesForType = run.getBeanNamesForType(User.class);
//		System.out.println("------------");
//
//		for (String s : beanNamesForType) {
//			System.out.println(s);
//		}
//
//		DBHelper bean = run.getBean(DBHelper.class);
//		System.out.println(bean);


    }
}
