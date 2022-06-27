package org.gecko.reformer;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.simpleFactory.SimpleFactory;
import org.gecko.reformer.simpleFactory.entity.AbsEntity;
import org.gecko.reformer.simpleFactory.entity.EntityA;
import org.gecko.reformer.simpleFactory.param.DataParamDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 简单工厂测试
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-25
 **/
@Slf4j
@SpringBootTest
public class SimpleFactoryTest {


    List<DataParamDTO> list = Lists.newArrayList(
            new DataParamDTO("EntityA", "{\"name\":\"名称1\",\"agree\":\"true\"}"),
            new DataParamDTO("EntityB", "{\"startDate\":\"2020-01-01\",\"endDate\":\"2020-01-02\"}"),
            new DataParamDTO("EntityC", "留言备注"),
            new DataParamDTO("EntityA", "{\"name\":\"我是名字\",\"agree\":\"false\"}"),
            new DataParamDTO("EntityB", "{\"startDate\":\"2022-10-11\",\"endDate\":\"2022-12-13\"}"),
            new DataParamDTO("EntityC", "同意操作"),
            new DataParamDTO("EntityA", "{\"name\":\"这是名称\",\"agree\":\"true\"}"),
            new DataParamDTO("EntityB", "{\"startDate\":\"2021-05-06\",\"endDate\":\"2021-06-08\"}"),
            new DataParamDTO("EntityC", "按规定办事"));


    @Test
    public void test() {
        List<AbsEntity> result = list.stream()
                .map(dataParamDTO -> SimpleFactory.getEntity(dataParamDTO.getType(), dataParamDTO.getText()))
                .collect(Collectors.toList());

        log.info("result==={}", result);
    }



}
