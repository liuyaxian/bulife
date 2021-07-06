package com.ruiya.config;

import com.ruiya.Converter.MessageConerter;
import com.ruiya.bean.Car;
import com.ruiya.bean.Pet;
import com.ruiya.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;

/**
 * @author admin
 */
// 这是一个配置类， 同之前的配置文件 beans.xml
//@Configuration()
//@Configuration(proxyBeanMethods=false)
//给容器中自动创建出这两个类型的组件
//@Import({User.class, DBHelper.class})
@Configuration()
//@ConditionalOnBean(name = "tom1")
// 条件装备， 容器中有tom1时操作
// 导入spring。xml配置文件生效
//@ImportResource("classpath:beans.xml")
// 开启carde 属性配置功能   配置文件中的类
@EnableConfigurationProperties(Car.class)
public class MyConfig  implements WebMvcConfigurer {

    @ConditionalOnBean(name = "tom")
    @Bean()
    // 给容器中添加组件  方法名为组件的id = use01  返回的类型为组件类型，  方法返回的值为组件在容器中实例
    public User user01(){
        User user = new User("zjag", 11);
        user.setPet(new Pet("eeee"));
        return user;
    }

    @Bean("tt")
    @ConditionalOnBean(name = "tom")
    public Pet tomcatPet(){
        return new Pet("tomcat1");
    }


    public WebMvcConfigurer webMvcConfigurer(){
        return  new WebMvcConfigurer() {

            /***
             * 自定义内容协商策略
             * @param configurer
             */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                Map<String, MediaType> mediaTypes  = new HashMap<>();
                mediaTypes.put("json", MediaType.APPLICATION_JSON);
                mediaTypes.put("xml", MediaType.APPLICATION_XML);
                mediaTypes.put("liusi", MediaType.parseMediaType("application/x-liusi"));

                ParameterContentNegotiationStrategy parameterContentNegotiationStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
                parameterContentNegotiationStrategy.setParameterName("param");
                HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();

                configurer.strategies(Arrays.asList(parameterContentNegotiationStrategy, headerContentNegotiationStrategy));
            }


            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new MessageConerter());
            }

            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);
                WebMvcConfigurer.super.configurePathMatch(configurer);
            }

            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addFormatter(new Formatter<String>() {
                                          @Override
                                          public String print(String object, Locale locale) {
                                              return  new String(object.getBytes(StandardCharsets.UTF_8));
                                          }

                                          @Override
                                          public String parse(String text, Locale locale) throws ParseException {
//                                              DateTimeFormat dateFormat = new DateTimeFormat();
                                              return null;
                                          }
                                      }
                );

                registry.addConverter(new Converter<String, Pet>() {
                    @Override
                    public Pet convert(String source) {
                        Pet pet  = new Pet();
                        String[] split = source.split(",");
                        pet.setName(split[0]);
                        return pet;
                    }
                });
            }
        };
    }
}
