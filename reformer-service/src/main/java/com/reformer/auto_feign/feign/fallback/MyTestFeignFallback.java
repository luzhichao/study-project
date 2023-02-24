package com.reformer.auto_feign.feign.fallback;

import com.reformer.exception.CustomException;
import com.reformer.auto_feign.feign.TestFeign;
import com.reformer.system.api.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-05
 **/
@Slf4j
@Component
public class MyTestFeignFallback implements TestFeign {


    @Override
    public UserDTO queryUserById(@RequestBody()
                                         String userId) {
        log.error("===自定义===服务降级===");
        return new UserDTO();
    }


    @Override
    public List<UserDTO> queryUserByIds(@RequestBody()
                                                Collection<String> ids) {
        log.error("===自定义===服务降级===");
        return new ArrayList<>();
    }


    @Override
    public List<UserDTO> queryUserByPhones(@RequestBody()
                                                   Collection<String> phones) {
        log.error("===自定义===服务降级===");
        return new ArrayList<>();
    }


    @Override
    public List<UserDTO> queryUserByOrgId(@RequestBody()
                                                  String orgId) {
        log.error("===自定义===服务降级===");
        return new ArrayList<>();
    }


    @Override
    public List<UserDTO> queryUserByOrgIds(@RequestBody()
                                                   Collection<String> orgIds) {
        log.error("===自定义===服务降级===");
        return new ArrayList<>();
    }


    @Override
    public List<OrgDTO> queryOrgByIds(@RequestBody()
                                              Collection<String> orgIds) {
        log.error("===自定义===服务降级===");
        return new ArrayList<>();
    }


    @Override
    public OrgDTO queryOrgById(@RequestBody()
                                       String orgId) {
        log.error("===自定义===服务降级===");
        return new OrgDTO();
    }


    @Override
    public List<OrgDTO> listCurrRuleOrgIds(@RequestBody()
                                                   String orgId) {
        log.error("===自定义===服务降级===");
        return new ArrayList<>();
    }


    @Override
    public List<OrgDTO> listUnderOrgIds(@RequestBody()
                                                String orgId) {
        log.error("===自定义===服务降级===");
        return new ArrayList<>();
    }


    @Override
    public List<OrgDTO> listAllEnterprise() {
        log.error("===自定义===服务降级===");
        return new ArrayList<>();
    }

    @Override
    public List<OrgDTO> listOrgByCondition(QueryOrgDTO dto) {
        log.error("===自定义===服务降级===");
        return new ArrayList<>();
    }

    @Override
    public List<OrgDTO> findOrgList(OrgReqDTO dto) {
        log.error("===自定义===服务降级===");
        return new ArrayList<>();
    }

    @Override
    public LoginUserDTO toLogin(String str) {
        return null;
    }

    @Override
    public LoginUserDTO getLoginUserDetail(String userId) {
        return null;
    }

    @Override
    public String saveWechatAppletAuth(WechatAppletAuthDTO dto) {
        throw new CustomException("");
    }
}