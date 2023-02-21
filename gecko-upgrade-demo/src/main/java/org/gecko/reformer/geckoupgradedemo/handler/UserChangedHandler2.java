package org.gecko.reformer.geckoupgradedemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.handler.IChangedHandler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-02-21
 **/
@Slf4j
@Component
public class UserChangedHandler2 implements IChangedHandler<Date, ChangeDTO> {

    @Override
    public void exec(Date oldValue, Date newValue, ChangeDTO param) {
        log.info("===DateHandler===>");
        log.info("oldValue===>{}", oldValue);
        log.info("newValue===>{}", newValue);
        log.info("param===>{}", param);
    }
}
