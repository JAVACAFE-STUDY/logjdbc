package net.chandol.logjdbc.config;

import net.chandol.logjdbc.logging.printer.resultset.ResultSetTablePrinter;
import net.chandol.logjdbc.logging.printer.resultset.ResultSetPrinter;
import net.chandol.logjdbc.logging.printer.sql.DefaultSqlPrinter;
import net.chandol.logjdbc.logging.printer.sql.SqlPrinter;
import net.chandol.logjdbc.logging.printer.sql.formatter.DefaultSqlFormatter;
import net.chandol.logjdbc.logging.printer.sql.formatter.SqlFormatter;
import net.chandol.logjdbc.logging.printer.sql.paramconverter.ParameterConverter;


import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * SQL로그 설정.
 */
public class LogJdbcConfig {
    private DatabaseType type;
    private ParameterConverter converter;
    private SqlFormatter formatter;
    private SqlPrinter sqlPrinter;
    private ResultSetPrinter resultSetPrinter;
    private Map<String, String> properties;

    public LogJdbcConfig() {
        // TODO 기본설정 작업할 것
        this(new HashMap<String, String>());
    }

    public LogJdbcConfig(DatabaseType type) {
        // TODO 기본설정 작업할 것
        this(type, new HashMap<String, String>());
    }

    public LogJdbcConfig(Map<String, String> properties) {
        this.formatter = DefaultSqlFormatter.getInstance();
        this.sqlPrinter = DefaultSqlPrinter.getInstance();
        this.resultSetPrinter = ResultSetTablePrinter.getInstance();
        this.properties = Collections.unmodifiableMap(properties);
    }

    public LogJdbcConfig(DatabaseType type, Map<String, String> properties) {
        this(properties);
        this.type = type;
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

    public SqlPrinter getSqlPrinter(){
        return sqlPrinter;
    }

    public ResultSetPrinter getResultSetPrinter(){
        return resultSetPrinter;
    }

    public String getProperty(String key){
        return this.properties.get(key);
    }


    /**
     * Datasource를 분석하여 Database를 찾고 설정합니다.
     * @param datasource
     */
    public void setDatabaseTypeBaseOnDatasource(DataSource datasource) {
        DatabaseType type = DatabaseType.find(datasource);

        this.type = type;
        this.converter = type.getParameterConverter();
    }
}
