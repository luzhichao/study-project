package org.gecko.reformer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gecko.reformer.annotation.Column;
import org.gecko.reformer.annotation.Table;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "test_table", comment = "测试表")
@TableName("test_table")
public class TestTable extends BaseEntity {

    @Column(comment = "报警名称", length = 60)
    private String alarmName;
    @Column(comment = "设备编号1111", length = 64)
    private String deviceCode;
    @Column(comment = "报警类型2222", length = 32)
    private String alarmType;

    @Column(comment = "报警类型名称33333", length = 64)
    private String alarmTypeName;
    @Column(comment = "报警开始时间44444")
    private Date alarmStartTime;
    @Column(comment = "报警结束时间")
    private Date alarmEndTime;
    @Column(comment = "报警次数", length = 5)
    private Integer alarmNum;
    @Column(comment = "处置时间")
    private Date handleTime;
    @Column(comment = "处置状态", length = 2)
    private Integer handleStatus;
    @Column(comment = "告警状态(1:在线报警，0:离线报警)", length = 2)
    private Integer alarmFlag;

    @Column(comment = "是否信息接报(1:已接报,0:未接报)", length = 2, defaultValue = "0")
    private Boolean infoReport;
    @Column(comment = "报警方式(1:人工告警,2:设备告警,3:公众上报)", length = 2)
    private Integer alarmMode;
    @Column(comment = "事发地点经度", length = 10, decimalLength = 6)
    private Double longitude;
    @Column(comment = "事发地点纬度", length = 10, decimalLength = 6)
    private Double latitude;

    @Column(comment = "告警内容", length = 50)
    private String alarmContent;
}
