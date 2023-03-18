package org.gecko.reformer.auto_feign.api;

import org.gecko.reformer.annotation.FeignAPI;
import org.gecko.reformer.auto_feign.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-05
 **/
@FeignAPI
public interface SystemUserAPI {
    @PostMapping({"/feign/system/user/queryUserByRoleIds"})
    List<UserDTO> queryUserByRoleIds(@RequestBody List<String> roleIds);
}