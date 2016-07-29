package net.chandol.datasource._testhelper;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

// 로그가 정상적으로 초기화되는지를 확인합니다.
public class AutoLogInitializerTest extends AutoLogInitializer{
    private static final Logger logger = LoggerFactory.getLogger(AutoLogInitializerTest.class);

    @Test
    public void getLogTest(){
        logger.debug("test4");
        logger.debug("test3");
        logger.debug("test2");
        logger.debug("test1");
        logger.debug("test0");

        assertThat(getLogs().size(), is(5));
    }

    @Test
    public void testGetLogSize(){
        logger.debug("test0");
        logger.debug("test1");
        logger.debug("test2");
        logger.debug("test3");
        logger.debug("test4");


        assertThat(getLogSize(), is(5));
    }
}
