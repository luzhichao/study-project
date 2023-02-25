package org.gecko.reformer.design.question;

import java.util.*;

/**
 * @author LZC
 * @version 1.0.0
 * @date 2021/07/05
 **/
public class QuestionUtils {

    /**
     * 将问题选项乱序，并转成问题对象返回
     *
     * @param question 问题对象
     * @return org.gecko.reformer.question.Question 问题选项乱序后的问题对象
     * @throws
     * @author LZC
     * @date 2021-07-05
     * @version 1.1.1
     **/
    public static Question random(Question question) {
        String topic = question.getTopic();
        Map<String, String> option = question.getOption();
        String key = question.getKey();
        Set<String> keySet = option.keySet();
        ArrayList<String> keyList = new ArrayList<>(keySet);
        Collections.shuffle(keyList);
        HashMap<String, String> optionNew = new HashMap<>(option.size());
        int idx = 0;
        String keyNew = "";
        for (String next : keySet) {
            String randomKey = keyList.get(idx++);
            if (key.equals(next)) {
                keyNew = randomKey;
            }
            optionNew.put(randomKey, option.get(next));
        }
        return new Question(topic, optionNew, keyNew);
    }
}
