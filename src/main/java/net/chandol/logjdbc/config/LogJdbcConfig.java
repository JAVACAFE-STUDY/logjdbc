package net.chandol.logjdbc.config;

import net.chandol.logjdbc.logging.printer.resultset.ResultSetPrinter;
import net.chandol.logjdbc.logging.printer.resultset.ResultSetTablePrinter;
import net.chandol.logjdbc.logging.printer.sql.DefaultSqlPrinter;
import net.chandol.logjdbc.logging.printer.sql.SqlPrinter;
import net.chandol.logjdbc.logging.printer.sql.formatter.DefaultSqlFormatter;
import net.chandol.logjdbc.logging.printer.sql.formatter.SqlFormatter;
import net.chandol.logjdbc.logging.printer.sql.paramconverter.ParameterConverter;

import javax.sql.DataSource;

/**
 * LogJdbc 설정
 */
public class LogJdbcConfig {
    private DatabaseType type;
    private ParameterConverter converter;
    private SqlFormatter formatter;
    private SqlPrinter sqlPrinter;
    private ResultSetPrinter resultSetPrinter;

    // 로깅에 필요한 설정값을 담을 수 있다.
    private LogJdbcProperties properties;

    public LogJdbcConfig() {
        this(null, new LogJdbcProperties());
    }

    public LogJdbcConfig(DatabaseType type) {
        this(type, new LogJdbcProperties());
    }

    public LogJdbcConfig(LogJdbcProperties properties) {
        this(null, properties);
    }

    public LogJdbcConfig(DatabaseType type, LogJdbcProperties properties) {
        this.formatter = DefaultSqlFormatter.getInstance();
        this.sqlPrinter = DefaultSqlPrinter.getInstance();
        this.resultSetPrinter = ResultSetTablePrinter.getInstance();
        this.properties = properties;

        if (type != null)
            this.converter = type.getParameterConverter();
    }

    /* getter */
    public DatabaseType getType() {
        return type;
    }

    public ParameterConverter getConverter() {
        return converter;
    }

    public SqlFormatter getFormatter() {
        return formatter;
    }

    public SqlPrinter getSqlPrinter() {
        return sqlPrinter;
    }

    public ResultSetPrinter getResultSetPrinter() {
        return resultSetPrinter;
    }

    public LogJdbcProperties getProperties() {
        return properties;
    }

    /**
     * Datasource를 분석하여 Database를 찾고 설정합니다.
     *
     * @param datasource
     */
    public void setDatabaseTypeBaseOnDatasource(DataSource datasource) {
        DatabaseType type = DatabaseType.find(datasource);

        this.type = type;
        this.converter = type.getParameterConverter();
    }
}
