package net.chandol.logjdbc._testhelper;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 테스트시 발생하는 로그를 모으며, 이를 검증할 수 있도록 한다.
 * 해당 객체는 동시성에서 문제가 발생할 수 있으므로
 * 테스트시 fork모드는 사용하지 않도록 유의하여야 한다.
 */
public class TestAppender extends AppenderBase<LoggingEvent> {
    private static List<LoggingEvent> events = new ArrayList<>();

    @Override
    protected void append(LoggingEvent e) {
        events.add(e);
    }

    static List<LoggingEvent> getEvents() {
        return events;
    }

    static void cleanEvents(){
        events = new ArrayList<>();
    }
}
