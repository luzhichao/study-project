package org.gecko.reformer.sink;

import org.apache.flink.configuration.Configuration;
import org.gecko.reformer.model.TestModel;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-11
 **/
public class TestSink extends BaseReformerSinkFunction<TestModel>{

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
    }

    @Override
    public void close() throws Exception {
        super.close();
    }

    @Override
    public void invoke(TestModel value, Context context) throws Exception {
        super.invoke(value, context);
    }
}
