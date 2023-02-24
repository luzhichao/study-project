package org.gecko.reformer.log.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-02-09
 **/
@Slf4j
public class MyApplicationContextInitializer implements ApplicationContextInitializer, Ordered {

    static {
        System.setProperty("nacos.logging.default.config.enabled", "false");
    }

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        log.info("My==start#====");
        System.setProperty("nacos.logging.default.config.enabled", "false");
        log.info("My==end#====");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
