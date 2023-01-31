package org.gecko.reformer.api;

import org.gecko.reformer.annotation.FeignAPI;
import org.gecko.reformer.dto.MyOrgTestDTO;
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
public interface SystemOrgAPI {

    @PostMapping("/feign/system2/listOrg2")
    List<MyOrgTestDTO> listOrg2(@RequestBody Collection<String> orgId);

    @PostMapping("/feign/system2/saveTest2")
    void saveTest2(@RequestBody MyOrgTestDTO dto);

    @PostMapping("/feign/system2/getById2")
    MyOrgTestDTO getById2(@RequestBody String id);

    @PostMapping("/feign/system2/getMapByIds2")
    Map<String, MyOrgTestDTO> getMapByIds2(@RequestBody Collection<String> ids);

    @PostMapping("/feign/system2/queryOrg2")
    Set<MyOrgTestDTO> queryOrg2();

    @PostMapping("/feign/system2/orgUpdate2")
    MyOrgTestDTO orgUpdate2(@RequestBody MyOrgTestDTO dto);


}

