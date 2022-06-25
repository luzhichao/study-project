package org.gecko.reform.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 导出表结构DTO
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/08/18
 **/
@Data
public class ExportDTO implements Serializable {
    private static final long serialVersionUID = 8340021890500394994L;

    /**
     * 表名
     **/
    private String tableName;
    /**
     * 表描述
     **/
    private String tableComment;
    /**
     * 字段名
     **/
    private String columnName;
    /**
     * 字段类型
     **/
    private String columnType;
    /**
     * 是否是主键
     **/
    private String columnKey;
    /**
     * 是否允许为空
     **/
    private String nullable;
    /**
     * 字段描述
     **/
    private String columnComment;

}
