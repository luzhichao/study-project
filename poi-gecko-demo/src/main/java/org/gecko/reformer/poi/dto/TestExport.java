package org.gecko.reformer.poi.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-09
 **/
@Data
public class TestExport implements Serializable {
    @ExcelProperty("key")
    private String key;
    @ExcelProperty("value")
    private String value;
}

