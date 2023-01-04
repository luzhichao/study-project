package org.gecko.reformer.feign;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.contexts.DataRuleContext;
import org.gecko.reformer.entity.User;
import org.gecko.reformer.mapper.UserMapper;
import org.gecko.reformer.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-21
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingTest {

    @Value("${jasypt.encryptor.password:}")
    private String password;
    @Value("${spring.jackson.property-naming-strategy:}")
    private String strategy;
    @Value("${logging.config:null}")
    private Object config;
    @Value("${app.version:null}")
    private String version;

    private static final List<String> birthdayList =
            Lists.newArrayList("2022-05-01", "2022-05-02", "2022-05-03", "2022-05-04",
                    "2022-05-05", "2022-05-06", "2022-05-07", "2022-05-08", "2022-05-09",
                    "2022-05-10", "2022-05-11", "2022-05-12", "2022-05-13", "2022-05-14",
                    "2022-05-15", "2022-05-16", "2022-05-17", "2022-05-18", "2022-05-19",
                    "2022-05-20", "2022-05-21", "2022-05-22", "2022-05-23", "2022-05-24",
                    "2022-05-25", "2022-05-26", "2022-05-27", "2022-05-28", "2022-05-29",
                    "2022-05-30", "2022-05-31", "2022-06-01", "2022-06-02", "2022-06-03",
                    "2022-06-04", "2022-06-05", "2022-06-06", "2022-06-07", "2022-06-08",
                    "2022-06-09", "2022-06-10", "2022-06-11", "2022-06-12", "2022-06-13",
                    "2022-06-14", "2022-06-15", "2022-06-16", "2022-06-17", "2022-06-18",
                    "2022-06-19", "2022-06-20", "2022-06-21", "2022-06-22", "2022-06-23",
                    "2022-06-24", "2022-06-25", "2022-06-26", "2022-06-27", "2022-06-28",
                    "2022-06-29", "2022-06-30");

    @Resource
    private UserMapper userMapper;

    @Test
    public void testShardingInsert() {
        Date one = DateUtils.parse("2022-05-01", DateUtils.YYYY_MM_DD);
        Date two = DateUtils.parse("2022-06-01", DateUtils.YYYY_MM_DD);
        List<Date> createDate = Lists.newArrayList(one, two);
        int size = birthdayList.size();
        for (int i = 0; i < 60; i++) {
            User user = new User();
            user.setUserName("user" + i);
            user.setSex(StrUtil.toString(i % 2));
            user.setTenantId("123");

            int index = RandomUtil.getRandom().nextInt(size);
            DateTime birthday = DateUtils.parse(birthdayList.get(index), DateUtils.YYYY_MM_DD);
            user.setBirthday(birthday);
            userMapper.insert(user);
        }
    }

    @Test
    public void testShardingSelectRule() {
        DataRuleContext.setDataRules(Lists.newArrayList("123"));
        Date one = DateUtils.parse("2022-05-05", DateUtils.YYYY_MM_DD);
        Date two = DateUtils.parse("2022-06-08", DateUtils.YYYY_MM_DD);
        List<User> list = new LambdaQueryChainWrapper<>(userMapper)
                .between(User::getBirthday, one, two)
                .list();
        list.forEach(user -> log.info("{}", user));
    }

}
