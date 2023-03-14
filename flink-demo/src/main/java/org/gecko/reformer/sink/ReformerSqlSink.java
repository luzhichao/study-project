package org.gecko.reformer.sink;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.domain.DataChangeDO;

/**
 * 自定义sql语言数据变更sink
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-03
 **/
@Slf4j
public class ReformerSqlSink extends AbsReformerSinkFunction<String> {

    @Override
    public void invoke(String value, Context context) throws Exception {
        super.invoke(value, context);
        DataChangeDO dto = JSONUtil.toBean(value, DataChangeDO.class);
        if (handler != null) {
            handler.handlerData(dto, modelClazz);
        }
    }
}
