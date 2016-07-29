package net.chandol.logjdbc._testhelper;

import ch.qos.logback.classic.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;

import java.util.List;

/**
 * 테스트 시작과 종료 전에 테스트 로그를 초기화 시킨다.
 * 멀티스레드 테스트시 문제가 생길 가능성이 있으므로 유의하도록 하자.
 */
public abstract class AutoLogInitializer {
    @Before
    public void beforeRemoveLog(){
        TestAppender.cleanEvents();
    }

    @After
    public void afterRemoveLog(){
        TestAppender.cleanEvents();
    }

    protected static List<LoggingEvent> getLogs(){
        return TestAppender.getEvents();
    }

    protected static int getLogSize(){
        return getLogs().size();
    }
}
