package org.gecko.reformer.poi.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义sheet写入处理器
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-08
 **/
public class CustomSheetWriteHandler0 implements SheetWriteHandler {
    // 动态下拉框的数据
    private List<String> list;

    public CustomSheetWriteHandler0(List<String> list) {
        this.list = list;
    }

    public CustomSheetWriteHandler0() {
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        DataValidationHelper helper = writeSheetHolder.getSheet().getDataValidationHelper();
        Map<Integer, String[]> mapDropDown = new HashMap<>();
        String[] listArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listArray[i] = list.get(i);
        }

        //定义下拉框中的数据 并添加到map中 这里的key是写死的
        // 第一个数表示对应的excel字段的序号，第二个则是数组
        mapDropDown.put(9, listArray);
        //获取工作簿
        // 创建sheet，突破下拉框255的限制
        //获取一个workbook
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        //定义sheet的名称
        String sheetName = "hidden";
        //1.创建一个隐藏的sheet 名称为 providerSheet
        Sheet providerSheet = workbook.createSheet(sheetName);
        // 设置隐藏
        workbook.setSheetHidden(1, true);
        //2.循环赋值（为了防止下拉框的行数与隐藏域的行数相对应，将隐藏域加到结束行之后）
        for (Map.Entry<Integer, String[]> entry : mapDropDown.entrySet()) {
            CellRangeAddressList addressList = new CellRangeAddressList(1, 65535, entry.getKey(), entry.getKey());
            String[] values = entry.getValue();
            for (int i = 0, length = values.length; i < length; i++) {
                // i:表示你开始的行数  0表示你开始的列数
                Row row = providerSheet.getRow(i);
                if (row == null) {
                    row = providerSheet.createRow(i);
                }
                row.createCell(entry.getKey()).setCellValue(values[i]);
            }
            //4 $A$1:$A$N代表 以A列1行开始获取N行下拉数据
            String excelLine = getExcelLine(entry.getKey());
            String refers = "=" + sheetName + "!$" + excelLine + "$1:$" + excelLine + "$" + (values.length);
            //5 将刚才设置的sheet引用到你的下拉列表中
            DataValidationConstraint constraint = helper.createFormulaListConstraint(refers);
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            writeSheetHolder.getSheet().addValidationData(dataValidation);
        }
    }

    /**
     * 返回excel列标A-Z-AA-ZZ
     *
     * @param num 列数
     * @return java.lang.String
     * @throws
     * @author LZC
     * @date 2022-11-09
     * @version 1.1.2
     **/
    private static String getExcelLine(int num) {
        String line = "";
        int first = num / 26;
        int second = num % 26;
        if (first > 0) {
            line = (char) ('A' + first - 1) + "";
        }
        line += (char) ('A' + second) + "";
        return line;
    }
}

