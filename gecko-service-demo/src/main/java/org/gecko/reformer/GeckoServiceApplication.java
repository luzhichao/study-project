package org.gecko.reformer;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.ReformerApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

@Slf4j
@ReformerApplication
@MapperScan("org.gecko.reformer.log.mapper")
public class GeckoServiceApplication {

    public static void main(String[] args) {
        log.info("====run方法启动star#====");
        SpringApplication.run(GeckoServiceApplication.class, args);
        log.info("====run方法启动end#====");
    }

}
