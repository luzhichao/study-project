package com.reformer.processorreformertest.feign;

import com.reformer.annotation.AutoFeignFallback;
import com.reformer.system.api.feign.LoginAPI;
import com.reformer.system.api.feign.SystemAPI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-05
 **/
@AutoFeignFallback
@FeignClient(name = "system", contextId = "testFeign", url = "localhost:8081")
public interface TestFeign extends SystemAPI, LoginAPI {
}
