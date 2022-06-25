package org.gecko.reform.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gecko.reform.demo.dto.ExportDTO;

import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/08/18
 **/
@Mapper
public interface ExportMapper {

    /**
     * 获取database下所有表的设计结构
     *
     * @param schema
     * @return java.util.List<org.gecko.reform.demo.dto.ExportDTO>
     * @author LZC
     * @date 2021/08/18
     * @version 1.0.0
     **/
    List<ExportDTO> getExportList(@Param("schema") String schema);

}
