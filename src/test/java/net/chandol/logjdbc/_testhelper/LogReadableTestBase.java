package net.chandol.logjdbc._testhelper;

import ch.qos.logback.classic.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 아래의 자동초기화보다 중요한것은 이 클래스를통해서 로그를 캐치할 수 있다는 것이다.
 * 이름은 다시한번 변경하도록 하자.
 * <p>
 * 테스트 시작과 종료 전에 테스트 로그를 초기화 시킨다.
 * 멀티스레드 테스트시 문제가 생길 가능성이 있으므로 유의하도록 하자.
 */
public abstract class LogReadableTestBase {
    @Before
    public void beforeRemoveLog() {
        TestAppender.cleanEvents();
    }

    @After
    public void afterRemoveLog() {
        TestAppender.cleanEvents();
    }

    protected static List<LoggingEvent> getLogs() {
        return TestAppender.getEvents();
    }

    protected static List<String> getLogMessages() {
        List<String> logMessages = new ArrayList<>();
        for(LoggingEvent event : getLogs())
            logMessages.add(event.getMessage());

        return logMessages;
    }

    protected static List<LoggingEvent> getRecentLogs(int size) {
        List<LoggingEvent> logs = getLogs();
        int logSize = logs.size();
        return TestAppender.getEvents().subList(logSize - size, logSize);
    }

    protected static List<String> getRecentLogMessages(int size) {
        List<String> logMessages = new ArrayList<>();
        for(LoggingEvent event : getRecentLogs(size))
            logMessages.add(event.getMessage());

        return logMessages;
    }

    protected static int getLogSize() {
        return getLogs().size();
    }
}
