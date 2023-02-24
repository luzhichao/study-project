package org.gecko.reformer.shrading.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * 描述：精确分片算法类名称，用于=和IN。。该类需实现PreciseShardingAlgorithm接口并提供无参数的构造器
 *
 * @author jianpeng
 * @date 2022/6/30 14:01
 */
@Component
public class ReformerPreciseShardingAlgorithm implements org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm<LocalDateTime> {

    /**
     * 功能描述:
     *      Collection<String> collection ：配置文件中数据库的数节点范围
     *      PreciseShardingValue<String> preciseShardingValue ：查询语句中查询值
     *      返回合适的表名
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<LocalDateTime> preciseShardingValue) {
        String dataBaseName = "";
        //获取到日期
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDateTime value = preciseShardingValue.getValue();
        String tableNameSuffix = value.format(fmt);
        for(String item : collection) {
            if(item.endsWith(tableNameSuffix)) {
                dataBaseName = item;
                break;
            }
        }
        return dataBaseName;
    }
}
