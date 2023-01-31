package org.gecko.reformer.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-04
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MyOrgTestDTO extends MyBaseTestDTO {
    private static final long serialVersionUID = -5398553214549903163L;

    private String parentId;
    private String name;
    private String type;
    private String areaCode;
    private String officePhone;
    private String zipCode;
    private String status;
    private String address;
    private String duty;
    private Double longitude;
    private Double latitude;
    private String zoom;
    private String administrativeDivisionCode;

}
