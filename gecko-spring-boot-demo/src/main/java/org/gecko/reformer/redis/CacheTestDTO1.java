package org.gecko.reformer.redis;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * TODO
 *
 * @author LZC
 * @version 1.2.0
 * @date 2023-03-23
 **/
@Data
public class CacheTestDTO1 implements Serializable {

    private String name;
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;
}
