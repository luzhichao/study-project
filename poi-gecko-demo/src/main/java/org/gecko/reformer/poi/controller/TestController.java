package org.gecko.reformer.poi.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.excel.util.ExcelUtils;
import org.gecko.reformer.poi.dto.SensorImportDTO;
import org.gecko.reformer.poi.handler.impl.SensorImportHandler;
import org.gecko.reformer.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * test
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-11
 **/
@Slf4j
@Api(tags = "test")
@WebController
@RequestMapping("/poi/gecko/api/")
public class TestController {


    // ----------------------------EasyPoi----------------------------------

    @SneakyThrows
    @ApiOperation("test1")
    @PostMapping("/test1")
    public void test1(HttpServletResponse response) {
        final ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        ExcelUtils.exportTemplate(exportParams, "test1.aa", SensorImportDTO.class, null, response);
    }

    @SneakyThrows
    @ApiOperation("test2")
    @PostMapping("/test2")
    public void test2(HttpServletResponse response) {
        final ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.HSSF);
        ExcelUtils.exportTemplate(exportParams, "test2.bb", SensorImportDTO.class, null, response);
    }

    //@SneakyThrows
    //@ApiOperation("test1")
    //@PostMapping("/test1")
    //public void test1(HttpServletResponse response) {
    //    final ExportParams exportParams = new ExportParams();
    //    exportParams.setType(ExcelType.XSSF);
    //    Workbook workbook = ExcelExportUtil.exportExcel(exportParams, SensorImportDTO.class, Lists.newArrayList());
    //    ExcelColHandlerUtils.dropDownColHandler(workbook, SensorImportDTO.class);
    //
    //    String fileName = URLEncoder.encode("test3.xlsx", CharsetUtil.UTF_8);
    //    response.setContentType(FileType.XLSX.getType());
    //    response.setHeader(PoiConstant.CONTENT_DISPOSITION, StrUtil.format(PoiConstant.ATTACHMENT, URLEncoder.encode(fileName, PoiConstant.UTF_8)));
    //    workbook.write(response.getOutputStream());
    //}
    //
    //@SneakyThrows
    //@ApiOperation("test2")
    //@PostMapping("/test2")
    //public void test2(HttpServletResponse response) {
    //    final ExportParams exportParams = new ExportParams();
    //    Workbook workbook = ExcelExportUtil.exportExcel(exportParams, SensorImportDTO.class, Lists.newArrayList());
    //    ExcelColHandlerUtils.dropDownColHandler(workbook, SensorImportDTO.class);
    //
    //    String fileName = URLEncoder.encode("test3.xls", CharsetUtil.UTF_8);
    //    response.setContentType(FileType.XLS.getType());
    //    response.setHeader(PoiConstant.CONTENT_DISPOSITION, StrUtil.format(PoiConstant.ATTACHMENT, URLEncoder.encode(fileName, PoiConstant.UTF_8)));
    //    workbook.write(response.getOutputStream());
    //}


    @Autowired
    private SensorImportHandler sensorDicHandler;

    @SneakyThrows
    @PostMapping("/test3")
    @ApiOperation("test3")
    public Result<String> test3(@RequestPart("file") MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setNeedVerify(true);
        params.setVerifyHandler(sensorDicHandler);
        params.setKeyIndex(2);
        List<SensorImportDTO> exportList = ExcelImportUtil.importExcel(file.getInputStream(), SensorImportDTO.class, params);
        log.info("导入数据大小==={}", exportList.size());
        return Result.success();
    }


    // ----------------------------EasyExcel----------------------------------

    //@SneakyThrows
    //@ApiOperation("downloadTemplateEasyExcel")
    //@PostMapping("/downloadTemplateEasyExcel")
    //public void downloadTemplateEasyExcel(HttpServletResponse response) {
    //    // 导出模板名称
    //    response.setContentType("multipart/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    //    response.setCharacterEncoding("utf-8");
    //    // 编码 防止中文乱码
    //    String fileName = URLEncoder.encode("NoHandler.xlsx", "UTF-8");
    //    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    //    // excel表头数据
    //    List<TemplateVO> downloadTemplateVOList = new ArrayList<>();
    //
    //    EasyExcel.write(response.getOutputStream(), TemplateVO.class)
    //            .sheet("sheet名称")
    //            .doWrite(downloadTemplateVOList);
    //}
    //
    //@SneakyThrows
    //@ApiOperation("downloadTemplateNoHandler")
    //@PostMapping("/downloadTemplateNoHandler")
    //public void downloadTemplateNoHandler(HttpServletResponse response) {
    //    // 导出模板名称
    //    response.setContentType("multipart/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    //    response.setCharacterEncoding("utf-8");
    //    // 编码 防止中文乱码
    //    String fileName = URLEncoder.encode("NoHandler.xlsx", "UTF-8");
    //    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    //    // excel表头数据
    //    List<TemplateVO> downloadTemplateVOList = new ArrayList<>();
    //
    //    EasyExcel.write(response.getOutputStream(), TemplateVO.class)
    //            .sheet("sheet名称")
    //            .doWrite(downloadTemplateVOList);
    //}
    //
    //@SneakyThrows
    //@ApiOperation("downloadTemplateSimpleHandler")
    //@PostMapping("/downloadTemplateSimpleHandler")
    //public void downloadTemplateSimpleHandler(HttpServletResponse response) {
    //    // 导出模板名称
    //    response.setContentType("multipart/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    //    response.setCharacterEncoding("utf-8");
    //    // 编码 防止中文乱码
    //    String fileName = URLEncoder.encode("SimpleHandler.xlsx", "UTF-8");
    //
    //    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    //    // excel表头数据
    //    List<TemplateVO> downloadTemplateVOList = new ArrayList<>();
    //    // excel下拉框动态数据（一般都是从数据库查出）
    //    final List<DropDownDO> dataList = Lists.newArrayList();
    //    for (int i = 0; i < 5; i++) {
    //        final DropDownDO dd = new DropDownDO();
    //        dd.setColNum(i);
    //        List<String> data = Lists.newArrayList();
    //        for (int j = 0; j < 10; j++) {
    //            data.add(RandomUtils.randomStr(6));
    //        }
    //        dd.setData(data);
    //        dataList.add(dd);
    //    }
    //
    //    EasyExcel.write(response.getOutputStream(), TemplateVO.class)
    //            .registerWriteHandler(new CustomSimpleSheetWriteHandler(dataList))
    //            .sheet("sheet名称")
    //            .doWrite(downloadTemplateVOList);
    //}
    //
    //@SneakyThrows
    //@ApiOperation("downloadTemplateCellHandler")
    //@PostMapping("/downloadTemplateCellHandler")
    //public void downloadTemplateCellHandler(HttpServletResponse response) {
    //    // 导出模板名称
    //    response.setContentType("multipart/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    //    response.setCharacterEncoding("utf-8");
    //    // 编码 防止中文乱码
    //    String fileName = URLEncoder.encode("CellHandler.xlsx", "UTF-8");
    //
    //    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    //    // excel表头数据
    //    List<TestExport> dataList = buildExportList();
    //    // excel下拉框动态数据（一般都是从数据库查出）
    //    final List<DropDownDO> handlerDataList = Lists.newArrayList();
    //    for (int i = 5; i < 10; i++) {
    //        final DropDownDO dd = new DropDownDO();
    //        dd.setColNum(i);
    //        List<String> data = Lists.newArrayList();
    //        for (int j = 0; j < 10; j++) {
    //            data.add(RandomUtils.randomStr(6));
    //        }
    //        dd.setData(data);
    //        handlerDataList.add(dd);
    //    }
    //
    //    EasyExcel.write(response.getOutputStream(), TestExport.class)
    //            .registerWriteHandler(new SelectedCellWriteHandler())
    //            .registerWriteHandler(new CustomSimpleSheetWriteHandler(handlerDataList))
    //            .sheet("sheet名称")
    //            .doWrite(dataList);
    //}
    //
    //private List<TestExport> buildExportList() {
    //    List<TestExport> list = new ArrayList<>();
    //    TestExport export = new TestExport();
    //    export.setKey("A");
    //    export.setValue("1");
    //
    //
    //    TestExport export1 = new TestExport();
    //    export1.setKey("B");
    //    export1.setValue("2");
    //
    //    list.add(export);
    //    list.add(export1);
    //    return list;
    //}
}
