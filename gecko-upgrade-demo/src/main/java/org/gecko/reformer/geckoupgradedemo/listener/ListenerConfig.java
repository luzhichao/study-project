package org.gecko.reformer.geckoupgradedemo.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-02-09
 **/
@Configuration
public class ListenerConfig {

    //@Bean
    public MyApplicationListener myListener(){
        return new MyApplicationListener();
    }
}
