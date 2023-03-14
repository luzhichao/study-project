package org.gecko.reformer.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-11
 **/
@Data
public class TestModel implements Serializable {

    private String operation;
    private String database;
    private String table;
    private Map<String, Object> before;
    private Map<String, Object> after;

}
