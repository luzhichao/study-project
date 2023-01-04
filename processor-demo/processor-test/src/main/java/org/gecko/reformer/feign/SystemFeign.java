package org.gecko.reformer.feign;

import org.gecko.reformer.annotation.FeignClient;
import org.gecko.reformer.annotation.FeignFallback;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@FeignFallback
@FeignClient(name = "system", contextId = "SystemFeign")
public interface SystemFeign extends SystemAPI {
}
