package org.gecko.reformer.geckoupgradedemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.handler.IChangedHandler;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-02-21
 **/
@Slf4j
@Component
public class UserChangedHandler implements IChangedHandler<String, ChangeDTO> {

    @Override
    public void exec(String oldValue, String newValue, ChangeDTO param, String mark) {
        log.info("===StringHandler===>");
        log.info("oldValue===>{}", oldValue);
        log.info("newValue===>{}", newValue);
        log.info("param===>{}", param);
        log.info("mark===>{}", mark);
    }
}
