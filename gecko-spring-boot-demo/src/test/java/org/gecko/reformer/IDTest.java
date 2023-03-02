package org.gecko.reformer;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.exception.CustomException;
import org.gecko.reformer.id.SnowflakeIdUtil;
import org.gecko.reformer.util.SnowFlakeUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Set;

/**
 * 触发任务测试
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-08
 **/
@Slf4j
@SpringBootTest
public class IDTest {

    @Test
    public void test() {
        Set<Long> set = Sets.newHashSet();
        for (int i = 0; i < 20; i++) {
            ((Runnable) () -> {
                for (int j = 0; j < 10000; j++) {
                    long id = SnowFlakeUtil.getFlowIdInstance().nextId();
                    if (CollUtil.contains(set, id)) {
                        throw new CustomException("id重复了==" + id);
                    }
                    set.add(id);
                }
            }).run();
        }
        System.out.println(set.size());
    }

    @Test
    public void testDefault() {
        // 默认实现
        for (int i = 0; i < 100; i++) {
            long id = SnowflakeIdUtil.newId();
            System.out.println("生成第" + i + "id：" + id);
        }
    }

    @Test
    public void testCustom() {
        // 自定义实现，需要传入 workerId工作id 与 datacenterId 数据中心id  请自行实现这里默认取0
        SnowflakeIdUtil util2 = new SnowflakeIdUtil(0, 0);
        for (int i = 0; i < 100; i++) {
            long id = util2.nextId();
            System.out.println("生成第" + i + "id：" + id);
        }
    }

    @Test
    public void test2() {
        for (int i = 0; i < 20; i++) {
            long datacenterId = getDatacenterId(maxDatacenterId);
            long workerId = getWorkerId(datacenterId, maxWorkerId);
            System.out.println("datacenterId==" + datacenterId);
            System.out.println("workerId==" + workerId);
        }
    }


    // 机器标识位数
    private final static long workerIdBits = 5L;
    // 数据中心标识位数
    private final static long datacenterIdBits = 5L;
    // 机器ID最大值
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 数据中心ID最大值
    private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 获取工作中心id
     *
     * @param datacenterId
     * @param maxWorkerId
     * @return long
     * @throws
     * @author LZC
     * @date 2023-03-02
     * @version 1.1.2
     **/
    private static long getWorkerId(long datacenterId, long maxWorkerId) {
        StringBuffer pid = new StringBuffer();
        pid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            // GET jvmPid
            pid.append(name.split("@")[0]);
        }
        // MAC + PID 的 hashcode 获取16个低位
        return (pid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    /**
     * 获取数据中心id
     *
     * @param maxDatacenterId
     * @return long
     * @throws
     * @author LZC
     * @date 2023-03-02
     * @version 1.1.2
     **/
    private static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (maxDatacenterId + 1);
            }
        } catch (UnknownHostException | SocketException e) {
            getDatacenterId(maxDatacenterId);
        }
        return id;
    }
}
