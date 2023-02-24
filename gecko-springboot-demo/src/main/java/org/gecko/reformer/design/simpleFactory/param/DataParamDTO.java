package org.gecko.reformer.design.simpleFactory.param;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据参数
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-25
 **/
@Data
@AllArgsConstructor
public class DataParamDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;

    private String text;

}
