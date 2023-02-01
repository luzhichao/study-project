package org.gecko.reformer.geckoupgradedemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gecko.reformer.entity.BaseEntity;

/**
 * 应急专家实体
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/07/23
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_expert")
public class Expert extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;
    private String type;
    private String typeLevel;

    private String sex;
    private String title;
    private String phone;
    private String photo;

    private String sourceOrgName;
    private String headName;
    private String headPhone;
    private String workPhone;
    private String fillInOrgId;


    private Double longitude;
    private Double latitude;
    private String address;

    private String speciality;
    private String education;

    private String areaCode;
    private String remark;

    /*** 用于数据汇聚 **/
    @TableField(select = false)
    private String geoJsonPoint;

}
