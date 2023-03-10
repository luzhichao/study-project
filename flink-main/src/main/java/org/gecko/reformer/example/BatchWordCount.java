package org.gecko.reformer.example;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-12-29
 **/
public class BatchWordCount {

    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> lineDataSource = env.readTextFile("/Users/luzhichao/workspace/GitHub/study-project/flink-demo/input/words.txt");
        FlatMapOperator<String, Tuple2<String, Integer>> wordAndOneTuple = lineDataSource.flatMap((String line, Collector<Tuple2<String, Integer>> out) -> {
            final String[] words = line.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.INT));

        UnsortedGrouping<Tuple2<String, Integer>> wordAndOneTupleGroup = wordAndOneTuple.groupBy(0);
        AggregateOperator<Tuple2<String, Integer>> sum = wordAndOneTupleGroup.sum(1);




        sum.print();
    }

}
