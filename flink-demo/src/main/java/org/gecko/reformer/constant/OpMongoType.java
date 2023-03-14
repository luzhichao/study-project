/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gecko.reformer.constant;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * mongodb 操作类型枚举
 * {@link com.mongodb.client.model.changestream.OperationType}
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-08
 **/
@Getter
@AllArgsConstructor
public enum OpMongoType {

    INSERT("insert"),
    UPDATE("update"),
    REPLACE("replace"),
    DELETE("delete"),
    INVALIDATE("invalidate"),
    DROP("drop"),
    DROP_DATABASE("dropDatabase"),
    RENAME("rename"),
    OTHER("other");

    private final String code;

    /**
     * 根据code获取操作类型
     *
     * @param code
     * @return org.gecko.reformer.dto.OperationType
     * @throws
     * @author LZC
     * @date 2023-03-08
     * @version 1.1.2
     **/
    public static OpMongoType getByCode(final String code) {
        return Arrays.stream(OpMongoType.values()).filter(e -> StrUtil.equals(e.getCode(), code))
                .findFirst().orElse(OpMongoType.OTHER);
    }
}
