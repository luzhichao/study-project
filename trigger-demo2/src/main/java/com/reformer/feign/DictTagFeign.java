package com.reformer.feign;

import com.reformer.dictionary.api.feign.DictionaryAPI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:字典项服务
 *
 * @author LiuCheng
 * @date 2022/1/14 15:52
 */
@FeignClient(name = "dictionary", contextId = "dictTagFeign")
public interface DictTagFeign extends DictionaryAPI {
}
