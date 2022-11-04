package org.gecko.reformer.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.Base64;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-04
 **/
public class VerifyImgUtils {
    private VerifyImgUtils() {
    }

    /*** 验证码的宽 **/
    private static final int width = 165;
    /*** 验证码的高 **/
    private static final int height = 45;
    /*** 验证码中夹杂的干扰线数量 **/
    private static final int lineNum = 15;
    /*** JAVA中的Font有三种属性决定(1.字体名：(familyName),2.风格：(style),3.大小：(size)) **/
    private static final Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, 40);

    /**
     * 颜色的设置
     *
     * @return java.awt.Color
     * @throws
     * @author LZC
     * @date 2022-11-04
     * @version 1.1.2
     **/
    private static Color getRandomColor() {
        int r = RandomUtil.randomInt(255);
        int g = RandomUtil.randomInt(255);
        int b = RandomUtil.randomInt(255);
        return new Color(r, g, b);
    }

    /**
     * 干扰线的绘制
     *
     * @param g
     * @param width
     * @param height
     * @return void
     * @throws
     * @author LZC
     * @date 2022-11-04
     * @version 1.1.2
     **/
    private static void drawLine(Graphics g, int width, int height) {
        int x1 = RandomUtil.randomInt(width);
        int y1 = RandomUtil.randomInt(height);
        int x2 = RandomUtil.randomInt(width);
        int y2 = RandomUtil.randomInt(height);
        g.setColor(getRandomColor());
        // [干扰线]设置线条的位置:drawLine(int x1, int y1, int x2, int y2),x1与y1属于线条起点坐标，x2与y2属于线条终点坐标
        g.drawLine(x1, y1, x2, y2);
    }

    /**
     * 字符串的绘制
     *
     * @param g
     * @param verifyCode
     * @return java.lang.String
     * @throws
     * @author LZC
     * @date 2022-11-04
     * @version 1.1.2
     **/
    private static void drawString(Graphics2D g, String verifyCode, int width, int height) {
        char[] chars = verifyCode.toCharArray();
        g.setFont(font);
        final int length = chars.length;
        final int w = Double.valueOf(NumUtils.div(width, length, 0, RoundingMode.UP)).intValue();
        final int h = Double.valueOf(NumUtils.div(height, 5, 0, RoundingMode.UP)).intValue();
        for (int i = 0; i < length; i++) {
            final int x = (w - 10) * i + RandomUtil.randomInt(20);
            final int y = 3 * h;
            g.setColor(getRandomColor());
            g.translate(RandomUtil.randomInt(3), RandomUtil.randomInt(6));
            // 设置字体旋转角度
            int degree = RandomUtil.randomInt() % 30;
            g.rotate(degree * Math.PI / 180, x, y);
            // 设置文本字符的位置:drawString(String str , int x , int y),x与y属于str的左下角的坐标
            g.drawString(StrUtil.toString(chars[i]), x, y);
            // 还原旋转
            g.rotate(-degree * Math.PI / 180, x, y);
        }
    }

    /**
     * 构建验证码图片
     *
     * @param width      宽度
     * @param height     高度
     * @param lineNum    干扰线数量
     * @param verifyCode 验证码
     * @return void
     * @throws
     * @author LZC
     * @date 2022-11-04
     * @version 1.1.2
     **/
    public static BufferedImage getVerifyImage(int width, int height, int lineNum, String verifyCode) {
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 设置画布背景
        // g.setColor(new Color(240,240,140));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        // 绘制干扰线
        for (int i = 0; i < lineNum; i++) {
            drawLine(g, width, height);
        }

        // 绘制验证码
        drawString(g, verifyCode, width, height);
        g.dispose();
        return image;
    }

    /**
     * 根据验证码生成图片
     *
     * @param verifyCode 验证码
     * @return java.awt.image.BufferedImage
     * @throws
     * @author LZC
     * @date 2022-11-04
     * @version 1.1.2
     **/
    public static BufferedImage getVerifyImage(String verifyCode) {
        return getVerifyImage(width, height, lineNum, verifyCode);
    }


    /**
     * 根据验证码生成图片的base64编码字符串
     *
     * @param width      宽度
     * @param height     高度
     * @param lineNum    干扰线数量
     * @param verifyCode 验证码
     * @return java.lang.String
     * @throws
     * @author LZC
     * @date 2022-11-04
     * @version 1.1.2
     **/
    public static String getVerifyImageBase64(int width, int height, int lineNum, String verifyCode) throws IOException {
        BufferedImage image = getVerifyImage(width, height, lineNum, verifyCode);

        // 返回base64
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", bos);

        String base64String = Base64.getEncoder().encodeToString(bos.toByteArray());
        return base64String;
    }

    /**
     * 根据验证码生成图片的base64编码字符串
     *
     * @param verifyCode 验证码
     * @return java.lang.String
     * @throws
     * @author LZC
     * @date 2022-11-04
     * @version 1.1.2
     **/
    public static String getVerifyImageBase64(String verifyCode) throws IOException {
        return getVerifyImageBase64(width, height, lineNum, verifyCode);
    }
}
