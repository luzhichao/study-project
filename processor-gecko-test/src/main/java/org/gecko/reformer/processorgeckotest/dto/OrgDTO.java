package org.gecko.reformer.processorgeckotest.dto;

import lombok.Data;
import org.gecko.reformer.dto.BaseDTO;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-05
 **/
@Data
public class OrgDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
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