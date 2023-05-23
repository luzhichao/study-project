package org.gecko.reformer;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * TODO
 *
 * @author LZC
 * @date 2023-05-23
 * @version 1.2.0
 **/
@Slf4j
@SpringBootApplication
public class Application {

    @Value("${proper}")
    private String proper;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(TestService testService) {
        return (args) -> {
            log.info("proper mode: {}", proper);
            log.info(testService.getServiceName());
        };
    }

}
