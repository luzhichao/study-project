package org.gecko.reformer.condition;

import cn.hutool.core.util.BooleanUtil;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 开启flank条件
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-10
 **/
public class FlinkCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取当前环境信息
        Environment environment = context.getEnvironment();
        Boolean enable = environment.getProperty("reformer.gecko.flink-cdc.enable", Boolean.class, false);
        return BooleanUtil.isTrue(enable);
    }
}
