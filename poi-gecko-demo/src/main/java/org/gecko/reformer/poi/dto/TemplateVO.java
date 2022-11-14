package org.gecko.reformer.poi.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.BooleanEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-08
 **/
@Data
public class TemplateVO implements Serializable {

    @ExcelProperty("名称")
    @ContentStyle(dataFormat = 0)
    private String name;

    @ExcelProperty("报警名称")
    @ContentStyle(dataFormat = 0)
    private String alarmName;

    @ExcelProperty("设备编号")
    @ContentStyle(dataFormat = 0)
    private String deviceCode;

    @ExcelProperty(value = "报警类型", index = 3)
    @ContentStyle(dataFormat = 0, hidden = BooleanEnum.TRUE)
    private String alarmType;

    @ExcelProperty("报警类型名称")
    @ContentStyle(dataFormat = 0)
    private String alarmTypeName;

    @ExcelProperty("报警开始时间")
    @ContentStyle(dataFormat = 14)
    private Date alarmStartTime;

    @ExcelProperty("报警结束时间")
    @ContentStyle(dataFormat = 0)
    private Date alarmEndTime;

    @ExcelProperty("报警次数")
    @ContentStyle(dataFormat = 1)
    private Integer alarmNum;
}
