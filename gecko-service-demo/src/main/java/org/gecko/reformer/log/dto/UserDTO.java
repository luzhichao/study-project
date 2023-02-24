package org.gecko.reformer.log.dto;

import lombok.Data;
import org.gecko.reformer.annotation.Changed;
import org.gecko.reformer.log.handler.UserChangedHandler;
import org.gecko.reformer.log.handler.UserChangedHandler2;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-02-21
 **/
@Data
public class UserDTO implements Serializable {

    /*** 真实姓名 **/
    @Changed(handle = UserChangedHandler.class)
    private String realName;
    /*** 账号 **/
    private String username;
    /*** 手机 **/
    private String phone;
    /*** 生日 **/
    @Changed(handle = UserChangedHandler2.class)
    private Date birthDay;
}
