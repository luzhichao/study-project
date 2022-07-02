package org.gecko.reformer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * TODO
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-21
 **/
@Data
@ToString(callSuper = true)
@TableName(value = "t_user")
public class User extends BaseEntity {
    private static final long serialVersionUID = -129340888943814894L;

    private String userName;
    private Date birthday;
    private String sex;

}
