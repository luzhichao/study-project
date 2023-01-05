package org.gecko.reformer.feign;

import org.gecko.reformer.annotation.FeignClient;
import org.gecko.reformer.annotation.AutoFeignFallback;
import org.gecko.reformer.api.SystemAPI;
import org.gecko.reformer.api.SystemOrgAPI;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@AutoFeignFallback
@FeignClient(name = "system", contextId = "SystemFeign")
public interface SystemFeign extends SystemAPI, SystemOrgAPI {
}
