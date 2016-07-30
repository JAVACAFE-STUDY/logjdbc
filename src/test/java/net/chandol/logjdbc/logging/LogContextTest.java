package net.chandol.logjdbc.logging;

import ch.qos.logback.classic.spi.LoggingEvent;
import net.chandol.logjdbc._testhelper.AutoLogInitializer;
import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.logging.collector.parameter.ParameterCollector;
import net.chandol.logjdbc.logging.collector.parameter.ParameterType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static net.chandol.logjdbc.config.DatabaseType.H2;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class LogContextTest extends AutoLogInitializer {
    LogJdbcConfig config;
    private String sourceSql;
    private List<LoggingEvent> recentLogs;

    @Before
    public void setup() {
        this.config = new LogJdbcConfig(H2);
    }

    @Test
    public void 컨텍스트가_빈값인_경우() throws Exception {
        //given
        LogContext context = LogContext.of(config);
        //when
        context.printLog();

        //then
        // 출력될 로그가 없으므로, 로그의 size는 0이 된다.
        assertThat(getLogSize(), is(0));
    }

    @Test
    public void SQL_로깅() throws Exception {
        //given
        sourceSql = "SELECT * FROM DUAL";

        LogContext context0 = LogContext.of(config, sourceSql);
        LogContext context1 = LogContext.of(config);
        context1.setSql(sourceSql);

        //when
        context0.printLog();
        context1.printLog();

        //then
        // 로그는 2개 출력이 된다.
        assertThat(getLogSize(), is(2));

        List<LoggingEvent> lastLogs = getRecentLogs(2);
        // 두 로그가 동일한지 검증한다.
        assertThat(lastLogs.get(0).getMessage(), is(lastLogs.get(1).getMessage()));
    }

    @Test
    public void SQL_Parameter_로깅() throws Exception {
        //given
        sourceSql = "SELECT * FROM DUAL WHERE id=? AND name=?";
        LogContext context = LogContext.of(config, sourceSql);
        ParameterCollector parameterCollector = context.initParameterCollector();
        parameterCollector.add(1, ParameterType._Int, 1);
        parameterCollector.add(2, ParameterType._String, "테스트");

        //when
        context.printLog();

        //then
        assertThat(getLogSize(), is(2));
        recentLogs = AutoLogInitializer.getRecentLogs(2);
        String paramMessage = recentLogs.get(0).getMessage();
        assertThat(paramMessage, is(containsString("{String = '테스트'}")));

        String sqlMessage = recentLogs.get(1).getMessage();
        assertThat(sqlMessage, is(containsString("name='테스트'")));
        assertThat(sqlMessage, is(containsString("id=1")));
    }

}