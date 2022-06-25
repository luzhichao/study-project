package org.gecko.reformer.notice.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * MQ监听事件
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/08/21
 **/
@Slf4j
public class MQEventListener implements EventListener {
    @Override
    public <T> void doEvent(T result) {
        log.info("记录⽤户结果(MQ)===>{}", result.toString());
    }
}
