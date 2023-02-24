package org.gecko.reformer.auto_table.entity;

import lombok.Data;
import org.gecko.reformer.annotation.Column;
import org.gecko.reformer.annotation.Index;
import org.gecko.reformer.annotation.Table;
import org.gecko.reformer.annotation.Unique;
import org.gecko.reformer.constant.CharsetConstant;
import org.gecko.reformer.constant.EngineConstant;
import org.gecko.reformer.entity.BaseEntity;

import java.time.LocalDate;

/**
 * 测试表2
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-17
 **/
@Data
@Table(name = "test_table2", comment = "测试表2", engine = EngineConstant.InnoDB, charset = CharsetConstant.UTF8MB4)
public class TestTable2 extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(comment = "这是key", length = 60)
    private String key;
    @Column(comment = "这是aaa", length = 60)
    private String aaa;
    @Index(value = "test_table1_name_aaa_index", columns = {"name", "aaa"})
    @Column(value = "name", comment = "姓名", length = 50)
    private String name;
    @Column(value = "age", comment = "年龄", length = 3, defaultValue = "20")
    private Integer age;
    @Column(value = "birthday", comment = "生日")
    private LocalDate birthday;
    @Column(value = "deposit", comment = "存款")
    private Double deposit;
    @Column(value = "id_card", comment = "身份证号")
    private String idCard;

    @Index
    @Column(comment = "是否信息接报(1:已接报,0:未接报)", length = 2, defaultValue = "0")
    private String infoReport;
    @Index
    @Column(comment = "报警方式(1:人工告警,2:设备告警,3:公众上报)", length = 2)
    private String alarmMode;

    @Unique
    @Column(comment = "事发地点经度", length = 10, decimalLength = 6)
    private String longitude;
    @Unique
    @Column(comment = "事发地点纬度", length = 10, decimalLength = 6)
    private String latitude;

    @Column(comment = "告警内容", length = 50)
    private String alarmContent;
}
