package net.chandol.logjdbc.config;

import net.chandol.logjdbc.logging.printer.resultset.ResultSetPrinter;
import net.chandol.logjdbc.logging.printer.resultset.ResultSetTablePrinter;
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
 * LogJdbc 설정
 */
public class LogJdbcConfig {
    private DatabaseType type;
    private ParameterConverter converter;
    private SqlFormatter formatter;
    private SqlPrinter sqlPrinter;
    private ResultSetPrinter resultSetPrinter;

    // 로깅에 필요한 설정값을 담을 수 있다.
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
        this.properties = Collections.unmodifiableMap(
                combinePropertiesMap(properties)
        );
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

    public SqlPrinter getSqlPrinter() {
        return sqlPrinter;
    }

    public ResultSetPrinter getResultSetPrinter() {
        return resultSetPrinter;
    }

    public boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        if (value.equalsIgnoreCase("true"))
            return true;
        else if (value.equalsIgnoreCase("false"))
            return false;
        else
            throw new IllegalArgumentException("cannot convert to boolean : " + value);
    }

    public int getIntProperty(String key){
        return Integer.valueOf(getProperty(key));
    }

    public String getProperty(String key){
        return this.properties.get(key);
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


    private static HashMap<String, String> combinePropertiesMap(Map<String, String> properties) {
        HashMap<String, String> propertiesMap = new HashMap<>();
        propertiesMap.putAll(LogJdbcConfigDefaultProperties.getDefaultPropertiesMap());
        propertiesMap.putAll(properties);
        return propertiesMap;
    }
}
