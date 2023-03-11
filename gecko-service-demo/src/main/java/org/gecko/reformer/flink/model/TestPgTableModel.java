package org.gecko.reformer.flink.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
@Data
public class TestPgTableModel implements Serializable {
    private static final long serialVersionUID = 7184285960715646256L;

    private String name;
    private Date createTime;
    private Double money;

}
