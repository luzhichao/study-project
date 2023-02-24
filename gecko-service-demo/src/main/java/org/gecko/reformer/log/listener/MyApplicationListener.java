package org.gecko.reformer.log.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-02-09
 **/
@Slf4j
public class MyApplicationListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("MyListener#onApplicationEvent==start#====");
        //System.setProperty("nacos.logging.default.config.enabled", "false");
        final ConfigurableEnvironment env = event.getApplicationContext().getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();
        Map<String, Object> myMap = new HashMap<>();
        myMap.put("logging.config", "classpath:my/logback.xml");
        propertySources.addFirst(new MapPropertySource("MY_MAP", myMap));
        log.info("MyListener#onApplicationEvent==end#====");
    }
}
