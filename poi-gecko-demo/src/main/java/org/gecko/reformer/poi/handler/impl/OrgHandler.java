package org.gecko.reformer.poi.handler.impl;

import com.google.common.collect.Lists;
import org.gecko.reformer.excel.pojo.LookupParam;
import org.gecko.reformer.handler.IColHandler;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 机构处理器
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-10
 **/
@Component
public class OrgHandler implements IColHandler {

    @Override
    public List<String> getDropDownData() {
        return Lists.newArrayList("（括号）浏阳市经济开发区（括号）",
                "湖南方锐达科技有限(公司)",
                "湖南锐林新能源科技有限-公司",
                "湖南罗比特化学材料有限/公司");
    }

    @Override
    public List<LookupParam> getLookupData() {
        List<LookupParam> dataParams = Lists.newArrayList();
        dataParams.add(new LookupParam("（括号）浏阳市经济开发区（括号）", "524045510349164544"));
        dataParams.add(new LookupParam("湖南方锐达科技有限(公司)", "524045829321789440"));
        dataParams.add(new LookupParam("湖南锐林新能源科技有限-公司", "524417282194018304"));
        dataParams.add(new LookupParam("湖南罗比特化学材料有限/公司", "548641444114468864"));
        return dataParams;
    }
}
