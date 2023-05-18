package com.reformer.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述:
 * 作者: LiuCheng
 * 时间: 2023/5/9 17:38
 */
@Data
public class RegeoDTO implements Serializable {
    private static final long serialVersionUID = -1870458441071275421L;

    private String status;

    private String info;

    @JSONField(name = "infocode")
    private String infocode;

    @JSONField(name = "regeocode")
    private RegeoCodeDTO regeocode;

}
