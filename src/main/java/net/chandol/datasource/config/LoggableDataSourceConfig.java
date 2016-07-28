package net.chandol.datasource.config;

import net.chandol.datasource.logging.logger.resultset.DefaultResultSetLogger;
import net.chandol.datasource.logging.logger.resultset.ResultSetLogger;
import net.chandol.datasource.logging.logger.sql.DefaultSqlLogger;
import net.chandol.datasource.logging.logger.sql.SqlLogger;
import net.chandol.datasource.sql.formatter.DefaultSqlFormatter;
import net.chandol.datasource.sql.formatter.SqlFormatter;
import net.chandol.datasource.sql.parameter.converter.ParameterConverter;


import javax.sql.DataSource;

/**
 * SQL로그 설정.
 */
public class LoggableDataSourceConfig {
    private DatabaseType type;
    private ParameterConverter converter;
    private SqlFormatter formatter;

    private SqlLogger sqlLogger;
    private ResultSetLogger resultSetLogger;

    // 각 항목들을 초기화시킨다.
    public LoggableDataSourceConfig(DatabaseType type, SqlFormatter formatter) {
        this.type = type;
        this.converter = type.getParameterConverter();
        this.formatter = formatter;

        //TODO need singleton
        this.sqlLogger = new DefaultSqlLogger();
        this.resultSetLogger = new DefaultResultSetLogger();
    }

    public LoggableDataSourceConfig(DatabaseType type) {
        this(type, new DefaultSqlFormatter());
    }

    public DatabaseType getType() {
        return type;
    }

    public ParameterConverter getConverter() {
        return converter;
    }

    public SqlFormatter getFormatter() {
        return formatter;
    }

    public SqlLogger getSqlLogger(){
        return sqlLogger;
    }

    public ResultSetLogger getResultSetLogger(){
        return resultSetLogger;
    }


    public static LoggableDataSourceConfig autoconfig(DataSource datasource) {
        DatabaseType type = DatabaseType.find(datasource);
        SqlFormatter formatter = new DefaultSqlFormatter();

        return new LoggableDataSourceConfig(type, formatter);
    }
}
