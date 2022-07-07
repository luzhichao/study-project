package org.gecko.reformer.entity;

import lombok.Data;
import org.gecko.reformer.annotation.Column;
import org.gecko.reformer.annotation.Index;
import org.gecko.reformer.annotation.Table;
import org.gecko.reformer.annotation.Unique;
import org.gecko.reformer.constant.CharsetConstant;
import org.gecko.reformer.constant.EngineConstant;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 测试表3
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-17
 **/
@Data
@Table(name = "test_table3", comment = "测试表3", engine = EngineConstant.InnoDB, charset = CharsetConstant.UTF8MB4)
public class TestTable3 implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(comment = "这是key", length = 60)
    private String key;
    @Unique(columns = {"name", "key"})
    @Column(value = "name", comment = "姓名", length = 50)
    private String name;
    @Column(value = "age", comment = "年龄", length = 3, defaultValue = "20")
    private Integer age;
    @Column(value = "birthday", comment = "生日")
    private LocalDate birthday;
    @Column(value = "deposit", comment = "存款")
    private Double deposit;
    @Column(value = "del_flag", comment = "删除标识", length = 1, defaultValue = "0")
    private Boolean delFlag;
}
