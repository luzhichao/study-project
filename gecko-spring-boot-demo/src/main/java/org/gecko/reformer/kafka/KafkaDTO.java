package org.gecko.reformer.kafka;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * TODO
 *
 * @author LZC
 * @version 1.2.0
 * @date 2023-03-17
 **/
@Data
public class KafkaDTO implements Serializable {
    private static final long serialVersionUID = 3338092459910123350L;

    private String topic;
    private String massage;
    private Date date;
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;

}
