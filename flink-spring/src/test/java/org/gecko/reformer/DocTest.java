package org.gecko.reformer;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-10
 **/
@Slf4j
@SpringBootTest
public class DocTest {

    @Test
    public void docTest(){
        final Document parse = Document.parse("{}");
        System.out.println(parse);
    }
}
