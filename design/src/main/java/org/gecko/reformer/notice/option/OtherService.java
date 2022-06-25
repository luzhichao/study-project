package org.gecko.reformer.notice.option;

import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/08/21
 **/
@Service
public class OtherService {

    /**
     * 其他方法
     *
     * @param id ⽤户编号
     * @return 结果
     */
    public String lottery(String id) {
        return Math.abs(id.hashCode()) % 2 == 0 ? "恭喜你，编码".concat(id).concat("在本次中奖")
                : "很遗憾，编码".concat(id).concat("在本次摇号未中奖或已过期");
    }
}
