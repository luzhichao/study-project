package org.gecko.reformer.handler;

import org.gecko.reformer.domain.BaseChangeDo;

/**
 * flink cdc数据接口
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
public interface IHandler<T, C extends BaseChangeDo> {

    /**
     * 处理数据
     *
     * @param data
     * @param modelClazz
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    void handlerData(C data, Class<T> modelClazz);
}
