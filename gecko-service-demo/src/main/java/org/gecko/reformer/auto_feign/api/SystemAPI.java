package org.gecko.reformer.auto_feign.api;

import org.gecko.reformer.annotation.FeignAPI;
import org.gecko.reformer.auto_feign.dto.OrgDTO;
import org.gecko.reformer.auto_feign.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-05
 **/
@FeignAPI
public interface SystemAPI {
    @PostMapping({"/feign/system/user/getUserInfoById"})
    UserDTO queryUserById(@RequestBody String userId);

    @PostMapping({"/feign/system/user/queryUserByIds"})
    List<UserDTO> queryUserByIds(@RequestBody Collection<String> ids);

    @PostMapping({"/feign/system/user/queryUserByPhones"})
    List<UserDTO> queryUserByPhones(@RequestBody Collection<String> phones);

    @PostMapping({"/feign/system/user/queryByOrgId"})
    List<UserDTO> queryUserByOrgId(@RequestBody String orgId);

    @PostMapping({"/feign/system/user/queryByOrgIds"})
    List<UserDTO> queryUserByOrgIds(@RequestBody Collection<String> orgIds);

    @PostMapping({"/feign/system/org/queryByIds"})
    List<OrgDTO> queryOrgByIds(@RequestBody Collection<String> orgIds);

    @PostMapping({"/feign/system/org/queryById"})
    OrgDTO queryOrgById(@RequestBody String orgId);

    @PostMapping({"/feign/system/orgRule/listCurrRuleOrgIds"})
    List<OrgDTO> listCurrRuleOrgIds(@RequestBody String orgId);

    @PostMapping({"/feign/system/orgRule/listUnderOrgIds"})
    List<OrgDTO> listUnderOrgIds(@RequestBody String orgId);

    @PostMapping({"/feign/system/org/listAllEnterprise"})
    List<OrgDTO> listAllEnterprise();
}
