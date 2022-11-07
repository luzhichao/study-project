package com.reformer.pg;

import com.reformer.annotation.ReformerApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

@ReformerApplication
@MapperScan("com.reformer.pg.mapper")
public class PgReformerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PgReformerDemoApplication.class, args);
    }
}
