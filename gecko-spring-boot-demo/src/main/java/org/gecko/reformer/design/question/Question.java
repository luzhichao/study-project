package org.gecko.reformer.design.question;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 问题对象
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/07/05
 **/
@Data
@AllArgsConstructor
public class Question implements Serializable {
    private static final long serialVersionUID = -9032840990105565426L;

    /*** 题目 **/
    private String topic;
    /*** 选项：A=JAVA2 EE, B=JAVA2 Card, C=JAVA2 ME, D=JAVA2 HE, E=JAVA2 SE **/
    private Map<String, String> option;
    /*** 正确答案 **/
    private String key;
}
