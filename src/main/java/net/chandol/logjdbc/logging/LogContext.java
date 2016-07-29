package net.chandol.logjdbc.logging;

import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.logging.collector.parameter.ParameterCollector;
import net.chandol.logjdbc.logging.collector.resultset.ResultSetCollector;
import net.chandol.logjdbc.logging.printer.LogPrinter;

import java.sql.ResultSetMetaData;


public class LogContext {
    private LogJdbcConfig config;
    private String sql;
    private ResultSetCollector resultSetCollector;
    private ParameterCollector parameterCollector;

    // TODO 리팩토링
    public void printLog() {
        // parameter가 없는 경우
        if (parameterCollector != null)
            LogPrinter.logSql(config, sql, parameterCollector);
        else
            LogPrinter.logSql(config, sql);

        if(resultSetCollector!=null)
            LogPrinter.logResultSet(config, resultSetCollector);
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public ParameterCollector initParameterCollector() {
        this.parameterCollector = new ParameterCollector();
        return this.parameterCollector;
    }

    public ResultSetCollector initResultSetCollector(ResultSetMetaData metaData) {
        this.resultSetCollector = new ResultSetCollector(metaData);
        return this.resultSetCollector;
    }

    /* Constructor */
    private LogContext(LogJdbcConfig config) {
        this.config = config;
    }

    public LogContext(LogJdbcConfig config, String sql) {
        this.config = config;
        this.sql = sql;
    }

    public static LogContext of(LogJdbcConfig config) {
        return new LogContext(config);
    }

    public static LogContext of(LogJdbcConfig config, String sql) {
        return new LogContext(config, sql);
    }

}
