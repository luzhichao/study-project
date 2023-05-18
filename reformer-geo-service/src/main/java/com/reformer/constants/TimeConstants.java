package com.reformer.constants;

/**
 * 描述:
 * 作者: LiuCheng
 * 时间: 2023/5/18 13:24
 */
public interface TimeConstants {

    //秒
    long cache_time_second = 1;
    //时分
    long cache_time_division = cache_time_second * 60;
    //小时
    long cache_time_hour = cache_time_division * 60;
    //天
    long cache_time_day = cache_time_hour * 24;
}
