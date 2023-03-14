package org.gecko.reformer.sink;

import lombok.Data;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.runtime.taskmanager.TaskManagerRuntimeInfo;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.operators.StreamingRuntimeContext;
import org.gecko.reformer.domain.SinkParamDO;
import org.gecko.reformer.handler.IHandler;
import org.gecko.reformer.util.SinkUtils;

/**
 * sink自定义抽象工厂
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
@Data
public abstract class AbsReformerSinkFunction<IN> extends RichSinkFunction<IN> {

    /*** 业务处理器 **/
    protected IHandler handler;
    /*** 业务模型class **/
    protected Class<?> modelClazz;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        final StreamingRuntimeContext context = (StreamingRuntimeContext) getRuntimeContext();
        final TaskManagerRuntimeInfo taskInfo = context.getTaskManagerRuntimeInfo();
        final Configuration configuration = taskInfo.getConfiguration();
        final String jobName = configuration.get(PipelineOptions.NAME);
        final SinkParamDO param = SinkUtils.setSinkProperties(jobName, handler, modelClazz);
        handler = param.getHandler();
        modelClazz = param.getModelClazz();
    }

    @Override
    public void close() throws Exception {
        super.close();
    }
}
