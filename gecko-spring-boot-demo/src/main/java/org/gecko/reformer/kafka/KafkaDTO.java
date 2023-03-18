package org.gecko.reformer.kafka;

import lombok.Data;

import java.io.Serializable;

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

}
