/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gecko.reformer.transform;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.json.JsonConverter;
import org.apache.kafka.connect.json.JsonConverterConfig;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.storage.ConverterConfig;
import org.apache.kafka.connect.storage.ConverterType;
import org.gecko.reformer.domain.MongoDataChangeDO;
import org.gecko.reformer.domain.MongoUpdateDescDO;
import org.gecko.reformer.constant.OpMongoType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mongodb 自定义反序列化schema
 * {@link com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema}
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-08
 **/
public class MongodbDebeziumDeserializationSchema implements DebeziumDeserializationSchema<String> {
    private static final long serialVersionUID = 1L;

    private transient JsonConverter jsonConverter;
    /*** 配置是否启用{@link JsonConverterConfig#SCHEMAS_ENABLE_CONFIG}在schema中 **/
    private final Boolean includeSchema;
    /*** 给{@link JsonConverter}添加自定义配置 **/
    private Map<String, Object> customConverterConfigs;

    public MongodbDebeziumDeserializationSchema() {
        this(false);
    }

    public MongodbDebeziumDeserializationSchema(Boolean includeSchema) {
        this.includeSchema = includeSchema;
    }

    public MongodbDebeziumDeserializationSchema(Boolean includeSchema, Map<String, Object> customConverterConfigs) {
        this.includeSchema = includeSchema;
        this.customConverterConfigs = customConverterConfigs;
    }

    @Override
    public void deserialize(SourceRecord record, Collector<String> out) throws Exception {
        if (jsonConverter == null) {
            initializeJsonConverter();
        }
        byte[] bytes = jsonConverter.fromConnectData(record.topic(), record.valueSchema(), record.value());
        out.collect(buildJsonStr(bytes));
    }

    @Override
    public TypeInformation<String> getProducedType() {
        return BasicTypeInfo.STRING_TYPE_INFO;
    }

    /**
     * 转换成自定义对象json字符串
     *
     * @param bytes
     * @return java.lang.String
     * @throws
     * @author LZC
     * @date 2023-03-08
     * @version 1.1.2
     **/
    private String buildJsonStr(byte[] bytes) {
        final String str = StrUtil.str(bytes, CharsetUtil.UTF_8);
        final JSONObject obj = JSONUtil.parseObj(str);
        final JSONObject _id = obj.getJSONObject("_id");
        final Boolean copyingData = _id.getBool("copyingData", false);
        final String data = _id.getStr("_data");
        final String opType = obj.getStr("operationType");
        final String fullDoc = obj.getStr("fullDocument");
        final JSONObject source = obj.getJSONObject("source");
        final Long tsMs = source.getLong("ts_ms");
        final JSONObject ns = obj.getJSONObject("ns");
        final String db = ns.getStr("db");
        final String coll = ns.getStr("coll");
        final JSONObject docKey = obj.getJSONObject("documentKey");
        final String id = docKey.getStr("_id");
        final JSONObject updateDesc = obj.getJSONObject("updateDescription");
        final Map<String, Object> updatedFields = updateDesc.getBean("updatedFields", Map.class);
        final List<String> removedFields = JSONUtil.toList(updateDesc.getJSONArray("removedFields"), String.class);
        final MongoUpdateDescDO descDO = new MongoUpdateDescDO(updatedFields, removedFields);

        MongoDataChangeDO changeDO = new MongoDataChangeDO();
        changeDO.setId(id);
        changeDO.setCopyingData(copyingData);
        changeDO.setData(data);
        changeDO.setOp(OpMongoType.getByCode(opType));
        changeDO.setFullDoc(fullDoc);
        changeDO.setDatabase(db);
        changeDO.setCollect(coll);
        changeDO.setTsMs(tsMs);
        changeDO.setUpdateDesc(descDO);

        return JSONUtil.toJsonStr(changeDO);
    }

    /**
     * 给{@link JsonConverter}初始化配置
     *
     * @param
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-08
     * @version 1.1.2
     **/
    private void initializeJsonConverter() {
        jsonConverter = new JsonConverter();
        final HashMap<String, Object> configs = new HashMap<>(2);
        configs.put(ConverterConfig.TYPE_CONFIG, ConverterType.VALUE.getName());
        configs.put(JsonConverterConfig.SCHEMAS_ENABLE_CONFIG, includeSchema);
        if (customConverterConfigs != null) {
            configs.putAll(customConverterConfigs);
        }
        jsonConverter.configure(configs);
    }
}
