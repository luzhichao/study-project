package org.gecko.reformer.domain;

import lombok.Data;
import org.gecko.reformer.handler.IHandler;

import java.io.Serializable;

/**
 * sink参数类
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
@Data
public class SinkParamDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /*** 处理器对象 **/
    private IHandler handler;
    /*** 数据对象 **/
    private Class<?> modelClazz;
}
