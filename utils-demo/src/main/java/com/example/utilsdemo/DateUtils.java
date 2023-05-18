package com.example.utilsdemo;

import com.reformer.exception.CustomException;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author FanXuan
 * @date 2023/5/18
 */
public class DateUtils {
    /**
     * 功能描述: 时间格式转换，只保留数字
     * @param dateTimeStyle 时间格式
     */
    public static String timeFormatConversion(String dateTimeStyle, String dateTime) {
        // 时间格式校验
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeStyle, Locale.CHINA).withResolverStyle(ResolverStyle.STRICT);
        try {
            dateTimeFormatter.parse(dateTime);
        } catch (DateTimeException e) {
            throw new CustomException("时间格式不正确");
        }

        return Pattern.compile("[^0-9]").matcher(dateTime).replaceAll("").trim();
    }

    /**
     * 功能描述: 时间范围内随机生成任意数量的时间点（yyyy-MM-dd HH:mm:ss）,时间排倒叙
     * @param start 开始时间
     * @param end 结束时间
     * @param size 生成时间个数
     */
    public static List<Date> randomDate(String start, String end, int size) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date startTime = sdf.parse(start);
        Date endTime = sdf.parse(end);

        Random random = new Random();
        List<Date> dates = random.longs(size, startTime.getTime(), endTime.getTime()).mapToObj(t -> new Date(t)).collect(Collectors.toList());

        dates.sort((t1,t2)->{
            return t2.compareTo(t1);
        });
        return dates;
    }
}
