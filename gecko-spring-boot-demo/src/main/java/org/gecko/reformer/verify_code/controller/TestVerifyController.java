package org.gecko.reformer.verify_code.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.service.RedisService;
import org.gecko.reformer.util.RandomUtils;
import org.gecko.reformer.util.VerifyImgUtils;
import org.gecko.reformer.verify_code.constant.RedisConstants;
import org.gecko.reformer.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-04
 **/
@Slf4j
@Api(tags = "TestVerify")
@WebController
@RequestMapping("/verify/api/")
public class TestVerifyController {

    @Resource
    private RedisService redisService;

    @SneakyThrows
    @ApiOperation("testCreateVerifyImg")
    @GetMapping("/testCreateVerifyImg")
    public void testCreateVerifyImg(HttpServletResponse response) {
        final String verifyCode = getAndCacheVerifyCode();
        final BufferedImage image = VerifyImgUtils.getVerifyImage(verifyCode);
        //  将图片以png格式返回,返回的是图片
        ImageIO.write(image, "PNG", response.getOutputStream());
    }

    @SneakyThrows
    @ApiOperation("testCreateVerifyBase64")
    @GetMapping("/testCreateVerifyBase64")
    public Result<String> testCreateVerifyBase64() {
        final String verifyCode = getAndCacheVerifyCode();
        final String base64 = VerifyImgUtils.getVerifyImageBase64(verifyCode);
        return Result.success(base64);
    }

    @ApiOperation("testCheckVerify")
    @PostMapping("/testCheckVerify")
    public Result<Boolean> testCheckVerify(@RequestBody String verifyCode) {
        final String str = redisService.getStr(RedisConstants.CACHE_VERIFY_CODE_KEY);
        return Result.success(StrUtil.equalsIgnoreCase(str, verifyCode));
    }

    /**
     * 获取并缓存验证码
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author LZC
     * @date 2022-11-04
     * @version 1.1.2
     **/
    private String getAndCacheVerifyCode() {
        final int i = RandomUtil.randomInt(4, 6);
        final String verifyCode = RandomUtils.randomCaseStr(i);
        log.info("验证码==={}", verifyCode);
        redisService.set(RedisConstants.CACHE_VERIFY_CODE_KEY, verifyCode, RedisConstants.CACHE_VERIFY_CODE_EXPIRE);
        return verifyCode;
    }
}
