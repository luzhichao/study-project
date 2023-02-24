package org.gecko.reformer.poi.constant;

/**
 * 常量
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-09
 **/
public interface PoiConstants {

    /*** 数据sheet页下标 **/
    int SHEET_DATA_INDEX = 0;
    /*** 表格数据开始行 **/
    int FIRST_ROW = 1;
    /*** 表格数据最后一行 **/
    int LAST_ROW = 1000;
    /*** 线程数 **/
    int THREAD_NUM = 10;
    /*** 名称管理器前缀 **/
    String NAME_PREFIX = "name_";
    /*** sheet名称模板 **/
    String SHEET_NAME_TEMPLE = "sheet{}";
    /*** 简单下拉选单元格引用公式=sheetName!$列名$开始行:$列名$结束行，例如=sheetName!$A$1:$A$20 **/
    String DROP_DOWN_CELL_REFERS = "{}!$A$1:$A${}";
    /*** 通过显示选择的数据获取程序需要的数据，第一个参数为来源列，第二个参数为行号，第三个参数为sheetName，=IF(ISNA(VLOOKUP(E2,sheet3!A:B,2,0)),"",VLOOKUP(E2,sheet3!A:B,2,0)) **/
    String LOOKUP_CELL_REFERS = "IF(ISNA(VLOOKUP({0}{1},{2}!A:B,2,0)),\"\",VLOOKUP({0}{1},{2}!A:B,2,0))";
    /*** 级联下拉名称定义公式，第一个参数是数据所在sheet名字，第二个参数是子级数据开始行，第三个参数是子级数据结束列，第四个参数是子级数据结束行，例如=sheet5!$A$1:$A$1 **/
    String NAME_REFERS = "{}!$A${}:${}${}";
    /*** 级联下拉子级下拉公式，第一个参数是名称管理器前缀，第二个参数是级联列，第三个参数是级联行，例如=INDIRECT("name_"&$A2) **/
    String CASCADE_CELL_REFERS = "INDIRECT(\"{}\"&${}{})";


}
