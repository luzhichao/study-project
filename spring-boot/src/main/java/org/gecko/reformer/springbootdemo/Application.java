package org.gecko.reformer.springbootdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "org.gecko.reformer.**")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("======info======");
        log.debug("======debug======");
        log.error("======error======");
    }

}
