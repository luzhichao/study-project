package org.gecko.reformer.geckoupgradedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gecko.reformer.geckoupgradedemo.entity.User;
import org.gecko.reformer.geckoupgradedemo.mapper.IUserMapper;
import org.gecko.reformer.geckoupgradedemo.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-02-01
 **/
@Service
public class TestServiceImpl extends ServiceImpl<IUserMapper, User> implements ITestService {
}
