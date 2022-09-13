package org.gecko.reformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-08
 **/
@EnableScheduling
@SpringBootApplication
public class TriggerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TriggerDemoApplication.class, args);
    }
}
