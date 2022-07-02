package org.gecko.reformer.algorithm;

import cn.hutool.core.util.StrUtil;
import org.gecko.reformer.constant.ShardingConstants;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * 精确查询分片算法
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-21
 **/
@Component
public class PreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Date> {

    private SimpleDateFormat sdf = new SimpleDateFormat(ShardingConstants.YYYY_MM);

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {

        String tableName = preciseShardingValue.getLogicTableName();
        Date value = preciseShardingValue.getValue();
        String actual = sdf.format(value);
        return StrUtil.join(StrUtil.UNDERLINE, tableName, actual);
    }
}
