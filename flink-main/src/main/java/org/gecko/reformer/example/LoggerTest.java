package org.gecko.reformer.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-15
 **/
public class LoggerTest {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        String msg = "${java:vm}";
        logger.error("hello,{}",msg);
        logger.error("Try${date:YYYY-MM-dd}");
    }
}
