package org.gecko.reformer.poi.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.gecko.reformer.annotation.ColHandler;
import org.gecko.reformer.annotation.ExcelHandler;
import org.gecko.reformer.constant.DataFormula;
import org.gecko.reformer.poi.handler.impl.CategoryHandler;
import org.gecko.reformer.poi.handler.impl.OrgHandler;
import org.gecko.reformer.poi.handler.impl.RegionHandler;

import java.io.Serializable;

/**
 * 导出实体类
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-14
 **/
@Data
@ExcelHandler(firstRow = 3, lastRow = 1234)
public class SensorImportDTO implements Serializable {

    @Excel(name = "设备编号(*)", width = 20, orderNum = "1")
    private String deviceCode;

    @Excel(name = "设备名称(*)", width = 20, orderNum = "0")
    private String deviceName;

    @Excel(name = "设备类型(*)", width = 20, orderNum = "2")
    @ColHandler(colNum = 2, type = DataFormula.DROPDOWN, service = CategoryHandler.class)
    private String deviceCategoryName;

    @Excel(name = "设备类型KEY", width = 20, /*isColumnHidden = true,*/ orderNum = "3")
    @ColHandler(colNum = 3, sourceColNum = 2, type = DataFormula.LOOKUP, service = CategoryHandler.class)
    private String deviceCategory;

    @Excel(name = "设备状态(*)", width = 15, orderNum = "4")
    private String statusName;

    @Excel(name = "设备状态KEY", width = 10, isColumnHidden = true, orderNum = "5")
    private Integer status;

    @Excel(name = "安装地址(*)", width = 30, orderNum = "6")
    private String address;

    @Excel(name = "地址坐标(经度,纬度)(*)", width = 20, orderNum = "7")
    private String point;

    @Excel(name = "所属机构(*)", width = 25, orderNum = "8")
    @ColHandler(colNum = 8, type = DataFormula.DROPDOWN, service = OrgHandler.class)
    private String deptName;

    @Excel(name = "所属机构ID", width = 25, isColumnHidden = true, orderNum = "9")
    @ColHandler(colNum = 9, sourceColNum = 8, type = DataFormula.LOOKUP, service = OrgHandler.class)
    private String deptId;

    @Excel(name = "所属区域(*)", width = 20, orderNum = "10")
    @ColHandler(colNum = 10, sourceColNum = 9, type = DataFormula.CASCADE, service = RegionHandler.class)
    private String regionName;

    @Excel(name = "所属区域ID", width = 20, isColumnHidden = true, orderNum = "11")
    @ColHandler(colNum = 11, sourceColNum = 10, type = DataFormula.LOOKUP, service = RegionHandler.class)
    private String regionId;

    @Excel(name = "设备SN编码", width = 20, orderNum = "12")
    private String deviceSn;
}
