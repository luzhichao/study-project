package com.reformer.service.impl;

import com.reformer.config.GeoConfig;
import com.reformer.constants.TimeConstants;
import com.reformer.dto.RegeoDTO;
import com.reformer.service.AmapService;
import com.reformer.service.RedisService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 描述:
 * 作者: LiuCheng
 * 时间: 2023/5/10 13:14
 */
@Component
public class AmapServiceImpl implements AmapService {

    public AmapServiceImpl() {

    }

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RedisService redisService;

    @Resource
    private GeoConfig geoConfig;

    //是否启用缓存
    private static final boolean OPEN_CACHE = true;


    public String getAddress(Double longitude, Double latitude) {
        return getAddress(longitude, latitude, OPEN_CACHE);
    }

    public String getAddress(Double longitude, Double latitude, boolean openCache) {
        String address = null;
        //小数点后只保留6位
        longitude = intercept(longitude, 6);
        latitude = intercept(latitude, 6);
        String location = longitude + "," + latitude;
        if (openCache) {
            Object o = redisService.hGet(geoConfig.getRedisKey(), location);
            if (o != null) {
                return o.toString();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(geoConfig.getHttpUrl());
        sb.append("location=");
        sb.append(location);
        sb.append("&key=");
        sb.append(geoConfig.getHttpKey());
        RegeoDTO regeoDTO = restTemplate.getForObject(sb.toString(), RegeoDTO.class);
        if (regeoDTO != null && "1".equals(regeoDTO.getStatus())) {
            address = regeoDTO.getRegeocode().getFormatted_address();
            redisService.hSet(geoConfig.getRedisKey(), location, address, TimeConstants.cache_time_day);
        }
        return address;
    }

    private static double intercept(double value, int num) {
        BigDecimal decimalValue = new BigDecimal(value);
        return decimalValue.setScale(num, BigDecimal.ROUND_DOWN).doubleValue();
    }
}
