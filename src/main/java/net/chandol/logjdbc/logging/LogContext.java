package net.chandol.logjdbc.logging;

import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.logging.collector.parameter.ParameterCollector;
import net.chandol.logjdbc.logging.collector.resultset.ResultSetCollector;

import java.sql.ResultSetMetaData;

/**
 * JDBC 작업에서 발생하는 로그성 자료들을 모으고 출력한다.
 */
public class LogContext {
    private LogJdbcConfig config;

    private String sql;
    private ParameterCollector parameterCollector;
    private ResultSetCollector resultSetCollector;

    private PrintLogHelper helper;

    public void printLog() {
        if (parameterPrintable())
            config.getSqlPrinter().printParameter(this);

        if (sqlPrintable())
            config.getSqlPrinter().printSql(this);

        if (resultSetPrintable())
            config.getResultSetPrinter().printResultSet(this);
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

    /* getter */
    public LogJdbcConfig getConfig() {
        return config;
    }

    public String getSql() {
        return sql;
    }

    public ParameterCollector getParameterCollector() {
        return parameterCollector;
    }

    public ResultSetCollector getResultSetCollector() {
        return resultSetCollector;
    }

    public PrintLogHelper getHelper() {
        return helper;
    }

    /* printable */
    private boolean resultSetPrintable() {
        return resultSetCollector != null;
    }

    private boolean parameterPrintable() {
        return parameterCollector != null;
    }

    private boolean sqlPrintable() {
        return sql != null && !sql.isEmpty();
    }

    /* Constructor */
    private LogContext(LogJdbcConfig config) {
        this.config = config;
        this.helper = new PrintLogHelper(config);
    }

    private LogContext(LogJdbcConfig config, String sql) {
        this(config);
        this.sql = sql;
    }

    public static LogContext of(LogJdbcConfig config) {
        return new LogContext(config);
    }

    public static LogContext of(LogJdbcConfig config, String sql) {
        return new LogContext(config, sql);
    }

}
