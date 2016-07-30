package net.chandol.logjdbc.config;

import net.chandol.logjdbc.logging.printer.resultset.DefaultResultSetPrinter;
import net.chandol.logjdbc.logging.printer.resultset.ResultSetPrinter;
import net.chandol.logjdbc.logging.printer.sql.DefaultSqlPrinter;
import net.chandol.logjdbc.logging.printer.sql.SqlPrinter;
import net.chandol.logjdbc.logging.printer.sql.formatter.DefaultSqlFormatter;
import net.chandol.logjdbc.logging.printer.sql.formatter.SqlFormatter;
import net.chandol.logjdbc.logging.printer.sql.paramconverter.ParameterConverter;


import javax.sql.DataSource;

/**
 * SQL로그 설정.
 */
public class LogJdbcConfig {
    private DatabaseType type;
    private ParameterConverter converter;
    private SqlFormatter formatter;

    private SqlPrinter sqlPrinter;
    private ResultSetPrinter resultSetPrinter;

    // 각 항목들을 초기화시킨다.
    public LogJdbcConfig(DatabaseType type, SqlFormatter formatter) {
        this.type = type;
        this.converter = type.getParameterConverter();
        this.formatter = formatter;

        //TODO need singleton
        this.sqlPrinter = new DefaultSqlPrinter();
        this.resultSetPrinter = new DefaultResultSetPrinter();
    }

    public LogJdbcConfig(DatabaseType type) {
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

    public SqlPrinter getSqlPrinter(){
        return sqlPrinter;
    }

    public ResultSetPrinter getResultSetPrinter(){
        return resultSetPrinter;
    }


    public static LogJdbcConfig autoconfig(DataSource datasource) {
        DatabaseType type = DatabaseType.find(datasource);
        SqlFormatter formatter = new DefaultSqlFormatter();

        return new LogJdbcConfig(type, formatter);
    }
}
