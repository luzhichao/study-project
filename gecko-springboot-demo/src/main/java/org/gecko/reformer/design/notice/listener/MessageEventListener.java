package org.gecko.reformer.design.notice.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信监听事件
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/08/21
 **/
@Slf4j
public class MessageEventListener implements EventListener {
    @Override
    public <T> void doEvent(T result) {
        log.info("给⽤户发送短信通知(短信)===>{}", result.toString());
    }
}
