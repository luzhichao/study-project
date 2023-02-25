package org.gecko.reformer.poi.dto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 下拉数据DO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-09
 **/
@Data
public class DropDownDO implements Serializable {

    /*** 数据列在第几列，从0开始 **/
    Integer colNum;
    /*** 下拉的数据 **/
    List<String> data;

}
