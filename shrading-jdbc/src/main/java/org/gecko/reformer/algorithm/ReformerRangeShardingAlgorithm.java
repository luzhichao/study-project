package org.gecko.reformer.algorithm;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.gecko.reformer.exception.CustomException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 描述：范围分片算法类名称，用于BETWEEN，可选。。该类需实现RangeShardingAlgorithm接口并提供无参数的构造器
 *
 * @author jianpeng
 * @date 2022/6/30 14:03
 */
@Component
public class ReformerRangeShardingAlgorithm implements org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm<LocalDateTime> {

    /**
     * 功能描述:
     *      Collection<String> collection ：配置文件中数据库的数节点范围
     *      PreciseShardingValue<String> preciseShardingValue ：查询语句中查询值
     *      返回合适的表名集合
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<LocalDateTime> rangeShardingValue) {
        Collection<String> collect = new ArrayList<>();//返回数据库节点名称list
        LocalDate startDate;//查询开始时间
        LocalDate endDate;//查询结束
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        //1.获取到要查询的表的开始时间和结束时间
        Range<LocalDateTime> valueRange = rangeShardingValue.getValueRange();
        LocalDateTime lower = ObjectUtil.isNotNull(valueRange.lowerEndpoint()) ? valueRange.lowerEndpoint() : null;//查询下限值
        LocalDateTime upper = ObjectUtil.isNotNull(valueRange.upperEndpoint()) ? valueRange.upperEndpoint() : null;//查询上限值
        if(ObjectUtil.isNotNull(lower) && ObjectUtil.isNotNull(upper)) {
            startDate = lower.toLocalDate();
            endDate = upper.toLocalDate();
        }else if(ObjectUtil.isNotNull(lower)) {
            startDate = lower.toLocalDate();
            endDate = LocalDateTime.now().toLocalDate();
        }else if(ObjectUtil.isNotNull(upper)) {
            startDate = LocalDate.parse("2022_09_01", fmt);
            endDate = upper.toLocalDate();
        }else {
            throw new CustomException("未找到要查询的表");
        }
        //2.根据开始、结束时间筛选出符合条件的表
        //表名示例：t_vehicle_real_location_2022_07_01
        String TableNamePrefix = "t_vehicle_real_location_";
        for(String item : collection) {
            //获取表名的日期后缀
            String tableDate = item.substring(TableNamePrefix.length());
            LocalDate tableLocalDate = LocalDate.parse(tableDate, fmt);
            //判断当前时间在开始和结束时间之内 则返回该表
            if((tableLocalDate.isAfter(startDate) && tableLocalDate.isBefore(endDate)) ||
                    tableLocalDate.compareTo(startDate) == 0 ||
                    tableLocalDate.compareTo(endDate) == 0) {
                collect.add(item);
            }
        }
        return collect;
    }
}
