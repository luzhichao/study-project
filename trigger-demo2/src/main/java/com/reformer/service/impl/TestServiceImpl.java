package com.reformer.service.impl;

import com.reformer.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-04
 **/
@Slf4j
@Service
public class TestServiceImpl implements ITestService {
    @Override
    public void test() {
        log.info("======TestServiceImpl#test======");
    }
}
