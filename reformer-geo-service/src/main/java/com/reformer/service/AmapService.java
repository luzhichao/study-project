package com.reformer.service;


/**
 * 描述:
 * 作者: LiuCheng
 * 时间: 2023/5/9 17:13
 */
public interface AmapService {

    /**
     * 描述: 根据经纬度获取位置信息
     * @param: longitude 经度
     * @param: latitude 纬度
     * 返回值: java.lang.String
     * 作者: LiuCheng
     * 时间: 2023/5/10 13:13
     */
    String getAddress(Double longitude, Double latitude);

    /**
     * 描述:  根据经纬度获取位置信息
     * @param: longitude 经度
     * @param: latitude  纬度
     * @param: openCache 是否启用缓存
     * 返回值: java.lang.String
     * 作者: LiuCheng
     * 时间: 2023/5/10 13:14
     */
    String getAddress(Double longitude, Double latitude, boolean openCache);

}
