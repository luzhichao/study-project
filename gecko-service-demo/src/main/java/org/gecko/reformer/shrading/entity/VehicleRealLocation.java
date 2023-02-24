package org.gecko.reformer.shrading.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.gecko.reformer.entity.BaseEntity;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 描述：车辆实时点位信息表
 *
 * @author jianpeng
 * @date 2022/6/15 10:12
 */
@Data
@TableName(value = "t_vehicle_real_location")
public class VehicleRealLocation extends BaseEntity {

    private String terminalId;

    private String vehiclePlateNum;

    private Integer alarmState;

    private Integer accState;

    private Long alarmCodes;

    private Long statusCodes;

    private Integer serviceStatus;

    private Integer onCallStatus;

    private Double lat;

    private Double lng;

    private Double velocity;

    private LocalDateTime dateTime;

    private Integer elevation;

    private Double mileage;

    private Double oilmass;

    private Integer direction;

    private Integer dataType;

}
