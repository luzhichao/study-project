package org.gecko.reformer.flink.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-10
 **/
@Data
public class TestResourceModel implements Serializable {
    private static final long serialVersionUID = 9061117671113331987L;

    //@JSONField(name = "_id")
    //@JsonProperty("_id")
    private String id;
    private String tenantId;
    private int delFlag;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

    private String name;
    private Integer siteArea;
    private String sourceOrgName;
    private String fillInOrgId;
    private Double longitude;
    private Double latitude;
    private String address;
    private String headName;
    private String headPhone;
    private String workPhone;
    private String areaCode;
    private String remark;
    private String point;
}
