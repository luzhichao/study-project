package org.gecko.reformer.processorgeckotest.feign;

import org.gecko.reformer.annotation.AutoFeignFallback;
import org.gecko.reformer.processorgeckotest.api.SystemAPI;
import org.gecko.reformer.processorgeckotest.api.SystemUserAPI;
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
public interface TestFeign extends SystemAPI, SystemUserAPI {
}
