package org.gecko.reformer.geckoupgradedemo;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.ReformerApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@ReformerApplication
@MapperScan("org.gecko.reformer.geckoupgradedemo.mapper")
public class GeckoUpgradeDemoApplication {

    public static void main(String[] args) {
        log.info("====run方法启动star#====");
        SpringApplication.run(GeckoUpgradeDemoApplication.class, args);
        log.info("====run方法启动end#====");
    }

}
