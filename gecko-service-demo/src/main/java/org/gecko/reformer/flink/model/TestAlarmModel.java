package org.gecko.reformer.flink.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
@Data
public class TestAlarmModel implements Serializable {
    private static final long serialVersionUID = 7184285960715646256L;

    private String id;
    private String tenantId;
    private int delFlag;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

    private Integer alarmType;
    private String alarmTypeDetail;
    private String terminalId;
    private String vehiclePlateNum;
    private Integer alarmState;
    private Integer accState;
    private String driverId;
    private String alarmInformationSource;
    private LocalDateTime alarmTime;
    private LocalDateTime removeAlarmTime;
    private Double alarmLat;
    private Double alarmLng;
    private Double velocity;
    private String alarmPictureUrl;
    private String alarmVideoUrl;
    private String regionId;
    private String operatingId;
    private Integer passengerStatus;
    private Double forecastMoney;
    private String orgId;
    private Integer direction;
}
