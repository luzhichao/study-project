package org.gecko.reformer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.constant.IFlinkConstants;
import org.gecko.reformer.filter.DirFileNameStartFilter;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-08
 **/
@Slf4j
@SpringBootTest
public class LocalTest {

    @Test
    public void testJobId() {
        String jobName = "test-local-job";
        System.out.println(DigestUtil.md5Hex(jobName));
    }

    @Test
    public void testSearchCheckPointDir() {
        String jobName = "test-local-job";
        String storageDir = "/Users/luzhichao/Downloads/flink/savePoints/test/";
        final String prefix = IFlinkConstants.CHECK_POINT_DIR_PREFIX;

        List<String> result = new ArrayList<>();
        if (StrUtil.endWith(storageDir, File.separator)) {
            storageDir = StrUtil.subBefore(storageDir, File.separator, true);
        }

        String jobStorageDir = storageDir + File.separator + DigestUtil.md5Hex(jobName);
        final File file = FileUtil.newFile(jobStorageDir);
        if (FileUtil.exist(file)) {
            String[] names = file.list(new DirFileNameStartFilter(prefix));
            result = Arrays.stream(names).sorted((o1, o2) -> {
                final BigDecimal id1 = NumberUtil.toBigDecimal(StrUtil.subAfter(o1, prefix, true));
                final BigDecimal id2 = NumberUtil.toBigDecimal(StrUtil.subAfter(o2, prefix, true));
                return id2.compareTo(id1);
            }).map(f -> StrUtil.join(File.separator, jobStorageDir, f)).collect(Collectors.toList());
        }
        System.out.println(CollUtil.join(result, StrUtil.COMMA));
    }
}
