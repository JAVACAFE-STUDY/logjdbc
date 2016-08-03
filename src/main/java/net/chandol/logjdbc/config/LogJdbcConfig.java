package net.chandol.logjdbc.config;

import net.chandol.logjdbc.logging.printer.resultset.ResultSetTablePrinter;
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

    public LogJdbcConfig(DatabaseType type) {
        this.type = type;
        this.converter = type.getParameterConverter();

        this.formatter = DefaultSqlFormatter.getInstance();
        this.sqlPrinter = DefaultSqlPrinter.getInstance();
        this.resultSetPrinter = ResultSetTablePrinter.getInstance();
    }

    void setType(DatabaseType type) {this.type = type;}

    void setConverter(ParameterConverter converter) {
        this.converter = converter;
    }

    void setFormatter(SqlFormatter formatter) {
        this.formatter = formatter;
    }

    void setSqlPrinter(SqlPrinter sqlPrinter) {
        this.sqlPrinter = sqlPrinter;
    }

    void setResultSetPrinter(ResultSetPrinter resultSetPrinter) {
        this.resultSetPrinter = resultSetPrinter;
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

    public SqlPrinter getSqlPrinter(){
        return sqlPrinter;
    }

    public ResultSetPrinter getResultSetPrinter(){
        return resultSetPrinter;
    }


    public static LogJdbcConfig autoconfig(DataSource datasource) {
        DatabaseType type = DatabaseType.find(datasource);
        return new LogJdbcConfig(type);
    }
}
