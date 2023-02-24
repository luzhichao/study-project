package org.gecko.reformer.auto_feign.dto;

import lombok.Data;
import org.gecko.reformer.dto.BaseDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-05
 **/
@Data
public class UserDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    private String realName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String orgId;
    private String orgName;
    private String sex;
    private String avatar;
    private Boolean sysUser;
    private String officePhone;
    private String post;
    private String card;
    private LocalDate birthDay;
    private String nation;
    private String politicalStatus;
    private String emergencyPerson;
    private String emergencyPhone;
    private String address;
    private String location;
    private String status;
    private String remark;
    private List<OrgDTO> deptList;
}
