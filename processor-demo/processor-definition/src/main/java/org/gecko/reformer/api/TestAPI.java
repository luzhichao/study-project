package org.gecko.reformer.api;

import org.gecko.reformer.annotation.FeignAPI;
import org.gecko.reformer.dto.MyBaseTestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-31
 **/
@FeignAPI
public interface TestAPI {

    @PostMapping("/feign/system2/listOrg2")
    <T extends MyBaseTestDTO> void test(@RequestBody T dto);

    @PostMapping("/feign/system2/listOrg2")
    void test2(@RequestBody Class<? extends MyBaseTestDTO> clazz);

}
