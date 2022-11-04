package com.reformer.feign;

import com.reformer.dictionary.api.dto.ItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author LiuCheng
 * @date 2022-01-14 13:47
 **/
@Slf4j
@Component
public class FeignUtils implements Serializable {

    @Resource
    private DictTagFeign dictTagFeign;

    /**
     * 功能描述 根据字典类型Code获取所有字典项
     *
     * @param typeCode 字典类型Code
     * @author LL
     * @date 2022/9/16
     */
    public List<ItemDTO> queryItemListByTypeCode(String typeCode) {
        return dictTagFeign.queryItemListByTypeCode(typeCode);
    }
}
