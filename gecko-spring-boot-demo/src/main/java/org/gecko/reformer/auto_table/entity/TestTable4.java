package org.gecko.reformer.auto_table.entity;

import lombok.Data;
import org.gecko.reformer.annotation.*;
import org.gecko.reformer.constant.CharsetConstant;
import org.gecko.reformer.constant.EngineConstant;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 测试表4
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-17
 **/
@Data
@Table(name = "test_table4", comment = "测试表4", engine = EngineConstant.InnoDB, charset = CharsetConstant.UTF8)
public class TestTable4 implements Serializable {
    private static final long serialVersionUID = 1L;

    @IsKey
    @Column(comment = "这是自增长key", length = 60)
    private String key;
    @Index(value = "test_table1_name_index", columns = "name")
    @Column(value = "name", comment = "姓名", length = 50)
    private String name;
    @Column(value = "age", comment = "年龄", length = 3)
    private Integer age = 22;
    @Column(value = "birthday", comment = "生日")
    private LocalDate birthday;
    @Column(value = "deposit", comment = "存款")
    private Double deposit;

    @IsNativeDefValue
    @Column(value = "del_flag", comment = "删除标识", length = 1, defaultValue = "b'0'")
    private Boolean delFlag;

    @Column(comment = "aaa", length = 1)
    private Boolean aaa = false;
}
