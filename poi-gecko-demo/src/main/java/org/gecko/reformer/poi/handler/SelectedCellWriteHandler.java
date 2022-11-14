package org.gecko.reformer.poi.handler;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-09
 **/
@Slf4j
public class SelectedCellWriteHandler implements CellWriteHandler {

    private final String KEY = "key";
    private final String VAL = "value";
    private static String  key = "";

    /**
     * 数据map
     */
    private static Map<String, List<TestSelectData>> dataMap;

    static {
        //实际场景中可以使用SpringContext工具获取spring管理的bean调用service或repository中的数据查询方法
        List<TestSelectData> list = new ArrayList();
        TestSelectData d1 = new TestSelectData();
        d1.setKey("A");
        d1.setValue("1");

        TestSelectData d2 = new TestSelectData();
        d2.setKey("A");
        d2.setValue("2");

        TestSelectData d3 = new TestSelectData();
        d3.setKey("B");
        d3.setValue("3");

        TestSelectData d4 = new TestSelectData();
        d4.setKey("B");
        d4.setValue("4");

        list.add(d1);
        list.add(d2);
        list.add(d3);
        list.add(d4);
        dataMap = list.stream().collect(Collectors.groupingBy(TestSelectData::getKey));
    }


    /**
     * called after the cell is disposed
     * @param writeSheetHolder
     * @param writeTableHolder
     * @param cellDataList 单元格数据
     * @param cell 单元格
     * @param head 标题
     * @param relativeRowIndex
     * @param isHead 是否是标题列
     */
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

        if (!isHead) {
            //key列
            if (KEY.equals(head.getFieldName())){
                key = cell.getStringCellValue();
            }

            //value列
            if (VAL.equals(head.getFieldName())){
                //设置value下拉框
                setSelectDataList(writeSheetHolder,key,cell.getRowIndex(),cell.getColumnIndex());
            }
        }
    }

    /**
     * 设置下拉框数据
     * @param writeSheetHolder
     * @param key
     * @param rowIndex 行号
     * @param columnIndex 列号
     */
    private void setSelectDataList(WriteSheetHolder writeSheetHolder, String key, int rowIndex, int columnIndex) {
        Sheet sheet = writeSheetHolder.getSheet();
        DataValidationHelper helper = sheet.getDataValidationHelper();

        // 设置下拉列表的行： 首行，末行，首列，末列
        CellRangeAddressList rangeList = new CellRangeAddressList(rowIndex, rowIndex, columnIndex, columnIndex);
        // 设置下拉列表的值
        DataValidationConstraint constraint = helper.createExplicitListConstraint(getSourceByKey(key));
        // 设置约束
        DataValidation validation = helper.createValidation(constraint, rangeList);
        // 阻止输入非下拉选项的值
        validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        validation.setShowErrorBox(true);
        validation.setSuppressDropDownArrow(true);
        validation.createErrorBox("提示", "请输入下拉选项中的内容");
        sheet.addValidationData(validation);
    }

    /**
     * 根据key关联出下拉框数据
     * @param key
     * @return
     */
    private String[] getSourceByKey(String key) {
        List<TestSelectData> values = dataMap.get(key);
        List<String> selectList = values.stream().map(TestSelectData::getValue).collect(Collectors.toList());
        String[] selectArray = selectList.toArray(new String[selectList.size()]);
        return selectArray;
    }

}
@Data
class TestSelectData {
    private String key;
    private String value;
}

