package org.gecko.reformer.config;

import org.gecko.reformer.service.TestService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
@ConditionalOnProperty(prefix = "org.gecko.springboot.starter", name = "enabled", havingValue = "true")
public class TestServiceAutoConfiguration {

    @Bean
    public TestService testService() {
        return new TestService();
    }

}
