package org.gecko.reformer.shrading.algorithm;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.gecko.reformer.shrading.constant.ShardingConstants;
import org.gecko.reformer.util.DateUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 范围查询分片算法
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-21
 **/
@Component
public class RangeTableShardingAlgorithm implements RangeShardingAlgorithm<Date> {

    private SimpleDateFormat sdf = new SimpleDateFormat(ShardingConstants.YYYY_MM);

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        Range<Date> valueRange = shardingValue.getValueRange();
        Date lower = valueRange.lowerEndpoint();
        Date upper = valueRange.upperEndpoint();

        Collection<String> tables = new ArrayList<>();
        while (upper.after(lower)) {
            String tableName = shardingValue.getLogicTableName();
            String actual = sdf.format(upper);
            String actualDataNode = StrUtil.join(StrUtil.UNDERLINE, tableName, actual);
            tables.add(actualDataNode);
            upper = DateUtils.offsetMonth(upper, -1);
        }

        availableTargetNames.retainAll(tables);
        return availableTargetNames;
    }
}
