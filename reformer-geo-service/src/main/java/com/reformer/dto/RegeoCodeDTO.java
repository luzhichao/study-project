package com.reformer.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述:
 * 作者: LiuCheng
 * 时间: 2023/5/9 17:39
 */
@Data
public class RegeoCodeDTO implements Serializable {

    @JSONField(name="formatted_address")
    private String formatted_address;

}
