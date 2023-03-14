package org.gecko.reformer.constant;

/**
 * 常量
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-02
 **/
public interface IFlinkConstants {

    /*** all **/
    String ALL = "all";
    /*** 执行保存点命令 **/
    String EXECUTION_SAVE_POINT_COMMAND = "execution.savepoint.path";
    /*** 系统文件存储协议模版 file://文件夹绝对路径 **/
    String CHECK_POINT_SYS_FILE_AGREEMENT_TEMPLATE = "file://{}";
    /*** check point存储文件夹前缀 **/
    String CHECK_POINT_DIR_PREFIX = "chk-";
    /*** mysql数据源名称模版 **/
    String MYSQL_SOURCE_NAME_TEMPLATE = "MySQL Source {}";
    /*** 扫描处理器包路径 **/
    String SCAN_BASE_PACKAGE_NAME = "org.gecko.reformer";


}
