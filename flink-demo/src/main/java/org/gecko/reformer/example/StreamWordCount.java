package org.gecko.reformer.example;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-12-29
 **/
public class StreamWordCount {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // nc -lk 7777
        final DataStreamSource<String> lineDss = env.socketTextStream("localhost", 7777);

        final SingleOutputStreamOperator<Tuple2<String, Integer>> sum = lineDss.flatMap((String line, Collector<Tuple2<String, Integer>> out) -> {
            final String[] words = line.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(t -> t.f0)
                .sum(1);

        sum.print();

        env.execute();
    }

}
