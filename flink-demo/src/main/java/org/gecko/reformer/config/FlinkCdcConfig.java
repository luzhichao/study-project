package org.gecko.reformer.config;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.connect.json.DecimalFormat;
import org.apache.kafka.connect.json.JsonConverterConfig;
import org.gecko.reformer.condition.FlinkCondition;
import org.gecko.reformer.factory.FlinkCdcMongoFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-02-28
 **/
@Slf4j
@Component
@DependsOn("springUtils")
@Conditional(FlinkCondition.class)
public class FlinkCdcConfig {

    @Resource
    private ReformerFlinkCdcProperties reformerFlinkCdcProperties;

    private ExecutorService executors = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());


    @PostConstruct
    public void start() {
        Properties properties = new Properties();
        properties.setProperty("bigint.unsigned.handling.mode", "long");
        properties.setProperty("decimal.handling.mode", "precise");
        properties.setProperty("time.precision.mode", "connect");

        Map<String, Object> config = new HashMap(1);
        config.put(JsonConverterConfig.DECIMAL_FORMAT_CONFIG, DecimalFormat.NUMERIC.name());
        List<StreamExecutionEnvironment> envList = Lists.newArrayList();

        final List<ReformerFlinkCdcMySqlProperties> mySqlSourceConf = reformerFlinkCdcProperties.getMysqlSource();
        final List<ReformerFlinkCdcPostgreSqlProperties> postgreSourceConf = reformerFlinkCdcProperties.getPostgreSource();
        final List<ReformerFlinkCdcMongoProperties> mgSourceConf = reformerFlinkCdcProperties.getMongodbSource();

        final FlinkCdcMongoFactory mongoFactory = new FlinkCdcMongoFactory();
        for(ReformerFlinkCdcMongoProperties conf:mgSourceConf){
            final StreamExecutionEnvironment env = mongoFactory.buildEnv(conf);
            envList.add(env);
        }

        //final FlinkCdcPostgreSqlHandle postgreSqlHandle = new FlinkCdcPostgreSqlHandle();
        //for (ReformerFlinkCdcPostgreSqlProperties conf: postgreSourceConf){
        //    final StreamExecutionEnvironment env = postgreSqlHandle.buildEnv(conf);
        //    envList.add(env);
        //}

        //final FlinkCdcMySqlFactory mysqlHandle = new FlinkCdcMySqlFactory();
        //for (ReformerFlinkCdcMySqlProperties conf : mySqlSourceConf) {
        //    final StreamExecutionEnvironment env = mysqlHandle.buildEnv(conf);
        //    envList.add(env);
        //}

        for (StreamExecutionEnvironment evn : envList) {
            executors.execute(() -> {
                try {
                    evn.execute();
                } catch (Exception e) {
                    log.error("evn启动异常==={}", e);
                }
            });
        }

    }
}
