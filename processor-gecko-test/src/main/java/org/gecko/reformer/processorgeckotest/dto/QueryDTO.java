package org.gecko.reformer.processorgeckotest.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-05
 **/
@Data
public class QueryDTO implements Serializable {

    private String id;
    private List<String> ids;
}