package org.gecko.reformer.geckoupgradedemo;

import org.gecko.reformer.annotation.ReformerApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ReformerApplication
@MapperScan("org.gecko.reformer.geckoupgradedemo.mapper")
public class GeckoUpgradeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeckoUpgradeDemoApplication.class, args);
    }

}
