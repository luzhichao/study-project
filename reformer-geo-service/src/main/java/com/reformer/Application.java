package com.reformer;

import com.reformer.annotation.ReformerApplication;
import org.springframework.boot.SpringApplication;

/**
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-08
 **/
@ReformerApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
