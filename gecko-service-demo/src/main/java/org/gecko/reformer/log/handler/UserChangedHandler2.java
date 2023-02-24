package org.gecko.reformer.log.handler;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.handler.IChangedHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    public void exec(Date oldValue, Date newValue, ChangeDTO param, String mark) {
        log.info("===DateHandler===>");
        log.info("oldValue===>{}", oldValue);
        log.info("newValue===>{}", newValue);
        log.info("param===>{}", param);
        log.info("mark===>{}", mark);
    }
}
