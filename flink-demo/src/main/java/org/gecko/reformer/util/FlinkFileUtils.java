package org.gecko.reformer.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.gecko.reformer.filter.DirFileNameStartFilter;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Fink 文件工具类
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-02
 **/
final class FlinkFileUtils {
    private FlinkFileUtils() {
    }

    /**
     * 获取checkPoint目录下指定jobName中特定前缀的目录(即chekPoint存储的所有目录)
     *
     * @param storageDir
     * @param jobId
     * @param prefix
     * @return java.util.List<java.lang.String>
     * @throws
     * @author LZC
     * @date 2023-03-02
     * @version 1.1.2
     **/
    protected static List<String> searchCheckPointDir(String storageDir, String jobId, String prefix) {
        List<String> result = new ArrayList<>();
        if (StrUtil.endWith(storageDir, File.separator)) {
            storageDir = StrUtil.subBefore(storageDir, File.separator, true);
        }

        String jobStorageDir = storageDir + File.separator + jobId;
        final File file = FileUtil.newFile(jobStorageDir);
        if (FileUtil.exist(file)) {
            String[] names = file.list(new DirFileNameStartFilter(prefix));
            result = Arrays.stream(names).sorted((o1, o2) -> {
                final BigDecimal id1 = NumberUtil.toBigDecimal(StrUtil.subAfter(o1, prefix, true));
                final BigDecimal id2 = NumberUtil.toBigDecimal(StrUtil.subAfter(o2, prefix, true));
                return id2.compareTo(id1);
            }).map(f -> StrUtil.join(File.separator, jobStorageDir, f)).collect(Collectors.toList());
        }

        return result;
    }

    /**
     * 获取最大的存储目录(即当前存储目录)
     *
     * @param dirList
     * @return java.lang.String
     * @throws
     * @author LZC
     * @date 2023-03-02
     * @version 1.1.2
     **/
    protected static String getMaxDir(List<String> dirList) {
        return CollUtil.get(dirList, 0);
    }

    /**
     * 删除过期的目录
     *
     * @param dirList
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-02
     * @version 1.1.2
     **/
    protected static void delPastDir(List<String> dirList) {
        if (null != dirList) {
            final List<String> pastDir = CollUtil.sub(dirList, 1, dirList.size());
            for (String dir : pastDir) {
                if (StrUtil.isNotBlank(dir)) {
                    FileUtil.del(dir);
                }
            }
        }
    }
}
