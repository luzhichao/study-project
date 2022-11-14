package org.gecko.reformer.poi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.gecko.reformer.**")
public class PoiGeckoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoiGeckoDemoApplication.class, args);
    }

}
