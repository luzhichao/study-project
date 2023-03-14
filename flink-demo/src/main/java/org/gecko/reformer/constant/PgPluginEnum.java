package org.gecko.reformer.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 服务器上安装的Postgres逻辑解码插件的名称。
 * 支持的值有decoderbufs, wal2json, wal2json_rds, wal2json_streaming, wal2json_rds_streaming, pgoutput。
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-07
 **/
@Getter
@AllArgsConstructor
public enum PgPluginEnum {

    DECODER_BUF("decoderbufs"),
    WAL_2_JSON("wal2json"),
    WAL_2_JSON_RDS("wal2json_rds"),
    WAL_2_JSON_STREAMING("wal2json_streaming"),
    WAL_2_JSON_RDS_STREAMING("wal2json_rds_streaming"),
    PG_OUTPUT("pgoutput"),
    ;

    private String code;
}
