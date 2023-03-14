package org.gecko.reformer.constant;

import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MySQL CDC消费者的启动模式
 * {@link com.ververica.cdc.connectors.mysql.table.StartupOptions}
 * {@link com.ververica.cdc.connectors.mysql.table.StartupMode}
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
@Getter
@AllArgsConstructor
public enum MysqlStartupMode {
    /*** 初始启动，先全量再增量 **/
    INITIAL(StartupOptions.initial()),
    /*** 从最新开始增量 **/
    LATEST_OFFSET(StartupOptions.latest()),

    //EARLIEST_OFFSET,
    //SPECIFIC_OFFSETS,
    //TIMESTAMP,
    ;

    /*** 执行模式 **/
    private StartupOptions option;
}
