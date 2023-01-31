package org.gecko.reformer.feign;

import org.gecko.reformer.annotation.AutoFeignFallback;
import org.gecko.reformer.annotation.MyFeignClient;
import org.gecko.reformer.api.TestAPI;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-31
 **/
//@AutoFeignFallback
@MyFeignClient(name = "test", contextId = "testFeign")
public interface TestFeign extends TestAPI {
}
