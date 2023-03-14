package org.gecko.reformer.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 如果遇到错误，是否继续处理消息。不接受或全部接受
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-07
 **/
@Getter
@AllArgsConstructor
public enum OpErrorEnum {
    /*** 全部接受 **/
    ALL("all"),
    /*** 不接受 **/
    NONE("none"),
    ;

    private String code;
}
