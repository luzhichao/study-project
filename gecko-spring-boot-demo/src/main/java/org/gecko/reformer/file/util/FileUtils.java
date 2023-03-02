package org.gecko.reformer.file.util;

import cn.hutool.core.io.FileUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-02-25
 **/
@Slf4j
public class FileUtils {


    private static String endTag = "end";

    /**
     * 判断文件是否写入内容完毕，如果完毕返回true，反之false
     *
     * @param f
     * @return
     */
    public static boolean checkFileCompleted(File f) {
        OutputStream output = null;
        try {
            output = new FileOutputStream(f, true);
            output.write((endTag+"\n").getBytes());
            output.flush();
            output.close();
        } catch (IOException e) {
            log.error("checkFileCompleted", e);
        } finally {
            IOUtils.closeQuietly(output);
        }
        InputStream input = null;
        try {
            input = new FileInputStream(f);
            List<String> list = IOUtils.readLines(input);
            if (list.size() > 0) {
                String endstr = list.get(list.size() - 1);
                if (endTag.equals(endstr)) {
                    return true;
                }
            }
        } catch (IOException e) {
            log.error("checkFileCompleted", e);
        } finally {
            IOUtils.closeQuietly(input);
        }
        return false;
    }


    @SneakyThrows
    public static Boolean fileIsUsed(String path, String filename) {
        Boolean isUsed = false;
        String oldName = path + File.separator + filename;
        String newName = path + File.separator + "template";

        File o = FileUtil.newFile(oldName);
        File n = FileUtil.newFile(newName);

        if (!FileUtil.exist(o)) {
            log.info("File in use.");
            return true;
        }

        o.renameTo(n);
        if (FileUtil.exist(o)) {
            log.info("File in use.");
            return true;
        } else {
            // 改回来
            n.renameTo(o);
            return false;
        }
    }

    /**
     * 等待文件有数据且已写完，费时操作放在子线程中执行
     *
     * @param file 文件
     * @return boolean true：已写完；false:外部程序阻塞或者文件不存在
     * @throws
     * @author LZC
     * @date 2023-02-28
     * @version 1.1.2
     **/
    @SneakyThrows
    public static boolean isFileCompleted(File file) {
        if (!file.exists()) {
            return false;
        }

        final long start = System.currentTimeMillis();
        long fileLength = 0;
        int i = 0;
        log.info("正在监测文件是否写入完成, 请稍候...");
        // 文件在外部一直在写入数据，每次进入循环体时，文件大小都会改变，一直到不改变时，说明文件数据写入完毕或者文件大小一直都是0(外部程序阻塞)
        while (true) {
            if (file.length() > fileLength) {
                // 判断文件大小是否有改变，有改变说明还未写完
                fileLength = file.length();
                if (i % 60 == 0) {
                    // 每隔1分钟输出一次日志
                    log.info("文件: " + file.getName() + " 正在被写入，请稍候...");
                }

                // 一秒后再循环一次
                Thread.sleep(1000);
            } else {
                // 否则：只能等于 不会小于，等于有两种情况，一种是数据写完了，一种是外部程序阻塞了，导致文件大小一直为0
                // 文件大小不为0，等待写入
                if (file.length() != 0) {
                    //被写入完成则立即输出日志
                    log.info("文件: " + file.getName() + " 被写入完成");
                    //写完了退出当前循环体 执行下面的 return true
                    break;
                } else {
                    // 文件大小为0则外表程序未写入，等待外部程序写入
                    if (i % 60 == 0) {
                        // 每隔1分钟输出一次日志
                        log.info("文件: " + file.getName() + " 大小为0，正在等待外部程序写入，已等待：" + (System.currentTimeMillis() - start) + "毫秒");
                    }

                    // 如果30分钟内一直(1800/60=30)等于0，说明外部程序阻塞了
                    if (i == 1800) {
                        log.info("文件: " + file.getName() + " 大小在 ："
                                + (System.currentTimeMillis() - start) + "毫秒内始终为0,说明：在程序监测时间内文件未写入 ,程序监测结束");
                        return false;
                    }

                    // 等待外部程序开始写
                    Thread.sleep(1000);
                }
            }
            i++;
        }
        return true;
    }
}
