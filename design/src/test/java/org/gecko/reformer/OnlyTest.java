package org.gecko.reformer;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 模式测试
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-25
 **/
@Slf4j
@SpringBootTest
public class OnlyTest {

    @Test
    public void test() {
        val s = StrUtil.toString(null);
        final boolean notBlank = StrUtil.isNotBlank(s);
        final val b = StrUtil.isNullOrUndefined(s);
        log.info("{}", s);
        log.info("{}", notBlank);
        log.info("{}", b);
    }
}
