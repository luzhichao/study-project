package org.gecko.reformer.poi.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.gecko.reformer.constant.PoiConstant;
import org.gecko.reformer.poi.dto.DropDownDO;

import java.util.List;

/**
 * 自定义简单sheet写入处理器
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-08
 **/
public class CustomSimpleSheetWriteHandler implements SheetWriteHandler {
    /*** 动态下拉框的数据 **/
    private List<DropDownDO> list;

    public CustomSimpleSheetWriteHandler(List<DropDownDO> list) {
        this.list = list;
    }

    /*** 定义sheet的名称 **/
    private final static String sheetName = "hidden";

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        DataValidationHelper helper = writeSheetHolder.getSheet().getDataValidationHelper();
        // 获取一个workbook工作簿，创建sheet，突破下拉框255的限制
        Workbook workbook = writeWorkbookHolder.getWorkbook();

        // 1.创建一个隐藏的sheet 名称为 providerSheet
        Sheet providerSheet = workbook.createSheet(sheetName);
        // 设置隐藏
        workbook.setSheetHidden(1, true);
        // 2.循环赋值
        for (DropDownDO entry : list) {
            CellRangeAddressList addressList = new CellRangeAddressList(1, 65535, entry.getColNum(), entry.getColNum());
            final List<String> values = entry.getData();
            for (int i = 0; i < values.size(); i++) {
                // 创建行
                Row row = providerSheet.getRow(i);
                if (row == null) {
                    row = providerSheet.createRow(i);
                }
                // 创建单元格并赋值
                row.createCell(entry.getColNum()).setCellValue(values.get(i));
            }
            // 3.$A$1:$A$N代表 以A列1行开始获取N行下拉数据
            String excelLine = getExcelLine(entry.getColNum());
            String refers = StrUtil.format(PoiConstant.DROP_DOWN_CELL_REFERS, sheetName, values.size());
            // 4.将刚才设置的sheet引用到你的下拉列表中
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

