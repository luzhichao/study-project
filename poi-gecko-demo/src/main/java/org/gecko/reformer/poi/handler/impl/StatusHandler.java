package org.gecko.reformer.poi.handler.impl;

import com.google.common.collect.Lists;
import org.gecko.reformer.handler.IColHandler;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 状态处理器
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-10
 **/
@Component
public class StatusHandler implements IColHandler {

    @Override
    public List<String> getDropDownData() {
        return Lists.newArrayList();
    }
}
