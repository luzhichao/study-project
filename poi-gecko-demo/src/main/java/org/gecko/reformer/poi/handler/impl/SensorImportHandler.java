package org.gecko.reformer.poi.handler.impl;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import org.gecko.reformer.poi.dto.SensorImportDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-14
 **/
@Slf4j
@Component
public class SensorImportHandler implements IExcelVerifyHandler<SensorImportDTO> {

    @Override
    public ExcelVerifyHandlerResult verifyHandler(SensorImportDTO dto) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult(true);
        log.info("verifyHandler==={}", dto);
        return result;
    }
}
