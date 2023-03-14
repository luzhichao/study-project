package org.gecko.reformer.constant;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 操作类型枚举
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-06
 **/
@Getter
@AllArgsConstructor
public enum OpType {

    READ("r"),
    CREATE("c"),
    UPDATE("u"),
    DELETE("d"),
    TRUNCATE("t"),
    OTHER("other"),
    ;

    private final String code;

    /**
     * 根据code获取操作类型
     *
     * @param code
     * @return org.gecko.reformer.dto.OpEnum
     * @throws
     * @author LZC
     * @date 2023-03-06
     * @version 1.1.2
     **/
    public static OpType getByCode(String code) {
        return Arrays.stream(OpType.values()).filter(e -> StrUtil.equals(e.getCode(), code))
                .findFirst().orElse(OpType.OTHER);
    }
}
