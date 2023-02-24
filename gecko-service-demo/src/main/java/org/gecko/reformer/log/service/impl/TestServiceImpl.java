package org.gecko.reformer.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gecko.reformer.log.entity.User;
import org.gecko.reformer.log.mapper.IUserMapper;
import org.gecko.reformer.log.service.ITestService;
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
