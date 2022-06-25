package org.gecko.reformer.notice.service;

import org.gecko.reformer.notice.option.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务Service实现类
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/08/21
 **/
@Service
public class DrawServiceImpl extends AsbDrawService {

    @Autowired
    private OtherService otherService;

    @Override
    protected String doDraw(String id) {
        // 执行业务代码
        String msg = otherService.lottery(id);
        // 结果
        return msg;
    }
}
