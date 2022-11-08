package org.gecko.reformer;

import org.gecko.reformer.annotation.ReformerApplication;
import org.springframework.boot.SpringApplication;

@ReformerApplication
public class ShardingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingApplication.class, args);
    }
}
