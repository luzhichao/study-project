package org.gecko.reformer.poi.handler.impl;

import com.google.common.collect.Lists;
import org.gecko.reformer.excel.pojo.LookupParam;
import org.gecko.reformer.handler.IColHandler;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备类型处理器
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-10
 **/
@Service
public class CategoryHandler implements IColHandler {

    @Override
    public List<String> getDropDownData() {
        return Lists.newArrayList("温湿度传感器", "烟雾传感器", "二氧化碳传感器", "压力传感器", "易燃性气体传感器");
    }

    @Override
    public List<LookupParam> getLookupData() {
        List<LookupParam> dataParams = Lists.newArrayList();
        dataParams.add(new LookupParam("温湿度传感器", "TEMP_RH"));
        dataParams.add(new LookupParam("烟雾传感器", "SMOKE"));
        dataParams.add(new LookupParam("二氧化碳传感器", "CO2"));
        dataParams.add(new LookupParam("压力传感器", "PRESS"));
        dataParams.add(new LookupParam("易燃性气体传感器", "KRQT"));
        return dataParams;
    }
}
