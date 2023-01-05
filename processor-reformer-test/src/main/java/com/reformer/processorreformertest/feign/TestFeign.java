package com.reformer.processorreformertest.feign;

import com.reformer.processorreformertest.feign.fallback.TestFeignFallback;
import com.reformer.system.api.feign.SystemAPI;
import com.reformer.system.api.feign.SystemUserAPI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-05
 **/
@FeignClient(name = "system", contextId = "testFeign", url = "localhost:8081", fallback = TestFeignFallback.class)
public interface TestFeign extends SystemAPI, SystemUserAPI {
}
