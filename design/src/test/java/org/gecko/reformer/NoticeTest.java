package org.gecko.reformer;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.notice.service.IDrawService;
import org.gecko.reformer.vo.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 观察者模式测试
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-25
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class NoticeTest {

    @Resource
    private IDrawService drawService;

    @Test
    public void testDraw() {
        String id = "1";
        String result = drawService.draw(id);
        System.out.println(result);
    }
}
