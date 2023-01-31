package org.gecko.reformer.feign;

import org.gecko.reformer.annotation.AutoFeignFallback;
import org.gecko.reformer.annotation.MyFeignClient;
import org.gecko.reformer.api.SystemAPI;
import org.gecko.reformer.api.SystemOrgAPI;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@AutoFeignFallback
@MyFeignClient(name = "system", contextId = "SystemFeign")
public interface SystemFeign extends SystemAPI, SystemOrgAPI {

    String myaa = "myaa";

    @PostMapping("/feign/system/mytest0")
    default String mytest0(String str){
        return null;
    }
}
