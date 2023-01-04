package org.gecko.reformer.feign;


import java.util.Collection;
import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
public interface SystemAPI {

    List<String> listOrg(Collection<String> orgId);

}

