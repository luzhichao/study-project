package org.gecko.reformer.geckoupgradedemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.gecko.reformer.annotation.AutoDataRule;
import org.gecko.reformer.dto.PageDTO;
import org.gecko.reformer.geckoupgradedemo.entity.Expert;

import java.util.Collection;
import java.util.List;

/**
 * 应急专家domainService接口
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-01-19
 **/
public interface IExpertService extends IService<Expert> {

    @AutoDataRule
    Page<Expert> pageExpert(PageDTO dto);
}
