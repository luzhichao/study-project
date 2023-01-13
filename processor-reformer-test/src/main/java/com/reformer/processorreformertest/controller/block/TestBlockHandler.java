package com.reformer.processorreformertest.controller.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.reformer.annotation.BlockHandler;
import com.reformer.vo.Result;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-13
 **/
@BlockHandler
public class TestBlockHandler {

    public static Result<String> testBlockHandler(BlockException e) {
        return Result.error("操作过于频繁");
    }
}
