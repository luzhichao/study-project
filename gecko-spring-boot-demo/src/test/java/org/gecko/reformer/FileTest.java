package org.gecko.reformer;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.file.util.FileUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * 触发任务测试
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-08
 **/
@Slf4j
@SpringBootTest
public class FileTest {

    @Test
    public void testFileIsUsed() {
        final Boolean isUsed = FileUtils.fileIsUsed("/Users/luzhichao/logs/reformer/gecko-spring-boot/", "info.log");
        log.info("File is used == {}", isUsed);
    }

    @Test
    public void testFileIsCompleted() {
        final File file = FileUtil.newFile("/Users/luzhichao/logs/reformer/gecko-spring-boot/info.log");
        final Boolean completed = FileUtils.isFileCompleted(file);
        log.info("File is completed == {}", completed);
    }
    @Test
    public void testCheckFileCompleted() {
        //final File file = FileUtil.newFile("/Users/luzhichao/logs/reformer/gecko-spring-boot/aaa.png");
        final File file = FileUtil.newFile("/Users/luzhichao/logs/reformer/gecko-spring-boot/info.log");
        final Boolean completed = FileUtils.checkFileCompleted(file);
        log.info("File is completed == {}", completed);
    }
}
