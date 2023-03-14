package org.gecko.reformer.constant;

import org.apache.kafka.connect.json.DecimalFormat;
import org.apache.kafka.connect.json.JsonConverterConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 常量类
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-07
 **/
public class FlinkConstants implements IFlinkConstants {
    private FlinkConstants() {
    }

    /*** 字段类型转换配置 **/
    public static Properties properties = new Properties();

    static {
        properties.setProperty("bigint.unsigned.handling.mode", "long");
        properties.setProperty("decimal.handling.mode", "precise");
        properties.setProperty("time.precision.mode", "connect");
    }

    /*** jsonConverter配置数字类型 **/
    public static Map<String, Object> config = new HashMap(1);

    static {
        config.put(JsonConverterConfig.DECIMAL_FORMAT_CONFIG, DecimalFormat.NUMERIC.name());
    }
}
