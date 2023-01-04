package org.gecko.reformer.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-04
 **/
public class BaseTestDTO implements Serializable {
    private static final long serialVersionUID = -6663287734846142794L;

    private String id;
    private String createUser;
    private LocalDateTime createTime;
    private String updateUser;
    private LocalDateTime updateTime;
}
