package org.gecko.reformer.api;


import org.gecko.reformer.annotation.FeignAPI;
import org.gecko.reformer.dto.OrgTestDTO;
import org.gecko.reformer.exception.CustomException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@FeignAPI
public interface SystemAPI {

    @PostMapping("/feign/system/test")
    List<Integer> test(@RequestBody OrgTestDTO dto) throws CustomException, RuntimeException;

    @PostMapping("/feign/system/listOrg")
    List<OrgTestDTO> listOrg(@RequestBody Collection<String> orgId) throws CustomException;

    @PostMapping("/feign/system/saveTest")
    void saveTest(@RequestBody OrgTestDTO dto);

    @PostMapping("/feign/system/getById")
    OrgTestDTO getById(@RequestBody String id);

    @PostMapping("/feign/system/getMapByIds")
    Map<String, OrgTestDTO> getMapByIds(@RequestBody Collection<String> ids);

    @PostMapping("/feign/system/queryOrg")
    Set<OrgTestDTO> queryOrg();

    @PostMapping("/feign/system/orgUpdate")
    OrgTestDTO orgUpdate(@RequestBody OrgTestDTO dto);

    @PostMapping("/feign/system/getNameById")
    String getNameById(@RequestBody String id);

    @PostMapping("/feign/system/add")
    String add(@RequestBody String id, @RequestBody String name);
}

