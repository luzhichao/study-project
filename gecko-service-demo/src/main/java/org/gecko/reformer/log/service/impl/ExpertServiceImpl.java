package org.gecko.reformer.log.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.dto.PageDTO;
import org.gecko.reformer.log.entity.Expert;
import org.gecko.reformer.log.mapper.ExpertMapper;
import org.gecko.reformer.log.service.IExpertService;
import org.gecko.reformer.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 应急专家domainService
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-01-19
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ExpertServiceImpl extends ServiceImpl<ExpertMapper, Expert> implements IExpertService {
    @Override
    public Page<Expert> pageExpert(PageDTO dto) {
        return page(PageUtil.buildPage(dto));
    }
}
