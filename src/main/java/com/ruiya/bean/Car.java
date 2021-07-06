package com.ruiya.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 只有在容器中的组件才具有springboot提供的强大功能
 */
//@Component
@ConfigurationProperties(prefix = "mycar")
//@Data
//@Getter
//@Setter
////@FieldNameConstants
//@ToString
//@EqualsAndHashCode
//@AllArgsConstructor
//@RequiredArgsConstructor
//@NoArgsConstructor
//@Log
//@Log4j @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
//@Builder
//@SuperBuilder
//@Singular
//@Delegate
//@Value
//@Accessors
//@Wither
//@With
//@SneakyThrows
//@val
//@var
//@UtilityClass

public class Car {
    private String name;
    private double price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Car() {
    }
}
