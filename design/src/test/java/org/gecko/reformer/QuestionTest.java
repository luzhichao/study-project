package org.gecko.reformer;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.question.Question;
import org.gecko.reformer.question.QuestionUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-25
 **/
@Slf4j
@SpringBootTest
public class QuestionTest {

    @Test
    public void questionTest() {
        Map<String, String> option = new ConcurrentHashMap<>();
        option.put("A", "c");
        option.put("B", "python");
        option.put("C", ".net");
        option.put("D", "vue");
        option.put("E", "JAVA");
        Question question = new Question("世界上最好的编程语言是什么？", option, "E");
        System.out.println("question===>" + question);
        question = QuestionUtils.random(question);
        System.out.println("new===>" + question);
    }
}
