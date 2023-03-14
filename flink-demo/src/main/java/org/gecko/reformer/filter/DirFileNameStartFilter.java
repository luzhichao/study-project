package org.gecko.reformer.filter;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 以指定文件夹名称开始过滤器
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-02
 **/
public class DirFileNameStartFilter implements FilenameFilter {

    private String startName;

    public DirFileNameStartFilter(String startName) {
        this.startName = startName;
    }

    @Override
    public boolean accept(File dir, String name) {
        if (FileUtil.isDirectory(dir) && StrUtil.startWith(name, startName)) {
            return true;
        }
        return false;
    }
}
