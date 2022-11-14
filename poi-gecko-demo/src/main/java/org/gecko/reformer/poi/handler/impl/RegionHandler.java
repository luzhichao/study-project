package org.gecko.reformer.poi.handler.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.gecko.reformer.excel.pojo.LookupParam;
import org.gecko.reformer.handler.IColHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 区域处理器
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-10
 **/
@Service
public class RegionHandler implements IColHandler {

    @Override
    public List<LookupParam> getLookupData() {
        List<LookupParam> dataParams = Lists.newArrayList();
        dataParams.add(new LookupParam("电子信息产业区", "541762588959051776"));
        dataParams.add(new LookupParam("智慧物流产业区", "541763678974775296"));
        dataParams.add(new LookupParam("方锐达一号车间", "536686117013950464"));
        dataParams.add(new LookupParam("反应釜区域", "539956148942409728"));
        dataParams.add(new LookupParam("合产区域", "539956977187426304"));
        dataParams.add(new LookupParam("陈品仓库", "540975803358253056"));
        dataParams.add(new LookupParam("方锐达二号车间", "548578341297328128"));
        dataParams.add(new LookupParam("生产车间", "541094238767550464"));
        dataParams.add(new LookupParam("成品库房", "541094469240360960"));
        dataParams.add(new LookupParam("一车间", "548648516428238848"));
        return dataParams;
    }

    @Override
    public Map<String, List<String>> getCascadeData() {
        Map<String, List<String>> cascadeData = Maps.newConcurrentMap();
        List<String> list1 = Lists.newArrayList("电子信息产业区", "智慧物流产业区");
        List<String> list2 = Lists.newArrayList("方锐达一号车间", "反应釜区域", "合产区域", "陈品仓库", "方锐达二号车间");
        List<String> list3 = Lists.newArrayList("生产车间", "成品库房");
        List<String> list4 = Lists.newArrayList("一车间");
        // 浏阳市经济开发区
        cascadeData.put("524045510349164544", list1);
        // 湖南方锐达科技有限公司
        cascadeData.put("524045829321789440", list2);
        // 湖南锐林新能源科技有限公司
        cascadeData.put("524417282194018304", list3);
        // 湖南罗比特化学材料有限公司
        cascadeData.put("548641444114468864", list4);
        return cascadeData;
    }
}
